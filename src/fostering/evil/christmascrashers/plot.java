package fostering.evil.christmascrashers;

import java.util.ArrayList;

public class plot {
	public static ArrayList<Integer> keys = new ArrayList<Integer>();
	public static ArrayList<Integer> piecesGrabbed = new ArrayList<Integer>();
	//Hashmap?
	//What messages does the user need to get?
	public static boolean hasWon = false;
	public static void checkPlot(){
		//Cannot actually happen yet :P... modify portals with A-Z??? Physics first :)
		if (piecesGrabbed.size()==worldInfo.objectiveCount)
			if (!hasWon){
				System.out.println("YOU WIN :D");
				hasWon = true;
			}
	}
}
