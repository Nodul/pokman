public class Cell {
    private int x;
    private int y;
    private boolean isWall;

    public Cell(int x, int y, boolean isWall){
        this.x = x;
        this.y= y;
        this.isWall = isWall;
    }

    public int GetX() {return this.x;}

    public  int GetY() {return this.y;}

    public  boolean GetIsWall() {return this.isWall;}
}
