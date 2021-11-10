import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class DoubleLetter extends BoardTile {
    private static final long serialVersionUID = 1L;

    public DoubleLetter(Point _p) {
	super(_p, Color.CYAN);
	drawText("DL");
    }

    public void drawText(String _text) {
	textLabel = new JLabel(_text);
	textLabel.setVerticalAlignment(SwingConstants.CENTER);
	textLabel.setFont(font.deriveFont(Font.PLAIN, 16));
	this.add(textLabel);
    }
}