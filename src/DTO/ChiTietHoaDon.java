
package DTO;

public class ChiTietHoaDon
{
	private int m_macthd;
        private HoaDon m_hd;
	private PhieuThuePhong m_ptp;
	private PhieuDichVu m_pdv;
	private int m_thanhtien;

    public ChiTietHoaDon(int m_macthd, HoaDon m_hd, PhieuThuePhong m_ptp, PhieuDichVu m_pdv, int m_thanhtien) {
        this.m_macthd = m_macthd;
        this.m_hd = m_hd;
        this.m_ptp = m_ptp;
        this.m_pdv = m_pdv;
        this.m_thanhtien = m_thanhtien;
    }

    public ChiTietHoaDon(int m_macthd) {
        this.m_macthd = m_macthd;
    }

    public int getM_macthd() {
        return m_macthd;
    }

    public void setM_macthd(int m_macthd) {
        this.m_macthd = m_macthd;
    }

    public HoaDon getM_hd(){
        return m_hd;
    }
    
    public void setM_hd(HoaDon m_hd){
        this.m_hd = m_hd;
    }
    
    public PhieuThuePhong getM_ptp() {
        return m_ptp;
    }

    public void setM_ptp(PhieuThuePhong m_ptp) {
        this.m_ptp = m_ptp;
    }

    public PhieuDichVu getM_pdv() {
        return m_pdv;
    }

    public void setM_pdv(PhieuDichVu m_pdv) {
        this.m_pdv = m_pdv;
    }

    public int getM_thanhtien() {
        return m_thanhtien;
    }

    public void setM_thanhtien(int m_thanhtien) {
        this.m_thanhtien = m_thanhtien;
    }

	
	
	
}
