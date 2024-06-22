package view;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.toedter.calendar.JDateChooser;

import EnCode.QRCodePaymentGenerator;
import model.Model_PhieuMuon;
import model.Model_Sach;
import model.Model_NguoiMuon;
import service.Service;
import swing.PlaceholderTextField;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;

public class MenuLeft extends JPanel{
	private JTextField tf_ma;
	private JTextField tf_ten;
	private JLabel lb_username;
	private JLabel lb_quay;
	private PlaceholderTextField tf_sdt;
	private Model_NguoiMuon khachhang;
	private ArrayList<Model_Sach> sachList;
	private Date date;
	private JDateChooser tf_ngaymuon;
	private JDateChooser tf_ngayhentra;
	private ArrayList<Model_PhieuMuon> phieumuonList;
	private ArrayList<Model_PhieuMuon> phieutraList;
	
	public MenuLeft() {
		setSize(300, 840);
		setLayout(null);
		setBackground(new Color(251, 229, 82));
		
		JLabel lb_logo = new JLabel("");
		lb_logo.setIcon(new ImageIcon(new ImageIcon(MenuLeft.class.getResource("/images/user_account.png")).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
		lb_logo.setBounds(72, 31, 150, 150);
		add(lb_logo);
		
		sachList = new ArrayList<>();
		phieutraList = new ArrayList<>();
		
		lb_username = new JLabel("ĐÍNH DƯƠNG");
		lb_username.setFont(new Font("Tahoma", Font.BOLD, 18));
		lb_username.setHorizontalAlignment(SwingConstants.CENTER);
		lb_username.setBounds(10, 185, 280, 28);
		add(lb_username);
		
		JLabel lblNewLabel = new JLabel("Mã KH");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 261, 82, 28);
		add(lblNewLabel);
		
		JLabel lblTnKh = new JLabel("Tên KH");
		lblTnKh.setForeground(Color.WHITE);
		lblTnKh.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTnKh.setBounds(10, 296, 82, 28);
		add(lblTnKh);
		
		JLabel lblStKh = new JLabel("SĐT KH");
		lblStKh.setForeground(Color.WHITE);
		lblStKh.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblStKh.setBounds(10, 334, 82, 28);
		add(lblStKh);
		
		tf_ma = new JTextField();
		tf_ma.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_ma.setBounds(107, 261, 183, 28);
		add(tf_ma);
		tf_ma.setColumns(10);
		
		tf_ten = new JTextField();
		tf_ten.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_ten.setColumns(10);
		tf_ten.setBounds(107, 296, 183, 28);
		add(tf_ten);
		
		tf_sdt = new PlaceholderTextField("Tra cứu...");
		tf_sdt.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_sdt.setColumns(10);
		tf_sdt.setBounds(107, 334, 183, 28);
		add(tf_sdt);
		
		JButton bt_tracuu = new JButton("TRA CỨU");
		bt_tracuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!tf_sdt.equals("Tra cứu...")) {
					Service.getInstance().tracuu(tf_sdt.getText());
				}
			}
		});
		bt_tracuu.setFont(new Font("Tahoma", Font.BOLD, 14));
		bt_tracuu.setBounds(10, 388, 110, 36);
		add(bt_tracuu);
		
		JButton bt_trasach = new JButton("TRẢ SÁCH");
		bt_trasach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
	        	JDialog dialog = new JDialog();
	        	TraSach trasach = new TraSach(khachhang, dialog, phieutraList);
	    		dialog.getContentPane().setLayout(new GridLayout(1,1));
	    		dialog.setSize(1200, 750);
	    		dialog.setLocationRelativeTo(Service.getInstance().getMain().getBody());
	        	dialog.getContentPane().add(trasach);
	        	dialog.setVisible(true);
			}
		});
		bt_trasach.setFont(new Font("Tahoma", Font.BOLD, 14));
		bt_trasach.setBounds(180, 388, 110, 36);
		add(bt_trasach);
		
		JButton bt_them = new JButton("");
		bt_them.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	        	JDialog dialog = new JDialog();
	        	ThemThanhVien them = new ThemThanhVien(dialog);
	    		dialog.getContentPane().setLayout(new GridLayout(1,1));
	    		dialog.setSize(650, 450);
	    		dialog.setLocationRelativeTo(null);
	        	dialog.getContentPane().add(them);
	        	dialog.setVisible(true);
			}
		});
		bt_them.setIcon(new ImageIcon(MenuLeft.class.getResource("/images/icon_cong.png")));
		bt_them.setFont(new Font("Tahoma", Font.BOLD, 14));
		bt_them.setBounds(130, 388, 40, 40);
		add(bt_them);
		
		
		
		JLabel lblnGi = new JLabel("Ngày mượn");
		lblnGi.setForeground(Color.WHITE);
		lblnGi.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblnGi.setBounds(10, 450, 123, 28);
		add(lblnGi);
		
		JLabel lblGimGi = new JLabel("Ngày hẹn trả");
		lblGimGi.setForeground(Color.WHITE);
		lblGimGi.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblGimGi.setBounds(10, 488, 123, 28);
		add(lblGimGi);
		
		tf_ngaymuon = new JDateChooser();
		tf_ngaymuon.setBackground(new Color(255, 255, 255));
		tf_ngaymuon.setFont(new Font("Tahoma", Font.BOLD, 16));
		tf_ngaymuon.setBounds(140, 450, 150, 28);
		add(tf_ngaymuon);
		
		Calendar today = Calendar.getInstance();
		tf_ngaymuon.setDate(today.getTime());
		
        Date toDate = tf_ngaymuon.getDate();
        java.sql.Date sqlToDate = new java.sql.Date(toDate.getTime());
		
		tf_ngayhentra = new JDateChooser();
		tf_ngayhentra.setBackground(new Color(255, 255, 255));
		tf_ngayhentra.setFont(new Font("Tahoma", Font.BOLD, 16));
		tf_ngayhentra.setBounds(140, 488, 150, 28);
		add(tf_ngayhentra);
		
		JButton bt_xuatphieumuon = new JButton("XUẤT PHIẾU MƯỢN");
		bt_xuatphieumuon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xuatPhieuMuon();
			}
		});
		bt_xuatphieumuon.setFont(new Font("Tahoma", Font.BOLD, 14));
		bt_xuatphieumuon.setBounds(7, 542, 283, 36);
		add(bt_xuatphieumuon);
		
		lb_quay = new JLabel("QUẦY SỐ 1");
		lb_quay.setForeground(new Color(0, 0, 0));
		lb_quay.setFont(new Font("Tahoma", Font.BOLD, 20));
		lb_quay.setHorizontalAlignment(SwingConstants.CENTER);
		lb_quay.setBounds(0, 763, 300, 41);
		lb_quay.setBackground(new Color(255, 215, 0));
		lb_quay.setOpaque(true);
		add(lb_quay);
				
	}
	
	public void update(String name, int quay) {
		lb_username.setText(name);
		lb_quay.setText("QUẦY SỐ " + quay);
	}
	
	public void tracuu(Model_NguoiMuon khachhang) {
		this.khachhang = khachhang;
		tf_ten.setText(khachhang.getTen());
		tf_sdt.setText(khachhang.getSdt());
		tf_ma.setText(khachhang.getMaKhachHang()+"");
	}
	
	public void xuatPhieuMuon() {

		ArrayList<Model_PhieuMuon> phieumuonList = new ArrayList<>();
		Date ngayMuon = tf_ngaymuon.getDate();
		java.sql.Date sqlNgayMuon = new java.sql.Date(ngayMuon.getTime());
		Date ngayHenTra = tf_ngayhentra.getDate();
		java.sql.Date sqlNgayHenTra = new java.sql.Date(ngayHenTra.getTime());
		int cnt = 0;
		for(Model_Sach sach : sachList) { 
        	Model_PhieuMuon phieumuon = new Model_PhieuMuon(0, sach.getHinhAnh(), khachhang.getMaKhachHang(), khachhang.getTen(), sach.getMaSach(), sach.getTen(), sqlNgayMuon, sqlNgayHenTra, "Đang mượn");
        	phieumuonList.add(phieumuon);
        	cnt++;
		}
		this.phieumuonList = phieumuonList;
		Service.getInstance().xuatHoaDonSach(phieumuonList);
		
		khachhang.setDaMuon(khachhang.getDaMuon() + cnt);
		khachhang.setDangMuon(khachhang.getDangMuon() + cnt);	
		Service.getInstance().xuatHoaDonKhachHang(khachhang.toJsonObject("xuatHoaDonKhachHang"));
		
		printHoaDon();
		reset();

	}
	
	public void printHoaDon() {
    	PhieuMuonSach hoadon = new PhieuMuonSach();
    	int stt = 1;
    	hoadon.getTextArea().append(String.format("%-7s%-50s%-15s%-15s\n", "STT", "Tên Sách", "Ngày mượn", "Ngày hẹn trả"));
		for(Model_PhieuMuon phieumuon : phieumuonList) { 
        	hoadon.getTextArea().append(String.format("%-7s%-50s%-15s%-15s\n", stt++, phieumuon.getTenSach(), phieumuon.getNgayMuon(), phieumuon.getNgayHenTra()));
        }
    	
		QRCodePaymentGenerator.generatePaymentQRCode(hoadon.getLb_qr());
    	
    	JDialog dialog = new JDialog();
		dialog.getContentPane().setLayout(new GridLayout(1,1));
		dialog.setSize(600, 700);
		dialog.setLocationRelativeTo(null);
    	dialog.getContentPane().add(hoadon);
    	dialog.setVisible(true);
	}
	
	public void reset() {
		tf_ten.setText("");
		tf_sdt.setText("");
		tf_ma.setText("");
		tf_ngayhentra.setDate(null);
		
		phieumuonList = new ArrayList<>();
		sachList = new ArrayList<>();
		phieutraList = new ArrayList<>();
		Service.getInstance().getMain().getBody().getTable_model().setRowCount(0);
	}

	public ArrayList<Model_Sach> getSachList() {
		return sachList;
	}

	public ArrayList<Model_PhieuMuon> getPhieutraList() {
		return phieutraList;
	}

	
	
	
	
}
