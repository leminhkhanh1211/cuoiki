package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import swing.ImageRenderer;

import javax.swing.JScrollPane;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import EnCode.ImageUtil;
import dao.DBMuonSach;
import model.Model_DoanhThu;
import model.Model_NguoiMuon;
import model.Model_PhieuMuon;

import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QL_MuonSach extends JPanel{
	private JTable table;
	private DefaultTableModel table_model;
	private JDateChooser date_from;
	private JDateChooser date_to;
	
	public QL_MuonSach() {
		setBackground(Color.WHITE);
		setSize(1240, 830);
		setLayout(null);
		
		JLabel lb_logo = new JLabel("");
		lb_logo.setIcon(new ImageIcon(QL_CuaHang.class.getResource("/images/logo_title.png")));
		lb_logo.setHorizontalAlignment(SwingConstants.CENTER);
		lb_logo.setBounds(370, 10, 442, 63);
		add(lb_logo);
		
		table_model = new DefaultTableModel(
				new Object[][] {},
				new String[] {
					"M\u00E3", "", "T\u00EAn s\u00E1ch", "T\u00EAn kh\u00E1ch h\u00E0ng", "Ng\u00E0y m\u01B0\u1EE3n", "Ng\u00E0y h\u1EB9n tr\u1EA3", "Tr\u1EA1ng th\u00E1i"
				}
			);
		table = new JTable();
		table.setModel(table_model);
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(399);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.getColumnModel().getColumn(4).setPreferredWidth(140);
		table.getColumnModel().getColumn(5).setPreferredWidth(140);
		table.getColumnModel().getColumn(6).setPreferredWidth(130);
		table.getColumnModel().getColumn(1).setCellRenderer(new ImageRenderer());
		table.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		Font headerFont = new Font("Arial", Font.BOLD, 20);
		table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 40));
		table.getTableHeader().setFont(headerFont);
		table.setRowHeight(70);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 167, 1129, 614);
		add(scrollPane);
		
		JLabel bt_homnay = new JLabel("Hôm nay");
		bt_homnay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Calendar today = Calendar.getInstance();
				date_from.setDate(today.getTime());
				date_to.setDate(today.getTime());
				
		        Date fromDate = date_from.getDate();
		        Date toDate = date_to.getDate();
	            java.sql.Date sqlFromDate = new java.sql.Date(fromDate.getTime());
	            java.sql.Date sqlToDate = new java.sql.Date(toDate.getTime());
				timkiem(sqlFromDate, sqlFromDate);
			}
		});
		bt_homnay.setFont(new Font("Tahoma", Font.BOLD, 20));
		bt_homnay.setHorizontalAlignment(SwingConstants.CENTER);
		bt_homnay.setBounds(20, 120, 124, 48);
		bt_homnay.setOpaque(true);
		bt_homnay.setBackground(new Color(255, 240, 140));
		add(bt_homnay);
		
		JLabel bt_thangnay = new JLabel("Tháng nay");
		bt_thangnay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setMonthToCurrent();
		        Date fromDate = date_from.getDate();
		        Date toDate = date_to.getDate();
	            java.sql.Date sqlFromDate = new java.sql.Date(fromDate.getTime());
	            java.sql.Date sqlToDate = new java.sql.Date(toDate.getTime());
				timkiem(sqlFromDate, sqlToDate);
			}
		});
		bt_thangnay.setOpaque(true);
		bt_thangnay.setHorizontalAlignment(SwingConstants.CENTER);
		bt_thangnay.setFont(new Font("Tahoma", Font.BOLD, 20));
		bt_thangnay.setBackground(new Color(255, 233, 83));
		bt_thangnay.setBounds(143, 120, 124, 48);
		add(bt_thangnay);
		
		JLabel bt_namnay = new JLabel("Năm nay");
		bt_namnay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setYearToCurrent();
		        Date fromDate = date_from.getDate();
		        Date toDate = date_to.getDate();
	            java.sql.Date sqlFromDate = new java.sql.Date(fromDate.getTime());
	            java.sql.Date sqlToDate = new java.sql.Date(toDate.getTime());
				timkiem(sqlFromDate, sqlToDate);
			}
		});
		bt_namnay.setOpaque(true);
		bt_namnay.setHorizontalAlignment(SwingConstants.CENTER);
		bt_namnay.setFont(new Font("Tahoma", Font.BOLD, 20));
		bt_namnay.setBackground(new Color(255, 223, 2));
		bt_namnay.setBounds(266, 120, 124, 48);
		add(bt_namnay);
		
		date_from = new JDateChooser();
		date_from.setBackground(new Color(135, 206, 235));
		date_from.setSize(170, 37);
		date_from.setFont(new Font("Tahoma", Font.BOLD, 20));
		date_from.setLocation(628, 120);
		add(date_from);
		
		date_to = new JDateChooser();
		date_to.setBackground(new Color(135, 206, 235));
		date_to.setBounds(880, 120, 170, 37);
		date_to.setFont(new Font("Tahoma", Font.BOLD, 20));
		add(date_to);
		
		JButton bt_timkiem = new JButton("");
		bt_timkiem.setIcon(new ImageIcon(QL_MuonSach.class.getResource("/images/search.png")));
		bt_timkiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        Date fromDate = date_from.getDate();
		        Date toDate = date_to.getDate();
	            java.sql.Date sqlFromDate = new java.sql.Date(fromDate.getTime());
	            java.sql.Date sqlToDate = new java.sql.Date(toDate.getTime());
				timkiem(sqlFromDate, sqlToDate);
			}
		});
		bt_timkiem.setBounds(1070, 120, 60, 37);
		add(bt_timkiem);
		
		JLabel lblNewLabel_1 = new JLabel("đến");
		lblNewLabel_1.setForeground(new Color(169, 169, 169));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(820, 120, 45, 37);
		add(lblNewLabel_1);
		
		JButton bt_huy = new JButton("");
		bt_huy.setIcon(new ImageIcon(QL_MuonSach.class.getResource("/images/refresh.png")));
		bt_huy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadDonMua();
			    date_from.setDate(null);
			    date_to.setDate(null);
			}
		});
		bt_huy.setBounds(546, 120, 72, 37);
		add(bt_huy);
	}
	
	public void loadDonMua() {
		ArrayList<Model_PhieuMuon> list = DBMuonSach.getInstance().loadDonMua();
		table_model.setRowCount(0);
		for(Model_PhieuMuon phieumuon : list) {
			ImageIcon image = ImageUtil.bytesToImageIcon(phieumuon.getHinhAnh(), 80, 80);
			
	        Object[] newRow = {phieumuon.getMa(), image, phieumuon.getTenSach(), phieumuon.getTenThanhVien(), phieumuon.getNgayMuon(), phieumuon.getNgayHenTra(), phieumuon.getTinhTrang()};
	        table_model.addRow(newRow);
		}
	}
	
	public void timkiem( java.sql.Date from ,  java.sql.Date to) {
		ArrayList<Model_PhieuMuon> list = DBMuonSach.getInstance().timkiem(from, to);
		table_model.setRowCount(0);
		for(Model_PhieuMuon phieumuon : list) {
			ImageIcon image = ImageUtil.bytesToImageIcon(phieumuon.getHinhAnh(), 80, 80);
			
	        Object[] newRow = {phieumuon.getMa(), image, phieumuon.getTenSach(), phieumuon.getTenThanhVien(), phieumuon.getNgayMuon(), phieumuon.getNgayHenTra(), phieumuon.getTinhTrang()};
	        table_model.addRow(newRow);
		}
	}
	
    private void setMonthToCurrent() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = calendar.getTime();

        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        Date lastDayOfMonth = calendar.getTime();

        date_from.setDate(firstDayOfMonth);
        date_to.setDate(lastDayOfMonth);
    }
    
    private void setYearToCurrent() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfYear = calendar.getTime();

        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        Date lastDayOfYear = calendar.getTime();

        date_from.setDate(firstDayOfYear);
        date_to.setDate(lastDayOfYear);
    }
}
