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
		super(new GridLayout(11, 11));
		this.setBackground(Color.WHITE);
		board = new Tile[11][11];
		makeBoard();
		setBoardTiles();
		addBoard();
		setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.WHITE));
	}

	public void makeBoard() {
		for (int k = 0; k < 121; k++) {
			board[k / 11][k % 11] = new BeginnerBoardTile(new Point(k / 11, k % 11), Color.white);
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
			board[currentX][currentY] = new BeginnerDoubleLetter(new Point(currentX, currentY));
		}
		for (int k = 0; k < tripleLetter.size(); k++) {
			int currentX = tripleLetter.get(k).getXLoc();
			int currentY = tripleLetter.get(k).getYLoc();
			board[currentX][currentY] = new BeginnerTripleLetter(new Point(currentX, currentY));
		}
		for (int k = 0; k < doubleWord.size(); k++) {
			int currentX = doubleWord.get(k).getXLoc();
			int currentY = doubleWord.get(k).getYLoc();
			board[currentX][currentY] = new BeginnerDoubleWord(new Point(currentX, currentY));
		}
		for (int k = 0; k < tripleWord.size(); k++) {
			int currentX = tripleWord.get(k).getXLoc();
			int currentY = tripleWord.get(k).getYLoc();
			board[currentX][currentY] = new BeginnerTripleWord(new Point(currentX, currentY));
		}
	}

	
	public void setTileLists() {
		doubleLetter = new ArrayList<Tile>(Arrays.asList(board[2][5], board[5][2], board[5][8], board[8][5]));
		
		tripleLetter = new ArrayList<Tile>(
				Arrays.asList(board[1][3], board[1][7], board[3][1], board[3][9], board[7][1], board[7][9],
						board[9][3], board[9][7]));
		
		doubleWord = new ArrayList<Tile>(Arrays.asList(board[1][1], board[1][9], board[2][2], board[2][8],
				board[3][3], board[3][7], board[4][4], board[4][6], board[5][5], board[6][4], board[6][6],
				board[7][3], board[7][7], board[8][8], board[8][2], board[9][1], board[9][9]));
		
		tripleWord = new ArrayList<Tile>(Arrays.asList(board[0][0], board[0][5], board[0][10], board[5][0],
				board[5][10], board[10][0], board[10][5], board[10][10]));
	}

	public void refresh() {
		removeAll();
		addBoard();
		revalidate();
	}

	public void addTile(BeginnerTrayTile t, int x, int y) {
		board[x][y] = t;
		refresh();
	}

	public void removeTile(int x, int y) {
		setTileLists();
		if (doubleLetter.contains(board[x][y])) {
			board[x][y] = new BeginnerDoubleLetter(new Point(x, y));
		} else if (tripleLetter.contains(board[x][y])) {
			board[x][y] = new BeginnerTripleLetter(new Point(x, y));
		} else if (doubleWord.contains(board[x][y])) {
			board[x][y] = new BeginnerDoubleWord(new Point(x, y));
		} else if (tripleWord.contains(board[x][y])) {
			board[x][y] = new BeginnerTripleWord(new Point(x, y));
		} else {
			board[x][y] = new BeginnerBoardTile(new Point(x, y), new Color(0xCBC4A8));
			refresh();
		}
		refresh();
	}

}
