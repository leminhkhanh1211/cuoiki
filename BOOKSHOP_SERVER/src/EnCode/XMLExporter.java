package EnCode;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import model.Model_NguoiMuon;
import model.Model_NhanVien;
import model.Model_Sach;

public class XMLExporter {

    public static void exportNhanVienListToXML(List<Model_NhanVien> nhanVienList) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn nơi lưu trữ");

        FileNameExtensionFilter filter = new FileNameExtensionFilter("XML files (*.xml)", "xml");
        fileChooser.setFileFilter(filter);

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".xml")) {
                filePath += ".xml"; // Đảm bảo rằng tệp có đuôi .xml
            }
            exportNhanVienListToXML(nhanVienList, filePath);
        }
    }

    private static void exportNhanVienListToXML(List<Model_NhanVien> nhanVienList, String filePath) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("nhanvienlist");
            doc.appendChild(rootElement);

            for (Model_NhanVien nhanVien : nhanVienList) {
                Element nhanVienElement = doc.createElement("nhanvien");
                rootElement.appendChild(nhanVienElement);

                createElementWithValue(doc, nhanVienElement, "maNhanVien", String.valueOf(nhanVien.getMaNhanVien()));
                createElementWithValue(doc, nhanVienElement, "ten", nhanVien.getTen());
                createElementWithValue(doc, nhanVienElement, "cccd", nhanVien.getCccd());
                createElementWithValue(doc, nhanVienElement, "gioiTinh", nhanVien.getGioiTinh());
                createElementWithValue(doc, nhanVienElement, "ngaySinh", formatDate(nhanVien.getNgaySinh()));
                createElementWithValue(doc, nhanVienElement, "sdt", nhanVien.getSdt());
                createElementWithValue(doc, nhanVienElement, "chucVu", nhanVien.getChucVu());
                createElementWithValue(doc, nhanVienElement, "luong", String.valueOf(nhanVien.getLuong()));
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);

            JOptionPane.showMessageDialog(null, "File đã được lưu thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi lưu file.");
        }
    }
    
    public static void exportSachListToXML(List<Model_Sach> sachList) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn nơi lưu trữ");

        FileNameExtensionFilter filter = new FileNameExtensionFilter("XML files (*.xml)", "xml");
        fileChooser.setFileFilter(filter);

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".xml")) {
                filePath += ".xml"; 
            }
            exportSachListToXML(sachList, filePath);
        }
    }

    private static void exportSachListToXML(List<Model_Sach> sachList, String filePath) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("sachlist");
            doc.appendChild(rootElement);

            for (Model_Sach sach : sachList) {
                Element sachElement = doc.createElement("sach");
                rootElement.appendChild(sachElement);

                createElementWithValue(doc, sachElement, "maSach", String.valueOf(sach.getMaSach()));
                createElementWithValue(doc, sachElement, "ten", sach.getTen());
                createElementWithValue(doc, sachElement, "theLoai", sach.getTheLoai());
                createElementWithValue(doc, sachElement, "tacGia", sach.getTacGia());
                createElementWithValue(doc, sachElement, "hienCo", String.valueOf(sach.getSlHienCo()));
                createElementWithValue(doc, sachElement, "daMuon", String.valueOf(sach.getSlDaMuon()));
            }
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);

            JOptionPane.showMessageDialog(null, "File đã được lưu thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi lưu file.");
        }
    }
    
    public static void exportKhachHangListToXML(List<Model_NguoiMuon> khachHangList) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn nơi lưu trữ");

        FileNameExtensionFilter filter = new FileNameExtensionFilter("XML files (*.xml)", "xml");
        fileChooser.setFileFilter(filter);

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".xml")) {
                filePath += ".xml"; 
            }
            exportKhachHangListToXML(khachHangList, filePath);
        }
    }

    private static void exportKhachHangListToXML(List<Model_NguoiMuon> khachHangList, String filePath) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("khachhanglist");
            doc.appendChild(rootElement);

            for (Model_NguoiMuon khachHang : khachHangList) {
                Element khachHangElement = doc.createElement("khachhang");
                rootElement.appendChild(khachHangElement);

                createElementWithValue(doc, khachHangElement, "maKhachHang", String.valueOf(khachHang.getMaKhachHang()));
                createElementWithValue(doc, khachHangElement, "ten", khachHang.getTen());
                createElementWithValue(doc, khachHangElement, "sdt", khachHang.getSdt());
                createElementWithValue(doc, khachHangElement, "slDaMuon", String.valueOf(khachHang.getDaMuon()));
                createElementWithValue(doc, khachHangElement, "slDangMuon", String.valueOf(khachHang.getDangMuon()));
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);

            JOptionPane.showMessageDialog(null, "File đã được lưu thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi lưu file.");
        }
    }


    private static void createElementWithValue(Document doc, Element parentElement, String elementName, String value) {
        Element element = doc.createElement(elementName);
        element.appendChild(doc.createTextNode(value));
        parentElement.appendChild(element);
    }

    private static String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }
}
