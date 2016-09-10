/**
 * Created by Krzysio on 2016-09-01.
 */
public class Main {
    public static void main(String[] args) {
        int randomSize = 50;
        //Board board = new Board(randomSize);
        //Dice dice = new Dice();
        //System.out.println(dice.roll_k100());
        NeuroshimaCharacter character = new NeuroshimaCharacter();
        character.generateCharacter();
        Dungeon d = new Dungeon();
        //d.createDungeon(50,50,20);

        //System.out.println(d.showDungeon());
    }
}
