import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PokmanKeyListener implements KeyListener {

    private Pokman pokman;

    public PokmanKeyListener(Pokman pokman) {
        this.pokman = pokman;
    }

    public Pokman getPokman() {return this.pokman;}

    @Override
    public void keyTyped(KeyEvent e) {
       // System.out.println(e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
       // System.out.println(e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //System.out.println(e.getKeyChar());
        if(e.getKeyChar() == 's'){
            pokman.SetIntendedDirection(MovementDirection.S);
        }
        else if(e.getKeyChar() == 'd'){
            pokman.SetIntendedDirection(MovementDirection.E);
        }
        else if(e.getKeyChar() == 'w'){
            pokman.SetIntendedDirection(MovementDirection.N);
        }
        else if(e.getKeyChar() == 'a'){
            pokman.SetIntendedDirection(MovementDirection.W);
        }
    }
}
