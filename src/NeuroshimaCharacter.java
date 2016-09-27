import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pieru on 08.09.2016.
 */
public class NeuroshimaCharacter extends Character {
    private NeuroshimaEnum placeOfOrigin;
    private NeuroshimaEnum featureOfOrigin;
    private NeuroshimaEnum profession;
    private NeuroshimaEnum featureOfProfession;
    private NeuroshimaEnum specialization;

    private List<String> tricks = new ArrayList<String>();
    private List<String> wounds = new ArrayList<String>();
    private int reputation;
    private int fame;
    private int exp;
    private NeuroshimaEnum sickness;
    private NeuroshimaEnum sicknessCase;
    private String medicine;
    private String sex;
    private int medicines;
    private int food;
    private int water;

    private List<Stuff> inventory;
    private List<Weapon> weapons;
    private List<Ammo> ammo;
    private double  maximumLoad;

    JTextField nameTexField;
    JComboBox placeOfOriginList;

    public NeuroshimaCharacter() {
        inventory = new ArrayList<>();
    }

    public void generateSurname(){
        BufferedReader br = null;
        String[] characters = new String[1024];//just an example - you have to initialize it to be big enough to hold all the lines!

        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader("surnames.txt"));
            int rand = (int)((Math.random()*1000%200)+1);
            int i=0;
            while ((sCurrentLine = br.readLine()) != null) {
                String[] arr = sCurrentLine.split("\t");
                if(i == rand){
                    surname = arr[1];
                    System.out.println(surname);
                    break;
                }

                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void generateName(){
        BufferedReader br = null;
        String[] characters = new String[1024];//just an example - you have to initialize it to be big enough to hold all the lines!

        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader("names.txt"));
            int rand = (int)((Math.random()*1000%200)+1);
            int i=0;
            while ((sCurrentLine = br.readLine()) != null) {
                String[] arr = sCurrentLine.split("\t");
                if(Integer.parseInt(arr[0]) == rand){
                    if(sex.equals("male")){
                        name = arr[2];
                    }else{
                        name = arr[1];
                    }
                    System.out.println(name);
                    break;
                }

                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private String imputDialogString(String message, String errorMessage){
        boolean incorrect = true;
        String result = "dupa";
        while(incorrect){
            try{
                result = JOptionPane.showInputDialog(message);
                if(result.isEmpty()){
                    throw new Exception();
                }else
                {
                    incorrect = false;
                }
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,errorMessage);
            }
        }
        return result;
    }

    private int imputDialogInt(String message, String errorMessage){
        boolean incorrect = true;
        int result = 15001900;
        while(incorrect){
            try{
                result = Integer.parseInt(JOptionPane.showInputDialog(message));
                    incorrect = false;
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,errorMessage);
            }
        }
        return result;
    }

    private void saveNameAndPlaceOfOrigin(){
        String placeOfOriginName = placeOfOriginList.getSelectedItem().toString();
        name = nameTexField.getText();
        switch(placeOfOriginName){
            case "Man from not your fucking business":
                placeOfOrigin = NeuroshimaEnum.MAN_FROM_NOT_YOUR_FUCKING_BUSINESS;
                break;
            case "Detroit":
                placeOfOrigin = NeuroshimaEnum.DETROIT;
                break;
            case  "Appalachian Federation":
                placeOfOrigin = NeuroshimaEnum.APPALACHIAN_FEDERATION;
                break;
            case  "Desert Man":
                placeOfOrigin = NeuroshimaEnum.DESERT_MAN;
                break;
            case  "Miami":
                placeOfOrigin = NeuroshimaEnum.MIAMI;
                break;
            case  "Missisipi":
                placeOfOrigin = NeuroshimaEnum.MISSISIPI;
                break;
            case  "New York":
                placeOfOrigin = NeuroshimaEnum.NEW_YORK;
                break;
            case  "South Hegemony":
                placeOfOrigin = NeuroshimaEnum.SOUTH_HEGEMONY;
                break;
            case  "Sentinel":
                placeOfOrigin = NeuroshimaEnum.SENTINEL;
                break;
            case  "Salk Lake City":
                placeOfOrigin = NeuroshimaEnum.SALT_LAKE_CITY;
                break;
            case  "Texas":
                placeOfOrigin = NeuroshimaEnum.TEXAS;
                break;
            case  "Vegas":
                placeOfOrigin = NeuroshimaEnum.VEGAS;
                break;
        }
        System.out.println(placeOfOrigin);
    }

    private void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Character Creation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jPanel=new JPanel();
        //jPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 7));
        frame.setLayout(new GridLayout(7, 2));

        frame.add(new JLabel("Name: "));
        nameTexField = new JTextField(20);
        frame.add(nameTexField);

        frame.add(new JLabel("Place of origin:"));
        String[] placeOfOriginStrings = { "Man from not your fucking business", "Detroit", "Appalachian Federation", "Desert Man", "Miami", "Missisipi", "New York", "South Hegemony", "Sentinel", "Salk Lake City", "Texas", "Vegas" };
        placeOfOriginList = new JComboBox(placeOfOriginStrings);
        frame.add(placeOfOriginList);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveNameAndPlaceOfOrigin();
            }
        });
        frame.add(new JButton("Button 5"));
        frame.add(new JButton("Button 6"));
        frame.add(new JButton("Button 7"));
        frame.add(okButton);
        frame.pack();
        frame.setVisible(true);
    }

    void generateCharacter(){
        //name = imputDialogString("Name: ", "Fill this nigga ");

        createAndShowGUI();


//        Object[] possibilities = {"ham", "spam", "yam"};
//        String s = (String)JOptionPane.showInputDialog(null,
//                "Complete the sentence:\n"
//                        + "\"Green eggs and...\"",
//                "Customized Dialog",
//                JOptionPane.PLAIN_MESSAGE,
//                null,
//                possibilities,
//                "ham");
//
////If a string was returned, say so.
//        if ((s != null) && (s.length() > 0)) {
//            System.out.println("YAY");
//            return;
//        }
//
////If you're here, the return value was null/empty.
//        System.out.println("NOPE");
//
    }
}
