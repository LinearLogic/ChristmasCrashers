import java.util.*;
public class usefulNumbers {
	public static double framerate = 25;
	public static double width = 12;
	public static double height = 9;//Half the actual height and width
	public static int lives = 10;
	public static boolean canHasDjump = false;
	//Number of frames per second
	//NOT 0.04
	public static double spawnX=1,spawnY=10,spawnHealth=10;
	public static int spawnWorld=0;
	public static ArrayList <Integer> keys = new ArrayList<Integer>();
	public static ArrayList <Boolean> backup = new ArrayList <Boolean>();
	public static double H = spawnHealth;
	public static void kbu(){
		keys = new ArrayList<Integer>();
		int t;
		for (t = 0;t<plot.keys.size();t++){
			keys.add(plot.keys.get(t));
		}
		//System.out.println(keys.size());
	}
	public static void kubu(){
		plot.keys = new ArrayList<Integer>();
		int t;
		for (t=0;t<keys.size();t++){
			plot.keys.add(keys.get(t));
		}
		//System.out.println(keys.size());
		//System.out.println(plot.keys.size());
	}
	public static void backUp(){
		int t;
		backup = new ArrayList<Boolean>();
		for (t=0;t<grabbable.allGrabbables.size();t++){
			backup.add(grabbable.allGrabbables.get(t).exists);//NEED TO EMPTY ANY PLOT CACHES
		}
	}
	public static void unBackUp(){
		int t;
		for (t=0;t<backup.size();t++){
			grabbable.allGrabbables.get(t).exists=backup.get(t);
		}
	}
	public static void setSpawnInfo (double x, double y, int w, double h) {
		spawnX = x;
		spawnY = y;
		spawnWorld = w;
		H = h;
	}
	public static double jumpConstant = 30;
	public static double playerSpeed = 10;
	public static double airSpeed = 5;
	public static double gravity = 3;
	public static double gremlinSpeed = 3;
	public static double gremlinAttack = 1;
	
	public static double firstSpawnX = 0;public static double firstSpawnY = 0;
	public static double firstSpawnH = 0;public static int firstSpawnW = 0;
}

//Notes on item types
//0 is an element of dinner
//1 is something which kills you, :(
//2 is a checkpoint
//3 is a health pill

//Extra ints will differentiate what they look like