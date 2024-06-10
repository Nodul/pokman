import javax.swing.*;

public class Pokman {
    private int x;
    private int y;
    private int width;
    private int height;
    private MovementDirection direction;

    public Pokman(int x, int y, int width, int height) {
        this.x = x - width / 2;
        this.y = y - height / 2;
        this.width = width;
        this.height = height;
        direction = MovementDirection.Stop;
    }

    public int GetX() {return this.x;}

    public int GetY() {return this.y;}

    public int GetWidth() {return this.width;}
    public int GetHeight() {return this.height;}

    public  MovementDirection GetCurrentDirection() {return this.direction;}

    public void SetCurrentDirection(MovementDirection direction) {this.direction = direction;}

    public void Translate(int x, int y) {
        this.x += x;
        this.y += y;
    }
}

