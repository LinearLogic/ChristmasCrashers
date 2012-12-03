import java.util.*;

public class lineFunctions {
	public static void moveToIntersection(){
		ArrayList<Integer> futureBoxes = project();
		String walls = worldInfo.walls.get(player.world);
		int n;//This is just going to deal with walls
		//Still possible to glitch through other things... but they usually lurk on floors
		for (n=0;n<futureBoxes.size();n+=2){
			int x = futureBoxes.get(n);
			int y = futureBoxes.get(n+1);
			String checkMe = " "+x+","+y+" ";
			if (walls.contains(checkMe)){
				player.yCoord = y+1;
			}
		}
	}
	
	public static ArrayList<Integer> project(){
		ArrayList<Integer> out = new ArrayList<Integer>();//Ordered pairs, more or less
		//This will probably work without too many bugs
		double yV = player.yVel;
		double t = usefulNumbers.framerate;
		double frameTime = 1.0/t;
		double startY = player.yCoord;
		double endY = (startY+(yV*frameTime));
		if (endY < startY){//Only really needed when going downhill :)
			long n;
			for (n = Math.round(startY);n>endY;n--){
				int x = (int)Math.round(player.xCoord);
				int y = (int)(n);
				out.add(x);out.add(y);
			}
		}
		return out;
	}
}
