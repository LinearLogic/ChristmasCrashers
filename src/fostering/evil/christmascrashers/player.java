import org.lwjgl.input.Keyboard;

public class player {
	
	public static int deaths = 0;
	
	public static double xCoord;
	public static double yCoord;
	public static int world;
	public static double health;
	public static double xVel = 0;
	public static double yVel = 0;
	public static void init(){//Only done once at the beginning
		xCoord = usefulNumbers.spawnX;
		yCoord = usefulNumbers.spawnY;
		world = usefulNumbers.spawnWorld;
		health = usefulNumbers.spawnHealth;
	}
	
	public static void respawn(){//If you die...
		xCoord = usefulNumbers.spawnX;
		yCoord = usefulNumbers.spawnY;
		world = usefulNumbers.spawnWorld;
		health = usefulNumbers.H;
		xVel = 0;
		yVel = 0;
		usefulNumbers.kubu();
		usefulNumbers.unBackUp();
		deaths++;
	}
	
	public static void changePlace(double x,double y,int w){//If you die...
		xCoord = x;
		yCoord = y;
		world = w;
		xVel = 0;
		yVel = 0;
	}
	
	public static int dJump = 0;
	public static boolean hasDJumped = false;
	public static boolean hasBeenReleased = true;
	
	public static void input(){
		xVel = 0;
		//Whatever the heck we do here...
		if ((Keyboard.isKeyDown(Keyboard.KEY_W) && ((hasWallBelow())||(dJump >= 8 && !hasDJumped && hasBeenReleased)))){
			yVel=usefulNumbers.jumpConstant;
			if (dJump >= 8 && !hasDJumped)
				hasDJumped = true;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D))
			xVel=usefulNumbers.playerSpeed;
		//if (Keyboard.isKeyDown(Keyboard.KEY_D)&&!hasWallBelow())
		//	xVel=0.5*usefulNumbers.playerSpeed;
		//if (Keyboard.isKeyDown(Keyboard.KEY_D)&&(!(hasWallBelow())))
		//	xVel=usefulNumbers.airSpeed;
		if (Keyboard.isKeyDown(Keyboard.KEY_A))
			xVel=-1*usefulNumbers.playerSpeed;
		//if (Keyboard.isKeyDown(Keyboard.KEY_A)&&!hasWallBelow())
		//	xVel=-0.5*usefulNumbers.playerSpeed;
		//if (Keyboard.isKeyDown(Keyboard.KEY_A)&&(!(hasWallBelow())))
		//	xVel=-1*usefulNumbers.airSpeed;
		//gravity
		yVel-=usefulNumbers.gravity;
		
		//The stuff is in here...
	}
	
	public static void move(){
		if (!usefulNumbers.canHasDjump)
			hasDJumped = true;
		lineFunctions.moveToIntersection();
		if (hasWallAbove()&&(yVel>0)){//Define me!!!
			yVel = 0;
			yCoord=Math.round(yCoord);//So they're not hovering awkwardly
		}
		if (hasWallBelow()&&(yVel<0)){
			hasDJumped = false;
			hasBeenReleased = false;
			dJump = 0;
			yVel = 0;
			yCoord=Math.round(yCoord);//So they're not hovering awkwardly
		} else {
			dJump++;
			if (!(Keyboard.isKeyDown(Keyboard.KEY_W)))
				hasBeenReleased = true;
		}
		if (hasWallRight()&&(xVel>0)){//Remember, right means #'s go up
			xVel = 0;
		}
		if (hasWallLeft() &&(xVel<0)){
			xVel = 0;
		}
		xCoord =xCoord+(xVel/usefulNumbers.framerate);
		yCoord = yCoord+(yVel/usefulNumbers.framerate);//WHY WONT IT ADD
		if ((yCoord < (worldInfo.bottomOfWorld-10))||(player.health<=0))
			player.die();
		if (!usefulNumbers.canHasDjump)
			hasDJumped = true;
	}
	
	public static int justGotHurt = 0;
	
	
	public static void checkGremlins(){
		//Check and respond to hitting gremlins
		long px = Math.round(player.xCoord);
		long py = Math.round(player.yCoord);
		int n;
		for (n=0;n<gremlin.allGremlins.size();n++){
			if (gremlin.allGremlins.get(n).world==player.world){
			long x = Math.round(gremlin.allGremlins.get(n).xCoord);
			long y = Math.round(gremlin.allGremlins.get(n).yCoord);
			if ((py == y)&&(px == x)){
				justGotHurt%=24;
				if (justGotHurt==0 && gremlin.allGremlins.get(n).extra == 0)
					player.health-=usefulNumbers.gremlinAttack;
				else if (justGotHurt==0 && gremlin.allGremlins.get(n).extra == 1)
					player.health-=3*usefulNumbers.gremlinAttack;
				else
					justGotHurt++;
			}
			}
		}
	}
	
	public static void checkGrabbables(){
		//Check and respond to hitting grabbables
		long px = Math.round(player.xCoord);
		long py = Math.round(player.yCoord);
		int n;
		for (n=0;n<grabbable.allGrabbables.size();n++){
			if (grabbable.allGrabbables.get(n).world==player.world){
			long x = Math.round(grabbable.allGrabbables.get(n).xCoord);
			long y = Math.round(grabbable.allGrabbables.get(n).yCoord);
			if (((py == y)&&(px == x))&&grabbable.allGrabbables.get(n).exists){
				grabbable.allGrabbables.get(n).playerPickUp(n);
			}
			}
		}
	}
	
	//Collision detectors :)... set up string such that there's halfblocks, etc?
	//Recurring problem: How to check for walls which are > 1 block below us?   find " 0"...idk
	public static boolean hasWallAbove(){
		String x = Long.toString(Math.round(xCoord));
		String x1 = Long.toString(Math.round(xCoord-0.45));
		String x2 = Long.toString(Math.round(xCoord+0.45));
		String y = Long.toString(Math.round(yCoord+1));
		String checkMe = " "+x+","+y+" ";
		String checkMe1 = " "+x1+","+y+" ";
		String checkMe2 = " "+x2+","+y+" ";
		String walls = worldInfo.walls.get(world);
		if (walls.contains(checkMe)||walls.contains(checkMe1)||walls.contains(checkMe2))
			return true;
		return false;
	}
	public static boolean hasWallRight(){
		String x = Long.toString(Math.round(xCoord+0.75));
		String y = Long.toString(Math.round(yCoord));
		String walls = worldInfo.walls.get(world);
		String checkMe = " "+x+","+y+" ";
		if (walls.contains(checkMe))
			return true;
		return false;
	}
	public static boolean hasWallLeft(){
		String x = Long.toString(Math.round(xCoord-0.75));
		String y = Long.toString(Math.round(yCoord));
		String checkMe = " "+x+","+y+" ";
		String walls = worldInfo.walls.get(world);
		if (walls.contains(checkMe))
			return true;
		return false;
	}
	public static boolean hasWallBelow(){
		String x = Long.toString(Math.round(xCoord));
		String x1 = Long.toString(Math.round(xCoord-0.45));
		String x2 = Long.toString(Math.round(xCoord+0.45));
		String y = Long.toString(Math.round(yCoord-1));
		String walls = worldInfo.walls.get(world);
		String checkMe = " "+x+","+y+" ";
		String checkMe1 = " "+x1+","+y+" ";
		String checkMe2 = " "+x2+","+y+" ";
		if (walls.contains(checkMe)||walls.contains(checkMe1)||walls.contains(checkMe2))
		{
			return true;
		}
		return false;
	}
	public static void die(){
		//System.out.println("YOU DIED");
		player.respawn();
	}
}
