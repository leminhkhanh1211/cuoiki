package service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import dao.DBAccount;
import dao.DBMuonSach;
import dao.DBNguoiMuon;
import dao.DBSach;
import dao.DatabaseConnection;
import model.Model_PhieuMuon;
import model.Model_NguoiMuon;
import model.Model_Login;
import model.Model_NhanVien;
import model.Model_Register;
import model.Model_Sach;
import view.Main;

public class Service {
    private static Service instance;
    private ServerSocket serverSocket;
    private final int PORT_NUMBER = 1610;
    // danh sách các client được kết nối 
	private ArrayList<ClientHandler> clients = new ArrayList<>();
	private static int id = 1000000;
	private Main main;
	
    public static Service getInstance(Main main) {
        if (instance == null) {
            instance = new Service(main);
        }
        return instance;
    }
    
    public static Service getInstance() {
        return instance;
    }
    
    private Service(Main main) {
        this.main = main;
        DatabaseConnection.getInstance().connectToDatabase();
        main.getBody().getNhanvien().loadNhanVien();// 
        main.getBody().getKhachhang().loadThanhVien();
        main.getBody().getKhosach().loadSach();
        main.getBody().getDoanhthu().loadDonMua();
        main.getBody().getThongke().thongke();
  
    }  
    
    public void startServer() {
    	// tạo và khởi động 1 luồng mới để chạy máy chủ
        new Thread(() -> {
            try {
            	serverSocket = new ServerSocket(PORT_NUMBER);
            	System.out.println("START SERVER ON PORT " + PORT_NUMBER);
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("ONE CLIENT CONNECT");
                    try {

                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream() , StandardCharsets.UTF_8));                     
                        DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
                       
                        InputStream in_image = clientSocket.getInputStream();
                        OutputStream out_image = clientSocket.getOutputStream();
                        
                        ClientHandler clientHandler = new ClientHandler(++id,this, in, out, clients, clientSocket);
                    }
                    catch (Exception e) {
                    	
                    	clientSocket.close();
                    }
                }
            } catch (Exception e) {
            }
        }).start();
    }
   
    public void listen(ClientHandler client, String newdata) {
    	
    	String data = new String(newdata);
    	 
		new Thread(()->{
			
    		try {
    			
    			JSONObject jsonData = new JSONObject(data);
    			
    	    	if(jsonData.getString("type").equals("register")) {
    	            Model_Register register = new Model_Register(jsonData);
    	           
    	            boolean message = DBAccount.getInstance().register(register);
    	            
    	        	JSONObject json = new JSONObject();
    	    		try {
    	    			json.put("type", "register");
    	    			json.put("check", message);
    	    		} catch (Exception e) {
    	    			e.printStackTrace();
    	    		}    	            
    	            broadcast(client.getUserId(), json);
    	    	}
    	    	
    	    	else if(jsonData.getString("type").equals("themNhanVien")) {
    	            Model_NhanVien nhanvien = new Model_NhanVien(jsonData);
    	            main.getBody().getNhanvien().themNhanVien2(nhanvien);
    	    	}
    	    	
    			else if(jsonData.getString("type").equals("login")) {
    				
    	            Model_Login login = new Model_Login(jsonData);
    	           
    	            boolean message = DBAccount.getInstance().login(login);
    	            
    	        	JSONObject json = new JSONObject();
    	    		try {
    	    			
    	    			json.put("type", "login");
    	    			json.put("check", message);
    	    		} catch (Exception e) {
    	    			e.printStackTrace();
    	    		}    	                 	            
    	            broadcast(client.getUserId(), json);    	            
    	            if(message) {
    	            	int quay = jsonData.getInt("quay");
    	            	Model_NhanVien nhanvien = DBAccount.getInstance().getNhanVien(login.getUserName());
    	            	client.setUserId(quay);
    	            	main.getBody().getCuahang().online(nhanvien, quay);
    	            	
        	            JSONObject json3 = new JSONObject();
        	            json3.put("type", "update");
        	            json3.put("nhanVien", nhanvien.getTen());
        	            json3.put("quay", quay);
        	            broadcast(client.getUserId(), json3);
    	            	
        	            List<Model_Sach> list = DBSach.getInstance().loadSach();
        	            JSONArray jsonArray = new JSONArray();
        	            for(Model_Sach sach : list) {    	    
        	            	jsonArray.put(sach.toJsonObject("listSach"));
        	            }
        	            JSONObject json2 = new JSONObject();
        	            json2.put("type", "listSach");
        	            json2.put("jsonArray", jsonArray);
    	            	broadcast(client.getUserId(), json2);
    	            }    	            
    	    	}
    	    	
    	    	else if(jsonData.getString("type").equals("tracuu")) {
    	            String sdt = jsonData.getString("sdt");
    	            Model_NguoiMuon khachhang = DBNguoiMuon.getInstance().tracuu(sdt);
    	            if(khachhang != null) {
    	            	broadcast(client.getUserId(), khachhang.toJsonObject("tracuu_true"));
    	            	
        	            List<Model_PhieuMuon> list = DBMuonSach.getInstance().listPhieuMuon(khachhang.getMaKhachHang());
        	            JSONArray jsonArray = new JSONArray();
        	            for(Model_PhieuMuon phieu : list) {    	    
        	            	jsonArray.put(phieu.toJsonObject("listSachMuon"));
        	            }
        	            JSONObject json = new JSONObject();
        	            json.put("type", "listSachMuon");
        	            json.put("jsonArray", jsonArray);
    	            	broadcast(client.getUserId(), json);
    	            }
    	            else {
        	            JSONObject json = new JSONObject();
        	            json.put("type", "tracuu_false");
    	            	broadcast(client.getUserId(), json) ;
    	            }
    	            
    	    	}
    	    	else if(jsonData.getString("type").equals("themThanhVien")) {
    	    		Model_NguoiMuon khachhang = new Model_NguoiMuon(jsonData);
    	    		Model_NguoiMuon khachHangMoi = DBNguoiMuon.getInstance().themThanhVien2(khachhang);
    	            broadcast(client.getUserId(), khachHangMoi.toJsonObject("themThanhVien")) ;
    	            main.getBody().getKhachhang().loadThanhVien();
    	    	}

    	    	else if(jsonData.getString("type").equals("xuatHoaDonKhachHang")) {
    	            Model_NguoiMuon khachhang = new Model_NguoiMuon(jsonData);
    	            DBNguoiMuon.getInstance().suaThongTin2(khachhang);
    	            main.getBody().getKhachhang().loadThanhVien();
    	    	}
    	    	 
    	    	else if(jsonData.getString("type").equals("xuatHoaDonSach")) {
    	    		JSONArray jsonArray = jsonData.getJSONArray("jsonArray");
    	    		List<Model_PhieuMuon> list = new ArrayList<>();
    	            for (int i = 0; i < jsonArray.length(); i++) {
    	            	System.out.println(jsonArray.getJSONObject(i).toString());
    	                JSONObject json = jsonArray.getJSONObject(i);
    	                Model_PhieuMuon phieumuon = new Model_PhieuMuon(json);
    	                DBMuonSach.getInstance().themDonMua(phieumuon);
    	                DBSach.getInstance().updateSoLuong(phieumuon.getMaSach());
    	            }
    	            main.getBody().getDoanhthu().loadDonMua();
    	            main.getBody().getKhosach().loadSach();
    	            
    	            
    	            List<Model_Sach> list2 = DBSach.getInstance().loadSach();
    	            JSONArray jsonArray2 = new JSONArray();
    	            for(Model_Sach sach : list2) {    	    
    	            	jsonArray2.put(sach.toJsonObject("listSach"));
    	            }
    	            JSONObject json2 = new JSONObject();
    	            json2.put("type", "listSach");
    	            json2.put("jsonArray", jsonArray2);
	            	broadcast(client.getUserId(), json2);
    	    	}    	
    	    	else if(jsonData.getString("type").equals("traSach")) {
    	    		JSONArray jsonArray = jsonData.getJSONArray("jsonArray");
    	    		List<Model_PhieuMuon> list = new ArrayList<>();
    	            for (int i = 0; i < jsonArray.length(); i++) {
    	                JSONObject json = jsonArray.getJSONObject(i);
    	                Model_PhieuMuon phieumuon = new Model_PhieuMuon(json);
    	                DBMuonSach.getInstance().updateTrangThai(phieumuon);
    	                DBSach.getInstance().updateSoLuongTraSach(phieumuon.getMaSach());
    	            }
    	            main.getBody().getDoanhthu().loadDonMua();
    	            main.getBody().getKhosach().loadSach();
    	            
    	            
    	            List<Model_Sach> list2 = DBSach.getInstance().loadSach();
    	            JSONArray jsonArray2 = new JSONArray();
    	            for(Model_Sach sach : list2) {    	    
    	            	jsonArray2.put(sach.toJsonObject("listSach"));
    	            }
    	            JSONObject json2 = new JSONObject();
    	            json2.put("type", "listSach");
    	            json2.put("jsonArray", jsonArray2);
	            	broadcast(client.getUserId(), json2);
    	    	}

    		} catch (JSONException e) {
    			e.printStackTrace();
    		}
    	}).start();
    }
    
    public synchronized void broadcast(int userId, JSONObject jsonData) {
        for (ClientHandler client : clients) {
            if(client.getUserId() == userId) {
            	client.sendMessage(jsonData);
            }
        }
}
    

	public Main getMain() {
		return main;
	}
    
    
    
}
