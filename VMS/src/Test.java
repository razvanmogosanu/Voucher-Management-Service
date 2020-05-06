import enums.CampaignStatusType;
import enums.UserType;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Integer.parseInt;

public class Test {
    public static VMS vms;

    public static void readCampaign(String path) throws IOException, ParseException {
        String[] temp = TestUtils.readFile(path);
        int nrOfCampaigns = parseInt(temp[0]);
        vms.setStartSession(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(temp[1]));


        for (int i = 2; i < temp.length; i++) {
            String[] campaignDetails = temp[i].split(";");
            Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(campaignDetails[3]);
            Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(campaignDetails[4]);

            CampaignStatusType campaignStatusType = TestUtils.getCampaignStatusType(startDate, endDate,vms.getStartSession());


            vms.addCampaignInVector(new Campaign(parseInt(campaignDetails[0]), campaignDetails[1],
                    campaignDetails[2], startDate, endDate, parseInt(campaignDetails[5]), campaignDetails[6], campaignStatusType));
        }
    }


    public static void readUsers(String path) throws IOException {
        String[] temp = TestUtils.readFile(path);
        int nrOfCampaigns = parseInt(temp[0]);

        for (int i = 1; i < temp.length; i++) {
            String[] userDetails = temp[i].split(";");
            vms.addUser(new User(parseInt(userDetails[0]),userDetails[1], userDetails[3]
                    , userDetails[2], (userDetails[4].equals("ADMIN")) ? UserType.ADMIN : UserType.GUEST));
        }

    }

    public static void readEvents(String path) throws IOException, ParseException {
        String[] temp = TestUtils.readFile(path);
        Date startSession = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(temp[0]);

        for (int i = 2; i < temp.length; i++) {
            String[] eventsDetails = temp[i].split(";");
            switch(eventsDetails[1]) {
                case "addCampaign":
                    vms.addCampaign(eventsDetails);
                    break;
                case "editCampaign":
                    vms.editCampaign(eventsDetails);
                    break;
                case "cancelCampaign":
                    vms.cancelCampaign(eventsDetails);
                    break;
                case "generateVoucher":
                    vms.generateVoucher(eventsDetails);
                    break;
                case "redeemVoucher":
                    vms.redeemVoucher(eventsDetails);
                    break;
                case "getVouchers":
                    vms.getVouchers(eventsDetails);
                    break;
                case "getObservers":
                    vms.getObservers(eventsDetails);
                    break;
                case "getNotifications":
                    vms.getNotifications(eventsDetails);
                    break;
            }
        }
    }


    public static void main(String[] args) throws IOException, ParseException {
        vms = VMS.getInstance();
    /**Testare*/
        readCampaign("teste/test03/input/campaigns.txt");
        readUsers("teste/test03/input/users.txt");
        readEvents("teste/test03/input/events.txt");

        JFrame frame = new JFrame("VMS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        GridLayout gridLayout = new GridLayout(5,1,10,10);
        frame.setSize( 1000, 600);
        frame.setLayout(gridLayout);
        JButton button = new JButton("Incarca fisiere");
        frame.add(button);
        button.addActionListener(e -> {
            try {
                readCampaign("teste/test02/input/campaigns.txt");
                readUsers("teste/test02/input/users.txt");
            } catch (IOException | ParseException ex) {
                ex.printStackTrace();
            }
        });

        JButton button2 = new JButton("Log in");
        JTextArea name = new JTextArea();
        JTextArea pass = new JTextArea();
        frame.add(name);
        frame.add(pass);
        button2.addActionListener(e -> {
            for(User user: vms.getUsers()){
                if(user.getUserName().equals(name.getText()) && user.getUserPassword().equals(pass.getText())) {
                    System.out.println("Merge");
                }
            }
        });
        frame.add(button2);

        frame.pack();

    }

}
