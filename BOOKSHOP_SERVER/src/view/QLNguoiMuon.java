package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import EnCode.XMLExporter;
import dao.DBNguoiMuon;
import dao.DBNhanVien;
import model.Model_NguoiMuon;
import model.Model_NhanVien;
import swing.PlaceholderTextField;
import view.component.ThemNhanVien;
import view.component.ThemThanhVien;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QLNguoiMuon extends JPanel{
	private JTextField tf_maKhanhHang;
	private JTextField tf_tenKhachHang;
	private JTextField tf_sdt;
	private JTextField tf_damuon;
	private JTextField tf_dangmuon;
	private JTextField tf_loc_damuon_from;
	private JTextField tf_loc_damuon_to;
	private JTable table;
	private DefaultTableModel table_model;
	private JButton bt_luu;
	private PlaceholderTextField tf_loc_makhachhang;
	private PlaceholderTextField tf_loc_sdt;
	private JTextField tf_loc_dangmuon_from;
	private JTextField tf_loc_dangmuon_to;
	
	public QLNguoiMuon() {
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
					"M\u00E3 TV", "T\u00EAn th\u00E0nh vi\u00EAn", "S\u0110T", "SL \u0111\u00E3 m\u01B0\u1EE3n", "SL \u0111ang m\u01B0\u1EE3n"
				}
			);
		table = new JTable();
		table.setModel(table_model);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(400);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(250);
		table.getColumnModel().getColumn(4).setPreferredWidth(250);
		table.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		Font headerFont = new Font("Arial", Font.BOLD, 20);
		table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 30));
		table.getTableHeader().setFont(headerFont);
		table.setRowHeight(40);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 373, 1200, 354);
		add(scrollPane);
		
		JLabel lblMKhchHng = new JLabel("Mã thành viên");
		lblMKhchHng.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMKhchHng.setBounds(10, 105, 154, 30);
		add(lblMKhchHng);
		
		JLabel lblTnKhchHng = new JLabel("Tên thành viên");
		lblTnKhchHng.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTnKhchHng.setBounds(10, 149, 177, 30);
		add(lblTnKhchHng);
		
		JLabel lblSt = new JLabel("SĐT");
		lblSt.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSt.setBounds(10, 195, 154, 30);
		add(lblSt);
		
		JLabel lblTngChi = new JLabel("SL đã mượn");
		lblTngChi.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTngChi.setBounds(10, 241, 154, 30);
		add(lblTngChi);
		
		JLabel lblimTchLy = new JLabel("SL đang mượn");
		lblimTchLy.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblimTchLy.setBounds(10, 287, 154, 30);
		add(lblimTchLy);
		
		tf_maKhanhHang = new JTextField();
		tf_maKhanhHang.setFont(new Font("Tahoma", Font.BOLD, 18));
		tf_maKhanhHang.setBounds(197, 104, 240, 30);
		add(tf_maKhanhHang);
		tf_maKhanhHang.setColumns(10);
		
		tf_tenKhachHang = new JTextField();
		tf_tenKhachHang.setFont(new Font("Tahoma", Font.BOLD, 18));
		tf_tenKhachHang.setColumns(10);
		tf_tenKhachHang.setBounds(197, 150, 240, 30);
		add(tf_tenKhachHang);
		
		tf_sdt = new JTextField();
		tf_sdt.setFont(new Font("Tahoma", Font.BOLD, 18));
		tf_sdt.setColumns(10);
		tf_sdt.setBounds(197, 196, 240, 30);
		add(tf_sdt);
		
		tf_damuon = new JTextField();
		tf_damuon.setFont(new Font("Tahoma", Font.BOLD, 18));
		tf_damuon.setColumns(10);
		tf_damuon.setBounds(197, 242, 240, 30);
		add(tf_damuon);
		
		tf_dangmuon = new JTextField();
		tf_dangmuon.setFont(new Font("Tahoma", Font.BOLD, 18));
		tf_dangmuon.setColumns(10);
		tf_dangmuon.setBounds(197, 288, 240, 30);
		add(tf_dangmuon);
		
		String[] itemHang = { "Đồng", "Bạc", "Vàng", "Kim cương" };
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 245));
		panel.setBounds(480, 104, 448, 258);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblMKh = new JLabel("Mã TV");
		lblMKh.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMKh.setBounds(10, 10, 92, 30);
		panel.add(lblMKh);
		
		JLabel lblSt_1 = new JLabel("SĐT");
		lblSt_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSt_1.setBounds(10, 58, 75, 30);
		panel.add(lblSt_1);
		
		JLabel lblSt_1_1 = new JLabel("Đã mượn");
		lblSt_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSt_1_1.setBounds(10, 106, 118, 30);
		panel.add(lblSt_1_1);
		
		tf_loc_makhachhang = new PlaceholderTextField("Nhập mã thành viên...");
		tf_loc_makhachhang.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                timkiemMaKH();
            }
            public void removeUpdate(DocumentEvent e) {
            	timkiemMaKH();
            }
            public void changedUpdate(DocumentEvent e) {
            	timkiemMaKH();
            }
        });
		tf_loc_makhachhang.setFont(new Font("Tahoma", Font.BOLD, 18));
		tf_loc_makhachhang.setBounds(137, 10, 306, 30);
		panel.add(tf_loc_makhachhang);
		tf_loc_makhachhang.setColumns(10);
		
		tf_loc_sdt = new PlaceholderTextField("Nhập SĐT...");
		tf_loc_sdt.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                timkiemSdt();
            }
            public void removeUpdate(DocumentEvent e) {
            	timkiemSdt();
            }
            public void changedUpdate(DocumentEvent e) {
            	timkiemSdt();
            }
        });
		tf_loc_sdt.setFont(new Font("Tahoma", Font.BOLD, 18));
		tf_loc_sdt.setColumns(10);
		tf_loc_sdt.setBounds(137, 58, 306, 30);
		panel.add(tf_loc_sdt);
		
		tf_loc_damuon_from = new JTextField();
		tf_loc_damuon_from.setFont(new Font("Tahoma", Font.BOLD, 18));
		tf_loc_damuon_from.setColumns(10);
		tf_loc_damuon_from.setBounds(137, 106, 126, 30);
		panel.add(tf_loc_damuon_from);
		
		tf_loc_damuon_to = new JTextField();
		tf_loc_damuon_to.setFont(new Font("Tahoma", Font.BOLD, 18));
		tf_loc_damuon_to.setColumns(10);
		tf_loc_damuon_to.setBounds(317, 106, 126, 30);
		panel.add(tf_loc_damuon_to);
		
		JLabel lblSt_1_1_1 = new JLabel("đến");
		lblSt_1_1_1.setForeground(new Color(128, 128, 128));
		lblSt_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSt_1_1_1.setBounds(273, 106, 38, 30);
		panel.add(lblSt_1_1_1);
		
		String[] itemHangLoc = {"Tất cả" ,"Đồng", "Bạc", "Vàng", "Kim cương" };
		
		JButton bt_loc = new JButton("LỌC");
		bt_loc.setIcon(new ImageIcon(QLNguoiMuon.class.getResource("/images/filter.png")));
		bt_loc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loc();
			}
		});
		bt_loc.setFont(new Font("Tahoma", Font.BOLD, 20));
		bt_loc.setBounds(160, 208, 150, 33);
		panel.add(bt_loc);
		
		JLabel lblSt_1_1_2 = new JLabel("Đang mượn");
		lblSt_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSt_1_1_2.setBounds(10, 154, 126, 30);
		panel.add(lblSt_1_1_2);
		
		tf_loc_dangmuon_from = new JTextField();
		tf_loc_dangmuon_from.setFont(new Font("Tahoma", Font.BOLD, 18));
		tf_loc_dangmuon_from.setColumns(10);
		tf_loc_dangmuon_from.setBounds(137, 154, 126, 30);
		panel.add(tf_loc_dangmuon_from);
		
		JLabel lblSt_1_1_1_1 = new JLabel("đến");
		lblSt_1_1_1_1.setForeground(Color.GRAY);
		lblSt_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSt_1_1_1_1.setBounds(273, 154, 38, 30);
		panel.add(lblSt_1_1_1_1);
		
		tf_loc_dangmuon_to = new JTextField();
		tf_loc_dangmuon_to.setFont(new Font("Tahoma", Font.BOLD, 18));
		tf_loc_dangmuon_to.setColumns(10);
		tf_loc_dangmuon_to.setBounds(317, 154, 126, 30);
		panel.add(tf_loc_dangmuon_to);
		
		JLabel bt_xuatDanhSach = new JLabel("XUẤT DANH SÁCH");
		bt_xuatDanhSach.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				XMLExporter.exportKhachHangListToXML(DBNguoiMuon.getInstance().loadThanhVien());
			}
		});
		bt_xuatDanhSach.setIcon(new ImageIcon(QLNguoiMuon.class.getResource("/images/icon_export.png")));
		bt_xuatDanhSach.setOpaque(true);
		bt_xuatDanhSach.setHorizontalAlignment(SwingConstants.CENTER);
		bt_xuatDanhSach.setFont(new Font("Tahoma", Font.BOLD, 16));
		bt_xuatDanhSach.setBackground(new Color(0, 185, 7));
		bt_xuatDanhSach.setBounds(937, 105, 197, 56);
		add(bt_xuatDanhSach);
		
		JLabel bt_themThanhVien = new JLabel("THÊM THÀNH VIÊN");
		bt_themThanhVien.setIcon(new ImageIcon(QLNguoiMuon.class.getResource("/images/icon_add.png")));
		bt_themThanhVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reset();
	        	JDialog dialog = new JDialog();
	        	ThemThanhVien them = new ThemThanhVien(dialog);
	    		dialog.getContentPane().setLayout(new GridLayout(1,1));
	    		dialog.setSize(650, 450);
	    		dialog.setLocationRelativeTo(null);
	        	dialog.getContentPane().add(them);
	        	dialog.setVisible(true);
			}
		});
		bt_themThanhVien.setOpaque(true);
		bt_themThanhVien.setHorizontalAlignment(SwingConstants.CENTER);
		bt_themThanhVien.setFont(new Font("Tahoma", Font.BOLD, 16));
		bt_themThanhVien.setBackground(new Color(2, 119, 185));
		bt_themThanhVien.setBounds(937, 165, 197, 56);
		add(bt_themThanhVien);
		
		JLabel bt_suaThongTin = new JLabel("SỬA THÔNG TIN");
		bt_suaThongTin.setIcon(new ImageIcon(QLNguoiMuon.class.getResource("/images/edit.png")));
		bt_suaThongTin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tf_maKhanhHang.getText().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Bạn chưa chọn đối tượng muốn sửa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					unreset();
					bt_luu.setVisible(true);
				}
			}
		});
		bt_suaThongTin.setOpaque(true);
		bt_suaThongTin.setHorizontalAlignment(SwingConstants.CENTER);
		bt_suaThongTin.setFont(new Font("Tahoma", Font.BOLD, 16));
		bt_suaThongTin.setBackground(new Color(188, 219, 0));
		bt_suaThongTin.setBounds(937, 225, 197, 56);
		add(bt_suaThongTin);
		
		JLabel bt_xoaThanhVien = new JLabel("XÓA THÀNH VIÊN");
		bt_xoaThanhVien.setIcon(new ImageIcon(QLNguoiMuon.class.getResource("/images/icon_delete (2).png")));
		bt_xoaThanhVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tf_maKhanhHang.getText().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Bạn chưa chọn đối tượng muốn xóa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					xoaThanhVien(Integer.parseInt(tf_maKhanhHang.getText()));
					reset();
					loadThanhVien();
				}

			}
		});
		bt_xoaThanhVien.setOpaque(true);
		bt_xoaThanhVien.setHorizontalAlignment(SwingConstants.CENTER);
		bt_xoaThanhVien.setFont(new Font("Tahoma", Font.BOLD, 16));
		bt_xoaThanhVien.setBackground(new Color(210, 25, 13));
		bt_xoaThanhVien.setBounds(937, 285, 197, 56);
		add(bt_xoaThanhVien);
		
		bt_luu = new JButton("LƯU");
		bt_luu.setIcon(new ImageIcon(QLNguoiMuon.class.getResource("/images/save (2).png")));
		bt_luu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
        		suaThongTin();
        		reset();
        		bt_luu.setVisible(false);
        		loadThanhVien();
			}
		});
		bt_luu.setVisible(false);
		bt_luu.setFont(new Font("Tahoma", Font.BOLD, 20));
		bt_luu.setBounds(182, 328, 110, 30);
		add(bt_luu);
		
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        tf_maKhanhHang.setText(table.getValueAt(selectedRow, 0).toString());
                        tf_tenKhachHang.setText(table.getValueAt(selectedRow, 1).toString());
                        tf_sdt.setText(table.getValueAt(selectedRow, 2).toString());
                        tf_damuon.setText(table.getValueAt(selectedRow, 3).toString());
                        tf_dangmuon.setText(table.getValueAt(selectedRow, 4).toString());
                    }
                }
            }
        });
		reset();
	}
	
	public void loadThanhVien() {
		ArrayList<Model_NguoiMuon> list = DBNguoiMuon.getInstance().loadThanhVien();
		table_model.setRowCount(0);
		for(Model_NguoiMuon khachHangMoi : list) {
	        Object[] newRow = {khachHangMoi.getMaKhachHang(), khachHangMoi.getTen(), khachHangMoi.getSdt(), khachHangMoi.getDaMuon(), khachHangMoi.getDaMuon()};
	        table_model.addRow(newRow);
		}
	}
	
	public void themThanhVien(Model_NguoiMuon khachhang) {
		Model_NguoiMuon khachHangMoi = DBNguoiMuon.getInstance().themThanhVien(khachhang);
        Object[] newRow = {khachHangMoi.getMaKhachHang(), khachHangMoi.getTen(), khachHangMoi.getSdt(), khachHangMoi.getDaMuon(), khachHangMoi.getDaMuon()};
        table_model.addRow(newRow);
	}
	
	
	public void suaThongTin() {
		int maKhachHang = Integer.parseInt(tf_maKhanhHang.getText());
		String ten = tf_tenKhachHang.getText();
		String sdt = tf_sdt.getText();
		int damuon = Integer.parseInt(tf_dangmuon.getText());
		int dangmuon = Integer.parseInt(tf_damuon.getText());
		
		Model_NguoiMuon khachHang = new Model_NguoiMuon(maKhachHang, ten, sdt, damuon, dangmuon);			
		DBNguoiMuon.getInstance().suaThongTin(khachHang);
	}
	
	public void xoaThanhVien(int maKhachHang) {
		DBNguoiMuon.getInstance().xoaThanhVien(maKhachHang);
	}
	
	public void reset() {
		tf_dangmuon.setText("");
		tf_maKhanhHang.setText("");
		tf_sdt.setText("");
		tf_tenKhachHang.setText("");
		tf_damuon.setText("");
		
		tf_dangmuon.setEditable(false);
		tf_maKhanhHang.setEditable(false);
		tf_sdt.setEditable(false);
		tf_tenKhachHang.setEditable(false);
		tf_damuon.setEditable(false);
	}
	
	public void unreset() {
		tf_dangmuon.setEditable(true);
		tf_maKhanhHang.setEditable(true);
		tf_sdt.setEditable(true);
		tf_tenKhachHang.setEditable(true);
		tf_damuon.setEditable(true);
	}
	
	public void loc() {
		String dieukien = "1=1";
		if(!tf_loc_damuon_from.getText().isEmpty()) {
			dieukien += " AND damuon>=" + tf_loc_damuon_from.getText();
		}
		if(!tf_loc_damuon_to.getText().isEmpty()) {
			dieukien += " AND damuon<=" + tf_loc_damuon_to.getText();
		}
		if(!tf_loc_dangmuon_from.getText().isEmpty()) {
			dieukien += " AND dangmuon>=" + tf_loc_dangmuon_from.getText();
		}
		if(!tf_loc_dangmuon_to.getText().isEmpty()) {
			dieukien += " AND dangmuon<=" + tf_loc_dangmuon_to.getText();
		}
		
		ArrayList<Model_NguoiMuon> list = DBNguoiMuon.getInstance().locThanhVien(dieukien);
		table_model.setRowCount(0);
		for(Model_NguoiMuon khachHangMoi : list) {
	        Object[] newRow = {khachHangMoi.getMaKhachHang(), khachHangMoi.getTen(), khachHangMoi.getSdt(), khachHangMoi.getDaMuon(), khachHangMoi.getDaMuon()};
	        table_model.addRow(newRow);
		}
		
		reset();
	}
	
	public void timkiemMaKH() {
		if(tf_loc_makhachhang.getText().isEmpty()|| tf_loc_makhachhang.getText().equals("Nhập mã thành viên...")) {
			loadThanhVien();
		}
		else {
			int ma = Integer.parseInt(tf_loc_makhachhang.getText());
			ArrayList<Model_NguoiMuon> list = DBNguoiMuon.getInstance().timkiemMaKH(ma);
			table_model.setRowCount(0);
			for(Model_NguoiMuon khachHangMoi : list) {
		        Object[] newRow = {khachHangMoi.getMaKhachHang(), khachHangMoi.getTen(), khachHangMoi.getSdt(), khachHangMoi.getDaMuon(), khachHangMoi.getDaMuon()};
		        table_model.addRow(newRow);
			}
		}
		
		reset();
	}
	
	public void timkiemSdt() {
		String sdt = tf_loc_sdt.getText();
		if(sdt.isEmpty()|| sdt.equals("Nhập SĐT...")) {
			loadThanhVien();
		}
		else {
			ArrayList<Model_NguoiMuon> list = DBNguoiMuon.getInstance().timkiemSdt(sdt + "%");
			table_model.setRowCount(0);
			for(Model_NguoiMuon khachHangMoi : list) {
		        Object[] newRow = {khachHangMoi.getMaKhachHang(), khachHangMoi.getTen(), khachHangMoi.getSdt(), khachHangMoi.getDaMuon(), khachHangMoi.getDaMuon()};
		        table_model.addRow(newRow);
			}
		}
		
		reset();
	}

}
