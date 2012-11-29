package fostering.evil.christmascrashers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class worldInfo {
	//Will contain locations of blocks, et cetera et cetera
	public static ArrayList<String> walls = new ArrayList<String>();
	//ARRAYLISCEPTION
	
	public int typeID;
	public int xCoord;
	public int yCoord;
	public worldInfo(int type,int x,int y){
		typeID = type;
		xCoord = x;
		yCoord = y;
	}
	
	public static ArrayList<ArrayList<worldInfo>> allWalls  = new ArrayList<ArrayList<worldInfo>>();
	
	//END ARRAYLISTCEPTION
	public static int bottomOfWorld;
	public static int objectiveCount = 0;
	public static void loadFile(String fileName,int world,double health) throws IOException{
		Scanner file = new Scanner(new File(fileName));
		boolean check = true;
		int line = 0;
		allWalls.add(new ArrayList<worldInfo>());
		String out = " ";
		boolean gremlinSequence = false;
		int gremlinStart = 0;
		int gremlinEnd;
		while (check){
			try {
				String s = file.nextLine();
				int t;
				for (t=0;t<s.length();t++){
					switch (s.charAt(t)){
					case 'X'://Solid Block
						String addMe = t+","+line+" ";
						out+=addMe;
						allWalls.get(world).add(new worldInfo(0,t,line));
						break;
					case 'Y'://Solid Block
						String addMeh = t+","+line+" ";
						out+=addMeh;//How to change color? :/
						allWalls.get(world).add(new worldInfo(1,t,line));
						break;
					case 'Z':
						String addMoi = t+","+line+" ";
						out+=addMoi;//How to change color? :/
						allWalls.get(world).add(new worldInfo(2,t,line));
						break;
					case 'S'://Spawn
						usefulNumbers.setSpawnInfo(t, line, world, health);//Change me!!!
						usefulNumbers.firstSpawnH = health;
						usefulNumbers.firstSpawnW = world;
						usefulNumbers.firstSpawnX = t;
						usefulNumbers.firstSpawnY = line;
						break;
					case 'H'://Health Pill
						grabbable.createGrabbable(0, 0, t, line, 0, 0, world);
						break;
					case 'K'://Kill Pill
						grabbable.createGrabbable(0, 0, t, line, 1, 0, world);
						break;
					case 'G':
						if (gremlinSequence == false){
							gremlinStart = t;
						} else {
							gremlinEnd = t;
							gremlin.makeGremlin(world, line, gremlinStart, gremlinEnd);
						}
						gremlinSequence = !gremlinSequence;
						break;
					case 'C': //Checkpoint
						grabbable.createGrabbable(1, 1, t, line, 5, 0, world);
						break;
					default:
						if ("0123456789!@#$%^&*()".contains(""+s.charAt(t))){
							objectiveCount++;
							grabbable.createGrabbable(1, 1, t, line, 2, s.charAt(t),world);
						} else if (Character.isLowerCase(s.charAt(0))){
							//Teleporter
							//No idea what I'm doing here yet :P, to be modified in class
						}
						break;
					}
				}
				line--;
			} catch (NoSuchElementException e){
				check = false;
			}
		}
		try {
			walls.set(world,out);
		} catch (IndexOutOfBoundsException a) {
			walls.add(out);
		}
		bottomOfWorld = line;
		usefulNumbers.backUp();
	}
	
	public static void loadPortals(String fileName) throws IOException {
		Scanner file = new Scanner(new File(fileName));
		boolean check = true;
		int count = 0;
		file.nextLine();
		while (check){
			try {
				String d = file.next();
				if (d.charAt(0)=='P'){
					int x = file.nextInt();
					int y = file.nextInt();
					int w = file.nextInt();
					int tx = file.nextInt();
					int ty = file.nextInt();
					int tw = file.nextInt();
					//System.out.println(x+" "+y+" "+w+"...."+tx+" "+ty+" "+tw);
					//What's wrong :(
					grabbable.createGrabbable(1, 1, x, y, 3, count, w);
					grabbable.portals.add(tx);grabbable.portals.add(ty);grabbable.portals.add(tw);
					count++;
				} else {
					int x = file.nextInt();
					if (x == -10)
						grabbable.portals.add(0);
					else {
						int y = file.nextInt();
						int w = file.nextInt();
						grabbable.portals.add(count);
						grabbable.createGrabbable(1, 1, x, y, 4, count, w);
					}
				}
			} catch (NoSuchElementException a) {
				check = false;
			}
		}
		usefulNumbers.backUp();
	}
}
