import enums.CampaignStatusType;
import enums.NotificationType;
import enums.VoucherStatusType;
import vouchers.GiftVoucher;
import vouchers.LoyalityVoucher;
import vouchers.Voucher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class VMS {
    private static Vector<Campaign> campaigns;
    private static Vector<User> users;
    private Date startSession;
    private static VMS single_instance = null;

    public VMS() {
        campaigns = new Vector<>();
        users = new Vector<>();
    }

    public static VMS getInstance() {
        if (single_instance == null) {
            single_instance = new VMS();
        }
        return single_instance;
    }

    public Vector<Campaign> getCampaigns(){
        return campaigns;
    }

    public Campaign getCampaign(Integer id){
        for(Campaign camp : campaigns){
            if (camp.getIdCampaign().equals(id)){
                return camp;
            }
        }
        return null;
    }

    public void addCampaignInVector(Campaign campaign) {
        campaigns.add(campaign);
    }

    public void updateCampaign(Integer id, Campaign campaign){
        for(Campaign camp : campaigns){
            if (camp.getIdCampaign().equals(id)){
                if(camp.getCampaignStatusType().equals(CampaignStatusType.NEW)){
                    camp.setCampaignName(campaign.getCampaignName());
                    camp.setCampaignDescription(campaign.getCampaignDescription());
                    camp.setTotalNrOfVauchers(campaign.getTotalNrOfVauchers());
                    camp.setStartDate(campaign.getStartDate());
                    camp.setEndDate(campaign.getEndDate());
                }
                if(camp.getCampaignStatusType().equals(CampaignStatusType.STARTED)){
                    if(campaign.getTotalNrOfVauchers() - camp.getTotalNrOfVauchers() + camp.getNrOfVouchers() > 0){
                        camp.setTotalNrOfVauchers(campaign.getTotalNrOfVauchers());
                        camp.setNrOfVouchers(campaign.getTotalNrOfVauchers() - camp.getTotalNrOfVauchers() - camp.getNrOfVouchers());
                    }
                    camp.setEndDate(campaign.getEndDate());
                }
            }
        }
    }

    public void cancelCampaignWithId(Integer id){
        for(Campaign camp : campaigns){
            if (camp.getIdCampaign().equals(id) && (camp.getCampaignStatusType().equals(CampaignStatusType.NEW)
                                                    || (camp.getCampaignStatusType().equals(CampaignStatusType.STARTED))
                                                    )){
                camp.setCampaignStatusType(CampaignStatusType.CANCELLED);
            }
        }
    }

    public Vector<User> getUsers(){
        return users;
    }

    public void addUser(User user){
        users.add(user);
    }

    @Override
    public String toString() {
        return "VMS{" +
                "campaigns=" + campaigns +
                ", users=" + users +
                '}';
    }

    public Date getStartSession() {
        return startSession;
    }

    public void setStartSession(Date startSession) {
        this.startSession = startSession;
    }

    public static void setCampaigns(Vector<Campaign> campaigns) {
        VMS.campaigns = campaigns;
    }

    public static void setUsers(Vector<User> users) {
        VMS.users = users;
    }

    public void addCampaign(String[] eventsDetails) throws ParseException {
        if(TestUtils.isAdmin(Integer.parseInt(eventsDetails[0]))) {
            Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(eventsDetails[5]);
            Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(eventsDetails[6]);
            CampaignStatusType campaignStatusType = TestUtils.getCampaignStatusType(startDate, endDate, startSession);
            addCampaignInVector(new Campaign(Integer.parseInt(eventsDetails[2]),eventsDetails[3],
                    eventsDetails[4],startDate,endDate, Integer.parseInt(eventsDetails[7]), eventsDetails[8], campaignStatusType));
        }
    }

    public  void editCampaign(String[] eventsDetails) throws ParseException {
        Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(eventsDetails[5]);
        Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(eventsDetails[6]);
        updateCampaign(Integer.parseInt(eventsDetails[2]),new Campaign(Integer.parseInt(eventsDetails[2]),
                eventsDetails[3], eventsDetails[4], startDate, endDate, Integer.parseInt(eventsDetails[7]),""
                , TestUtils.getCampaignStatusType(startDate, endDate, startSession)));

        for(User user : getUsers()){
            Notification notification = new Notification(NotificationType.EDIT,startSession, Integer.parseInt(eventsDetails[2]));
            user.addNotification(notification);
        }
    }

    public void cancelCampaign(String[] eventsDetails) throws ParseException {
        Integer id = Integer.parseInt(eventsDetails[2]) - 1;
        if(TestUtils.isAdmin(id)) {
            for(User user : getUsers()){
                Notification notification = new Notification(NotificationType.CANCEL, startSession, id + 1);
                user.addNotification(notification);
            }
            cancelCampaignWithId(id);
        }
    }

    public  void generateVoucher(String[] eventsDetails){
        if(TestUtils.isAdmin(Integer.parseInt(eventsDetails[0]) - 1)) {
            if(eventsDetails[4].equals("LoyaltyVoucher")&& Integer.parseInt(eventsDetails[2]) < getCampaigns().size()){
                int voucherId = getCampaign(Integer.parseInt(eventsDetails[2])).getVoucherId();
                Voucher generatedVoucher = new LoyalityVoucher(voucherId, VoucherStatusType.UNUSED,
                        eventsDetails[3],Integer.parseInt(eventsDetails[2]),Integer.parseInt(eventsDetails[5]));
                getCampaign(Integer.parseInt(eventsDetails[2])).setVoucherId(getCampaign(Integer.parseInt(eventsDetails[2])).getVoucherId() + 1);

                User admin = getUsers().get(Integer.parseInt(eventsDetails[0]) - 1);
                admin.getUserVoucherMap().addVoucher(generatedVoucher);
                if(!getCampaigns().get(Integer.parseInt(eventsDetails[2])).getObserver().contains(admin))
                    getCampaigns().get(Integer.parseInt(eventsDetails[2])).getObserver().add(admin);
            }

            if((eventsDetails[4].equals("GiftVoucher") && Integer.parseInt(eventsDetails[2]) < getCampaigns().size())){
                int voucherId = getCampaign(Integer.parseInt(eventsDetails[2])).getVoucherId();
                Voucher generatedVoucher = new GiftVoucher(voucherId, VoucherStatusType.UNUSED,
                        eventsDetails[3],Integer.parseInt(eventsDetails[2]),Integer.parseInt(eventsDetails[5]));
                getCampaign(Integer.parseInt(eventsDetails[2])).setVoucherId(getCampaign(Integer.parseInt(eventsDetails[2])).getVoucherId() + 1);
                User admin = getUsers().get(Integer.parseInt(eventsDetails[0]) - 1);
                admin.getUserVoucherMap().addVoucher(generatedVoucher);
                if(!getCampaigns().get(Integer.parseInt(eventsDetails[2])).getObserver().contains(admin))
                    getCampaigns().get(Integer.parseInt(eventsDetails[2])).getObserver().add(admin);
            }

        }
    }

    public void redeemVoucher(String[] eventsDetails) throws ParseException {
        if(TestUtils.isAdmin(Integer.parseInt(eventsDetails[0]))){
            Voucher voucher =getUsers().get(Integer.parseInt(eventsDetails[0])).getUserVoucherMap().
                    get(Integer.parseInt(eventsDetails[3])).get(Integer.parseInt(eventsDetails[3]));
            if(voucher.getVoucherStatusType().equals(VoucherStatusType.UNUSED)) {
                voucher.setVoucherStatusType(VoucherStatusType.USED);

                Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(eventsDetails[4]);
                voucher.setDataUtilizarii(date);
            }
        }
    }

    public void getVouchers(String[] eventsDetails) {
        if(!TestUtils.isAdmin(Integer.parseInt(eventsDetails[0]) - 1)) {
            for(User user : getUsers()){
                System.out.println(user.getUserVoucherMap().get(1));
            }

        }
    }

    public void getObservers(String[] eventsDetails) {
        System.out.println(getCampaigns().get(Integer.parseInt(eventsDetails[2])).getObserver());
    }

    public void getNotifications(String[] eventsDetails) {
        if(!TestUtils.isAdmin(Integer.parseInt(eventsDetails[0]) - 1)){
            System.out.println(getUsers().get(Integer.parseInt(eventsDetails[0]) - 1).getNotifications());
        }
    }

}
