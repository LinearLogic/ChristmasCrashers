import java.util.*;

public class gremlin {
	public static ArrayList<gremlin> allGremlins = new ArrayList<gremlin>();
	public int world;
	public double xCoord;
	public double yCoord;
	public double speed = 1.0;
	public double leftBound;
	public double rightBound;
	public int extra;
	public static void makeGremlin(int w,double y, double l, double r,int e){
		allGremlins.add(new gremlin(w,y,l,r,e));
	}
	
	public static void moveAllGremlins(){
		int n;
		for (n = 0;n<allGremlins.size();n++){
			if (allGremlins.get(n).world==player.world)
				allGremlins.get(n).move();
		}
	}
	
	public gremlin(int w,double y, double l, double r,int e){
		yCoord = y;
		xCoord = l+0.1;
		leftBound = l;
		rightBound = r;
		speed = usefulNumbers.gremlinSpeed;
		world = w;
		extra = e;
		//TBD From File Read
	}
	
	public void move(){//It would be very easy to modify this to accept vertically or diagonally moving gremlins
		if ((xCoord <= leftBound)||(xCoord >= rightBound))
			speed*=-1;
		xCoord += (speed/usefulNumbers.framerate);
	}
}
