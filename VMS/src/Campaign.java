import enums.CampaignStatusType;
import maps.CampaignVoucherMap;
import vouchers.Voucher;

import java.util.Date;
import java.util.Vector;

public class Campaign {
    private Integer idCampaign;
    private String campaignName;
    private String campaignDescription;//
    private Date startDate;//
    private Date endDate;//
    private Integer nrOfVouchers;
    private String strategy;
    private Integer totalNrOfVauchers;
    private Integer voucherId;

    private CampaignStatusType campaignStatusType;

    private CampaignVoucherMap campaignVoucherMap;
    private Vector<User> observer;
    public Campaign(Integer idCampaign, String campaignName, String campaignDescription,
                    Date startDate, Date endDate, Integer nrOfVouchers, String strategy, CampaignStatusType campaignStatusType) {
        this.idCampaign = idCampaign;
        this.campaignName = campaignName;
        this.campaignDescription = campaignDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.nrOfVouchers = nrOfVouchers;
        this.strategy = strategy;
        this.campaignStatusType = campaignStatusType;
        observer = new Vector<>();
        voucherId = 0;
    }


    public void setNrOfVouchers(Integer nrOfVouchers) {
        this.nrOfVouchers = nrOfVouchers;
    }

    public Integer getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Integer voucherId) {
        this.voucherId = voucherId;
    }

    public Integer getTotalNrOfVauchers() {
        return totalNrOfVauchers;
    }

    public void setTotalNrOfVauchers(Integer totalNrOfVauchers) {
        this.totalNrOfVauchers = totalNrOfVauchers;
    }

    public Integer getIdCampaign() {
        return idCampaign;
    }

    public void setIdCampaign(Integer idCampaign) {
        this.idCampaign = idCampaign;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getCampaignDescription() {
        return campaignDescription;
    }

    public void setCampaignDescription(String campaignDescription) {
        this.campaignDescription = campaignDescription;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getNrOfVouchers() {
        return nrOfVouchers;
    }

    public void setNrOfVouchers(int nrOfVouchers) {
        this.nrOfVouchers = nrOfVouchers;
    }

    public CampaignStatusType getCampaignStatusType() {
        return campaignStatusType;
    }

    public void setCampaignStatusType(CampaignStatusType campaignStatusType) {
        this.campaignStatusType = campaignStatusType;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public CampaignVoucherMap getCampaignVoucherMap() {
        return campaignVoucherMap;
    }

    public void setCampaignVoucherMap(CampaignVoucherMap campaignVoucherMap) {
        this.campaignVoucherMap = campaignVoucherMap;
    }

    public Vector<User> getObserver() {
        return observer;
    }

    public void setObserver(Vector<User> observer) {
        this.observer = observer;
    }

    public Vector<Voucher> getVoucher(){
//        return campaignVoucherMap.getVoucherFromCampaign();
        return null;
    }

    public void update(Notification notification) {

    }

    public void addObserver(User user){
        observer.add(user);
    }

    public void removeObserver(User user){
        observer.remove(user);
    }

    public void notifyAllObservers(Notification notification){
        for(User user : observer) {
            user.getNotifications().add(notification);
        }
    }

    @Override
    public String toString() {
        return "Campaign{" +
                "idCampaign=" + idCampaign +
                ", campaignName='" + campaignName + '\'' +
                ", campaignDescription='" + campaignDescription + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", nrOfVouchers=" + nrOfVouchers +
                ", strategy='" + strategy + '\'' +
                ", budget=" + nrOfVouchers +
                '}';
    }
}
