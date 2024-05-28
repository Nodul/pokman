public class Map {

    private Cell[][] cells;

    public Map(Cell[][] cells){
        this.cells = cells;
    }
    // TODO add Map(int x, int y) for generating maps

    public Cell GetAt(int x, int y){
        // TODO add validation
        return cells[x][y];
    }

    public static Map GenerateDebugMap(){
        var cells = new Cell[12][12];

        for(int y = 0; y < 12; y++){
            for(int x = 0; x < 12; x++){
                var isWall = x == 0 || y == 0 || x == 11 || y == 11;
                cells[x][y] = new Cell(x,y,isWall);
            }
        }

        return new Map(cells);
    }
}
