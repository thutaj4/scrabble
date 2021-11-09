import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.border.BevelBorder;

public class StatusBar extends JLabel {

    private static final long serialVersionUID = 1L;
    private int n;
    private String player;

    public StatusBar(int _n) {
	super();
	n = _n;
	super.setPreferredSize(new Dimension(100, 20));
	setBorder(new BevelBorder(BevelBorder.LOWERED));
	setPlayer();
	setMessage();
    }

    public void setMessage() {
	setPlayer();
	setText(n + " tiles left in the bag, " + player + "'s turn. "
		+ Scrabble.s.tilesPlaced + " tiles played this turn.");
    }

    public void setPlayer() {
	if (Scrabble.currentTurn.equals("playerOne")) {
	    player = "playerOne";
	}
	else if (Scrabble.currentTurn.equals("playerTwo")) {
	    player = "playerTwo";
	}
	else if (Scrabble.currentTurn.equals("playerThree")) {
	    player = "playerThree";
	}
	else if (Scrabble.currentTurn.equals("playerFour")) {
	    player = "playerFour";
	}
    }

    public void setBagCount(int _n) {
	n = _n;
    }

    public String getMessage() {
	return "Tiles: " + n + ", " + player;
    }
}