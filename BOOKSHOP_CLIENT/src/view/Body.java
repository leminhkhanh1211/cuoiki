package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import model.Model_PhieuMuon;
import model.Model_Sach;
import net.miginfocom.swing.MigLayout;
import swing.ImageRenderer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import EnCode.ImageUtil;
import javax.swing.border.LineBorder;

public class Body extends JPanel{
	
	private JLayeredPane listSach;
	private JTable table;
	private DefaultTableModel table_model;

	public Body() {
		setBackground(new Color(255, 255, 255));
		setSize(1240, 840);
		setLayout(null);
		
		JLabel lb_logo = new JLabel("");
		lb_logo.setIcon(new ImageIcon(Body.class.getResource("/images/logo_title.png")));
		lb_logo.setHorizontalAlignment(SwingConstants.CENTER);
		lb_logo.setBounds(370, 10, 442, 63);
		add(lb_logo);
		
		listSach = new JLayeredPane();
		listSach.setBackground(SystemColor.controlHighlight);
		listSach.setOpaque(true);
//		layeredPane.setBounds(56, 346, 1100, 439);
		JScrollPane scroll = new JScrollPane(listSach);
		scroll.setBounds(56, 346, 1100, 439);
		add(scroll);
		
		listSach.setLayout(new MigLayout("wrap 6, fillx", "30[150]10[150]10[150]10[150]10[150]10[150]30", "20[200]20"));
		
		table_model = new DefaultTableModel(
				new Object[][] {},
				new String[] {
					"M\u00E3", "", "T\u00EAn s\u00E1ch"
				}
			);
		table = new JTable();
		table.setModel(table_model);
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(950);
		table.getColumnModel().getColumn(1).setCellRenderer(new ImageRenderer());
		table.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		Font headerFont = new Font("Arial", Font.BOLD, 25);
		table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 40));
		table.getTableHeader().setFont(headerFont);
		table.setRowHeight(80);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setBounds(56, 83, 1100, 242);
		add(scrollPane);
		
	}
	
	public void addSach(Model_Sach sach) {
		listSach.add(new Item_Sach(sach), "width 150:150:150, height 200:200:200");
		listSach.repaint();
		listSach.revalidate();
	}
	
	public void themDonMua(Model_Sach sach) {
		ImageIcon image = ImageUtil.bytesToImageIcon(sach.getHinhAnh(), 80, 80);
        Object[] newRow = {sach.getMaSach(), image, sach.getTen()};
        table_model.addRow(newRow);
	}

	public DefaultTableModel getTable_model() {
		return table_model;
	}

	public JLayeredPane getListSach() {
		return listSach;
	}

	public void setListSach(JLayeredPane listSach) {
		this.listSach = listSach;
	}
	
	
	
	
	
}
