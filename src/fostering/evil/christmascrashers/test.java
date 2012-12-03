import org.lwjgl.input.Keyboard;
import java.io.*;

public class test {
	public static void testFunctions(){//Called along with "RenderDat"'s
		if (Keyboard.isKeyDown(Keyboard.KEY_K))
			player.health-=0.5;
		if (Keyboard.isKeyDown(Keyboard.KEY_L))
			System.out.println("Reloaded");
		if (Keyboard.isKeyDown(Keyboard.KEY_C))
			System.out.println(player.xCoord+"  "+player.yCoord);
	}
	public static void main(String[] args) throws InterruptedException,IOException{
		worldInfo.loadFile("/Users/Richard/Desktop/thievery/level1.txt",0,12);
		worldInfo.loadFile("/Users/Richard/Desktop/thievery/level2.txt",1,12);
		worldInfo.loadPortals("/Users/Richard/Desktop/thievery/portal.txt");
		player.init();
		renderingTest displayExample = new renderingTest();
		try {
			displayExample.start();//I lost where it prints the things' lengths :/
		} catch (IllegalStateException e){
			
		}
}}


//More powerful monsters... that's about it

