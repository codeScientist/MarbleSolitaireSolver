package solver;

import java.util.Stack;

public class SnapshotArea {
	char playArea[][];
	Stack<Move> moveList = new Stack<Move>();
	
	public SnapshotArea(char p[][]){
		playArea = p;
	}
	
	void findMoves(){
		for(int rowIndex=0;rowIndex<Constants.BOARD_HEIGHT;rowIndex++){
			for(int colIndex=0;colIndex<Constants.BOARD_WIDTH;colIndex++){
				if(playArea[rowIndex][colIndex] == 'e'){
					if(rowIndex>1 && playArea[rowIndex-1][colIndex] == 'b' && playArea[rowIndex-2][colIndex] == 'b'){
						Move nm = new Move(new Position(rowIndex,colIndex),Direction.UP);
						if(checkMove(nm) == true){
							moveList.push(nm);
						}
					}
					if(rowIndex<(Constants.BOARD_HEIGHT-2) && playArea[rowIndex+1][colIndex] == 'b' && playArea[rowIndex+2][colIndex] == 'b'){
						Move nm = new Move(new Position(rowIndex,colIndex),Direction.DOWN);
						if(checkMove(nm) == true){
							moveList.push(nm);
						}
					}
					if(colIndex>1 && playArea[rowIndex][colIndex-1] == 'b' && playArea[rowIndex][colIndex-2] == 'b'){
						Move nm = new Move(new Position(rowIndex,colIndex),Direction.LEFT);
						if(checkMove(nm) == true){
							moveList.push(nm);
						}
					}
					if(colIndex<(Constants.BOARD_WIDTH-2) && playArea[rowIndex][colIndex+1] == 'b' && playArea[rowIndex][colIndex+2] == 'b'){
						Move nm = new Move(new Position(rowIndex,colIndex),Direction.RIGHT);
						if(checkMove(nm) == true){
							moveList.push(nm);
						}
					}
				}
			}
		}
	}
	
	boolean checkMove(Move move){
		
		int count = 0;
		
		// mirror up down
		for(int rowIndex=0;rowIndex<Constants.BOARD_HEIGHT;rowIndex++){
			for(int colIndex=0;colIndex<Constants.BOARD_WIDTH;colIndex++){
				if(playArea[Constants.BOARD_HEIGHT-rowIndex -1][colIndex] == playArea[rowIndex][colIndex]){
					count++;
				}else{
					count = 0;
					break;
				}
			}
		}
		if(count == Constants.BOARD_HEIGHT*Constants.BOARD_WIDTH){
			Move testMove = Move.mirrorUpDown(move);
			for(int i=0;i<moveList.size();i++){
				if(Move.isSame(testMove, moveList.get(i))){
					return false;
				}
			}
		}
		count = 0;
		// mirror right left
		for(int rowIndex=0;rowIndex<Constants.BOARD_HEIGHT;rowIndex++){
			for(int colIndex=0;colIndex<Constants.BOARD_WIDTH;colIndex++){
				if(playArea[rowIndex][Constants.BOARD_WIDTH-colIndex -1] == playArea[rowIndex][colIndex]){
					count++;
				}else{
					count = 0;
					break;
				}
			}
		}
		if(count == Constants.BOARD_HEIGHT*Constants.BOARD_WIDTH){
			Move testMove = Move.mirrorRightLeft(move);
			for(int i=0;i<moveList.size();i++){
				if(Move.isSame(testMove, moveList.get(i))){
					return false;
				}
			}
		}
		count = 0;
		// rotate 180
		for(int rowIndex=0;rowIndex<Constants.BOARD_HEIGHT;rowIndex++){
			for(int colIndex=0;colIndex<Constants.BOARD_WIDTH;colIndex++){
				if(playArea[Constants.BOARD_HEIGHT-rowIndex -1][Constants.BOARD_WIDTH-colIndex -1] == playArea[rowIndex][colIndex]){
					count++;
				}else{
					count = 0;
					break;
				}
			}
		}
		if(count == Constants.BOARD_HEIGHT*Constants.BOARD_WIDTH){
			Move testMove = Move.rotate180(move);
			for(int i=0;i<moveList.size();i++){
				if(Move.isSame(testMove, moveList.get(i))){
					return false;
				}
			}
		}
		count = 0;
		// invert from diagonal from top left to bottom right
		for(int rowIndex=0;rowIndex<Constants.BOARD_HEIGHT;rowIndex++){
			for(int colIndex=0;colIndex<Constants.BOARD_WIDTH;colIndex++){
				if(playArea[colIndex][rowIndex] == playArea[rowIndex][colIndex]){
					count++;
				}else{
					count = 0;
					break;
				}
			}
		}
		if(count == Constants.BOARD_HEIGHT*Constants.BOARD_WIDTH){
			Move testMove = Move.invertDiagtl(move);
			for(int i=0;i<moveList.size();i++){
				if(Move.isSame(testMove, moveList.get(i))){
					return false;
				}
			}
		}
		count = 0;
		// rotate left
		for(int rowIndex=0;rowIndex<Constants.BOARD_HEIGHT;rowIndex++){
			for(int colIndex=0;colIndex<Constants.BOARD_WIDTH;colIndex++){
				if(playArea[Constants.BOARD_WIDTH-colIndex -1][rowIndex] == playArea[rowIndex][colIndex]){
					count++;
				}else{
					count = 0;
					break;
				}
			}
		}
		if(count == Constants.BOARD_HEIGHT*Constants.BOARD_WIDTH){
			Move testMove = Move.invertDiagtl(move);
			for(int i=0;i<moveList.size();i++){
				if(Move.isSame(testMove, moveList.get(i))){
					return false;
				}
			}
		}		
		count = 0;
		// rotate right
		for(int rowIndex=0;rowIndex<Constants.BOARD_HEIGHT;rowIndex++){
			for(int colIndex=0;colIndex<Constants.BOARD_WIDTH;colIndex++){
				if(playArea[colIndex][Constants.BOARD_HEIGHT-rowIndex -1] == playArea[rowIndex][colIndex]){
					count++;
				}else{
					count = 0;
					break;
				}
			}
		}
		if(count == Constants.BOARD_HEIGHT*Constants.BOARD_WIDTH){
			Move testMove = Move.rotateRight(move);
			for(int i=0;i<moveList.size();i++){
				if(Move.isSame(testMove, moveList.get(i))){
					return false;
				}
			}
		}		
		count = 0;
		// invert from diagonal from top right to bottom left
		for(int rowIndex=0;rowIndex<Constants.BOARD_HEIGHT;rowIndex++){
			for(int colIndex=0;colIndex<Constants.BOARD_WIDTH;colIndex++){
				if(playArea[Constants.BOARD_WIDTH-colIndex -1][Constants.BOARD_HEIGHT-rowIndex -1] == playArea[rowIndex][colIndex]){
					count++;
				}else{
					count = 0;
					break;
				}
			}
		}
		if(count == Constants.BOARD_HEIGHT*Constants.BOARD_WIDTH){
			Move testMove = Move.invertDiagtr(move);
			for(int i=0;i<moveList.size();i++){
				if(Move.isSame(testMove, moveList.get(i))){
					return false;
				}
			}
		}
		
		return true;
	}
	
	char[][] clonePlayArea(){
		
		char clone[][] = {
				{'w','w','b','b','b','w','w'},
				{'w','w','b','b','b','w','w'},
				{'b','b','b','b','b','b','b'},
				{'b','b','b','e','b','b','b'},
				{'b','b','b','b','b','b','b'},
				{'w','w','b','b','b','w','w'},
				{'w','w','b','b','b','w','w'}
		};
		
//		char clone[][] = {
//				{'b','b','b'},
//				{'b','b','b'},
//				{'b','b','e'}
//		};
		
		for(int rowIndex=0;rowIndex<Constants.BOARD_HEIGHT;rowIndex++){
			for(int colIndex=0;colIndex<Constants.BOARD_WIDTH;colIndex++){
				clone[rowIndex][colIndex] = playArea[rowIndex][colIndex];
			}
		}
		
		return clone;
	}

	char[][] applyMove(Move m){
		char PA[][] = clonePlayArea();
		PA[m.pos.row][m.pos.col] = 'b';
		if(m.dir == Direction.UP){
			PA[m.pos.row-1][m.pos.col] = 'e';
			PA[m.pos.row-2][m.pos.col] = 'e';
		}else if(m.dir == Direction.DOWN){
			PA[m.pos.row+1][m.pos.col] = 'e';
			PA[m.pos.row+2][m.pos.col] = 'e';
		}else if(m.dir == Direction.LEFT){
			PA[m.pos.row][m.pos.col-1] = 'e';
			PA[m.pos.row][m.pos.col-2] = 'e';
		}else{
			PA[m.pos.row][m.pos.col+1] = 'e';
			PA[m.pos.row][m.pos.col+2] = 'e';
		}
		
		return PA;
	}
	
	boolean win(){
		int count = 0;
		for(int rowIndex=0;rowIndex<Constants.BOARD_HEIGHT;rowIndex++){
			for(int colIndex=0;colIndex<Constants.BOARD_WIDTH;colIndex++){
				if(playArea[rowIndex][colIndex]=='b'){
					count++;
				}
				if(count > 1){
					return false;
				}
			}
		}
		
		return true;
	}
	
	boolean isSimilar(char[][] test){
		int count = 0;
		
		//simple
		for(int rowIndex=0;rowIndex<Constants.BOARD_HEIGHT;rowIndex++){
			for(int colIndex=0;colIndex<Constants.BOARD_WIDTH;colIndex++){
				if(test[rowIndex][colIndex] == playArea[rowIndex][colIndex]){
					count++;
				}else{
					count = 0;
					break;
				}
			}
		}
		if(count == Constants.BOARD_HEIGHT*Constants.BOARD_WIDTH){
			return true;
		}
		
		// mirror up down
		for(int rowIndex=0;rowIndex<Constants.BOARD_HEIGHT;rowIndex++){
			for(int colIndex=0;colIndex<Constants.BOARD_WIDTH;colIndex++){
				if(test[Constants.BOARD_HEIGHT-rowIndex-1][colIndex] == playArea[rowIndex][colIndex]){
					count++;
				}else{
					count = 0;
					break;
				}
			}
		}
		if(count == Constants.BOARD_HEIGHT*Constants.BOARD_WIDTH){
			return true;
		}
		
		//mirror right left
		for(int rowIndex=0;rowIndex<Constants.BOARD_HEIGHT;rowIndex++){
			for(int colIndex=0;colIndex<Constants.BOARD_WIDTH;colIndex++){
				if(test[rowIndex][Constants.BOARD_WIDTH-colIndex -1] == playArea[rowIndex][colIndex]){
					count++;
				}else{
					count = 0;
					break;
				}
			}
		}
		if(count == Constants.BOARD_HEIGHT*Constants.BOARD_WIDTH){
			return true;
		}
		
		//rotated 180
		for(int rowIndex=0;rowIndex<Constants.BOARD_HEIGHT;rowIndex++){
			for(int colIndex=0;colIndex<Constants.BOARD_WIDTH;colIndex++){
				if(test[Constants.BOARD_HEIGHT-rowIndex -1][Constants.BOARD_WIDTH-colIndex -1] == playArea[rowIndex][colIndex]){
					count++;
				}else{
					count = 0;
					break;
				}
			}
		}
		if(count == Constants.BOARD_HEIGHT*Constants.BOARD_WIDTH){
			return true;
		}
		
		// invert from diagonal from top left to bottom right
		for(int rowIndex=0;rowIndex<Constants.BOARD_HEIGHT;rowIndex++){
			for(int colIndex=0;colIndex<Constants.BOARD_WIDTH;colIndex++){
				if(test[colIndex][rowIndex] == playArea[rowIndex][colIndex]){
					count++;
				}else{
					count = 0;
					break;
				}
			}
		}
		if(count == Constants.BOARD_HEIGHT*Constants.BOARD_WIDTH){
			return true;
		}
		
		// rotate left
		for(int rowIndex=0;rowIndex<Constants.BOARD_HEIGHT;rowIndex++){
			for(int colIndex=0;colIndex<Constants.BOARD_WIDTH;colIndex++){
				if(test[Constants.BOARD_WIDTH-colIndex -1][rowIndex] == playArea[rowIndex][colIndex]){
					count++;
				}else{
					count = 0;
					break;
				}
			}
		}
		if(count == Constants.BOARD_HEIGHT*Constants.BOARD_WIDTH){
			return true;
		}
		
		//rotate right
		for(int rowIndex=0;rowIndex<Constants.BOARD_HEIGHT;rowIndex++){
			for(int colIndex=0;colIndex<Constants.BOARD_WIDTH;colIndex++){
				if(test[colIndex][Constants.BOARD_HEIGHT-rowIndex -1] == playArea[rowIndex][colIndex]){
					count++;
				}else{
					count = 0;
					break;
				}
			}
		}
		if(count == Constants.BOARD_HEIGHT*Constants.BOARD_WIDTH){
			return true;
		}
		
		// invert from diagonal from top right to bottom left
		for(int rowIndex=0;rowIndex<Constants.BOARD_HEIGHT;rowIndex++){
			for(int colIndex=0;colIndex<Constants.BOARD_WIDTH;colIndex++){
				if(test[Constants.BOARD_WIDTH-colIndex -1][Constants.BOARD_HEIGHT-rowIndex -1] == playArea[rowIndex][colIndex]){
					count++;
				}else{
					count = 0;
					break;
				}
			}
		}
		if(count == Constants.BOARD_HEIGHT*Constants.BOARD_WIDTH){
			return true;
		}
		return false;
	}
	
	int countE(){
		int count = 0;
		for(int rowIndex=0;rowIndex<Constants.BOARD_HEIGHT;rowIndex++){
			for(int colIndex=0;colIndex<Constants.BOARD_WIDTH;colIndex++){
				if(playArea[rowIndex][colIndex]=='e'){
					count++;
				}
			}
		}
		
		return count;
	}
}
