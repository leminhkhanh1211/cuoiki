package view;

import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JPanel;

import model.Model_NguoiMuon;
import model.Model_PhieuMuon;
import model.Model_Sach;
import service.Service;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TraSach extends JPanel{
	private Model_NguoiMuon nguoimuon;
	private JDialog dialog;
	private ArrayList<Model_PhieuMuon> phieumuonList;
	private ArrayList<Model_PhieuMuon> traSachList;
	private JTextField textField;
	private JTable table_tra;
	private JTable table_muon;
	private DefaultTableModel table_model_muon;
	private DefaultTableModel table_model_tra;
	
	public TraSach(Model_NguoiMuon nguoimuon, JDialog dialog, ArrayList<Model_PhieuMuon> phieumuonList) {
		traSachList = new ArrayList<>();
		setBackground(new Color(255, 255, 255));
		this.nguoimuon = nguoimuon;
		this.dialog = dialog;
		this.phieumuonList = phieumuonList;
		
		setSize(1200, 700);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 233, 83));
		panel.setBounds(0, 0, 600, 700);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblTrSch = new JLabel("TRẢ SÁCH");
		lblTrSch.setBounds(97, 10, 386, 72);
		panel.add(lblTrSch);
		lblTrSch.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrSch.setForeground(new Color(255, 255, 255));
		lblTrSch.setFont(new Font("Tahoma", Font.BOLD, 35));
		
		JLabel lblMSch = new JLabel("MÃ SÁCH");
		lblMSch.setForeground(Color.WHITE);
		lblMSch.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblMSch.setBounds(97, 92, 123, 43);
		panel.add(lblMSch);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 25));
		textField.setBorder(null);
		textField.setBounds(230, 95, 197, 35);
		panel.add(textField);
		textField.setColumns(10);
		
		table_model_tra = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"M\u00E3", "T\u00EAn s\u00E1ch"
				}
			);
		table_tra = new JTable();
		table_tra.setModel(table_model_tra);
		table_tra.getColumnModel().getColumn(0).setPreferredWidth(55);
		table_tra.getColumnModel().getColumn(1).setPreferredWidth(450);
		table_tra.setFont(new Font("Tahoma", Font.BOLD, 14));
		Font headerFont = new Font("Arial", Font.BOLD, 14);
		table_tra.getTableHeader().setPreferredSize(new Dimension(table_tra.getTableHeader().getWidth(), 40));
		table_tra.getTableHeader().setFont(headerFont);
		table_tra.setRowHeight(40);
		
		JScrollPane scrollPane = new JScrollPane(table_tra);
		scrollPane.setBorder(null);
		scrollPane.setBounds(45, 161, 505, 406);
		scrollPane.setOpaque(true);
		scrollPane.setBackground(new Color(255, 233, 83));
		panel.add(scrollPane);
		
		JButton bt_trasach = new JButton("TRẢ SÁCH");
		bt_trasach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nguoimuon.setDangMuon(nguoimuon.getDangMuon() - traSachList.size());
				
				Service.getInstance().xuatHoaDonKhachHang(nguoimuon.toJsonObject("xuatHoaDonKhachHang"));
				Service.getInstance().traSach(traSachList);
				dialog.dispose();
				Service.getInstance().getMain().getMenuLeft().reset();
			}
		});
		bt_trasach.setFont(new Font("Tahoma", Font.BOLD, 22));
		bt_trasach.setBounds(199, 606, 177, 50);
		panel.add(bt_trasach);
		
		JButton bt_them = new JButton("");
		bt_them.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Model_PhieuMuon phieu = searchByMaSach(Integer.parseInt(textField.getText()));
		        Object[] newRow = {phieu.getMaSach(), phieu.getTenSach()};
		        table_model_tra.addRow(newRow);
		        traSachList.add(phieu);
		        textField.setText("");
			}
		});
		bt_them.setIcon(new ImageIcon(TraSach.class.getResource("/images/icon_down.png")));
		bt_them.setBounds(430, 95, 35, 35);
		panel.add(bt_them);
		
		JLabel lblNewLabel = new JLabel("SÁCH ĐANG MƯỢN");
		lblNewLabel.setForeground(new Color(255, 233, 83));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(712, 10, 386, 72);
		add(lblNewLabel);
		
		table_model_muon = new DefaultTableModel(
				new Object[][] {},
				new String[] {
					"M\u00E3", "T\u00EAn s\u00E1ch", "Ng\u00E0y m\u01B0\u1EE3n", "Ng\u00E0y h\u1EB9n tr\u1EA3"
				}
			);
		table_muon = new JTable();
		table_muon.setModel(table_model_muon);
		table_muon.getColumnModel().getColumn(0).setPreferredWidth(55);
		table_muon.getColumnModel().getColumn(1).setPreferredWidth(250);
		table_muon.getColumnModel().getColumn(2).setPreferredWidth(100);
		table_muon.getColumnModel().getColumn(3).setPreferredWidth(100);
		table_muon.setFont(new Font("Tahoma", Font.BOLD, 14));
		table_muon.getTableHeader().setPreferredSize(new Dimension(table_muon.getTableHeader().getWidth(), 40));
		table_muon.getTableHeader().setFont(headerFont);
		table_muon.setRowHeight(40);
		
		JScrollPane scrollPane_1 = new JScrollPane(table_muon);
		scrollPane_1.setBorder(null);
		scrollPane_1.setBackground(new Color(255, 255, 255));
		scrollPane_1.setOpaque(true);
		scrollPane_1.setBounds(649, 161, 505, 406);
		add(scrollPane_1);
		
		loadSachMuon();
	}
	
	public void loadSachMuon() {
		table_model_muon.setRowCount(0);
		for(Model_PhieuMuon phieu : phieumuonList) {
	        Object[] newRow = {phieu.getMaSach(), phieu.getTenSach(), phieu.getNgayMuon(), phieu.getNgayHenTra()};
	        table_model_muon.addRow(newRow);
		}
	}
	
	public Model_PhieuMuon searchByMaSach(int maSach) {
		for(Model_PhieuMuon phieu : phieumuonList) {
	        if(phieu.getMaSach() == maSach) {
	        	return phieu;
	        }
		}
		return null;
	}
	
	public Model_NguoiMuon getNguoimuon() {
		return nguoimuon;
	}
	public void setNguoimuon(Model_NguoiMuon nguoimuon) {
		this.nguoimuon = nguoimuon;
	}
	public JDialog getDialog() {
		return dialog;
	}
	public void setDialog(JDialog dialog) {
		this.dialog = dialog;
	}
	public ArrayList<Model_PhieuMuon> getPhieumuonList() {
		return phieumuonList;
	}
	public void setPhieumuonList(ArrayList<Model_PhieuMuon> phieumuonList) {
		this.phieumuonList = phieumuonList;
	}
}
