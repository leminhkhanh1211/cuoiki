package view.component;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import model.Model_NguoiMuon;
import service.Service;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ThemThanhVien extends JPanel{
	private JDialog dialog;
	private JTextField tf_ten;
	private JTextField tf_sdt;
	private JTextField tf_damuon;
	private JTextField tf_dangmuon;
	
	public ThemThanhVien(JDialog dialog) {
		this.dialog = dialog;
		setBackground(new Color(255, 255, 255));
		setSize(650, 400);
		setLayout(null);
		
		JLabel lblTnKhchHng = new JLabel("Tên thành viên");
		lblTnKhchHng.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTnKhchHng.setBounds(86, 83, 177, 30);
		add(lblTnKhchHng);
		
		tf_ten = new JTextField();
		tf_ten.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_ten.setColumns(10);
		tf_ten.setBounds(273, 83, 283, 30);
		add(tf_ten);
		
		JLabel lblSt = new JLabel("SĐT");
		lblSt.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSt.setBounds(86, 135, 154, 30);
		add(lblSt);
		
		tf_sdt = new JTextField();
		tf_sdt.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_sdt.setColumns(10);
		tf_sdt.setBounds(273, 135, 283, 30);
		add(tf_sdt);
		
		JLabel lblTngChi = new JLabel("SL đã mượn");
		lblTngChi.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTngChi.setBounds(86, 186, 154, 30);
		add(lblTngChi);
		
		tf_damuon = new JTextField();
		tf_damuon.setText("0");
		tf_damuon.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_damuon.setColumns(10);
		tf_damuon.setBounds(273, 186, 283, 30);
		add(tf_damuon);
		
		JLabel lblimTchLy = new JLabel("SL đang mượn");
		lblimTchLy.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblimTchLy.setBounds(86, 240, 154, 30);
		add(lblimTchLy);
		
		tf_dangmuon = new JTextField();
		tf_dangmuon.setText("0");
		tf_dangmuon.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_dangmuon.setColumns(10);
		tf_dangmuon.setBounds(273, 240, 283, 30);
		add(tf_dangmuon);
		
		String[] itemHang = { "Đồng", "Bạc", "Vàng", "Kim cương" };
		
		JLabel lblNewLabel_1 = new JLabel("THÊM THÀNH VIÊN");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel_1.setBounds(89, 10, 449, 46);
		add(lblNewLabel_1);
		
		JButton bt_them = new JButton("THÊM");
		bt_them.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ten = tf_ten.getText();
				String sdt = tf_sdt.getText();
				int damuon = Integer.parseInt(tf_dangmuon.getText());
				int dangmuon = Integer.parseInt(tf_damuon.getText());
				
				Model_NguoiMuon khachHang = new Model_NguoiMuon(0, ten, sdt, damuon, dangmuon);
				Service.getInstance().getMain().getBody().getKhachhang().themThanhVien(khachHang);
				dialog.dispose();
			}
		});
		bt_them.setFont(new Font("Tahoma", Font.BOLD, 25));
		bt_them.setBounds(247, 319, 168, 36);
		add(bt_them);
	}
}
