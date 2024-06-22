package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Model_NguoiMuon;
import model.Model_NhanVien;

public class DBNguoiMuon {
	private final Connection con;
	private static DBNguoiMuon instance;
	private final String INSERT_THANHVIEN = "INSERT INTO nguoimuon (ten, sdt, damuon, dangmuon) VALUES (?,?,?,?)";
	private final String SELECT_THANHVIEN = "SELECT ma, ten, sdt, damuon, dangmuon FROM nguoimuon";
	private final String UPDATE_THONGTIN = "UPDATE nguoimuon SET ten=?, sdt=?, damuon=?, dangmuon=? WHERE ma=?";
	private final String DELETE_THANHVIEN = "DELETE FROM nguoimuon WHERE ma=?";
	private final String SELECT_TIMKIEM_MAKH = "SELECT ma, ten, sdt, damuon, dangmuon FROM nguoimuon WHERE ma=?";
	private final String SELECT_TIMKIEM_SDT = "SELECT ma, ten, sdt, damuon, dangmuon FROM nguoimuon  WHERE sdt LIKE ?";
	private final String SELECT_TRACUU_SDT = "SELECT ma, ten, sdt, damuon, dangmuon FROM nguoimuon WHERE sdt=?";
	
	public static DBNguoiMuon getInstance() {
		if(instance == null) {
			instance = new DBNguoiMuon();
		}
		return instance;
	}
	
	public DBNguoiMuon() {
        this.con = DatabaseConnection.getInstance().getConnection();
	}
	
	public ArrayList<Model_NguoiMuon> loadThanhVien() {
		ArrayList<Model_NguoiMuon> list = new ArrayList<>();
        try {
            PreparedStatement p = con.prepareStatement(SELECT_THANHVIEN);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	int maKhachHang = r.getInt(1);
            	String ten = r.getString(2);
            	String sdt = r.getString(3);
            	int damuon = r.getInt(4);
            	int dangmuon = r.getInt(5);
            	
				Model_NguoiMuon tv = new Model_NguoiMuon(maKhachHang, ten, sdt, damuon, dangmuon);
            	list.add(tv);
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return list;
	}
	
	public Model_NguoiMuon themThanhVien(Model_NguoiMuon thanhVien) {
        try {
            PreparedStatement p = con.prepareStatement(INSERT_THANHVIEN, PreparedStatement.RETURN_GENERATED_KEYS);
            p.setString(1, thanhVien.getTen());
            p.setString(2, thanhVien.getSdt());
            p.setInt(3, thanhVien.getDaMuon());
            p.setInt(4, thanhVien.getDangMuon());
                        
            p.execute();
            ResultSet r = p.getGeneratedKeys();
            r.first();
            int maKhanhHang = r.getInt(1);
            thanhVien.setMaKhachHang(maKhanhHang);
            p.close();
            r.close();
            
            JOptionPane.showMessageDialog(null, "Đã thêm thành viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
          } catch (SQLException e) {
          	e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Thêm thành viên thất bại XXX", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
          }
        return thanhVien;
	}
	
	public Model_NguoiMuon themThanhVien2(Model_NguoiMuon thanhVien) {
        try {
            PreparedStatement p = con.prepareStatement(INSERT_THANHVIEN, PreparedStatement.RETURN_GENERATED_KEYS);
            p.setString(1, thanhVien.getTen());
            p.setString(2, thanhVien.getSdt());
            p.setInt(3, thanhVien.getDaMuon());
            p.setInt(4, thanhVien.getDangMuon());
                        
            p.execute();
            ResultSet r = p.getGeneratedKeys();
            r.first();
            int maKhanhHang = r.getInt(1);
            thanhVien.setMaKhachHang(maKhanhHang);
            p.close();
            r.close();
            
          } catch (SQLException e) {
          	e.printStackTrace();
          }
        return thanhVien;
	}
	
	public Model_NguoiMuon suaThongTin(Model_NguoiMuon thanhVien) {
        try {
            PreparedStatement p = con.prepareStatement(UPDATE_THONGTIN);
            p.setString(1, thanhVien.getTen());
            p.setString(2, thanhVien.getSdt());
            p.setInt(3, thanhVien.getDaMuon());
            p.setInt(4, thanhVien.getDangMuon());
            p.setInt(5, thanhVien.getMaKhachHang());
                        
            p.execute();
            p.close();
            
            JOptionPane.showMessageDialog(null, "Đã cập nhật thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
          } catch (SQLException e) {
          	e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cập nhật thất bại XXX", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
          }
        return thanhVien;
	}
	
	public Model_NguoiMuon suaThongTin2(Model_NguoiMuon thanhVien) {
        try {
            PreparedStatement p = con.prepareStatement(UPDATE_THONGTIN);
            p.setString(1, thanhVien.getTen());
            p.setString(2, thanhVien.getSdt());
            p.setInt(3, thanhVien.getDaMuon());
            p.setInt(4, thanhVien.getDangMuon());
            p.setInt(5, thanhVien.getMaKhachHang());
                        
            p.execute();
            p.close();
            
          } catch (SQLException e) {
          	e.printStackTrace();
          }
        return thanhVien;
	}
	
	public void xoaThanhVien(int maKhachHang) {
        try {
            PreparedStatement p = con.prepareStatement(DELETE_THANHVIEN);
            p.setInt(1, maKhachHang);
                        
            p.execute();
            p.close();
            
            JOptionPane.showMessageDialog(null, "Đã xóa người mượn thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
          } catch (SQLException e) {
          	e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Xóa người mượn thất bại XXX", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
          }
	}
	
	public ArrayList<Model_NguoiMuon> locThanhVien(String dieukien) {
		ArrayList<Model_NguoiMuon> list = new ArrayList<>();
        try {
            PreparedStatement p = con.prepareStatement(SELECT_THANHVIEN + " WHERE " + dieukien);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	int maKhachHang = r.getInt(1);
            	String ten = r.getString(2);
            	String sdt = r.getString(3);
            	int damuon = r.getInt(4);
            	int dangmuon = r.getInt(5);
            	
				Model_NguoiMuon tv = new Model_NguoiMuon(maKhachHang, ten, sdt, damuon, dangmuon);
            	list.add(tv);
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return list;
	}
	
	public ArrayList<Model_NguoiMuon> timkiemMaKH(int ma) {
		ArrayList<Model_NguoiMuon> list = new ArrayList<>();
        try {
            PreparedStatement p = con.prepareStatement(SELECT_TIMKIEM_MAKH);
            p.setInt(1, ma);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	int maKhachHang = r.getInt(1);
            	String ten = r.getString(2);
            	String sdt = r.getString(3);
            	int damuon = r.getInt(4);
            	int dangmuon = r.getInt(5);
            	
				Model_NguoiMuon tv = new Model_NguoiMuon(maKhachHang, ten, sdt, damuon, dangmuon);
            	list.add(tv);
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return list;
	}
	
	public ArrayList<Model_NguoiMuon> timkiemSdt(String sodienthoai) {
		ArrayList<Model_NguoiMuon> list = new ArrayList<>();
        try {
            PreparedStatement p = con.prepareStatement(SELECT_TIMKIEM_SDT);
            p.setString(1, sodienthoai);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	int maKhachHang = r.getInt(1);
            	String ten = r.getString(2);
            	String sdt = r.getString(3);
            	int damuon = r.getInt(4);
            	int dangmuon = r.getInt(5);
            	
				Model_NguoiMuon tv = new Model_NguoiMuon(maKhachHang, ten, sdt, damuon, dangmuon);
            	list.add(tv);
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return list;
	}
	
	public Model_NguoiMuon tracuu(String sodienthoai) {
		Model_NguoiMuon khachhang = null;
        try {
            PreparedStatement p = con.prepareStatement(SELECT_TRACUU_SDT);
            p.setString(1, sodienthoai);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	int maKhachHang = r.getInt(1);
            	String ten = r.getString(2);
            	String sdt = r.getString(3);
            	int damuon = r.getInt(4);
            	int dangmuon = r.getInt(5);
            	
				Model_NguoiMuon nm = new Model_NguoiMuon(maKhachHang, ten, sdt, damuon, dangmuon);
				return nm;
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return khachhang;
	}
}
