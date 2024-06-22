package model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.json.JSONObject;

public class Model_PhieuMuon {
	private int ma;
	private byte[] hinhAnh;
	private int maThanhVien;
	private String tenThanhVien;
	private int maSach;
	private String tenSach;
	private Date ngayMuon;
	private Date ngayHenTra;
	private String tinhTrang;

	public Model_PhieuMuon(int ma, byte[] hinhAnh, int maThanhVien, String tenThanhVien, int maSach, String tenSach,
			Date ngayMuon, Date ngayHenTra, String tinhTrang) {
		this.ma = ma;
		this.hinhAnh = hinhAnh;
		this.maThanhVien = maThanhVien;
		this.tenThanhVien = tenThanhVien;
		this.maSach = maSach;
		this.tenSach = tenSach;
		this.ngayMuon = ngayMuon;
		this.ngayHenTra = ngayHenTra;
		this.tinhTrang = tinhTrang;
	}

	public Model_PhieuMuon(Object json) {
        JSONObject obj = (JSONObject) json;
        try {
        	ma = obj.getInt("ma");
        	hinhAnh = convertHexStringToByteArray(obj.getString("hinhAnh"));
        	maThanhVien = obj.getInt("maThanhVien");
        	tenThanhVien = obj.getString("tenThanhVien");
        	maSach = obj.getInt("maSach");
        	tenSach = obj.getString("tenSach");
        	ngayMuon = convertToSqlDate(obj.getString("ngayMuon"));
        	ngayHenTra = convertToSqlDate(obj.getString("ngayHenTra"));
        	tinhTrang = obj.getString("tinhTrang");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
    public JSONObject toJsonObject(String type) {
    	try {
			JSONObject json = new JSONObject();
			json.put("type", type);
			json.put("ma", ma);
			json.put("hinhAnh", convertByteArrayToHexString(hinhAnh));
			json.put("maThanhVien", maThanhVien);
			json.put("tenThanhVien", tenThanhVien);
			json.put("maSach", maSach);
			json.put("tenSach", tenSach);
			json.put("ngayMuon", formatDate(ngayMuon));
			json.put("ngayHenTra", formatDate(ngayHenTra));
			json.put("tinhTrang", tinhTrang);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
	
	
	private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
    
    private Date convertToSqlDate(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf.parse(dateString);
            return new Date(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private String convertByteArrayToHexString(byte[] array) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : array) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    
    private byte[] convertHexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                                 + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }

	public int getMa() {
		return ma;
	}

	public void setMa(int ma) {
		this.ma = ma;
	}

	public byte[] getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(byte[] hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public int getMaThanhVien() {
		return maThanhVien;
	}

	public void setMaThanhVien(int maThanhVien) {
		this.maThanhVien = maThanhVien;
	}

	public String getTenThanhVien() {
		return tenThanhVien;
	}

	public void setTenThanhVien(String tenThanhVien) {
		this.tenThanhVien = tenThanhVien;
	}

	public int getMaSach() {
		return maSach;
	}

	public void setMaSach(int maSach) {
		this.maSach = maSach;
	}

	public String getTenSach() {
		return tenSach;
	}

	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}

	public Date getNgayMuon() {
		return ngayMuon;
	}

	public void setNgayMuon(Date ngayMuon) {
		this.ngayMuon = ngayMuon;
	}

	public Date getNgayHenTra() {
		return ngayHenTra;
	}

	public void setNgayHenTra(Date ngayHenTra) {
		this.ngayHenTra = ngayHenTra;
	}

	public String getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
    
    


	
}
