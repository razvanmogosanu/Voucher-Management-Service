package vouchers;

import enums.VoucherStatusType;

import java.util.Date;

public abstract class Voucher{
    private Integer idVoucher;
    private int voucherCode;
    private VoucherStatusType voucherStatusType;
    private Date dataUtilizarii;
    private String email;
    private int idCampaign;


    public Integer getIdVoucher() {
        return idVoucher;
    }

    public void setIdVoucher(Integer idVoucher) {
        this.idVoucher = idVoucher;
    }

    public int getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(int voucherCode) {
        this.voucherCode = voucherCode;
    }

    public VoucherStatusType getVoucherStatusType() {
        return voucherStatusType;
    }

    public void setVoucherStatusType(VoucherStatusType voucherStatusType) {
        this.voucherStatusType = voucherStatusType;
    }

    public Date getDataUtilizarii() {
        return dataUtilizarii;
    }

    public void setDataUtilizarii(Date dataUtilizarii) {
        this.dataUtilizarii = dataUtilizarii;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdCampaign() {
        return idCampaign;
    }

    public void setIdCampaign(int idCampaign) {
        this.idCampaign = idCampaign;
    }

    Voucher(Integer idVoucher, VoucherStatusType voucherStatusType, String email, int idCampaign) {
        this.idVoucher = idVoucher;
        this.voucherStatusType = voucherStatusType;
        this.email = email;
        this.idCampaign = idCampaign;
    }
}
