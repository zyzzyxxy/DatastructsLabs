import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DrawCircle extends JPanel {

    public void paintComponents(ArrayList<CircleObject> cList) {
        Graphics g = this.getGraphics();
        for (CircleObject c : cList) {
            g.setColor(c.getColor());
            g.drawOval(20, 30, 75, 100);
            g.drawOval(150, 30, 100, 100);
        }

    }

}
