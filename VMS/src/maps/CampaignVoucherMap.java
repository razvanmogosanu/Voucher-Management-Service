package maps;

import vouchers.Voucher;

import java.util.Vector;

public class CampaignVoucherMap extends ArrayMap<String, Vector<Voucher>> {

    public boolean addVoucher(Voucher v) {
       if(arrayList.isEmpty()) {
            Vector<Voucher> vector = new Vector<>();
            vector.add(v);
            arrayList.add(0, new ArrayMapEntry(v.getEmail(), vector));
       }
       else {
           boolean ok = false;
           for(ArrayMapEntry entry : arrayList){
               if(entry.getKey().equals(v.getEmail()))
                   ok = true;
           }
           if(ok) {
               arrayList.get(v.getIdCampaign()).getValue().add(v);
               return put(v.getEmail(), arrayList.get(v.getIdCampaign()).getValue()) != null;
           }
           else {
              Vector<Voucher> vector = new Vector<>();
              vector.add(v);
              arrayList.add(0, new ArrayMapEntry(v.getEmail(), vector));
           }
       }
       return false;
    }

    public Vector<Voucher> getVoucherFromCampaign(String email){
        for(ArrayMapEntry entry : arrayList) {
            if(entry.getKey().equals(email))
                return entry.getValue();
        }
        return null;
    }
}
