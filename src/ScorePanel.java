import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

public class ScorePanel extends JPanel implements Cloneable{
    private static final long serialVersionUID = 1L;
    private JButton p1, p2;
    private JPanel logoPanel;
    private JLabel picLabel;

    public ScorePanel() {
	super(new BorderLayout());
	setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
	logoPanel = new JPanel();
	addButtons();
	this.add(BorderLayout.CENTER, logoPanel);
	this.setBackground(new Color(0xD62D26));
	p2.setVisible(false);
	addPicture();
    }

    public void addButtons() {
	p1 = new JButton("Play Turn");
	p2 = new JButton("Play Turn");
	this.add(BorderLayout.NORTH, p1);
	this.add(BorderLayout.SOUTH, p2);
	p1.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		Scrabble.s.setTurn(Scrabble.TRAY2);
	    }
	});
	p2.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		Scrabble.s.setTurn(Scrabble.TRAY1);
	    }
	});
    }

    public void addPicture() {
	try {
	    Image logo = ImageIO.read(new File("resources/logo1.png"));
	    picLabel = new JLabel(new ImageIcon(logo));
	    logoPanel.add(picLabel);
	} catch (IOException e) {
	}
	logoPanel.setLayout(new GridBagLayout());
	picLabel.setVerticalAlignment(SwingConstants.CENTER);
    }

    public void setButton(boolean _turn) {
	p1.setVisible(_turn);
	p2.setVisible(!_turn);
    }
}
