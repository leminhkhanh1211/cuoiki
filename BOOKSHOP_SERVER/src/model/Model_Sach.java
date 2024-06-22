package model;

import javax.swing.ImageIcon;

import org.json.JSONObject;

public class Model_Sach {
	private int maSach;
	private String ten;
	private String theLoai;
	private String tacGia;
	private int slHienCo;
	private int slDaMuon;
	private byte[] hinhAnh;
	
	public Model_Sach(int maSach, String ten, String theLoai, String tacGia, int slHienCo, int slDaMuon,
			byte[] hinhAnh) {
		this.maSach = maSach;
		this.ten = ten;
		this.theLoai = theLoai;
		this.tacGia = tacGia;
		this.slHienCo = slHienCo;
		this.slDaMuon = slDaMuon;
		this.hinhAnh = hinhAnh;
	}
	
	public Model_Sach(Object json) {
        JSONObject obj = (JSONObject) json;
        try {
        	maSach = obj.getInt("maSach");
        	ten = obj.getString("ten");
        	theLoai = obj.getString("theLoai");
        	tacGia = obj.getString("tacGia");
        	slHienCo = obj.getInt("slHienCo");
        	slDaMuon = obj.getInt("slDaMuon");
        	hinhAnh = convertHexStringToByteArray(obj.getString("hinhAnh"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
    public JSONObject toJsonObject(String type) {
    	try {
			JSONObject json = new JSONObject();
			json.put("type", type);
			json.put("maSach", maSach);
			json.put("ten", ten);
			json.put("theLoai", theLoai);
			json.put("tacGia", tacGia);
			json.put("slHienCo", slHienCo);
			json.put("slDaMuon", slDaMuon);
			json.put("hinhAnh", convertByteArrayToHexString(hinhAnh));
			return json;
		} catch (Exception e) {
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
    
    

	public int getMaSach() {
		return maSach;
	}

	public void setMaSach(int maSach) {
		this.maSach = maSach;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getTheLoai() {
		return theLoai;
	}

	public void setTheLoai(String theLoai) {
		this.theLoai = theLoai;
	}

	public String getTacGia() {
		return tacGia;
	}

	public void setTacGia(String tacGia) {
		this.tacGia = tacGia;
	}

	public int getSlHienCo() {
		return slHienCo;
	}

	public void setSlHienCo(int slHienCo) {
		this.slHienCo = slHienCo;
	}

	public int getSlDaMuon() {
		return slDaMuon;
	}

	public void setSlDaMuon(int slDaMuon) {
		this.slDaMuon = slDaMuon;
	}

	public byte[] getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(byte[] hinhAnh) {
		this.hinhAnh = hinhAnh;
	}



	
}
