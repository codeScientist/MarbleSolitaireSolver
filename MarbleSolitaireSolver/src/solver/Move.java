package solver;

public class Move {
	Position pos;
	Direction dir;
	
	Move(Position p, Direction d){
		pos = p;
		dir = d;
	}
	
	void display(){
		System.out.println("position: row " + pos.row + ", col " + pos.col + ", direction: " + dir );
	}
	
	static boolean isSame(Move a, Move b){
		if(Position.isSame(a.pos, b.pos) && a.dir == b.dir){
			return true;
		}
		return false;
	}
	
	static Move mirrorUpDown(Move move){
		Direction d;
		Position p = new Position(Constants.BOARD_HEIGHT - move.pos.row -1, move.pos.col);
		
		if(move.dir == Direction.UP){
			d = Direction.DOWN;
		}else if(move.dir == Direction.DOWN){
			d = Direction.UP;
		}else{
			d = move.dir;
		}
		
		return new Move(p,d);
	}
	
	static Move mirrorRightLeft(Move move){
		Direction d;
		Position p = new Position(move.pos.row, Constants.BOARD_WIDTH - move.pos.col -1);
		
		if(move.dir == Direction.RIGHT){
			d = Direction.LEFT;
		}else if(move.dir == Direction.LEFT){
			d = Direction.RIGHT;
		}else{
			d = move.dir;
		}
		
		return new Move(p,d);
	}

	static Move rotate180(Move move){
		Direction d;
		Position p = new Position(Constants.BOARD_HEIGHT - move.pos.row -1, Constants.BOARD_WIDTH - move.pos.col -1);
		
		if(move.dir == Direction.RIGHT){
			d = Direction.LEFT;
		}else if(move.dir == Direction.LEFT){
			d = Direction.RIGHT;
		}else if(move.dir == Direction.UP){
			d = Direction.DOWN;
		}else{
			d = Direction.UP;
		}
		
		return new Move(p,d);
	}
	
	static Move invertDiagtl(Move move){
		Direction d;
		Position p = new Position(move.pos.col, move.pos.row);
		
		if(move.dir == Direction.RIGHT){
			d = Direction.DOWN;
		}else if(move.dir == Direction.LEFT){
			d = Direction.UP;
		}else if(move.dir == Direction.UP){
			d = Direction.LEFT;
		}else{
			d = Direction.RIGHT;
		}
		
		return new Move(p,d);
	}
	
	static Move invertDiagtr(Move move){
		Direction d;
		Position p = new Position(Constants.BOARD_WIDTH - move.pos.col -1, Constants.BOARD_HEIGHT - move.pos.row -1);
		
		if(move.dir == Direction.RIGHT){
			d = Direction.UP;
		}else if(move.dir == Direction.LEFT){
			d = Direction.DOWN;
		}else if(move.dir == Direction.UP){
			d = Direction.RIGHT;
		}else{
			d = Direction.LEFT;
		}
		
		return new Move(p,d);
	}
	static Move rotateLeft(Move move){
		Direction d;
		Position p = new Position(Constants.BOARD_WIDTH - move.pos.col -1, move.pos.row);
		
		if(move.dir == Direction.RIGHT){
			d = Direction.UP;
		}else if(move.dir == Direction.LEFT){
			d = Direction.DOWN;
		}else if(move.dir == Direction.UP){
			d = Direction.LEFT;
		}else{
			d = Direction.RIGHT;
		}
		
		return new Move(p,d);
	}
	
	static Move rotateRight(Move move){
		Direction d;
		Position p = new Position(move.pos.col, Constants.BOARD_HEIGHT - move.pos.row -1);
		
		if(move.dir == Direction.RIGHT){
			d = Direction.DOWN;
		}else if(move.dir == Direction.LEFT){
			d = Direction.UP;
		}else if(move.dir == Direction.UP){
			d = Direction.RIGHT;
		}else{
			d = Direction.LEFT;
		}
		
		return new Move(p,d);
	}
}
