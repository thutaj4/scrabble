import java.util.ArrayList;

public class GameState {
    public static Tray p1, p2, p3, p4;
    public static Board board;
    public static boolean isFirstTurn, isSaved;
    public static int score1, score2, tilesPlaced;
    public static ArrayList<TrayTile> currentTiles, tilesPlayed;
    public static TrayTile activeTile;
    public static Bag bag;
    public static String currentTurn;

    public GameState(Tray _p1, Tray _p2, Tray _p3, Tray _p4, Board _board) {
	p1 = _p1;
	p2 = _p2;
	p3 = _p3;
	p4 = _p4;
	board = _board;
    }

    @SuppressWarnings("unchecked")
    public void setAll(Tray _p1, Tray _p2, Tray _p3, Tray _p4, Board _board, String _turn,
	    boolean _isFirstTurn, boolean _isSaved, int _score1, int _score2,
	    int _tilesPlaced, ArrayList<TrayTile> _currentTiles,
	    ArrayList<TrayTile> _tilesPlayed, TrayTile _activeTile, Bag _bag) {
	//p1 = _p1;
	//p2 = _p2;
	p1 = _p1; // new Tray((ArrayList<TrayTile>)_p1.getTiles().clone(), true);
	p2 = _p2; // new Tray((ArrayList<TrayTile>)_p2.getTiles().clone(), false);
	p3 = _p3;
	p4 = _p4;
	board = _board;
	currentTurn = _turn;
	isFirstTurn = _isFirstTurn;
	isSaved = _isSaved;
	score1 = _score1;
	score2 = _score2;
	tilesPlaced = _tilesPlaced;
	currentTiles = _currentTiles;
	tilesPlayed = _tilesPlayed;
	activeTile = _activeTile;
	bag = _bag;
    }

    
    public String toString() {
	return "GameState[p1="+p1+", p2="+p2+", score1="+score1+", score2="+score2+"]";
    }
}