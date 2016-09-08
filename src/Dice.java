/**
 * Created by pieru on 08.09.2016.
 */
public class Dice {
    private int k6;
    private int k4;
    private int k8;
    private int k12;
    private int k100;
    private int k10;
    private int k20;

    int roll_k4(){
        return (int) ((Math.random() * 1000 % 4) + 1);
    }

    int roll_k6(){
        return (int) ((Math.random() * 1000 % 6) + 1);
    }

    int roll_k8(){
        return (int) ((Math.random() * 1000 % 8) + 1);
    }

    int roll_k10(){
        return (int) ((Math.random() * 1000 % 10) + 1);
    }

    int roll_k12(){
        return (int) ((Math.random() * 1000 % 12) + 1);
    }

    int roll_k20(){
        return (int) ((Math.random() * 1000 % 20) + 1);
    }

    int roll_k100(){
        int result = (int) (Math.random() * 1000 % 10);
        return result*10;
    }
}
