import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BeginnerTray extends JPanel implements Cloneable{
    public ArrayList<BeginnerTrayTile> tiles;
    private ArrayList<String> letters;
    private JLabel label;
    private JPanel flow;
    private JButton shuffle;
    private int score;
    public String playerName;

    public BeginnerTray(ArrayList<String> _letters, boolean _isP1, String _playerName) {
	tiles = new ArrayList<BeginnerTrayTile>();
	flow = new JPanel();
	letters = _letters;
	playerName = _playerName;
	score = 0;
	label = new JLabel("Score: " + score + "   ");
	label.setVerticalAlignment(SwingConstants.CENTER);
	label.setAlignmentX(CENTER_ALIGNMENT);
	label.setFont(new Font("default", Font.BOLD, 20));
	addSquares();
//	shuffle = new JButton("Shuffle Tiles");
//	shuffle.setBounds(new Rectangle(0, 0, 100, 0));
	setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
	addBorderLayoutObjects();
//	shuffle.addActionListener(new ActionListener() {
//	    public void actionPerformed(ActionEvent e) {
//		shuffle();
////		System.out.println("undoStack: " + Scrabble.s.getUndoStack().toString());
//	    }
//	});
//    }
    }

    public void addBorderLayoutObjects() {
	setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
//	add(shuffle);
	add(flow);
	
	JLabel nameLabel = new JLabel(playerName + "  |  ");
	nameLabel.setFont(new Font("default", Font.BOLD, 20));
	add(nameLabel);
	add(label);
    }

    /******************** General Methods ********************/
    private void addSquares() {
	for (int k = 0; k < 7; k++) {
	    tiles.add(new BeginnerTrayTile(new Point(0, k), this, letters.get(k)));
	}
	for (BeginnerTrayTile t : tiles) {
	    flow.add(t);
	}
    }

    public void addTile(BeginnerTrayTile tile) {
	System.out.println("adding tile in tray");
	tiles.add(tile);
	refresh();
    }

    public ArrayList<BeginnerTrayTile> getTiles() {
	return tiles;
    }

    public void setLabel(int _score) {
	score = _score;
	label.setText("Score: " + score + "  ");
    }

    public void setActive(boolean active) {
	if (active) {
	    for (BeginnerTrayTile t : tiles) {
		t.setColor(new Color(0xD7B288));
		t.setTileBorder(Color.GRAY);
	    }
	} else if (!active) {
	    refill();
	    for (BeginnerTrayTile t : tiles) {
		t.setColor(new Color(0x362819));
		t.setTileBorder(Color.BLACK);
		flow.add(t);
	    }
	}
    }

    public void refill() {
	int size = tiles.size();
	for (int k = 0; k < 7 - size; k++) {
	    String newChar = BeginnerScrabble.s.bag.getLastChar();
	    if (newChar != null) {
		tiles.add(new BeginnerTrayTile(new Point(0, k), this, newChar));
	    }
	}
    }

    public void shuffle() {
	Collections.shuffle(tiles);
	for (Tile t : tiles) {
	    flow.add(t);
	}
	revalidate();
    }

    public void refresh() {
	flow.removeAll();
	for (BeginnerTrayTile t : tiles) {
	    flow.add(t);
	}
	flow.repaint();
	flow.revalidate();
    }
    
    public String toString() {
	return "Tray: "+tiles;
    }
}