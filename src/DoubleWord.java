import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Point;
import java.awt.Color;
import java.awt.Font;

public class DoubleWord extends BoardTile {
    private static final long serialVersionUID = 1L;

public DoubleWord(Point _p) {
    super(_p, new Color(0xFFC4B2));
    drawText("DW");
  }
  
  public void drawText(String _text) {
    textLabel = new JLabel(_text);
    textLabel.setVerticalAlignment(SwingConstants.CENTER);
    textLabel.setFont(font.deriveFont(Font.PLAIN, 16));
    this.add(textLabel);
  }
}