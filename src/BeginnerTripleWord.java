import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class BeginnerTripleWord extends BeginnerBoardTile {
    private static final long serialVersionUID = 1L;

    public BeginnerTripleWord(Point _p) {
	super(_p, Color.orange);
	drawText("TW");
    }

    public void drawText(String _text) {
	textLabel = new JLabel();
	textLabel.setVerticalAlignment(SwingConstants.CENTER);
	textLabel.setFont(font.deriveFont(Font.PLAIN, 16));
	this.add(textLabel);
    }
}