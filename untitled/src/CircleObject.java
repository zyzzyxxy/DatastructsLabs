import java.awt.*;
import java.util.Random;

public class CircleObject {
    int speed, weight, radius;
    Position position;
    Position direction;
    Random rnd = new Random();
    Color color;

    public CircleObject(int speed, int radius, Position position, Color color) {

        this.speed = speed;
        this.radius = radius;
        this.position = position;
        this.color = color;
        randomizeDirection();
    }
    public void randomizeDirection() {
        direction = new Position(rnd.nextInt(10)-5,rnd.nextInt(10)-5);
    }

    public Color getColor() {
        return color;
    }
}
