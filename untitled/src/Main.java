import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {

    static ArrayList<CircleObject> circles;

    public static void main(String[] args) {
        circles=new ArrayList<>();
        JFrame frame = new JFrame();
        frame.setSize(800,800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        DrawCircle label = new DrawCircle();
        label.setBackground(Color.black);
        label.setSize(800,800);
        System.out.println(label.isVisible());
        frame.getContentPane().add(label);
        label.paintComponents();
    }

    public static void makeCircles() {
        circles.add(new CircleObject(5,5,new Position(50,50)));
        circles.add(new CircleObject(5,5,new Position(200,50)));
    }
}
