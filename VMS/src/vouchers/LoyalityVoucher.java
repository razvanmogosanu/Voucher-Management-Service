package vouchers;

import enums.VoucherStatusType;

import java.util.Date;

public class LoyalityVoucher extends Voucher {
    private int value;

    public LoyalityVoucher(Integer idVoucher, VoucherStatusType voucherStatusType, String email, int idCampaign, int value) {
        super(idVoucher, voucherStatusType, email, idCampaign);
        this.value = value;
    }
}
