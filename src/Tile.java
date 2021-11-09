import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Tile extends JPanel implements MouseListener, Cloneable {
  private static final long serialVersionUID = 1L;
  private File scrabbleFont;
  public Font font;
  public Point point;
  private Color color, borderColor;
  public JLabel textLabel;
  private String text;
  
  public Tile(Point _point, String _text, Color _color, Color _borderColor) {
    point = _point;
    color = _color;
    text = _text;
    borderColor = _borderColor;
    // fontstuffs
    try {
      scrabbleFont = new File("resources/scrabble.ttf");
      font = Font.createFont(Font.TRUETYPE_FONT, scrabbleFont);
      font = new Font("default", Font.PLAIN, 16);
    } catch (Exception e) {
      font = new Font("default", Font.PLAIN, 16);
    }
    // GUI
    setLayout(new GridBagLayout());
    addMouseListener(this);
    setPreferredSize(new Dimension(50, 50));
    setTileBorder(borderColor);
  }
  
  public void drawText(String _text, int _val) {
    textLabel = new JLabel("<html><font size=6>" + _text
                             + "</font><font size=5><sub>" + _val + "</sub></font></html>");
    textLabel.setVerticalAlignment(SwingConstants.CENTER);
    textLabel.setFont(font.deriveFont(Font.PLAIN, 25));
    this.add(textLabel);
  }
  
  /******************** Set methods ************************/
  public void setColor(Color _color) {
    color = _color;
  }
  
  public void setText(String _text) {
    textLabel.setText(_text);
    text = _text;
  }
  
  public void setLoc(int x, int y) {
    point.setLocation(x, y);
  }
  
  public void setBoardText(String _text) {
    textLabel = new JLabel(_text);
    textLabel.setFont(font.deriveFont(Font.PLAIN, 15));
    textLabel.setText(_text);
  }
  
  public void setTileBorder(Color _c) {
    borderColor = _c;
    this.setBorder(BorderFactory.createLineBorder(borderColor, 2));
  }
  
  /*********************** Get methods ***********************/
  public int getXLoc() {
    return (int) point.getX();
  }
  
  public int getYLoc() {
    return (int) point.getY();
  }
  
  public Point getLoc() {
      return point;
  }
  public String getText() {
    return text;
  }
  
  public int getVal() {
    return 0;
  }
  /********************** Mouse Listeners ********************/
  public void mouseEntered(MouseEvent e) {
  }
  
  public void mouseExited(MouseEvent e) {
  }
  
  public void mousePressed(MouseEvent e) {
    System.out.println("Point (" + point.getX() + "," + point.getY() + ")");
  }
  
  public void mouseReleased(MouseEvent e) {
  }
  
  public void mouseClicked(MouseEvent e) {
  }
  
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(color);
    g.fillRect(0, 0, this.getWidth(), this.getHeight());
    g.setFont(new Font("", Font.PLAIN, 14));
    g.setColor(Color.BLACK);
  }
}