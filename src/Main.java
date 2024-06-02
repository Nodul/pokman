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
        mainWindowFrame.setLocationRelativeTo(null);

        // Prepare debug map
        currentMap = Map.GenerateDebugMap();

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
        for(int y = 0; y < 12; y++){
            for(int x = 0; x < 12; x++){
                var cell = currentMap.GetAt(x,y);
                JLabel picLabel = makeLabel(cell, wall_sprite, floor_sprite);

                var constraints = new GridBagConstraints();

                constraints.gridx = x;
                constraints.gridy = y;
                constraints.gridwidth = 1;
                constraints.gridheight = 1;

                mapPanel.add(picLabel, constraints);
            }
        }

        // Render rest of stuff
        pokman = new Pokman(1,1);
        var cell = currentMap.GetAt((int)pokman.GetX(),(int)pokman.GetY());
        pokmanLabel = makeEntity(cell, pokman_test_sprite);

        mainWindowFrame.addKeyListener(new PokmanKeyListener(pokman));

        //mapPanel.add(pokmanLabel);
        mainWindowFrame.add(pokmanLabel);

        // Finalize
        mainWindowFrame.add(mapPanel);
        mainWindowFrame.pack();
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

    private static JLabel makeEntity(Cell cell, BufferedImage sprite){
        JLabel label= new JLabel();

        var icon = new ImageIcon(sprite);
        label.setIcon(icon);
        label.setOpaque(true);
        // DEBUG
         //label.setBorder(BorderFactory.createLineBorder(Color.cyan, 1));
         label.setBounds(cell.GetX() * 28, cell.GetY() * 32,28,32);
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
        var previousFrameBounds = pokmanLabel.getBounds();

        var direction = pokman.GetCurrentDirection();

        var pokmanBounds = pokmanLabel.getBounds();
        var OneZero = mapPanel.getComponentAt(1 * 28,0);
        var oneZeroBounds = OneZero.getBounds();

        var canMove = !pokmanLabel.getBounds().intersects(oneZeroBounds);


        if(canMove == false){
            pokman.SetCurrentDirection(MovementDirection.Stop);
        }

        if(direction == MovementDirection.E){
            pokmanLabel.setBounds(previousFrameBounds.x + 1, previousFrameBounds.y + 0, previousFrameBounds.width, previousFrameBounds.height);
        }
        else if(direction == MovementDirection.S){
            pokmanLabel.setBounds(previousFrameBounds.x + 0, previousFrameBounds.y + 1, previousFrameBounds.width, previousFrameBounds.height);
        }
        else if(direction == MovementDirection.N){
            pokmanLabel.setBounds(previousFrameBounds.x + 0, previousFrameBounds.y - 1, previousFrameBounds.width, previousFrameBounds.height);
        }
        else if(direction == MovementDirection.W){
            pokmanLabel.setBounds(previousFrameBounds.x - 1, previousFrameBounds.y + 0, previousFrameBounds.width, previousFrameBounds.height);
        }
    }

    private  static void Render(){
        // TODO move rendering stuff here
    }

    private static void Cleanup() {
        // TODO fillout
    }
}