package view;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.CardLayout;

public class Body extends JPanel{
	
	private CardLayout cardLayout;
	private QL_MuonSach doanhthu;
	private QL_KhoSach khosach;
	private QLNguoiMuon khachhang;
	private QL_NhanVien nhanvien;
	private QL_CuaHang cuahang;
	private QL_Thong_Ke thongke;

	public Body() {
		setSize(1240, 840);
		cardLayout = new CardLayout(0, 0);
		setLayout(cardLayout);
		
		cuahang = new QL_CuaHang();
		add(cuahang, "cuahang");	
		
		doanhthu = new QL_MuonSach();
		add(doanhthu, "doanhthu");
		
		khosach = new QL_KhoSach();
		add(khosach, "khosach");
		
		khachhang = new QLNguoiMuon();
		add(khachhang, "khachhang");
		
		nhanvien = new QL_NhanVien();
		add(nhanvien, "nhanvien");	
		
		thongke = new QL_Thong_Ke();
		add(thongke, "thongke");
		
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public QL_MuonSach getDoanhthu() {
		return doanhthu;
	}

	public QL_KhoSach getKhosach() {
		return khosach;
	}

	public QLNguoiMuon getKhachhang() {
		return khachhang;
	}

	public QL_NhanVien getNhanvien() {
		return nhanvien;
	}

	public QL_CuaHang getCuahang() {
		return cuahang;
	}

	public QL_Thong_Ke getThongke() {
		return thongke;
	}
	
	
	
	
	
}
