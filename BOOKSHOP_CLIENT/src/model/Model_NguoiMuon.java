package model;

import org.json.JSONObject;

public class Model_NguoiMuon {
	private int maKhachHang;
	private String ten;
	private String sdt;
	private int daMuon;
	private int dangMuon;
	
	public Model_NguoiMuon(int maKhachHang, String ten, String sdt, int daMuon, int dangMuon ) {
		this.maKhachHang = maKhachHang;
		this.ten = ten;
		this.sdt = sdt;
		this.daMuon = daMuon;
		this.dangMuon = dangMuon;
	}
	
    public JSONObject toJsonObject(String type) {
    	try {
			JSONObject json = new JSONObject();
			json.put("type", type);
			json.put("maKhachHang", maKhachHang);
			json.put("ten", ten);
			json.put("sdt", sdt);
			json.put("daMuon", daMuon);
			json.put("dangMuon", dangMuon);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
    
	public Model_NguoiMuon(Object json) {
        JSONObject obj = (JSONObject) json;
        try {
        	maKhachHang = obj.getInt("maKhachHang");
        	ten = obj.getString("ten");
        	sdt = obj.getString("sdt");
        	daMuon = obj.getInt("daMuon");
        	dangMuon = obj.getInt("dangMuon");
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public int getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(int maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public int getDaMuon() {
		return daMuon;
	}

	public void setDaMuon(int daMuon) {
		this.daMuon = daMuon;
	}

	public int getDangMuon() {
		return dangMuon;
	}

	public void setDangMuon(int dangMuon) {
		this.dangMuon = dangMuon;
	}
	

	
	
}
