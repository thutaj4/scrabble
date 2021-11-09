import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;

public class BeginnerBoardTile extends Tile implements Cloneable{
    private static final long serialVersionUID = 1L;

    public BeginnerBoardTile(Point _p, Color _c) {
	super(_p, " ", _c, Color.BLACK);
    }

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {
	if (BeginnerScrabble.s.activeTile != null)
	    BeginnerScrabble.s.placeTile(this);
    }
    
    public void mouseReleased(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {}

    @Override
    public String toString() {
	return "BeginnerBoardTile: " + point.getX() + "," + point.getY() + ";";
    }
}