import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Point;
import java.awt.Color;
import java.awt.Font;

public class TripleWord extends BoardTile {
    private static final long serialVersionUID = 1L;

    public TripleWord(Point _p) {
	super(_p, new Color(0xFF6933));
	drawText("TW");
    }

    public void drawText(String _text) {
	textLabel = new JLabel(_text);
	textLabel.setVerticalAlignment(SwingConstants.CENTER);
	textLabel.setFont(font.deriveFont(Font.PLAIN, 16));
	this.add(textLabel);
    }
}