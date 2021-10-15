package solver;

import java.util.Stack;

public class Play {
	static Stack<SnapshotArea> snaps = new Stack<SnapshotArea>();
	static Stack<SnapshotArea> incorrectSnaps = new Stack<SnapshotArea>();
	static Stack<Move> moveL = new Stack<Move>();
	static boolean sol = false;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		char initialArea[][] = {
				{'w','w','b','b','b','w','w'},
				{'w','w','b','b','b','w','w'},
				{'b','b','b','b','b','b','b'},
				{'b','b','b','e','b','b','b'},
				{'b','b','b','b','b','b','b'},
				{'w','w','b','b','b','w','w'},
				{'w','w','b','b','b','w','w'}
		};
		
//		char initialArea[][] = {
//				{'b','b','b'},
//				{'b','b','b'},
//				{'b','b','e'}
//		};
		
		SnapshotArea a = new SnapshotArea(initialArea);
		
		snaps.push(a);
		
		playAhead();
		
		if(sol == true){
			for(int i=0;i<moveL.size();i++){
				moveL.get(i).display();
			}
		}else{
			System.out.println("did not find any solution");
		}

	}
	
static void playAhead(){
		
		char targetArea[][] = {
				{'w','w','b','b','b','w','w'},
				{'w','w','b','b','b','w','w'},
				{'e','e','b','b','b','b','e'},
				{'e','e','e','e','b','e','e'},
				{'e','e','b','e','e','e','e'},
				{'w','w','e','e','e','w','w'},
				{'w','w','e','e','e','w','w'}
		};
		
//		char targetArea[][] = {
//				{'b','e','b'},
//				{'e','b','e'},
//				{'b','e','e'}
//		};
		
		if(snaps.peek().isSimilar(targetArea) == true){
			System.out.println("we found a solution");
			sol = true;
			return;
		}
		
		if(snaps.size() >= 21){
			//System.out.println("number of balls left: " + (33 - snaps.peek().countE()));
			return;
		}
		
		snaps.peek().findMoves();
		
		while(snaps.peek().moveList.isEmpty() == false){
			moveL.add(snaps.peek().moveList.peek());
			//moveL.peek().display();
			System.out.println("size = " + snaps.size() + " incorrect " + incorrectSnaps.size() + ", moves left of the latest snap: " + snaps.peek().moveList.size());
			SnapshotArea s = new SnapshotArea(snaps.peek().applyMove(snaps.peek().moveList.peek()));
			boolean flag = true;
			for(int i=0;i<incorrectSnaps.size();i++){
				if(s.isSimilar(incorrectSnaps.get(i).playArea)){
					flag = false;
					break;
				}
			}
			
			if(flag == true){
				snaps.push(s);
				playAhead();
				if(sol == true){
					return;
				}
				System.out.printf("moves of each snap:");
				for(int i=0;i<snaps.size();i++){
					System.out.printf(", " + snaps.get(i).moveList.size());
				}
				System.out.println();
				incorrectSnaps.push(snaps.pop());
				snaps.peek().moveList.pop();
				moveL.pop();
			}
			else{
				snaps.peek().moveList.pop();
				moveL.pop();
			}
		}
	}

}
