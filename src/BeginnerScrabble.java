import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Pattern;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BeginnerScrabble extends JFrame implements java.io.Serializable, Runnable {
	public static BeginnerScrabble s;
	public static SmallBoard board;
	public String playerName1;
	public String playerName2;
	private JPanel bottom;
	public static Bag bag;
	public BeginnerTray p1, p2;

	private BeginnerScorePanel scorePanel;
	public BeginnerTrayTile activeTile;

	public static int tilesPlaced, score1, score2;
	public static boolean isVert, isFirstTurn, isSaved, dictEnabled, playerOneTurn, playerTwoTurn, playerThreeTurn,
			playerFourTurn;

	public static String currentTurn;
	private ArrayList<BeginnerTrayTile> currentTiles, tilesPlayed;
	private Stack<BeginnerGameState> undoStack, redoStack;
	private Set<String> dict;

	public BeginnerScrabble(String name1, String name2) {
		playerName1 = name1;
		playerName2 = name2;
		board = new SmallBoard();
		bottom = new JPanel(new BorderLayout());
		bag = new Bag();
		p1 = new BeginnerTray(bag.getNewTray(), true, playerName1);
		p2 = new BeginnerTray(bag.getNewTray(), false, playerName2);
		// totalPlayers = 2;

		tilesPlaced = 0;
		score1 = 0;
		score2 = 0;
		scorePanel = new BeginnerScorePanel(p1, p2);

		tilesPlayed = new ArrayList<BeginnerTrayTile>();
		// Dictionary
		makeDictionary();

		setUndoStack(new Stack<BeginnerGameState>());
		redoStack = new Stack<BeginnerGameState>();
		// Logic
		currentTiles = new ArrayList<BeginnerTrayTile>();
		isFirstTurn = true;
		isSaved = true;
		dictEnabled = true;
		setTurn("playerOne");
		addBorderLayoutObjects();
	}

	public void run() {
		makeSettings();
//	makeFileMenu();
		makeControlsMenu();
		makeEditMenu();
		setMinimumSize(new Dimension(600, 700));
		setSize(700, 800);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void makeSettings() {
		setSize(948, 1030);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// this.setResizable(false);
	}

	private void addBorderLayoutObjects() {
		bottom.add(BorderLayout.CENTER, p2);
//	bottom.add(BorderLayout.SOUTH, statusBar);
		getContentPane().add(BorderLayout.CENTER, board);
		getContentPane().add(BorderLayout.WEST, scorePanel);
		getContentPane().add(BorderLayout.NORTH, p1);
		getContentPane().add(BorderLayout.SOUTH, bottom);
	}

	private void removeBorderLayoutObjects() {
		bottom.remove(p2);
//	bottom.remove(statusBar);
		getContentPane().remove(board);
		getContentPane().remove(scorePanel);
		getContentPane().remove(p1);
		getContentPane().remove(bottom);
	}

	private void makeDictionary() {
		dict = new HashSet<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("resources/dict.txt")));
			String line = br.readLine();
			while (line != null) {
				dict.add(line);
				line = br.readLine();
			}
			br.close();
		} catch (java.io.FileNotFoundException e) {
		} catch (java.io.IOException e) {
		}
	}

	private void makeControlsMenu() {
		scorePanel.shuffleTiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentTurn.equals("playerOne"))
					p1.shuffle();
				else if (currentTurn.equals("playerTwo"))
					p2.shuffle();
			}
		});

		scorePanel.RecallTiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (activeTile != null && activeTile.t == null) {
					if (currentTurn.equals("playerOne")) {
						BeginnerTrayTile temp = new BeginnerTrayTile(new Point(0, p1.tiles.size()), p1, activeTile.getText());
						p1.addTile(temp);
						currentTiles.add(temp);
					} else if (currentTurn.equals("playerTwo")) {
						BeginnerTrayTile temp = new BeginnerTrayTile(new Point(0, p2.tiles.size()), p2, activeTile.getText());
						p2.addTile(temp);
						currentTiles.add(temp);
					}
					tilesPlaced--;
					board.removeTile(activeTile.getXLoc(), activeTile.getYLoc());
					currentTiles.remove(activeTile);
					activeTile = null;
					tilesPlaced--;
				} else {
					Toolkit.getDefaultToolkit().beep();
				}
			}
		});
		scorePanel.skipTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentTurn.equals("playerOne")) {
					setTurn("playerTwo");
				} else if (currentTurn.equals("playerTwo")) {
					setTurn("playerOne");

				}
			}
		});
		scorePanel.endGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int decision = JOptionPane.showConfirmDialog(null, "Are you sure you want to end the game?", "End Game",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (decision == JOptionPane.YES_OPTION) {
					if (score1 > score2)
						JOptionPane.showMessageDialog(null, "Player 1 wins!");
					else if (score1 < score2)
						JOptionPane.showMessageDialog(null, "Player 2 wins!");
					else
						JOptionPane.showMessageDialog(null, "Its a Tie!");
					makeNewGame();
				}
				// Toolkit.getDefaultToolkit().beep();
			}
		});

	}

	private void makeEditMenu() {
		JMenu editMenu = new JMenu("Edit");
//	mbar.add(editMenu);
		// Undo and redo are unimplemented
		JMenuItem undoItem = new JMenuItem("Undo Turn");
		JMenuItem redoItem = new JMenuItem("Redo Turn");
		JCheckBoxMenuItem dictItem = new JCheckBoxMenuItem("Enable Dict.", dictEnabled);
		editMenu.add(undoItem);
		editMenu.add(redoItem);
		editMenu.addSeparator();
		editMenu.add(dictItem);

		undoItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Unimplemented
			}
		});
		redoItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Unimplemented
			}
		});

		dictItem.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				System.out.println("enabling/disabling dict: " + dictEnabled);
				dictEnabled = !dictEnabled;
				System.out.println("dict is now: " + dictEnabled);
			}
		});
	}
	
	public Stack<BeginnerGameState> getUndoStack() {
		return undoStack;
	}
	
	public void setUndoStack(Stack<BeginnerGameState> undoStack) {
		this.undoStack = undoStack;
	}
	
	private void chooseGame() {
		JFileChooser jfc = new JFileChooser();
		int option = jfc.showOpenDialog(null);
		if (option == JFileChooser.APPROVE_OPTION) {
			if (jfc.getSelectedFile() != null) {
				File theFileToSave = jfc.getSelectedFile();
			}
		}
	}
	
	private BeginnerGameState makeBeginnerGameState() {
		BeginnerGameState s = new BeginnerGameState(p1, p2, board);
		// TODO the setAll method currently sends the POINTERS of the current mutable
		// variables in Scrabble.java to a new item on the stack.
		// Instead, it should send to the new stack item copies of the objects with the
		// same state
		s.setAll(p1, p2, board, currentTurn, isFirstTurn, isSaved, score1, score2, tilesPlaced, currentTiles,
				tilesPlayed, activeTile, bag);
		return s;
	}
	
	private void undoTurn() {
	}

	private void redoTurn() {
	}
	
	
	private void refresh() {
		setTurn(currentTurn);
		setActiveTile(activeTile);
		board.refresh();
		p1.refresh();
		p2.refresh();
	}
	
	private void makeNewGame() {
		removeBorderLayoutObjects();
		board = new SmallBoard();
		board.refresh();
		bottom = new JPanel(new BorderLayout());
		bag = new Bag();
		p1 = new BeginnerTray(bag.getNewTray(), true, playerName1);
		p2 = new BeginnerTray(bag.getNewTray(), false, playerName2);
		p1.refresh();
		p2.refresh();
		tilesPlaced = 0;
		score1 = 0;
		score2 = 0;
//         statusBar = new StatusBar(bag.getBagSize());
		scorePanel = new BeginnerScorePanel(p1, p2);
//         mbar = new JMenuBar();
		tilesPlayed.clear();
		getUndoStack().clear();
		redoStack.clear();
		currentTiles.clear();
		setTurn("playerOne");
		isFirstTurn = true;
		isSaved = true;
		addBorderLayoutObjects();
		revalidate();

	}
	
	public void setTurn(String _turn) {
		if (isValidMove()) {
			isSaved = false;
			currentTurn = _turn;
			scorePanel.setButton(currentTurn);
			setActiveTile(null);
			if (board.board[5][5] instanceof TrayTile)
				isFirstTurn = false;
			currentTiles.clear();
			tilesPlaced = 0;
			// Trays
			if (currentTurn.equals("playerOne")) {
				p1.setActive(true);
				p2.setActive(false);
				for (BeginnerTrayTile t : p1.getTiles()) {
					currentTiles.add(t);
				}
			} else if (currentTurn.equals("playerTwo")) {
				p1.setActive(false);
				p2.setActive(true);
				for (BeginnerTrayTile t : p2.getTiles()) {
					currentTiles.add(t);
				}
			} 

			getUndoStack().push(makeBeginnerGameState());
			redoStack.clear();

			// Status bar
//	    statusBar.setBagCount(bag.getBagSize());
//	    statusBar.setMessage();
		}
	}
	
	public boolean inCurrentTiles(BeginnerTrayTile tile) {// called in TrayTile
		// mouseListener
		for (BeginnerTrayTile t : currentTiles) {
			if (t == tile)
				return true;
		}
		return false;
	}
	
	
	public boolean inTileList(Tile tile, ArrayList<BeginnerTrayTile> list) {
		for (BeginnerTrayTile t : list) {
			if (t == tile)
				return true;
		}
		return false;
	}
	
	public boolean isMultiplier(int x, int y, ArrayList<Tile> list) {
		for (Tile t : list) {
			if (x == t.getXLoc() && y == t.getYLoc())
				return true;
		}
		return false;
	}
	
	public void setActiveTile(BeginnerTrayTile tile) {
		for (BeginnerTrayTile t : currentTiles) {
			t.setTileBorder(Color.GRAY);
			t.setColor(new Color(0xD7B288));
		}
		activeTile = tile;
		if (activeTile != null) {
			activeTile.setTileBorder(Color.BLACK);
			activeTile.setColor(new Color(0xF4A460));
			activeTile.repaint();
		}
	}
	
	public void placeTile(BeginnerBoardTile tile) {
		BeginnerTrayTile temp = new BeginnerTrayTile(tile.getLoc(), null, activeTile.getText());
		if (activeTile.t != null) {
			board.addTile(temp, tile.getXLoc(), tile.getYLoc());
			if (currentTurn.equals("playerOne")) {
				p1.tiles.remove(activeTile);
				p1.refresh();
			} else if (currentTurn.equals("playerTwo")) {
				p2.tiles.remove(activeTile);
				p2.refresh();
			}
			tilesPlaced++;
		} else {
			board.removeTile(activeTile.getXLoc(), activeTile.getYLoc());
			board.addTile(temp, tile.getXLoc(), tile.getYLoc());
			for (BeginnerTrayTile t : tilesPlayed) {
				if (t.equals(activeTile))
					t = temp;
			}
		}
//	statusBar.setMessage();
		currentTiles.add(temp);
		currentTiles.remove(activeTile);
		activeTile = null;
	}
	
	private boolean setVert() {
		if (tilesPlayed.get(0).getXLoc() == tilesPlayed.get(1).getXLoc()) {
			isVert = true;
			return true;
		}
		if (tilesPlayed.get(0).getYLoc() == tilesPlayed.get(1).getYLoc()) {
			isVert = false;
			return true;
		}
		return false;
	}
	
	private boolean isValidMove() {
		ArrayList<String> words = new ArrayList<String>();
		int score = 0;
		tilesPlayed.clear();
		for (BeginnerTrayTile t : currentTiles) {
			if (t.t == null)
				tilesPlayed.add(t);
		}
		if (tilesPlayed.size() == 0)
			return true;
		if (tilesPlayed.size() == 1) {
			words.add(makeVertWord(checkNorth(tilesPlayed.get(0)), checkSouth(tilesPlayed.get(0)),
					tilesPlayed.get(0).getXLoc()));
			score += makeScore(tilesPlayed.get(0), true);
			score += makeScore(tilesPlayed.get(0), false);
			words.add(makeHorizWord(checkWest(tilesPlayed.get(0)), checkEast(tilesPlayed.get(0)),
					tilesPlayed.get(0).getYLoc()));
			if (!checkConnected(tilesPlayed)) {
				showTilePlacementError();
				return false;
			}
			tilesPlayed.clear();
			for (String w : words) {
				if (!checkWord(w)) {
					showDictionaryError(w);
					return false;
				}
			}
			words.clear();
			if (currentTurn.equals("playerOne")) {
				score1 += score;
				p1.setLabel(score1);
			} else if (currentTurn.equals("playerTwo")) {
				score2 += score;
				p2.setLabel(score2);
			} 
			System.out.println("Score1 is " + score1);
			System.out.println("Score2 is " + score2);
			return true;
		}
		if(tilesPlayed.size() > 4) {
			//show error
			showTileTooLongError();
			return false;
		}
		tilesPlayed = sortTiles(tilesPlayed);
		if (!setVert()) {
			showTilePlacementError();
			return false;
		}
		if (setVert()) {
			if (isVert) {
				for (BeginnerTrayTile tile : tilesPlayed) {
					if (tilesPlayed.get(0).getXLoc() != tile.getXLoc()) {
						showTilePlacementError();
						return false;
					}
				}
				words.add(makeVertWord(checkNorth(tilesPlayed.get(0)),
						checkSouth(tilesPlayed.get(tilesPlayed.size() - 1)), tilesPlayed.get(0).getXLoc()));
				score += makeScore(tilesPlayed.get(0), isVert);
				score += makeScore(tilesPlayed.get(0), isVert);
				for (int k = checkNorth(tilesPlayed.get(0)); k <= checkSouth(
						tilesPlayed.get(tilesPlayed.size() - 1)); k++) {
					if (!(board.board[tilesPlayed.get(0).getXLoc()][k] instanceof BeginnerTrayTile)) {
						showTilePlacementError();
						return false;
					}
					if (inTileList(board.board[tilesPlayed.get(0).getXLoc()][k], tilesPlayed))
						words.add(makeHorizWord(checkWest(board.board[tilesPlayed.get(0).getXLoc()][k]),
								checkEast(board.board[tilesPlayed.get(0).getXLoc()][k]), k));
				}
			} else {
				for (BeginnerTrayTile tile : tilesPlayed) {
					if (tilesPlayed.get(0).getYLoc() != tile.getYLoc()) {
						showTilePlacementError();
						return false;
					}
				}
				words.add(makeHorizWord(checkWest(tilesPlayed.get(0)),
						checkEast(tilesPlayed.get(tilesPlayed.size() - 1)), tilesPlayed.get(0).getYLoc()));
				score += makeScore(tilesPlayed.get(0), isVert);
				score += makeScore(tilesPlayed.get(0), isVert);
				for (int k = checkWest(tilesPlayed.get(0)); k <= checkEast(
						tilesPlayed.get(tilesPlayed.size() - 1)); k++) {
					if (!(board.board[k][tilesPlayed.get(0).getYLoc()] instanceof BeginnerTrayTile)) {
						showTilePlacementError();
						return false;
					}
					if (inTileList(board.board[k][tilesPlayed.get(0).getYLoc()], tilesPlayed))
						words.add(makeVertWord(checkNorth(board.board[k][tilesPlayed.get(0).getYLoc()]),
								checkSouth(board.board[k][tilesPlayed.get(0).getYLoc()]), k));
				}
			}
		}
		if (!checkConnected(tilesPlayed)) {
			showTilePlacementError();
			return false;
		}
		tilesPlayed.clear();
		for (String w : words) {
			if (!checkWord(w)) {
				showDictionaryError(w);
				return false;
			}
		}
		words.clear();
		if (currentTurn.equals("playerOne")) {
			score1 += score;
			p1.setLabel(score1);
		} else if (currentTurn.equals("playerTwo")) {
			score2 += score;
			p2.setLabel(score2);
		} 
		return true;
	}
	
	private boolean checkConnected(ArrayList<BeginnerTrayTile> list) {
		int connectedTiles = 0;
		for (BeginnerTrayTile tile : list) {
			if (isFirstTurn && board.board[5][5] == tile)
				return true;
			if (isConnected(tile, list))
				connectedTiles++;
		}
		if (connectedTiles == 0)
			return false;
		return true;
	}
	
	private boolean isConnected(BeginnerTrayTile tile, ArrayList<BeginnerTrayTile> list) {
		for (BeginnerTrayTile t : list) {
			int x = t.getXLoc();
			int y = t.getYLoc();
			if (x != 0 && (board.board[x - 1][y] instanceof BeginnerTrayTile) && !inTileList(board.board[x - 1][y], list)) {
				return true;
			} else if (x != 14 && (board.board[x + 1][y] instanceof BeginnerTrayTile)
					&& !inTileList(board.board[x + 1][y], list)) {
				return true;
			} else if (y != 0 && (board.board[x][y - 1] instanceof BeginnerTrayTile)
					&& !inTileList(board.board[x][y - 1], list)) {
				return true;
			} else if (y != 14 && (board.board[x][y + 1] instanceof BeginnerTrayTile)
					&& !inTileList(board.board[x][y + 1], list)) {
				return true;
			}
		}
		return false;
	}
	
	private void showTilePlacementError() {
		Toolkit.getDefaultToolkit().beep();
		JOptionPane.showMessageDialog(this, "Tiles in Incorrect Position", "Illegal Tile Placement",
				JOptionPane.WARNING_MESSAGE);
	}
	
	private void showTileTooLongError() {
		Toolkit.getDefaultToolkit().beep();
		JOptionPane.showMessageDialog(this, "Words Must Be Of Length Four Or Shorter!",  "Too Many Tiles!",
				JOptionPane.WARNING_MESSAGE);
	}
	
	
	private int checkNorth(Tile tile) {
		if (tile.getYLoc() == 0 || board.board[tile.getXLoc()][tile.getYLoc() - 1] instanceof BeginnerBoardTile)
			return tile.getYLoc();
		return checkNorth(board.board[tile.getXLoc()][tile.getYLoc() - 1]);
	}
	
	private int checkSouth(Tile tile) {
		if (tile.getYLoc() == 14 || board.board[tile.getXLoc()][tile.getYLoc() + 1] instanceof BeginnerBoardTile)
			return tile.getYLoc();
		return checkSouth(board.board[tile.getXLoc()][tile.getYLoc() + 1]);
	}
	
	private int checkWest(Tile tile) {
		if (tile.getXLoc() == 0 || board.board[tile.getXLoc() - 1][tile.getYLoc()] instanceof BeginnerBoardTile)
			return tile.getXLoc();
		return checkWest(board.board[tile.getXLoc() - 1][tile.getYLoc()]);
	}
	
	private int checkEast(Tile tile) {
		if (tile.getXLoc() == 14 || board.board[tile.getXLoc() + 1][tile.getYLoc()] instanceof BeginnerBoardTile)
			return tile.getXLoc();
		return checkEast(board.board[tile.getXLoc() + 1][tile.getYLoc()]);
	}
	
	
	private ArrayList<BeginnerTrayTile> sortTiles(ArrayList<BeginnerTrayTile> tiles) {
		if (tiles.size() > 1) {
			if (isVert) {
				for (int pointer = tiles.size(); pointer > 0; pointer--) {
					for (int k = 0; k < pointer - 1; k++) {
						if (tiles.get(k).getYLoc() > tiles.get(k + 1).getYLoc())
							swap(tiles, k, k + 1);
					}
				}

			} else {

				for (int pointer = tiles.size(); pointer > 0; pointer--) {
					for (int k = 0; k < pointer - 1; k++) {
						if (tiles.get(k).getXLoc() > tiles.get(k + 1).getXLoc())
							swap(tiles, k, k + 1);
					}
				}
			}
		}
		return tiles;
	}
	
	private void swap(ArrayList<BeginnerTrayTile> tiles, int k, int l) {

		BeginnerTrayTile a = tiles.get(k);
		BeginnerTrayTile b = tiles.get(l);
		tiles.set(l, a);
		tiles.set(k, b);
	}
	
	private ArrayList<BeginnerTrayTile> sortTray(ArrayList<BeginnerTrayTile> tiles) {
		if (tiles.size() > 1) {
			for (int pointer = tiles.size(); pointer > 0; pointer--) {
				for (int k = 0; k < pointer - 1; k++) {
					if (tiles.get(k).getText().compareTo(tiles.get(k + 1).getText()) > 0)
						swap(tiles, k, k + 1);
				}
			}
		}
		return tiles;
	}
	
	private boolean checkWord(String s) {
		if (dictEnabled) {
			s = s.replace("" + (char) 0, "[A-Z]");
			Pattern p = Pattern.compile(s);
			for (String entry : dict) {
				if (p.matcher(entry).matches()) {
					return true;
				}
			}
			return false;
		}
		return true;
	}
	
	
	private String makeVertWord(int min, int max, int x) {
		String out = "";
		for (int k = min; k <= max; k++) {
			out = out + board.board[x][k].getText();
		}
		return out;
	}

	private String makeHorizWord(int min, int max, int y) {
		String out = "";
		for (int k = min; k <= max; k++) {
			out = out + board.board[k][y].getText();
		}
		return out;
	}

	private void showDictionaryError(String w) {
		Toolkit.getDefaultToolkit().beep();
		JOptionPane.showMessageDialog(this, "The word \"" + w + "\" does not exist", "Word not found!",
				JOptionPane.WARNING_MESSAGE);
	}
	
	private int makeScore(BeginnerTrayTile tile, boolean vert) {
		int out = 0;
		int multi = 1;
		int x = tile.getXLoc();
		int y = tile.getYLoc();
		if (vert) {
			for (int k = checkNorth(tile); k <= checkSouth(tile); k++) {
				if (isMultiplier(x, k, board.doubleLetter))
					out += (board.board[x][k].getVal() * 2);
				else if (isMultiplier(x, k, board.tripleLetter))
					out += (board.board[x][k].getVal() * 3);
				else
					out += board.board[x][k].getVal();
				if (isMultiplier(x, k, board.doubleWord))
					multi = (multi == 1) ? 2 : multi + 2;
				else if (isMultiplier(x, k, board.tripleWord))
					multi = (multi == 1) ? 3 : multi + 3;
			}
		} else {
			for (int k = checkWest(tile); k <= checkEast(tile); k++) {
				if (isMultiplier(k, y, board.doubleLetter))
					out += (board.board[k][y].getVal() * 2);
				else if (isMultiplier(k, y, board.tripleLetter))
					out += (board.board[k][y].getVal() * 3);
				else
					out += board.board[k][y].getVal();
				if (isMultiplier(k, y, board.doubleWord))
					multi = (multi == 1) ? 2 : multi + 2;
				else if (isMultiplier(k, y, board.tripleWord))
					multi = (multi == 1) ? 3 : multi + 3;
			}
		}
		if (tilesPlaced == 7)
			return (multi * out) + 50;
		return multi * out;
	}
	
	private int makeScore(ArrayList<TrayTile> tiles, boolean vert) {
		int out = 0;
		int multi = 1;
		int x, y;
		for (TrayTile tile : tiles) {
			x = tile.getXLoc();
			y = tile.getYLoc();
			if (vert) {
				if (checkWest(tile) == checkEast(tile))
					continue;
				for (int k = checkWest(tile); k <= checkEast(tile); k++) {
					if (isMultiplier(k, y, board.doubleLetter))
						out += (board.board[k][y].getVal() * 2);
					else if (isMultiplier(k, y, board.tripleLetter))
						out += (board.board[k][y].getVal() * 3);
					else
						out += board.board[k][y].getVal();
					if (isMultiplier(k, y, board.doubleWord))
						multi = (multi == 1) ? 2 : multi + 2;
					else if (isMultiplier(k, y, board.tripleWord))
						multi = (multi == 1) ? 3 : multi + 3;
				}
			} else {
				if (checkNorth(tile) == checkSouth(tile))
					continue;
				for (int k = checkNorth(tile); k <= checkSouth(tile); k++) {
					if (isMultiplier(x, k, board.doubleLetter))
						out += (board.board[x][k].getVal() * 2);
					else if (isMultiplier(x, k, board.tripleLetter))
						out += (board.board[x][k].getVal() * 3);
					else
						out += tile.getVal();
					if (isMultiplier(x, k, board.doubleWord))
						multi = (multi == 1) ? 2 : multi + 2;
					else if (isMultiplier(x, k, board.tripleWord))
						multi = (multi == 1) ? 3 : multi + 3;
				}
			}
		}
		return multi * out;
	}
	
	public static void main(String[] args) {
		s = new BeginnerScrabble("test", "test2");
		javax.swing.SwingUtilities.invokeLater(s);
	}
	
	
	
}
