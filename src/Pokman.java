public class Pokman {
    private int x;
    private int y;
    private MovementDirection direction;

    public Pokman(int x, int y) {
        this.x = x;
        this.y = y;
        direction = MovementDirection.E;
    }

    public int GetX() {return this.x;}

    public  int GetY() {return this.y;}

    public  MovementDirection GetCurrentDirection() {return this.direction;}

    public void SetCurrentDirection(MovementDirection direction) {this.direction = direction;}
}

