import enums.NotificationType;

import java.util.Date;

public class Notification {

    private NotificationType notificationType;
    private Date startDate;
    private int idCampaign;

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getIdCampaign() {
        return idCampaign;
    }

    public void setIdCampaign(int idCampaign) {
        this.idCampaign = idCampaign;
    }

    public Notification(NotificationType notificationType, Date startDate, int idCampaign) {
        this.notificationType = notificationType;
        this.startDate = startDate;
        this.idCampaign = idCampaign;
    }

    @Override
    public String toString() {
        return "" +
                "" + notificationType +
                ", " + startDate +
                ", " + idCampaign +
                '}';
    }
}
