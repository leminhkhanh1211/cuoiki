package dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Model_DoanhThu;
import model.Model_PhieuMuon;
import model.Model_NhanVien;
import model.Model_Sach;

public class DBMuonSach {
	private final Connection con;
	private static DBMuonSach instance;
	
	private final String SELECT_PHIEUMUON = "SELECT phieumuon.maPhieu, sach.hinhAnh, sach.ten, nguoimuon.ten, phieumuon.ngayMuon, phieumuon.ngayHenTra, phieumuon.tinhTrang\r\n"
			+ "FROM phieumuon JOIN sach ON phieumuon.maSach=sach.maSach\r\n"
			+ "JOIN nguoimuon ON phieumuon.maNguoiMuon=nguoimuon.ma ORDER BY phieumuon.maPhieu ASC";
	private final String SELECT_TIMKIEM_PHIEUMUON = "SELECT phieumuon.maPhieu, sach.hinhAnh, sach.ten, nguoimuon.ten, phieumuon.ngayMuon, phieumuon.ngayHenTra, phieumuon.tinhTrang\r\n"
			+ "FROM phieumuon JOIN sach ON phieumuon.maSach=sach.maSach\r\n"
			+ "JOIN nguoimuon ON phieumuon.maNguoiMuon=nguoimuon.ma "
			+ "WHERE phieumuon.ngaymuon BETWEEN ? AND ?";
	private final String SELECT_THONGKE_MUONSACH = "SELECT phieumuon.maPhieu\r\n"
			+ "FROM phieumuon JOIN sach ON phieumuon.maSach=sach.maSach\r\n"
			+ "JOIN nguoimuon ON phieumuon.maNguoiMuon=nguoimuon.ma\r\n"
			+ "WHERE phieumuon.ngaymuon BETWEEN ? AND ?";
	private final String INSERT_PHIEUMUON = "INSERT INTO phieumuon (maNguoiMuon, maSach, ngayMuon, ngayHenTra, tinhTrang) VALUES (?,?,?,?,?)";
	private final String SELECT_PHIEUMUON_NGUOIMUON = "SELECT phieumuon.maPhieu, sach.hinhAnh, sach.ten, nguoimuon.ten, phieumuon.ngayMuon, phieumuon.ngayHenTra, phieumuon.tinhTrang, sach.maSach\r\n"
			+ "FROM phieumuon JOIN sach ON phieumuon.maSach=sach.maSach\r\n"
			+ "JOIN nguoimuon ON phieumuon.maNguoiMuon=nguoimuon.ma "
			+ "WHERE nguoimuon.ma=? AND phieumuon.tinhTrang='Đang mượn' ORDER BY phieumuon.maPhieu ASC";
	private final String UPDATE_PHIEUMUON = "UPDATE phieumuon SET tinhtrang=? WHERE maPhieu=?";
	
	public static DBMuonSach getInstance() {
		if(instance == null) {
			instance = new DBMuonSach();
		}
		return instance;
	}
	
	public DBMuonSach() {
        this.con = DatabaseConnection.getInstance().getConnection();
	}
	
	public ArrayList<Model_PhieuMuon> loadDonMua() {
		ArrayList<Model_PhieuMuon> list = new ArrayList<>();
        try {
            PreparedStatement p = con.prepareStatement(SELECT_PHIEUMUON);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	int ma = r.getInt(1);
            	Blob blob = r.getBlob(2);
                byte[] image =  blob.getBytes(1, (int) blob.length());
                String tenSach = r.getString(4);
                String tenKhachHang = r.getString(3);
                Date ngaymuon = r.getDate(5);
                Date ngaytra = r.getDate(6);
                String trangthai = r.getString(7);
            	
            	Model_PhieuMuon sach = new Model_PhieuMuon(ma, image, 0, tenSach, 0, tenKhachHang, ngaymuon, ngaytra, trangthai);
            	list.add(sach);
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return list;
	}
	
	public ArrayList<Model_PhieuMuon> timkiem(Date from, Date to) {
		ArrayList<Model_PhieuMuon> list = new ArrayList<>();
        try {
            PreparedStatement p = con.prepareStatement(SELECT_TIMKIEM_PHIEUMUON);
            p.setDate(1, from);
            p.setDate(2, to);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	int ma = r.getInt(1);
            	Blob blob = r.getBlob(2);
                byte[] image =  blob.getBytes(1, (int) blob.length());
                String tenSach = r.getString(4);
                String tenKhachHang = r.getString(3);
                Date ngaymuon = r.getDate(5);
                Date ngaytra = r.getDate(6);
                String trangthai = r.getString(7);
            	
            	Model_PhieuMuon sach = new Model_PhieuMuon(ma, image, 0, tenSach, 0, tenKhachHang, ngaymuon, ngaytra, trangthai);
            	list.add(sach);
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return list;
	}
	
	public int thongkeSoLuong(Date from, Date to) {
		int soluong = 0;
        try {
            PreparedStatement p = con.prepareStatement(SELECT_THONGKE_MUONSACH);
            p.setDate(1, from);
            p.setDate(2, to);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	soluong ++;
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return soluong;
	}
	
	
	public void themDonMua(Model_PhieuMuon phieumuon) {
        try {
            PreparedStatement p = con.prepareStatement(INSERT_PHIEUMUON);
            p.setInt(1, phieumuon.getMaThanhVien());
            p.setInt(2, phieumuon.getMaSach());
            p.setDate(3, phieumuon.getNgayMuon());
            p.setDate(4, phieumuon.getNgayHenTra());
            p.setString(5, phieumuon.getTinhTrang());
            
            p.execute();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateTrangThai(Model_PhieuMuon phieumuon) {
        try {
            PreparedStatement p = con.prepareStatement(UPDATE_PHIEUMUON);
            p.setString(1, "Đã trả");
            p.setInt(2, phieumuon.getMa());
            
            p.execute();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Model_PhieuMuon> listPhieuMuon(int maNguoiMuon) {
		ArrayList<Model_PhieuMuon> list = new ArrayList<>();
        try {
            PreparedStatement p = con.prepareStatement(SELECT_PHIEUMUON_NGUOIMUON);
            p.setInt(1, maNguoiMuon);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	int ma = r.getInt(1);
            	Blob blob = r.getBlob(2);
                byte[] image =  blob.getBytes(1, (int) blob.length());
                String tenSach = r.getString(4);
                String tenKhachHang = r.getString(3);
                Date ngaymuon = r.getDate(5);
                Date ngaytra = r.getDate(6);
                String trangthai = r.getString(7);
                int maSach = r.getInt(8);
            	
            	Model_PhieuMuon phieu = new Model_PhieuMuon(ma, image, 0, tenSach, maSach, tenKhachHang, ngaymuon, ngaytra, trangthai);
            	list.add(phieu);
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return list;
	}
}
