import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;

public class BoardTile extends Tile implements Cloneable{
    private static final long serialVersionUID = 1L;

    public BoardTile(Point _p, Color _c) {
	super(_p, " ", _c, Color.BLACK);
    }

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {
	if (Scrabble.s.activeTile != null)
	    Scrabble.s.placeTile(this);
    }
    
    public void mouseReleased(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {}

    @Override
    public String toString() {
	return "BoardTile: " + point.getX() + "," + point.getY() + ";";
    }
}