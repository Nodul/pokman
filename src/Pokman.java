public class Pokman {
    private double x;
    private double y;
    private MovementDirection direction;

    public Pokman(double x, double y) {
        this.x = x;
        this.y = y;
        direction = MovementDirection.Stop;
    }

    public double GetX() {return this.x;}

    public double GetY() {return this.y;}

    public int GetXInt() {return (int)this.x;}

    public int GetYInt() {return (int)this.y;}

    public int GetXIntRound() {return (int)Math.round(this.x);}

    public int GetYIntRound() {return (int)Math.round(this.y);}

    public  MovementDirection GetCurrentDirection() {return this.direction;}

    public void SetCurrentDirection(MovementDirection direction) {this.direction = direction;}

    public void Translate(double x, double y) {
        this.x += x;
        this.y += y;
    }
}

