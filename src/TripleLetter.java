import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Point;
import java.awt.Color;
import java.awt.Font;

public class TripleLetter extends BoardTile {
    private static final long serialVersionUID = 1L;

    public TripleLetter(Point _p) {
	super(_p, new Color(0x3E9EB4));
	drawText("TL");
    }

    public void drawText(String _text) {
	textLabel = new JLabel(_text);
	textLabel.setVerticalAlignment(SwingConstants.CENTER);
	textLabel.setFont(font.deriveFont(Font.PLAIN, 16));
	this.add(textLabel);
    }
}