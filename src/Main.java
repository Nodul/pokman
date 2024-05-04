import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    private static Cell[][] map;

    public static void main(String[] args) {

        Init();
        RunLoop();
        // TODO Cleanup();
    }

    private static void Init(){

        // Draw main window
        var mainWindowFrame = new JFrame("Pokman");

        mainWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        var gridLayout = new GridBagLayout();

        mainWindowFrame.setLayout(gridLayout);
        mainWindowFrame.setLocationRelativeTo(null);

        // region DEBUG MAP
        // Prepare debug map
        map = new Cell[12][12];

        for(int y = 0; y < 12; y++){
            for(int x = 0; x < 12; x++){
                var isWall = x == 0 || y == 0 || x == 11 || y == 11;
                map[x][y] = new Cell(x,y,isWall);
            }
        }

        // Load test wall
        BufferedImage wall_sprite = null;
        BufferedImage floor_sprite = null;
        try {
            wall_sprite = ImageIO.read(new File("assets\\test_wall.png"));
            floor_sprite = ImageIO.read(new File("assets\\test_floor.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // endregion

        // Render initial map for prototyping purposes
        for(int y = 0; y < 12; y++){
            for(int x = 0; x < 12; x++){
                var cell = map[x][y];
                JLabel picLabel = makeLabel(cell, wall_sprite, floor_sprite);

                var constraints = new GridBagConstraints();

                constraints.gridx = x;
                constraints.gridy = y;
                constraints.gridwidth = 1;
                constraints.gridheight = 1;

                mainWindowFrame.add(picLabel, constraints);
            }
        }

        // Finalize
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

    private static void RunLoop(){

        var isRunning = true;

        while(isRunning) {

            ProcessInput();
            UpdateGameLogic();
            Render();
        }
    }

    private static void ProcessInput(){

    }

    private static void UpdateGameLogic(){

    }

    private  static void Render(){

    }
}