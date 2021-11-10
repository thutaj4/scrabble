import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ScorePanel extends JPanel implements Cloneable {
	private static final long serialVersionUID = 1L;
	private JButton p1, p2, p3, p4;
	private Tray p1Tray, p2Tray, p3Tray, p4Tray;
	public JButton shuffleTiles, sortTiles, RecallTiles, skipTurn, endGame;
//    private JPanel logoPanel;
//    private JLabel picLabel;

	public ScorePanel(Tray _p1, Tray _p2, Tray _p3, Tray _p4) {
		super(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
//	logoPanel = new JPanel();
		addButtons();
//	this.add(BorderLayout.CENTER, logoPanel);
		this.setBackground(new Color(0xD62D26));
		p2.setVisible(false);
		p3.setVisible(false);
		p4.setVisible(false);
		p1Tray = _p1;
		p2Tray = _p2;
		p3Tray = _p3;
		p4Tray = _p4;
//	addPicture();
	}

	public void addButtons() {
		p1 = new JButton("Play Turn");
		p1.setHorizontalAlignment(SwingConstants.LEFT);
		p2 = new JButton("Play Turn");
		p3 = new JButton("Play Turn");
		p4 = new JButton("Play Turn");

		this.add(BorderLayout.NORTH, p1);
		this.add(BorderLayout.SOUTH, p2);
		//this.add(BorderLayout.NORTH, p3);
		//this.add(BorderLayout.SOUTH, p4);
		this.add(BorderLayout.CENTER, createCenterPanel());
//	this.add(BorderLayout.CENTER, sortTiles);
//	this.add(BorderLayout.CENTER, RecallTiles);
//	this.add(BorderLayout.CENTER, skipTurn);
//	this.add(BorderLayout.CENTER, endGame);
		p1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow.se.setTurn("playerTwo");
			}
		});
		p2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(p3Tray != null) {
				MainWindow.se.setTurn("playerThree");
				}
				else {
					MainWindow.se.setTurn("playerOne");
				}
			}
		});
		p3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(p4Tray != null) {
					MainWindow.se.setTurn("playerFour");
					}
					else {
						MainWindow.se.setTurn("playerOne");
					}
			}
		});
		p4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow.se.setTurn("playerOne");
			}
		});
	}

	private JPanel createCenterPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);

		shuffleTiles = new JButton("Shuffle Tiles");
		shuffleTiles.setMargin(new Insets(2, 5, 2, 14));
		shuffleTiles.setHorizontalAlignment(SwingConstants.LEFT);

		shuffleTiles.setBounds(0, 24, 444, 23);
		panel.add(shuffleTiles);

		/*
		 * sortTiles = new JButton("Sort Tiles"); sortTiles.setMargin(new Insets(2, 5,
		 * 2, 14)); sortTiles.setHorizontalAlignment(SwingConstants.LEFT);
		 * sortTiles.setBounds(0, 68, 444, 23); panel.add(sortTiles);
		 */

		RecallTiles = new JButton("Recall Tile");
		RecallTiles.setMargin(new Insets(2, 5, 2, 14));
		RecallTiles.setHorizontalAlignment(SwingConstants.LEFT);
		RecallTiles.setBounds(0, 117, 444, 23);
		panel.add(RecallTiles);

		skipTurn = new JButton("Skip Turn");
		skipTurn.setMargin(new Insets(2, 5, 2, 14));
		skipTurn.setHorizontalAlignment(SwingConstants.LEFT);
		skipTurn.setBounds(0, 168, 444, 23);
		panel.add(skipTurn);

		endGame = new JButton("End Game");
		endGame.setMargin(new Insets(2, 5, 2, 14));
		endGame.setHorizontalAlignment(SwingConstants.LEFT);
		endGame.setBounds(0, 214, 444, 23);
		panel.add(endGame);

		return panel;
	}

//    public void addPicture() {
//	try {
//	    Image logo = ImageIO.read(new File("resources/logo1.png"));
//	    picLabel = new JLabel(new ImageIcon(logo));
//	    logoPanel.add(picLabel);
//	} catch (IOException e) {
//	}
//	logoPanel.setLayout(new GridBagLayout());
//	picLabel.setVerticalAlignment(SwingConstants.CENTER);
//    }

	public void setButton(String _turn) {
		if (_turn.equals("playerOne")) {
			p1.setVisible(true);
			p2.setVisible(false);
			p3.setVisible(false);
			p4.setVisible(false);
		} else if (_turn.equals("playerTwo")) {
			p1.setVisible(false);
			p2.setVisible(true);
			p3.setVisible(false);
			p4.setVisible(false);
		}
		else if (_turn.equals("playerThree")) {
			p1.setVisible(true);
			p2.setVisible(false);
			p3.setVisible(false);
			p4.setVisible(false);
		}
		else if (_turn.equals("playerFour")) {
			p1.setVisible(false);
			p2.setVisible(true);
			p3.setVisible(false);
			p4.setVisible(false);
		}
		
	}
}
