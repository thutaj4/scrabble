import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Tray extends JPanel implements Cloneable{
    public ArrayList<TrayTile> tiles;
    private ArrayList<String> letters;
    private JLabel label;
    private JPanel flow;
    private JButton shuffle;
    private int score;
    public String playerName;

    public Tray(ArrayList<String> _letters, boolean _isP1, String _playerName) {
	tiles = new ArrayList<TrayTile>();
	flow = new JPanel();
	letters = _letters;
	playerName = _playerName;
	score = 0;
	label = new JLabel("Score: " + score + "   ");
	label.setVerticalAlignment(SwingConstants.CENTER);
	label.setAlignmentX(CENTER_ALIGNMENT);
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
	
	JLabel nameLabel = new JLabel(playerName + " ");
	add(nameLabel);
	add(label);
    }

    /******************** General Methods ********************/
    private void addSquares() {
	for (int k = 0; k < 7; k++) {
	    tiles.add(new TrayTile(new Point(0, k), this, letters.get(k)));
	}
	for (TrayTile t : tiles) {
	    flow.add(t);
	}
    }

    public void addTile(TrayTile tile) {
	System.out.println("adding tile in tray");
	tiles.add(tile);
	refresh();
    }

    public ArrayList<TrayTile> getTiles() {
	return tiles;
    }

    public void setLabel(int _score) {
	score = _score;
	label.setText("Score: " + score + "  ");
    }

    public void setActive(boolean active) {
	if (active) {
	    for (TrayTile t : tiles) {
		t.setColor(new Color(0xD7B288));
		t.setTileBorder(Color.GRAY);
	    }
	} else if (!active) {
	    refill();
	    for (TrayTile t : tiles) {
		t.setColor(new Color(0x362819));
		t.setTileBorder(Color.BLACK);
		flow.add(t);
	    }
	}
    }

    public void refill() {
	int size = tiles.size();
	for (int k = 0; k < 7 - size; k++) {
	    String newChar = Scrabble.s.bag.getLastChar();
	    if (newChar != null) {
		tiles.add(new TrayTile(new Point(0, k), this, newChar));
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
	for (TrayTile t : tiles) {
	    flow.add(t);
	}
	flow.repaint();
	flow.revalidate();
    }
    
    public String toString() {
	return "Tray: "+tiles;
    }
}