import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    private static Map currentMap;
    private static Pokman pokman;
    private static JLabel pokmanLabel;
    private static JPanel mapPanel;
    private static int cellWidth = 16;
    private static int cellHeight = 16;

    public static void main(String[] args) throws InterruptedException {
        Init();
        RunLoop();
        Cleanup();
    }

    private static void Init(){

        // Draw main window
        var mainWindowFrame = new JFrame("Pokman");
        mapPanel = new JPanel();

        mainWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        var gridLayout = new GridBagLayout();

        mapPanel.setLayout(gridLayout);

        // Prepare debug map
        currentMap = Map.GenerateDebugMap(cellWidth, cellHeight);

        // Load game data TODO extract into separate class
        // Load test wall
        BufferedImage wall_sprite = null;
        BufferedImage floor_sprite = null;
        BufferedImage pokman_test_sprite = null;
        try {
            wall_sprite = ImageIO.read(new File("assets\\test_wall.png"));
            floor_sprite = ImageIO.read(new File("assets\\test_floor.png"));
            pokman_test_sprite = ImageIO.read(new File("assets\\Pacman\\32\\Pacman\\Right_2.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Render initial map for prototyping purposes
        for(int y = 0; y < 36; y++){
            for(int x = 0; x < 36; x++){
                var cell = currentMap.GetAt(x,y);
                JLabel picLabel = makeLabel(cell, wall_sprite, floor_sprite);
                picLabel.setPreferredSize(new Dimension(cellWidth, cellHeight));
                //picLabel.setSize(cellWidth, cellHeight);
                var constraints = new GridBagConstraints();

                constraints.gridx = x;
                constraints.gridy = y;
                constraints.gridwidth = 1;
                constraints.gridheight = 1;

                mapPanel.add(picLabel, constraints);
            }
        }

        // Render rest of stuff
        var pokmanStartCell = currentMap.GetAt(1,1);
        pokman = new Pokman(pokmanStartCell.GetWorldX(), pokmanStartCell.GetWorldY(),30,30);
        pokmanLabel = makeEntity(pokman, pokman_test_sprite);

        mainWindowFrame.addKeyListener(new PokmanKeyListener(pokman));

        //mapPanel.add(pokmanLabel);
        mainWindowFrame.add(pokmanLabel);

        // Finalize
        mainWindowFrame.add(mapPanel);
        mainWindowFrame.pack();
        mainWindowFrame.setLocationRelativeTo(null);
        mainWindowFrame.setVisible(true);
    }

    private static JLabel makeLabel(Cell cell, BufferedImage wall_sprite, BufferedImage floor_sprite){
        JLabel label= new JLabel();

        var icon = new ImageIcon(cell.GetIsWall() ? wall_sprite : floor_sprite);
        label.setIcon(icon);
        label.setOpaque(true);
        // DEBUG
        // label.setBorder(BorderFactory.createLineBorder(Color.cyan, 1));
        return label;
    }

    private static JLabel makeEntity(Pokman pokman, BufferedImage sprite){
        JLabel label= new JLabel();

        var icon = new ImageIcon(sprite);
        label.setIcon(icon);
        label.setOpaque(true);
        // DEBUG
         //label.setBorder(BorderFactory.createLineBorder(Color.cyan, 1));
         label.setBounds( pokman.GetX(), pokman.GetY(), pokman.GetWidth(), pokman.GetHeight() );
        return label;
    }

    private static void RunLoop() throws InterruptedException {

        var isRunning = true;

        while(isRunning) {
            ProcessInput();
            UpdateGameLogic();
            Render();

            // TODO check time between frames, in order to make it as close to constant as possible
            // Limit the game loop to 60fps
            var frameDuration = (1d / 60d) * 1000d;
           // System.out.println(frameDuration);
            Thread.sleep((long) frameDuration);
        }
    }

    private static void ProcessInput(){
        // TODO move stuff here
    }

    private static void UpdateGameLogic() throws InterruptedException {
        // Check if Entity wants to move at all
        var currentDirection = pokman.GetCurrentDirection();
        if(pokman.GetCurrentDirection() == MovementDirection.Stop)
            return;

        // If yes, check if it can actually move
        Cell intendedCell = switch(currentDirection){
            case N -> currentMap.GetAtWorld(pokman.GetX(), pokman.GetY() - currentMap.GetCellHeight());
            case E -> currentMap.GetAtWorld(pokman.GetX() + currentMap.GetCellWidth(), pokman.GetY());
            case S -> currentMap.GetAtWorld(pokman.GetX(), pokman.GetY() + currentMap.GetCellHeight());
            case W -> currentMap.GetAtWorld(pokman.GetX() - currentMap.GetCellWidth(), pokman.GetY());
            case Stop -> null; // placeholder, should never enter this point
        };

        if(intendedCell == null){
            // Out of map bounds, we can safely skip
            return;
        }

        if(intendedCell.GetIsWall()){
            // Cannot move into wall
            pokman.SetCurrentDirection(MovementDirection.Stop);
            return;
        }

        switch(currentDirection){
            case N -> pokman.Translate(0,-1);
            case E -> pokman.Translate(1,0);
            case S -> pokman.Translate(0,1);
            case W -> pokman.Translate(-1,0);
        };

        // Update UI pos
        var currentFrameBounds = pokmanLabel.getBounds();
        pokmanLabel.setBounds((int)pokman.GetX(), (int)pokman.GetY(), currentFrameBounds.width, currentFrameBounds.height);
    }

    private  static void Render(){
        // TODO move rendering stuff here
    }

    private static void Cleanup() {
        // TODO fillout
    }
}