
public class camera {
	public static double xCoord;
	public static double yCoord;
	public static double yVel = 0;
	public static void init(){
		xCoord = usefulNumbers.spawnX;
		yCoord = usefulNumbers.spawnY;
	}
	public static void update(){
		xCoord = player.xCoord;
		yCoord = player.yCoord;//How to damp this?
	}
}
