import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pieru on 08.09.2016.
 */
public class NeuroshimaCharacter extends Character {
    private static NeuroshimaEnum placeOfOrigin;
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

    private int medicines;
    private int food;
    private int water;

    static JTextField nameTexField;
    static JComboBox placeOfOriginList;


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

    private static void saveNameAndPlaceOfOrigin(){
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
                break;
            case  "Desert Man":
                break;
            case  "Miami":
                break;
            case  "Missisipi":
                break;
            case  "New York":
                break;
            case  "South Hegemony":
                break;
            case  "Sentinel":
                break;
            case  "Salk Lake City":
                break;
            case  "Texas":
                break;
            case  "Vegas":
                break;
        }
    }

    private static void createAndShowGUI() {
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
