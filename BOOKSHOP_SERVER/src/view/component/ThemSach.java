package view.component;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import EnCode.ImageUtil;
import model.Model_NguoiMuon;
import model.Model_Sach;
import service.Service;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ThemSach extends JPanel{
	private JDialog dialog;
	private JTextField tf_tenSach;
	private JTextField tf_tacGia;
	private JTextField tf_slHienCo;
	private JTextField tf_slDaMuon;
	private JComboBox cb_theloai;
	private JLabel lb_anh;
	private JButton bt_them;
	private JLabel lblNewLabel;
	private byte[] hinhAnh;
	
	public ThemSach(JDialog dialog) {
		this.dialog = dialog;
		setBackground(new Color(255, 255, 255));
		setSize(1000, 500);
		setLayout(null);
		
		JLabel lblTnSch = new JLabel("Tên sách");
		lblTnSch.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTnSch.setBounds(433, 124, 145, 30);
		add(lblTnSch);
		
		tf_tenSach = new JTextField();
		tf_tenSach.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_tenSach.setText("");
		tf_tenSach.setColumns(10);
		tf_tenSach.setBounds(560, 124, 318, 30);
		add(tf_tenSach);
		
		JLabel lblThLoi = new JLabel("Thể loại");
		lblThLoi.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblThLoi.setBounds(433, 170, 145, 30);
		add(lblThLoi);
		
		String[] itemTheLoai = {"Sách giáo khoa", "Sách khoa học", "Sách nghệ thuật", "Sách văn học", "Sách SELF HELP", "Truyện tranh" };
		cb_theloai = new JComboBox<>(itemTheLoai);
		cb_theloai.setFont(new Font("Tahoma", Font.BOLD, 20));
		cb_theloai.setSelectedIndex(0);
		cb_theloai.setBounds(560, 170, 318, 30);
		add(cb_theloai);
		
		JLabel lblTcGi = new JLabel("Tác giả");
		lblTcGi.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTcGi.setBounds(433, 217, 145, 30);
		add(lblTcGi);
		
		tf_tacGia = new JTextField();
		tf_tacGia.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_tacGia.setText("");
		tf_tacGia.setColumns(10);
		tf_tacGia.setBounds(560, 217, 318, 30);
		add(tf_tacGia);
		
		JLabel lblSlTnKho = new JLabel("SL hiện có");
		lblSlTnKho.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSlTnKho.setBounds(433, 265, 145, 30);
		add(lblSlTnKho);
		
		tf_slHienCo = new JTextField();
		tf_slHienCo.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_slHienCo.setText("");
		tf_slHienCo.setColumns(10);
		tf_slHienCo.setBounds(560, 265, 318, 30);
		add(tf_slHienCo);
		
		JLabel lblSlBn = new JLabel("SL đã mượn");
		lblSlBn.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSlBn.setBounds(433, 312, 145, 30);
		add(lblSlBn);
		
		tf_slDaMuon = new JTextField();
		tf_slDaMuon.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_slDaMuon.setText("");
		tf_slDaMuon.setColumns(10);
		tf_slDaMuon.setBounds(560, 312, 318, 30);
		add(tf_slDaMuon);
		
		lb_anh = new JLabel("");
		lb_anh.setIcon(new ImageIcon(ThemSach.class.getResource("/images/icon_image.png")));
		lb_anh.setBounds(71, 124, 250, 250);
		add(lb_anh);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hinhAnh = ImageUtil.imageToBytes(dialog, lb_anh);
			}
		});
		btnNewButton.setIcon(new ImageIcon(ThemSach.class.getResource("/images/icon_camera.png")));
		btnNewButton.setBounds(71, 374, 250, 30);
		add(btnNewButton);
		
		bt_them = new JButton("THÊM");
		bt_them.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ten = tf_tenSach.getText();
				String tacGia = tf_tacGia.getText();
				int slHienCo = Integer.parseInt(tf_slHienCo.getText());
				int slDaMuon = Integer.parseInt(tf_slDaMuon.getText());
				String theLoai = cb_theloai.getSelectedItem().toString();
								
				Model_Sach sach = new Model_Sach(0, ten, theLoai, tacGia, slHienCo, slDaMuon, hinhAnh);
				Service.getInstance().getMain().getBody().getKhosach().themSach(sach);
				dialog.dispose();
			}
		});
		bt_them.setFont(new Font("Tahoma", Font.BOLD, 25));
		bt_them.setBounds(386, 435, 235, 40);
		add(bt_them);
		
		lblNewLabel = new JLabel("THÊM SÁCH");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setBounds(263, 31, 449, 46);
		add(lblNewLabel);
	}
}
