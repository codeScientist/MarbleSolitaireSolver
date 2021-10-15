package solver;

public class Position {
	int row;
	int col;
	
	Position(int a, int b){
		row = a;
		col = b;
	}
	
	static boolean isSame(Position a, Position b){
		if(a.row == b.row && a.col == b.col){
			return true;
		}
		return false;
	}
}
