package vouchers;

import enums.VoucherStatusType;

import java.util.Date;

public class GiftVoucher extends Voucher {
    private int value;

    public GiftVoucher(Integer idVoucher, VoucherStatusType voucherStatusType, String email, int idCampaign, Integer value) {
        super(idVoucher, voucherStatusType, email, idCampaign);
        this.value = value;
    }
}
