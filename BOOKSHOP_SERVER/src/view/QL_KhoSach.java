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
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import EnCode.ImageUtil;
import EnCode.XMLExporter;
import dao.DBNhanVien;
import dao.DBSach;
import model.Model_NhanVien;
import model.Model_Sach;
import swing.PlaceholderTextField;
import view.component.ThemNhanVien;
import view.component.ThemSach;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QL_KhoSach extends JPanel{
	private JTextField tf_loc_damuon_from;
	private JTextField tf_loc_damuon_to;
	private JTextField tf_loc_hienco_from;
	private JTextField tf_loc_hienco_to;
	private JTextField tf_maSach;
	private JTextField tf_tenSach;
	private JTextField tf_tacGia;
	private JTextField tf_slHienCo;
	private JTextField tf_slDaMuon;
	private JTable table;
	private DefaultTableModel table_model;
	private JComboBox cb_theloai;
	private JLabel lb_hinhAnh;
	private JButton bt_luu;
	private JComboBox tf_loc_theloai;
	private PlaceholderTextField tf_loc_ten;
	
	public QL_KhoSach() {
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
					"M\u00E3", "T\u00EAn", "Th\u1EC3 lo\u1EA1i", "T\u00E1c gi\u1EA3", "Hi\u1EC7n c\u00F3", "\u0110\u00E3 m\u01B0\u1EE3n"
				}
			);
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 20));
		table.setModel(table_model);
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(470);
		table.getColumnModel().getColumn(2).setPreferredWidth(230);
		table.getColumnModel().getColumn(3).setPreferredWidth(230);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		
		Font headerFont = new Font("Arial", Font.BOLD, 14);
		table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 30));
		table.getTableHeader().setFont(headerFont);
		table.setRowHeight(40);
		//Bảng
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(15, 430, 1200, 308);
		add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(245, 245, 245));
		panel.setBounds(15, 83, 385, 329);
		add(panel);
		
		JLabel lblTn = new JLabel("Tên");
		lblTn.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTn.setBounds(10, 28, 92, 30);
		panel.add(lblTn);
		
		JLabel lblSt_1 = new JLabel("Thể loại");
		lblSt_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSt_1.setBounds(10, 74, 92, 30);
		panel.add(lblSt_1);
		
		tf_loc_ten = new PlaceholderTextField("Nhập tên sách...");
		tf_loc_ten.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                timkiem();
            }
            public void removeUpdate(DocumentEvent e) {
                timkiem();
            }
            public void changedUpdate(DocumentEvent e) {
                timkiem();
            }
        });
		tf_loc_ten.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_loc_ten.setColumns(10);
		tf_loc_ten.setBounds(112, 28, 268, 30);
		panel.add(tf_loc_ten);
		
		JButton bt_loc = new JButton("LỌC");
		bt_loc.setIcon(new ImageIcon(QL_KhoSach.class.getResource("/images/filter.png")));
		bt_loc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loc();
			}
		});
		bt_loc.setFont(new Font("Tahoma", Font.BOLD, 20));
		bt_loc.setBounds(124, 274, 150, 33);
		panel.add(bt_loc);
		
		String[] itemTheLoaiLoc = {"Tất cả" ,"Sách giáo khoa", "Sách khoa học", "Sách nghệ thuật", "Sách văn học", "Sách SELF HELP", "Truyện tranh" };
		tf_loc_theloai = new JComboBox<>(itemTheLoaiLoc);
		tf_loc_theloai.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_loc_theloai.setBounds(112, 74, 268, 30);
		panel.add(tf_loc_theloai);
		
		JLabel lblSt_1_1_2 = new JLabel("Số lượng đã mượn");
		lblSt_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSt_1_1_2.setBounds(10, 193, 198, 30);
		panel.add(lblSt_1_1_2);
		
		tf_loc_damuon_from = new JTextField();
		tf_loc_damuon_from.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_loc_damuon_from.setColumns(10);
		tf_loc_damuon_from.setBounds(10, 222, 147, 30);
		panel.add(tf_loc_damuon_from);
		
		JLabel lblSt_1_1_1_1 = new JLabel("đến");
		lblSt_1_1_1_1.setForeground(new Color(128, 128, 128));
		lblSt_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSt_1_1_1_1.setBounds(173, 222, 47, 30);
		panel.add(lblSt_1_1_1_1);
		
		tf_loc_damuon_to = new JTextField();
		tf_loc_damuon_to.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_loc_damuon_to.setColumns(10);
		tf_loc_damuon_to.setBounds(230, 222, 147, 30);
		panel.add(tf_loc_damuon_to);
		
		JLabel lblSt_1_1_3 = new JLabel("Số lượng hiện có");
		lblSt_1_1_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSt_1_1_3.setBounds(10, 124, 222, 30);
		panel.add(lblSt_1_1_3);
		
		tf_loc_hienco_from = new JTextField();
		tf_loc_hienco_from.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_loc_hienco_from.setColumns(10);
		tf_loc_hienco_from.setBounds(10, 153, 147, 30);
		panel.add(tf_loc_hienco_from);
		
		JLabel lblSt_1_1_1_2 = new JLabel("đến");
		lblSt_1_1_1_2.setForeground(new Color(128, 128, 128));
		lblSt_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSt_1_1_1_2.setBounds(173, 153, 47, 30);
		panel.add(lblSt_1_1_1_2);
		
		tf_loc_hienco_to = new JTextField();
		tf_loc_hienco_to.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_loc_hienco_to.setColumns(10);
		tf_loc_hienco_to.setBounds(230, 153, 147, 30);
		panel.add(tf_loc_hienco_to);
		
		JLabel lblMSch = new JLabel("Mã sách");
		lblMSch.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMSch.setBounds(425, 83, 140, 30);
		add(lblMSch);
		
		JLabel lblTnSch = new JLabel("Tên sách");
		lblTnSch.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTnSch.setBounds(425, 129, 140, 30);
		add(lblTnSch);
		
		JLabel lblThLoi = new JLabel("Thể loại");
		lblThLoi.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblThLoi.setBounds(425, 175, 140, 30);
		add(lblThLoi);
		
		JLabel lblTcGi = new JLabel("Tác giả");
		lblTcGi.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTcGi.setBounds(425, 222, 140, 30);
		add(lblTcGi);
		
		JLabel lblSlTnKho = new JLabel("SL hiện có");
		lblSlTnKho.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSlTnKho.setBounds(425, 270, 140, 30);
		add(lblSlTnKho);
		
		JLabel lblSlBn = new JLabel("SL đã mượn");
		lblSlBn.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSlBn.setBounds(425, 317, 140, 30);
		add(lblSlBn);
		
		tf_maSach = new JTextField();
		tf_maSach.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_maSach.setColumns(10);
		tf_maSach.setBounds(567, 83, 282, 30);
		add(tf_maSach);
		
		tf_tenSach = new JTextField();
		tf_tenSach.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_tenSach.setColumns(10);
		tf_tenSach.setBounds(567, 129, 282, 30);
		add(tf_tenSach);
		
		tf_tacGia = new JTextField();
		tf_tacGia.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_tacGia.setColumns(10);
		tf_tacGia.setBounds(567, 222, 282, 30);
		add(tf_tacGia);
		
		tf_slHienCo = new JTextField();
		tf_slHienCo.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_slHienCo.setColumns(10);
		tf_slHienCo.setBounds(567, 270, 282, 30);
		add(tf_slHienCo);
		
		tf_slDaMuon = new JTextField();
		tf_slDaMuon.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_slDaMuon.setColumns(10);
		tf_slDaMuon.setBounds(569, 317, 282, 30);
		add(tf_slDaMuon);
		
		String[] itemTheLoai = {"Sách giáo khoa", "Sách khoa học", "Sách nghệ thuật", "Sách văn học", "Sách SELF HELP", "Truyện tranh" };
		cb_theloai = new JComboBox<>(itemTheLoai);
		cb_theloai.setFont(new Font("Tahoma", Font.BOLD, 20));
		cb_theloai.setBounds(567, 175, 282, 30);
		add(cb_theloai);
		
		lb_hinhAnh = new JLabel("");
		lb_hinhAnh.setBackground(new Color(255, 255, 255));
		lb_hinhAnh.setIcon(new ImageIcon(QL_KhoSach.class.getResource("/images/icon_image2.png")));
		lb_hinhAnh.setOpaque(true);
		lb_hinhAnh.setBounds(862, 68, 260, 260);
		add(lb_hinhAnh);
		
		JLabel bt_them = new JLabel("THÊM");
		bt_them.setIcon(new ImageIcon(QL_KhoSach.class.getResource("/images/icon_add.png")));
		bt_them.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reset();
	        	JDialog dialog = new JDialog();
	        	ThemSach them = new ThemSach(dialog);
	    		dialog.getContentPane().setLayout(new GridLayout(1,1));
	    		dialog.setSize(1000, 550);
	    		dialog.setLocationRelativeTo(null);
	        	dialog.getContentPane().add(them);
	        	dialog.setVisible(true);
			}
		});
		bt_them.setOpaque(true);
		bt_them.setHorizontalAlignment(SwingConstants.CENTER);
		bt_them.setFont(new Font("Tahoma", Font.BOLD, 20));
		bt_them.setBackground(new Color(2, 119, 185));
		bt_them.setBounds(862, 333, 132, 35);
		add(bt_them);
		
		JLabel bt_sua = new JLabel("SỬA");
		bt_sua.setIcon(new ImageIcon(QL_KhoSach.class.getResource("/images/edit.png")));
		bt_sua.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tf_maSach.getText().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Bạn chưa chọn đối tượng muốn sửa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					unreset();
					bt_luu.setVisible(true);
				}
			}
		});
		bt_sua.setOpaque(true);
		bt_sua.setHorizontalAlignment(SwingConstants.CENTER);
		bt_sua.setFont(new Font("Tahoma", Font.BOLD, 20));
		bt_sua.setBackground(new Color(188, 219, 0));
		bt_sua.setBounds(995, 368, 129, 35);
		add(bt_sua);
		
		JLabel bt_xoa = new JLabel("XÓA");
		bt_xoa.setIcon(new ImageIcon(QL_KhoSach.class.getResource("/images/icon_delete (2).png")));
		bt_xoa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tf_maSach.getText().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Bạn chưa chọn đối tượng muốn xóa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					xoaSach(Integer.parseInt(tf_maSach.getText()));
					reset();
					loadSach();
				}

			}
		});
		bt_xoa.setOpaque(true);
		bt_xoa.setHorizontalAlignment(SwingConstants.CENTER);
		bt_xoa.setFont(new Font("Tahoma", Font.BOLD, 20));
		bt_xoa.setBackground(new Color(210, 25, 13));
		bt_xoa.setBounds(862, 368, 132, 35);
		add(bt_xoa);
		
		JLabel bt_excel = new JLabel("XML");
		bt_excel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				XMLExporter.exportSachListToXML(DBSach.getInstance().loadSach());
			}
		});
		bt_excel.setIcon(new ImageIcon(QL_KhoSach.class.getResource("/images/icon_export.png")));
		bt_excel.setOpaque(true);
		bt_excel.setHorizontalAlignment(SwingConstants.CENTER);
		bt_excel.setFont(new Font("Tahoma", Font.BOLD, 20));
		bt_excel.setBackground(new Color(0, 185, 7));
		bt_excel.setBounds(995, 333, 129, 35);
		add(bt_excel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(462, 370, 442, 2);
		add(separator);
		
		bt_luu = new JButton("LƯU");
		bt_luu.setIcon(new ImageIcon(QL_KhoSach.class.getResource("/images/save (2).png")));
		bt_luu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		suaThongTin();
        		reset();
        		bt_luu.setVisible(false);
        		loadSach();
			}
		});
		bt_luu.setVisible(false);
		bt_luu.setFont(new Font("Tahoma", Font.BOLD, 20));
		bt_luu.setBounds(643, 382, 111, 37);
		add(bt_luu);
		
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) { 
                        tf_maSach.setText(table.getValueAt(selectedRow, 0).toString());
                        tf_tenSach.setText(table.getValueAt(selectedRow, 1).toString());
                        cb_theloai.setSelectedItem(table.getValueAt(selectedRow, 2).toString());
                        tf_tacGia.setText(table.getValueAt(selectedRow, 3).toString());
                        tf_slHienCo.setText(table.getValueAt(selectedRow, 4).toString());
                        tf_slDaMuon.setText(table.getValueAt(selectedRow, 5).toString());
                    
                        ImageUtil.setImageLabelFromBytes(DBSach.getInstance().getImage(Integer.parseInt(table.getValueAt(selectedRow, 0).toString())), lb_hinhAnh);
                    }
                }
            }
        });
        reset();
		
	}
	
	public void loadSach() {
		ArrayList<Model_Sach> list = DBSach.getInstance().loadSach();
		table_model.setRowCount(0);
		for(Model_Sach sach : list) {
	        Object[] newRow = {sach.getMaSach(), sach.getTen(), sach.getTheLoai(), sach.getTacGia(), sach.getSlHienCo(), sach.getSlDaMuon()};
	        table_model.addRow(newRow);
		}
	}
	
	public void themSach(Model_Sach sach) {
		Model_Sach sachMoi = DBSach.getInstance().themSach(sach);
        Object[] newRow = {sach.getMaSach(), sach.getTen(), sach.getTheLoai(), sach.getTacGia(), sach.getSlHienCo(), sach.getSlDaMuon()};
        table_model.addRow(newRow);
	}
	
	public void suaThongTin() {
		int ma = Integer.parseInt(tf_maSach.getText());
		String ten = tf_tenSach.getText();
		String theLoai = cb_theloai.getSelectedItem().toString();
		String tacgia = tf_tacGia.getText();
		int hienco = Integer.parseInt(tf_slHienCo.getText());
		int damuon = Integer.parseInt(tf_slDaMuon.getText());

		Model_Sach sach = new Model_Sach(ma, ten, theLoai, tacgia, hienco, damuon, null);
		DBSach.getInstance().suaThongTin(sach);
	}
	
	public void xoaSach(int maSach) {
		DBSach.getInstance().xoaSach(maSach);
	}
	
	public void reset() {
		tf_maSach.setText("");
		tf_tenSach.setText("");
		cb_theloai.setSelectedIndex(0);
		tf_tacGia.setText("");
		tf_slHienCo.setText("");
		tf_slDaMuon.setText("");
		
		tf_maSach.setEditable(false);
		tf_tenSach.setEditable(false);
		cb_theloai.setEnabled(false);
		tf_tacGia.setEditable(false);
		tf_slHienCo.setEditable(false);
		tf_slDaMuon.setEditable(false);
		
		lb_hinhAnh.setIcon(new ImageIcon(QL_KhoSach.class.getResource("/images/icon_image2.png")));
		
	}
	
	public void unreset() {
		tf_tenSach.setEditable(true);
		cb_theloai.setEnabled(true);
		tf_tacGia.setEditable(true);
		tf_slHienCo.setEditable(true);
		tf_slDaMuon.setEditable(true);
	}
	
	public void loc() {
		String dieukien = "1=1";
		if(!tf_loc_theloai.getSelectedItem().toString().equals("Tất cả")) {
			dieukien += " AND theloai='" + tf_loc_theloai.getSelectedItem().toString() + "'";
		}
		if(!tf_loc_hienco_from.getText().isEmpty()) {
			dieukien += " AND hienco>=" + tf_loc_hienco_from.getText();
		}
		if(!tf_loc_hienco_to.getText().isEmpty()) {
			dieukien += " AND hienco<=" + tf_loc_hienco_to.getText();
		}
		if(!tf_loc_damuon_from.getText().isEmpty()) {
			dieukien += " AND damuon>=" + tf_loc_damuon_from.getText();
		}
		if(!tf_loc_damuon_to.getText().isEmpty()) {
			dieukien += " AND damuon<=" + tf_loc_damuon_to.getText();
		}
		
		ArrayList<Model_Sach> list = DBSach.getInstance().locSach(dieukien);
		table_model.setRowCount(0);
		for(Model_Sach sach : list) {
	        Object[] newRow = {sach.getMaSach(), sach.getTen(), sach.getTheLoai(), sach.getTacGia(), sach.getSlHienCo(), sach.getSlDaMuon()};
	        table_model.addRow(newRow);
		}
		
		reset();
	}
	
	public void timkiem() {
		String tensach = tf_loc_ten.getText();
		if(tensach.isEmpty() || tensach.equals("Nhập tên sách...")) {
			loadSach();
		}
		else {
			ArrayList<Model_Sach> list = DBSach.getInstance().timkiem("%" + tensach + "%");
			table_model.setRowCount(0);
			for(Model_Sach sach : list) {
		        Object[] newRow = {sach.getMaSach(), sach.getTen(), sach.getTheLoai(), sach.getTacGia(), sach.getSlHienCo(), sach.getSlDaMuon()};
		        table_model.addRow(newRow);
			}
		}
		
		reset();
	}
}
