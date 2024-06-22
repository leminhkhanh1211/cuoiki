package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import EnCode.ImageUtil;
import model.Model_PhieuMuon;
import model.Model_Sach;
import service.Service;
import java.awt.SystemColor;

public class ChiTiet extends JPanel{
	private JDialog dialog;
	private JTextField tf_tenSach;
	private JTextField tf_tacGia;
	private JTextField tf_slHienCo;
	private JLabel lb_anh;
	private JButton bt_them;
	private byte[] hinhAnh;
	private Model_Sach sach;
	private JTextField tf_theloai;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	
	public ChiTiet(JDialog dialog, Model_Sach sach) {
		this.sach = sach;
		this.dialog = dialog;
		setBackground(new Color(255, 255, 255));
		setSize(950, 450);
		setLayout(null);
		
		JLabel lblTnSch = new JLabel("Tên sách");
		lblTnSch.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTnSch.setBounds(366, 53, 145, 30);
		add(lblTnSch);
		
		tf_tenSach = new JTextField(sach.getTen());
		tf_tenSach.setBackground(new Color(255, 255, 255));
		tf_tenSach.setEditable(false);
		tf_tenSach.setFont(new Font("Tahoma", Font.BOLD, 24));
		tf_tenSach.setColumns(10);
		tf_tenSach.setBounds(521, 53, 359, 40);
		add(tf_tenSach);
		
		JLabel lblThLoi = new JLabel("Thể loại");
		lblThLoi.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblThLoi.setBounds(366, 112, 145, 30);
		add(lblThLoi);
		
		String[] itemTheLoai = {"Sách giáo khoa", "Sách khoa học", "Sách nghệ thuật", "Sách văn học", "Sách SELF HELP", "Truyện tranh" };
		
		JLabel lblTcGi = new JLabel("Tác giả");
		lblTcGi.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTcGi.setBounds(366, 166, 145, 30);
		add(lblTcGi);
		
		tf_tacGia = new JTextField(sach.getTacGia());
		tf_tacGia.setBackground(new Color(255, 255, 255));
		tf_tacGia.setEditable(false);
		tf_tacGia.setFont(new Font("Tahoma", Font.BOLD, 24));
		tf_tacGia.setColumns(10);
		tf_tacGia.setBounds(521, 164, 359, 40);
		add(tf_tacGia);
		
		JLabel lblSlTnKho = new JLabel("SL hiện có");
		lblSlTnKho.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblSlTnKho.setBounds(366, 220, 145, 30);
		add(lblSlTnKho);
		
		tf_slHienCo = new JTextField(sach.getSlHienCo() + "");
		tf_slHienCo.setBackground(new Color(255, 255, 255));
		tf_slHienCo.setEditable(false);
		tf_slHienCo.setFont(new Font("Tahoma", Font.BOLD, 24));
		tf_slHienCo.setColumns(10);
		tf_slHienCo.setBounds(521, 218, 359, 40);
		add(tf_slHienCo);
		
		lb_anh = new JLabel("");
        ImageUtil.setImageLabelFromBytes(sach.getHinhAnh(), lb_anh, 250, 250);
		lb_anh.setBackground(SystemColor.activeCaption);
		lb_anh.setOpaque(true);
		lb_anh.setBounds(70, 53, 250, 250);
		add(lb_anh);
		
		bt_them = new JButton("THÊM");
		bt_them.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        long millis = System.currentTimeMillis();
		        Date currentDate = new Date(millis);
				Service.getInstance().getMain().getBody().themDonMua(sach);
//				Service.getInstance().getMain().getMenuLeft().themDonMua(donmua);
				Service.getInstance().getMain().getMenuLeft().getSachList().add(sach);
//				
				dialog.dispose();
			}
		});
		bt_them.setFont(new Font("Tahoma", Font.BOLD, 25));
		bt_them.setBounds(384, 370, 235, 56);
		add(bt_them);
		
		tf_theloai = new JTextField(sach.getTheLoai());
		tf_theloai.setBackground(new Color(255, 255, 255));
		tf_theloai.setEditable(false);
		tf_theloai.setFont(new Font("Tahoma", Font.BOLD, 24));
		tf_theloai.setColumns(10);
		tf_theloai.setBounds(521, 108, 359, 40);
		add(tf_theloai);
		
		setBackground(new Color(150, 220, 248));
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ChiTiet.class.getResource("/images/icon_star.png")));
		lblNewLabel.setBounds(70, 313, 30, 30);
		add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ChiTiet.class.getResource("/images/icon_star.png")));
		lblNewLabel_1.setBounds(290, 313, 30, 30);
		add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(ChiTiet.class.getResource("/images/icon_star.png")));
		lblNewLabel_2.setBounds(182, 313, 30, 30);
		add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(ChiTiet.class.getResource("/images/icon_star.png")));
		lblNewLabel_3.setBounds(127, 313, 30, 30);
		add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(ChiTiet.class.getResource("/images/icon_star.png")));
		lblNewLabel_4.setBounds(237, 313, 30, 30);
		add(lblNewLabel_4);
	}

	public Model_Sach getSach() {
		return sach;
	}
	
	
}
