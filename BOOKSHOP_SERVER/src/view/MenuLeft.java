package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import service.Service;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuLeft extends JPanel{
	
	private JLabel lb_QL_ThongKe;
	private JLabel lb_QL_MuonSach;
	private JLabel lb_QL_NguoiMuon;
	private JLabel lb_QL_KhoSach;
	private JLabel lb_QL_NhanVien;
	private JLabel lb_QL_ThuVien;

	public MenuLeft() {
		setSize(300, 840);
		setLayout(null);
		setBackground(new Color(255, 233, 83));
		
		JLabel lb_logo = new JLabel("");
		lb_logo.setIcon(new ImageIcon(new ImageIcon(MenuLeft.class.getResource("/images/logo_sach.png")).getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH)));
		lb_logo.setBounds(57, 32, 180, 180);
		add(lb_logo);
		
		lb_QL_ThuVien = new JLabel("QUẢN LÝ THƯ VIỆN");
		lb_QL_ThuVien.setIcon(new ImageIcon(MenuLeft.class.getResource("/images/cuahang.png")));
		lb_QL_ThuVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lb_QL_ThuVien.setBackground(new Color(255, 223, 2));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lb_QL_ThuVien.setBackground(new Color(255, 240, 140));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Service.getInstance().getMain().getBody().getCardLayout().show(Service.getInstance().getMain().getBody(), "cuahang");
				reset();
				lb_QL_ThuVien.setBackground(new Color(255, 185, 2));
			}
		});
		lb_QL_ThuVien.setForeground(new Color(255, 255, 255));
		lb_QL_ThuVien.setFont(new Font("Tahoma", Font.BOLD, 22));
		lb_QL_ThuVien.setHorizontalAlignment(SwingConstants.CENTER);
		lb_QL_ThuVien.setBounds(0, 283, 300, 58);
		lb_QL_ThuVien.setBackground(new Color(255, 240, 140));
		lb_QL_ThuVien.setOpaque(true);
		add(lb_QL_ThuVien);
		
		lb_QL_NhanVien = new JLabel("QUẢN LÝ NHÂN VIÊN");
		lb_QL_NhanVien.setIcon(new ImageIcon(MenuLeft.class.getResource("/images/nhanvien.png")));
		lb_QL_NhanVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lb_QL_NhanVien.setBackground(new Color(255, 223, 2));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lb_QL_NhanVien.setBackground(new Color(255, 240, 140));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Service.getInstance().getMain().getBody().getCardLayout().show(Service.getInstance().getMain().getBody(), "nhanvien");
				reset();
				lb_QL_NhanVien.setBackground(new Color(255, 185, 2));
			}
		});
		lb_QL_NhanVien.setOpaque(true);
		lb_QL_NhanVien.setHorizontalAlignment(SwingConstants.CENTER);
		lb_QL_NhanVien.setForeground(Color.WHITE);
		lb_QL_NhanVien.setFont(new Font("Tahoma", Font.BOLD, 22));
		lb_QL_NhanVien.setBackground(new Color(255, 240, 140));
		lb_QL_NhanVien.setBounds(0, 345, 300, 58);
		add(lb_QL_NhanVien);
		
		lb_QL_KhoSach = new JLabel("QUẢN LÝ KHO SÁCH");
		lb_QL_KhoSach.setIcon(new ImageIcon(MenuLeft.class.getResource("/images/sach.png")));
		lb_QL_KhoSach.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lb_QL_KhoSach.setBackground(new Color(255, 223, 2));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lb_QL_KhoSach.setBackground(new Color(255, 240, 140));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Service.getInstance().getMain().getBody().getCardLayout().show(Service.getInstance().getMain().getBody(), "khosach");
				reset();
				lb_QL_KhoSach.setBackground(new Color(255, 185, 2));
			}
		});
		lb_QL_KhoSach.setOpaque(true);
		lb_QL_KhoSach.setHorizontalAlignment(SwingConstants.CENTER);
		lb_QL_KhoSach.setForeground(Color.WHITE);
		lb_QL_KhoSach.setFont(new Font("Tahoma", Font.BOLD, 22));
		lb_QL_KhoSach.setBackground(new Color(255, 240, 140));
		lb_QL_KhoSach.setBounds(0, 407, 300, 58);
		add(lb_QL_KhoSach);
		
		lb_QL_NguoiMuon = new JLabel("NGƯỜI MƯỢN");
		lb_QL_NguoiMuon.setIcon(new ImageIcon(MenuLeft.class.getResource("/images/kahchhang.png")));
		lb_QL_NguoiMuon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lb_QL_NguoiMuon.setBackground(new Color(255, 223, 2));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lb_QL_NguoiMuon.setBackground(new Color(255, 240, 140));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Service.getInstance().getMain().getBody().getCardLayout().show(Service.getInstance().getMain().getBody(), "khachhang");
				reset();
				lb_QL_NguoiMuon.setBackground(new Color(255, 185, 2));
			}
		});
		lb_QL_NguoiMuon.setOpaque(true);
		lb_QL_NguoiMuon.setHorizontalAlignment(SwingConstants.CENTER);
		lb_QL_NguoiMuon.setForeground(Color.WHITE);
		lb_QL_NguoiMuon.setFont(new Font("Tahoma", Font.BOLD, 22));
		lb_QL_NguoiMuon.setBackground(new Color(255, 240, 140));
		lb_QL_NguoiMuon.setBounds(0, 470, 300, 58);
		add(lb_QL_NguoiMuon);
		
		lb_QL_MuonSach = new JLabel("QUẢN LÝ MƯỢN SÁCH");
		lb_QL_MuonSach.setIcon(new ImageIcon(MenuLeft.class.getResource("/images/doanhthu.png")));
		lb_QL_MuonSach.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lb_QL_MuonSach.setBackground(new Color(255, 223, 2));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lb_QL_MuonSach.setBackground(new Color(255, 240, 140));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Service.getInstance().getMain().getBody().getCardLayout().show(Service.getInstance().getMain().getBody(), "doanhthu");
				reset();
				lb_QL_MuonSach.setBackground(new Color(255, 185, 2));
			}
		});
		lb_QL_MuonSach.setOpaque(true);
		lb_QL_MuonSach.setHorizontalAlignment(SwingConstants.CENTER);
		lb_QL_MuonSach.setForeground(Color.WHITE);
		lb_QL_MuonSach.setFont(new Font("Tahoma", Font.BOLD, 22));
		lb_QL_MuonSach.setBackground(new Color(255, 240, 140));
		lb_QL_MuonSach.setBounds(0, 533, 300, 58);
		add(lb_QL_MuonSach);
		
		lb_QL_ThongKe = new JLabel("THỐNG KÊ");
		lb_QL_ThongKe.setIcon(new ImageIcon(MenuLeft.class.getResource("/images/thongke.png")));
		lb_QL_ThongKe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lb_QL_ThongKe.setBackground(new Color(255, 223, 2));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lb_QL_ThongKe.setBackground(new Color(255, 240, 140));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Service.getInstance().getMain().getBody().getCardLayout().show(Service.getInstance().getMain().getBody(), "thongke");
				reset();
				lb_QL_ThongKe.setBackground(new Color(255, 185, 2));
			}
		});
		lb_QL_ThongKe.setOpaque(true);
		lb_QL_ThongKe.setHorizontalAlignment(SwingConstants.CENTER);
		lb_QL_ThongKe.setForeground(Color.WHITE);
		lb_QL_ThongKe.setFont(new Font("Tahoma", Font.BOLD, 22));
		lb_QL_ThongKe.setBackground(new Color(255, 240, 140));
		lb_QL_ThongKe.setBounds(0, 596, 300, 58);
		add(lb_QL_ThongKe);
	}
	
	public void reset() {
	}
}
