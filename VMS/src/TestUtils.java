import enums.CampaignStatusType;
import enums.UserType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public final class TestUtils {
    public static String[] readFile(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path))).split("\n");
    }

    public static CampaignStatusType getCampaignStatusType(Date startDate, Date endDate, Date startSession){
        return (startSession.compareTo(startDate) < 0) ?   CampaignStatusType.STARTED :
                (startSession.compareTo(startDate) > 0
                && startSession.compareTo(endDate) < 0) ?  CampaignStatusType.NEW :
                (startSession.compareTo(endDate) > 0) ?    CampaignStatusType.EXPIRED :
                                                            null;// sau CampaignStatusType.Canceled
    }

    public static boolean isAdmin(Integer id){
        return Test.vms.getUsers().get(id).getUserType().equals(UserType.ADMIN);
    }

}
