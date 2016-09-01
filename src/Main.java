/**
 * Created by Krzysio on 2016-09-01.
 */
public class Main {
    public static void main(String[] args) {
        int randomSize = (int)(Math.random()*1000%50);
        Board board = new Board(randomSize);
    }
}
