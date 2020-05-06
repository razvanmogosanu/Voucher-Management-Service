import enums.UserType;
import maps.UserVoucherMap;

import java.util.Vector;

public class User {
    private int id;
    private String userName;
    private String userEmail;
    private String userPassword;
    private UserType userType;

    private UserVoucherMap userVoucherMap;
    private Vector<Notification> notifications;

    public User(int id, String userName, String userEmail, String userPassword, UserType userType) {
        this.id = id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userType = userType;
        userVoucherMap = new UserVoucherMap();
        notifications = new Vector<>();
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public UserVoucherMap getUserVoucherMap() {
        return userVoucherMap;
    }

    public void setUserVoucherMap(UserVoucherMap userVoucherMap) {
        this.userVoucherMap = userVoucherMap;
    }

    public Vector<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifiAcations(Vector<Notification> notifications) {
        this.notifications = notifications;
    }
    public void addNotification(Notification notification) {
        notifications.add(notification);
    }

    @Override
    public String toString() {
        return "" + id +
                ", " + userName + '\'' +
                ", " + userEmail + '\''  +
                "," + userType;
    }
}
