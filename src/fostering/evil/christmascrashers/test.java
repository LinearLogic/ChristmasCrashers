package fostering.evil.christmascrashers;

import java.io.File;
import java.io.IOException;

import org.lwjgl.input.Keyboard;

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
		worldInfo.loadFile("files" + File.separator + "level1.txt",0,12);
		worldInfo.loadFile("files" + File.separator + "level2.txt",1,12);
		worldInfo.loadPortals("files" + File.separator + "portal.txt");
		player.init();
		renderingTest displayExample = new renderingTest();
		displayExample.start();//I lost where it prints the things' lengths :/
}}


//DO ME
//Menu Screen
//"You Died" Screen
//Fewer lives (parameter?)
//More difficult muensters
//Bosses
//Plot... load sprites for all the dinner pieces....
//Slide on ice
//Modify jump physics (damping, double jump)