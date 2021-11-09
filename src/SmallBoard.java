import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class SmallBoard extends JPanel implements Cloneable {
	public Tile[][] board;
	public ArrayList<Tile> doubleLetter, tripleLetter, doubleWord, tripleWord;

	public SmallBoard() {
		super(new GridLayout(15, 15));
		this.setBackground(Color.WHITE);
		board = new Tile[11][11];
		makeBoard();
		setBoardTiles();
		addBoard();
		setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.WHITE));
	}

	public void makeBoard() {
		for (int k = 0; k < 121; k++) {
			board[k / 11][k % 11] = new BoardTile(new Point(k / 11, k % 11), Color.white);
		}
	}

	public void addBoard() {
		for (int a = 0; a < 11; a++) {
			for (int l = 0; l < 11; l++) {
				add(board[l][a]);
			}
		}
	}

	public void setBoardTiles() {
		setTileLists();
		for (int k = 0; k < doubleLetter.size(); k++) {
			int currentX = doubleLetter.get(k).getXLoc();
			int currentY = doubleLetter.get(k).getYLoc();
			board[currentX][currentY] = new DoubleLetter(new Point(currentX, currentY));
		}
		for (int k = 0; k < tripleLetter.size(); k++) {
			int currentX = tripleLetter.get(k).getXLoc();
			int currentY = tripleLetter.get(k).getYLoc();
			board[currentX][currentY] = new TripleLetter(new Point(currentX, currentY));
		}
		for (int k = 0; k < doubleWord.size(); k++) {
			int currentX = doubleWord.get(k).getXLoc();
			int currentY = doubleWord.get(k).getYLoc();
			board[currentX][currentY] = new DoubleWord(new Point(currentX, currentY));
		}
		for (int k = 0; k < tripleWord.size(); k++) {
			int currentX = tripleWord.get(k).getXLoc();
			int currentY = tripleWord.get(k).getYLoc();
			board[currentX][currentY] = new TripleWord(new Point(currentX, currentY));
		}
	}

	// FIX PLZ
	public void setTileLists() {
		doubleLetter = new ArrayList<Tile>(Arrays.asList(board[0][3], board[0][11], board[2][6], board[2][8],
				board[3][0], board[3][7], board[3][14], board[6][2], board[6][6], board[6][8], board[6][12],
				board[7][3], board[7][11], board[8][2], board[8][6], board[8][8], board[8][12], board[11][0],
				board[11][7], board[11][14], board[12][6], board[12][8], board[14][3], board[14][11]));
		tripleLetter = new ArrayList<Tile>(
				Arrays.asList(board[1][5], board[1][9], board[5][1], board[5][5], board[5][9], board[5][13],
						board[9][1], board[9][5], board[9][9], board[9][13], board[13][5], board[13][9]));
		doubleWord = new ArrayList<Tile>(Arrays.asList(board[1][1], board[1][13], board[2][2], board[2][12],
				board[3][3], board[3][11], board[4][4], board[4][10], board[7][7], board[10][4], board[10][10],
				board[11][3], board[11][11], board[12][2], board[12][12], board[13][1], board[13][13]));
		tripleWord = new ArrayList<Tile>(Arrays.asList(board[0][0], board[0][7], board[0][14], board[7][0],
				board[7][14], board[14][0], board[14][7], board[14][14]));
	}

	public void refresh() {
		removeAll();
		addBoard();
		revalidate();
	}

	public void addTile(TrayTile t, int x, int y) {
		board[x][y] = t;
		refresh();
	}

	public void removeTile(int x, int y) {
		setTileLists();
		if (doubleLetter.contains(board[x][y])) {
			board[x][y] = new DoubleLetter(new Point(x, y));
		} else if (tripleLetter.contains(board[x][y])) {
			board[x][y] = new TripleLetter(new Point(x, y));
		} else if (doubleWord.contains(board[x][y])) {
			board[x][y] = new DoubleWord(new Point(x, y));
		} else if (tripleWord.contains(board[x][y])) {
			board[x][y] = new TripleWord(new Point(x, y));
		} else {
			board[x][y] = new BoardTile(new Point(x, y), new Color(0xCBC4A8));
			refresh();
		}
		refresh();
	}

}
