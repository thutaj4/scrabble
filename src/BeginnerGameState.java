import java.util.ArrayList;

public class BeginnerGameState {
    public static BeginnerTray p1, p2;
    public static SmallBoard board;
    public static boolean isFirstTurn, isSaved;
    public static int score1, score2, tilesPlaced;
    public static ArrayList<BeginnerTrayTile> currentTiles, tilesPlayed;
    public static BeginnerTrayTile activeTile;
    public static Bag bag;
    public static String currentTurn;

    public BeginnerGameState(BeginnerTray _p1, BeginnerTray _p2, SmallBoard _board) {
	p1 = _p1;
	p2 = _p2;
	board = _board;
    }

    @SuppressWarnings("unchecked")
    public void setAll(BeginnerTray _p1, BeginnerTray _p2, SmallBoard _board, String _turn,
	    boolean _isFirstTurn, boolean _isSaved, int _score1, int _score2,
	    int _tilesPlaced, ArrayList<BeginnerTrayTile> _currentTiles,
	    ArrayList<BeginnerTrayTile> _tilesPlayed, BeginnerTrayTile _activeTile, Bag _bag) {
	//p1 = _p1;
	//p2 = _p2;
	p1 = _p1; // new Tray((ArrayList<TrayTile>)_p1.getTiles().clone(), true);
	p2 = _p2; // new Tray((ArrayList<TrayTile>)_p2.getTiles().clone(), false);
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