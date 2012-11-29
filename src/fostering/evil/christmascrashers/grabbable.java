package fostering.evil.christmascrashers;

//Contains all the stuff you can pick up
import java.util.ArrayList;

public class grabbable {
	public static int piecesOfDinner = 0;
	public int type;//There will be a switch later on
	public int extra;//Any other stuff, for instance what dinner component something is
	public double width;
	public double height;
	public double xCoord;
	public double yCoord;
	public int world;
	public boolean exists;
	public static ArrayList<grabbable> allGrabbables = new ArrayList<grabbable>();
	public grabbable(double w,double h, double x, double y, int t, int e,int world_){
		width = w;
		height = h;
		xCoord = x;
		yCoord = y;
		type = t;
		extra = e;
		world = world_;
		exists = true;
	}
	public static void createGrabbable(double w,double h, double x, double y, int t, int e,int wo){
		allGrabbables.add(new grabbable(w,h,x,y,t,e,wo));
	}
	
	public static ArrayList<Integer> portals = new ArrayList<Integer>();
	public static int justPortaled = 0;
	
	public void playerPickUp(int index){
		//Switch
		grabbable g = grabbable.allGrabbables.get(index);
		switch(g.type){
		case 0:
			player.health+=3;
			if (player.health>usefulNumbers.spawnHealth)
				player.health=usefulNumbers.spawnHealth;
			grabbable.allGrabbables.get(index).exists=false;
			break;
		case 1:
			player.health-=1000;
			grabbable.allGrabbables.get(index).exists=false;
			break;
		case 2:
			System.out.println("Congrats, you got a piece of dinner!!!");
			grabbable.allGrabbables.get(index).exists=false;
			plot.piecesGrabbed.add(g.extra);
			break;
		case 3:
			if (justPortaled <= 0 && ((portals.get(g.extra*4+3))==0 || (plot.keys.contains(portals.get(g.extra*4+3))))){
				double targetX = portals.get(g.extra*4+0);
				double targetY = portals.get(g.extra*4+1);
				int targetWorld= portals.get(g.extra*4+2);
				player.changePlace(targetX, targetY, targetWorld);
				System.out.println("Teleporting");
				justPortaled = 48;//2 seconds
			}
				//if (!(plot.keys.contains(portals.get(g.extra*4+3))))
				//	System.out.println("YOU AINT GOT NO PANCAKE MIX");
				//System.out.print("X");
			break;
		case 4:
			plot.keys.add(g.extra);
			System.out.println("You picked up a key!");
			g.exists=false;
			break;
		case 5:
			usefulNumbers.setSpawnInfo(player.xCoord, player.yCoord, player.world, player.health);
			usefulNumbers.keys = new ArrayList<Integer>(plot.keys);
			usefulNumbers.backUp();
			usefulNumbers.kbu();
			break;
		}
	}
}//Now we just have to sort out collisions
