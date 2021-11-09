import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class DoubleWord extends BoardTile {
    private static final long serialVersionUID = 1L;

public DoubleWord(Point _p) {
    super(_p, new Color(0xe75480));
    drawText("DW");
  }
  
  public void drawText(String _text) {
    textLabel = new JLabel();
    textLabel.setVerticalAlignment(SwingConstants.CENTER);
    textLabel.setFont(font.deriveFont(Font.PLAIN, 16));
    this.add(textLabel);
  }
}