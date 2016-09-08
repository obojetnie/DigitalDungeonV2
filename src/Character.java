import javax.swing.*;

/**
 * Created by pieru on 08.09.2016.
 */
public class Character {
    private String name;
    private String player;
    private String playerClass;
    private String race;
    private String alignment;
    private String religion;
    private int level;
    private String playerSize;
    private int age;
    private String gender;
    private String height;
    private String weight;
    private String eyes;
    private String hair;

    //abilities:
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    private int healthPoints;
    private int armourPoints;
    private int initiative;

    private int attackFistPoints;
    private int attackDistancePoints;

    private Dice weaponDice;


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

    void generateDNDcharacter(){

        //name = imputDialogString("Name: ", "Fill this nigga ");
        /*name = JOptionPane.showInputDialog("Name: ");
        player = JOptionPane.showInputDialog("Player: ");
        playerClass = JOptionPane.showInputDialog("Class: ");
        race = JOptionPane.showInputDialog("Race: ");

        alignment = JOptionPane.showInputDialog("Alignment: ");
        religion = JOptionPane.showInputDialog("Religion: ");

        race = JOptionPane.showInputDialog("Race: ");*/

        level = imputDialogInt("Level: ", "EASY GEEZZY FILL THIS SHIT 0-1000000 RIGHT??");

        System.out.println(level);

    }
}
