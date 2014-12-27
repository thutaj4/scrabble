import java.awt.Point;
import java.awt.Color;
import java.awt.event.MouseEvent;

public class TrayTile extends Tile {

    private static final long serialVersionUID = 1L;
    public Tray t;
    private String letter;
    private int val;
    private Point p;

    public TrayTile(Point _p, Tray _t, String _letter) {
	super(_p, _letter, new Color(0xD7B288), Color.GRAY);
	t = _t;
	p = _p;
	letter = _letter;
	val = setVal(letter);
	drawText(letter, val);
    }

    public int setVal(String l) {
	if (l == "A" || l == "E" || l == "I" || l == "O" || l == "N"
		|| l == "R" || l == "T" || l == "L" || l == "S" || l == "U")
	    return 1;
	else if (l == "D" || l == "G")
	    return 2;
	else if (l == "B" || l == "C" || l == "M" || l == "P")
	    return 3;
	else if (l == "F" || l == "H" || l == "V" || l == "W" || l == "Y")
	    return 4;
	else if (l == "K")
	    return 5;
	else if (l == "J" || l == "X")
	    return 8;
	else if (l == "Q" || l == "Z")
	    return 10;
	return 0;
    }

    public String getText() {
	return letter;
    }

    @Override
    public int getVal() {
	return val;
    }

    @Override
    public void mousePressed(MouseEvent e) {
	System.out.println(p);
	if (Scrabble.s.activeTile == this) {
	    Scrabble.s.setActiveTile(null);
	    return;
	}
	if (Scrabble.s.inCurrentTiles(this))
	    Scrabble.s.setActiveTile(this);
    }

    public String toString() {
	return "Char:" + letter + "(" + getXLoc() + "," + getYLoc() + ")";
    }
}