import javax.imageio.ImageIO;
import javax.swing.*;
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
        var mainFrame = new JFrame("Pokman");

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainFrame.setSize(600, 500);
        mainFrame.setLocationRelativeTo(null);

        mainFrame.setVisible(true);

        var grid = new GridLayout(12,12);//(12,12,0,0);
        var constraints = new GridBagConstraints();
        mainFrame.setLayout(grid);

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

        // Render initial map for prototyping purposes

        for(int y = 0; y < 12; y++){
            for(int x = 0; x < 12; x++){
                var cell = map[x][y];
                // var image = cell.GetIsWall() ? "X" : " "; // TODO after loading test wall, render it instead of the "X" or " "

                JPanel  cellPanel = new JPanel();

               // cellPanel.setBounds(x,y,32,32);

                var icon = new ImageIcon(cell.GetIsWall() ? wall_sprite : floor_sprite);
                JLabel picLabel = new JLabel(icon);
               // picLabel.setMaximumSize(new Dimension(32,32));// setSize(32,32);
               // grid.
                //picLabel.setBounds(x,y,32,32);
                picLabel.setVisible(true);
               // cellPanel.setLocation(x,y);
                constraints.gridx = 0;
                constraints.gridy = 0;
                constraints.gridwidth = x;
                constraints.gridheight = y;
                constraints.fill = GridBagConstraints.CENTER;

                cellPanel.add(picLabel);//,constraints);
                mainFrame.add(cellPanel);//,x,y);
            }
        }
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