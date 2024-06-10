public class Map {

    private final int width;
    private final int height;

    private Cell[][] cells;


    public Map(Cell[][] cells, int width, int height){
        this.cells = cells;
        this.width = width;
        this.height = height;
    }
    // TODO add Map(int x, int y) for generating maps

    public Cell GetAt(int x, int y){
        if(x < 0 || y < 0 || x > cells.length || y > cells[0].length){
            return null;
        }

        return cells[x][y];
    }

    public Cell GetAtWorld(int x, int y){
        // this.x * this.width + (this.width / 2) = z;
        // this.x = (z - (this.width / 2)) / width

        var mapX = (float)x / width;  // Math.ceilDiv(x, width);  //Math.floorDiv(x, width);//  x / width;
        var mapY = (float)y / height; // Math.ceilDiv(y, height); //Math.floorDiv(y, height); // y / height;

        return GetAt( Math.round(mapX), Math.round(mapY));
    }

    public  int GetCellWidth() { return width; }
    public  int GetCellHeight() { return height; }

    public static Map GenerateDebugMap(int width, int height){
        var cells = new Cell[36][36];

        for(int y = 0; y < 36; y++){
            for(int x = 0; x < 36; x++){
                var isWall = x == 0 || y == 0 || x == 35 || y == 35 || (y == 2 && (x >= 2 && x <= 27));
                cells[x][y] = new Cell(x,y,width,height,isWall);
            }
        }

        return new Map(cells, width, height);
    }
}
