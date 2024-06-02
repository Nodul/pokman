public class Cell {
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private boolean isWall;

    public Cell(int x, int y, int width, int height, boolean isWall){
        this.x = x;
        this.y= y;
        this.width = width;
        this.height = height;
        this.isWall = isWall;
    }

    public int GetX() {return this.x;}

    public  int GetY() {return this.y;}

    public  int GetWorldX() {return this.x * this.width + (this.width / 2);}

    public  int GetWorldY() {return this.y * this.height + (this.height / 2);}

    public  boolean GetIsWall() {return this.isWall;}
}
