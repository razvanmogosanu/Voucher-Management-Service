package maps;

import vouchers.Voucher;

import java.util.Vector;

public class UserVoucherMap extends ArrayMap<Integer, Vector<Voucher>> {
    public boolean addVoucher(Voucher v) {
        if(arrayList.isEmpty()) {
            Vector<Voucher> vector = new Vector<>();
            vector.add(v);
            arrayList.add(0, new ArrayMapEntry(v.getIdCampaign(), vector));
        }
        else {
            boolean ok = false;
            for(ArrayMapEntry entry : arrayList){
                if(entry.getKey().equals(v.getIdCampaign()))
                    ok = true;
            }
            if(ok) {
                if(v.getIdCampaign() < arrayList.size()) {
                    arrayList.get(v.getIdCampaign()).getValue().add(v);
                    System.out.println(arrayList);
                    return put(v.getIdCampaign(), arrayList.get(v.getIdCampaign()).getValue()) != null;
                }
                return false;
            }
            else {
                Vector<Voucher> vector = new Vector<>();
                vector.add(v);
                arrayList.add(0, new ArrayMapEntry(v.getIdCampaign(), vector));
            }
        }
        return false;
    }


}
