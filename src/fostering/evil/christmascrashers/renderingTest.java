import org.lwjgl.LWJGLException;
import java.util.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import org.newdawn.slick.opengl.*;
import org.newdawn.slick.util.ResourceLoader;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.*;


public class renderingTest {
	
	//TEXTURES
	public static Texture block1;
	public static Texture block2;
	public static Texture block3;
	
	public static Texture gremlin1;
	public static Texture gremlin2;
	public static Texture player1;
	
	public static Texture health;
	public static Texture damage;
	public static Texture portal;
	public static Texture key;
	public static Texture spring;
	
	public static Texture win;
	public static Texture lose;
	public static Texture menu;
	
	public static Texture target;//Change me to an array list!!!
	//public static ArrayList<Texture> goals = new ArrayList<Texture>();
	//END TEXTURES
	
	public static void initAllTheTextures() throws IOException {
		target = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("/Users/Richard/Desktop/thievery/target.png"));
		health = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("/Users/Richard/Desktop/thievery/health.png"));
		damage = TextureLoader.getTexture("GIF", ResourceLoader.getResourceAsStream("/Users/Richard/Desktop/thievery/damage.gif"));
		portal = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("/Users/Richard/Desktop/thievery/portal.png"));
		key = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("/Users/Richard/Desktop/thievery/key.png"));
		gremlin1 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("/Users/Richard/Desktop/thievery/gremlin1.png"));
		gremlin2 = TextureLoader.getTexture("TIFF", ResourceLoader.getResourceAsStream("/Users/Richard/Desktop/thievery/gremlin2.tiff"));
		player1 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("/Users/Richard/Desktop/thievery/player.png"));
		block1 = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("/Users/Richard/Desktop/thievery/block1.jpg"));
		block2 = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("/Users/Richard/Desktop/thievery/block2.jpg"));
		block3 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("/Users/Richard/Desktop/thievery/block3.png"));
		win = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("/Users/Richard/Desktop/thievery/win.png"));
		lose = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("/Users/Richard/Desktop/thievery/lose.png"));
		menu = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("/Users/Richard/Desktop/thievery/menu.png"));
		spring = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("/Users/Richard/Desktop/thievery/spring.png"));
	}
	
	
	
	public static void renderDemBoxes(){
		int q = 0;
		boolean check = true;
		while (check){
			try {
				//YO RICHARD
				worldInfo w = worldInfo.allWalls.get(player.world).get(q);q++;
				float x1 = w.xCoord;
				float y1 = w.yCoord;
				TextureImpl.bindNone();
				switch(w.typeID){
				case 0:
					block1.bind();
					break;
				case 1:
					block2.bind();
					break;
				case 2:
					block3.bind();//This is very, very odd :(
					break;
				}
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2f(0,1);
	            GL11.glVertex2f(x1,y1);
	            GL11.glTexCoord2f(0,0);
	            GL11.glVertex2f(x1,y1+1);
	            GL11.glTexCoord2f(1,0);
	            GL11.glVertex2f(x1+1,y1+1);
	            GL11.glTexCoord2f(1,1);
	            GL11.glVertex2f(x1+1,y1);
	            GL11.glEnd();
			} catch(IndexOutOfBoundsException e){
				check = false;
			}
		}
		TextureImpl.bindNone();
	}
	
	
	public boolean inMenu = true;
	public boolean inLose = false;
	public boolean inWin = false;
	public boolean carlos = false;
	
	public static void renderDatScreen(int type){
		float x = (float)player.xCoord;
		float y = (float)player.yCoord;
		float l = (float)(x-usefulNumbers.width);
		float r = (float)(x+usefulNumbers.width);
		float t = (float)(y+usefulNumbers.height);
		float b = (float)(y-usefulNumbers.height);
		TextureImpl.bindNone();
		switch (type){
		case 0:
			menu.bind();
			break;
		case 1:
			win.bind();
			break;
		case 2:
			lose.bind();
			break;
		}
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0,1);
        GL11.glVertex2f(l,b);
        GL11.glTexCoord2f(0,0);
        GL11.glVertex2f(l,t);
        GL11.glTexCoord2f(1,0);
        GL11.glVertex2f(r,t);
        GL11.glTexCoord2f(1,1);
        GL11.glVertex2f(r,b);
        GL11.glEnd();
	}
	
	public static void renderDemGremlins(){
		int n;
		for (n=0;n<gremlin.allGremlins.size();n++){
			if (gremlin.allGremlins.get(n).world==player.world){
				float x = (float)(gremlin.allGremlins.get(n).xCoord);
				float y = (float)(gremlin.allGremlins.get(n).yCoord);
				TextureImpl.bindNone();
				if (gremlin.allGremlins.get(n).extra == 0){
					TextureImpl.bindNone();
					gremlin1.bind();
				}
				else if (gremlin.allGremlins.get(n).extra == 1){
					TextureImpl.bindNone();
					gremlin2.bind();
				}
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2f(0,1);
	            GL11.glVertex2f(x,(float)(y));
	            GL11.glTexCoord2f(0,0);
	            GL11.glVertex2f(x,(float)(y+1));
	            GL11.glTexCoord2f(1,0);
	            GL11.glVertex2f((float)(x+1),(float)(y+1));
	            GL11.glTexCoord2f(1,1);
	            GL11.glVertex2f((float)(x+1),(float)(y));
	            GL11.glEnd();
	            TextureImpl.bindNone();
			}
		}
	}
	
	public static void renderDemGrabbables(){
		Color.white.bind();
		health.bind();
		int n;
		int q = 1;
		for (n=0;n<grabbable.allGrabbables.size();n++){
			grabbable g = grabbable.allGrabbables.get(n);
			//What the hedge?
			if (g.exists && g.world==player.world && g.type!=5){
				TextureImpl.bindNone();
				float x = (float)(g.xCoord);
				float y = (float)(g.yCoord);
				//Just as examples... Type 0 is purple, Type 1 is yellow, type 2 is orange, type 3 is green
				switch(g.type){
				case 0:
					health.bind();
					break;
				case 1:
					damage.bind();
					break;
				case 2:
					target.bind();
					break;
				case 3:
					portal.bind();
					break;
				case 4:
					key.bind();
					break;
				case 6:
					spring.bind();
					break;
				}
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2f(0,1);
	            GL11.glVertex2f(x,(float)(y));
	            GL11.glTexCoord2f(0,0);
	            GL11.glVertex2f(x,(float)(y+1));
	            GL11.glTexCoord2f(1,0);
	            GL11.glVertex2f((float)(x+1),(float)(y+1));
	            GL11.glTexCoord2f(1,1);
	            GL11.glVertex2f((float)(x+1),(float)(y+0));
	            GL11.glEnd();
			}
		}
	}
	
	
	public static void renderDatHealth(){
		if (player.health>usefulNumbers.spawnHealth)
			player.health = usefulNumbers.spawnHealth;
		TextureImpl.bindNone();
		double a = usefulNumbers.width;
		double b = usefulNumbers.height;
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor3f(1.0f, 1.0f, 1.0f);
        GL11.glVertex2f((((float)(camera.xCoord-(a-0.25)))),(((float)(camera.yCoord+(b-0.25)))));
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        GL11.glVertex2f((((float)(camera.xCoord-(float)(a-6.75)))),(((float)(camera.yCoord+(b-0.25)))));
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        GL11.glVertex2f((((float)(camera.xCoord-(a-6.75)))),(((float)(camera.yCoord+(b-1.75)))));
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        GL11.glVertex2f((((float)(camera.xCoord-(a-0.25)))),(((float)(camera.yCoord+(b-1.75)))));
        //End frame
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor3f(0.0f, 0.0f, 0.0f);
        GL11.glVertex2f((((float)(camera.xCoord-(a-0.5)))),(((float)(camera.yCoord+(b-0.5)))));
        GL11.glColor3f(0.0f, 0.0f, 0.0f);
        GL11.glVertex2f((((float)(camera.xCoord-(a-6.5)))),(((float)(camera.yCoord+(b-0.5)))));
        GL11.glColor3f(0.0f, 0.0f, 0.0f);
        GL11.glVertex2f((((float)(camera.xCoord-(a-6.5)))),(((float)(camera.yCoord+(b-1.5)))));
        GL11.glColor3f(0.0f, 0.0f, 0.0f);
        GL11.glVertex2f((((float)(camera.xCoord-(a-0.5)))),(((float)(camera.yCoord+(b-1.5)))));
        //Actual bar
        float ratio = (float)(player.health)/(float)(usefulNumbers.spawnHealth);
        float n = (float)(-(usefulNumbers.width-0.5)+(6.0*ratio));
        //Convert actual health
        GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor3f(1.0f, 0.0f, 1.0f);
        GL11.glVertex2f((((float)(camera.xCoord-(a-0.5)))),(((float)(camera.yCoord+(b-0.5)))));
        GL11.glVertex2f((((float)(camera.xCoord+n))),(((float)(camera.yCoord+(b-0.5)))));
        GL11.glVertex2f((((float)(camera.xCoord+n))),(((float)(camera.yCoord+(b-1.5)))));
        GL11.glVertex2f(((float)(camera.xCoord-(a-0.5))),(((float)(camera.yCoord+(b-1.5)))));
		GL11.glEnd();
	}
	
	public static void renderDemLives(){
		double a = usefulNumbers.width;
		double b = usefulNumbers.height;
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor3f(1.0f, 1.0f, 1.0f);
        GL11.glVertex2f((((float)(camera.xCoord+(a-0.25)))),(((float)(camera.yCoord+(b-0.25)))));
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        GL11.glVertex2f((((float)(camera.xCoord+(a-10.75)))),(((float)(camera.yCoord+(b-0.25)))));
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        GL11.glVertex2f((((float)(camera.xCoord+(a-10.75)))),(((float)(camera.yCoord+(b-1.75)))));
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        GL11.glVertex2f((((float)(camera.xCoord+(a-0.25)))),(((float)(camera.yCoord+(b-1.75)))));
        //End frame
		GL11.glBegin(GL11.GL_QUADS);
		if (usefulNumbers.lives-player.deaths<=usefulNumbers.lives/4)
			GL11.glColor3f(1.0f, 0.0f, 0.0f);
		else if (usefulNumbers.lives-player.deaths<=usefulNumbers.lives/2)
			GL11.glColor3f(1.0f, 1.0f, 0.0f);
		else
			GL11.glColor3f(0.0f, 1.0f, 0.0f);
        GL11.glVertex2f((((float)(camera.xCoord+(a-0.5)))),(((float)(camera.yCoord+(b-0.5)))));
        GL11.glVertex2f((((float)(camera.xCoord+(a-10.5)))),(((float)(camera.yCoord+(b-0.5)))));
        GL11.glVertex2f((((float)(camera.xCoord+(a-10.5)))),(((float)(camera.yCoord+(b-1.5)))));
        GL11.glVertex2f((((float)(camera.xCoord+(a-0.5)))),(((float)(camera.yCoord+(b-1.5)))));
        //Actual bar
        float ratio = (usefulNumbers.lives-player.deaths)/(float)(usefulNumbers.lives);
        float n = (float)((a-10.5)+(10.0*ratio));
        //Convert actual health
        GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor3f(0.0f, 0.0f, 0.0f);
        GL11.glVertex2f((((float)(camera.xCoord+(a-0.5)))),(((float)(camera.yCoord+(b-0.5)))));
        GL11.glColor3f(0.0f, 0.0f, 0.0f);
        GL11.glVertex2f((((float)(camera.xCoord+n))),(((float)(camera.yCoord+(b-0.5)))));
        GL11.glColor3f(0.0f, 0.0f, 0.0f);
        GL11.glVertex2f((((float)(camera.xCoord+n))),(((float)(camera.yCoord+(b-1.5)))));
        GL11.glColor3f(0.0f, 0.0f, 0.0f);
        GL11.glVertex2f(((float)(camera.xCoord+(a-0.5))),(((float)(camera.yCoord+(b-1.5)))));
		GL11.glEnd();
	}
	
	public static void totalRestart(){
		usefulNumbers.setSpawnInfo(usefulNumbers.firstSpawnX,usefulNumbers.firstSpawnY, usefulNumbers.firstSpawnW, usefulNumbers.firstSpawnH);
		player.respawn();
		plot.keys=new ArrayList<Integer>();
		int t;
		for (t=0;t<grabbable.allGrabbables.size();t++){
			grabbable.allGrabbables.get(t).exists=true;//NEED TO EMPTY ANY PLOT CACHES
		}
		plot.piecesGrabbed=new ArrayList<Integer>();
		usefulNumbers.backUp();
		usefulNumbers.kbu();
		player.deaths = 0;
	}
	
	public void start() throws InterruptedException,IOException {
		try {
			Display.setDisplayMode(new DisplayMode(1280,800));
			Display.create();
			Display.setTitle("Christmas Crashers");
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		initAllTheTextures();
		camera.init();
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glLoadIdentity();
        double a = usefulNumbers.height;
        double b = usefulNumbers.width;
        GL11.glOrtho(((float)(camera.xCoord-b)),((float)(camera.xCoord+b)),((float)(camera.yCoord-a)),((float)(camera.yCoord+a)), -1, 1);//W,H,-1,1
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        float n;
        float m;
		while (!Display.isCloseRequested()) {
			grabbable.justPortaled--;//Don't touch me
			grabbable.justPortaled%=100;
			//Stuff for Thievery
			player.move();
			player.input();
			//System.out.println(player.xCoord);
			gremlin.moveAllGremlins();
			Thread.sleep(40);
			player.checkGremlins();
			player.checkGrabbables();
			//End Thievery
			//Translation
			m = (float)(player.xCoord);
			n = (float)(player.yCoord);
			//End translation
			camera.update();
			GL11.glMatrixMode(GL11.GL_PROJECTION);
	        GL11.glLoadIdentity();
	        GL11.glOrtho(((float)(camera.xCoord-b)),((float)(camera.xCoord+b)),((float)(camera.yCoord-a)),((float)(camera.yCoord+a)), -1, 1);//W,H,-1,1
	        GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			test.testFunctions();//DELETE ME
			renderingTest.renderDemBoxes();
			renderingTest.renderDemGrabbables();
			renderingTest.renderDemGremlins();
			renderingTest.renderDatHealth();
			renderingTest.renderDemLives();
			plot.checkPlot();
			//Floor
			/*
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glColor3f(0.0f, 1.0f, 0.0f);
            GL11.glVertex2f(-10, 1);
            GL11.glColor3f(0.0f, 1.0f, 0.0f);
            GL11.glVertex2f(10, 1);
            GL11.glColor3f(0.0f, 1.0f, 0.0f);
            GL11.glVertex2f(10, -0);
            GL11.glColor3f(0.0f, 1.0f, 0.0f);
            GL11.glVertex2f(-10, -0);
            GL11.glEnd();
            */
            
			//Triangles
			TextureImpl.bindNone();
			Color.white.bind();
			player1.bind();
			GL11.glBegin(GL11.GL_QUADS);
            // Top & Red
			GL11.glTexCoord2f(0,1);
            GL11.glVertex2f((m), (n));

            // Right & Green
            GL11.glTexCoord2f(1,1);
            GL11.glVertex2f((m), (n+1));

            // Left & Blue
            GL11.glTexCoord2f(1,0);
            GL11.glVertex2f((m+1), (n+1));
            //Second
            // Right & Green
            GL11.glTexCoord2f(0,0);
            GL11.glVertex2f((m+1), (n));
            
            //Special thing for menu screen
            GL11.glEnd();
            
            //MENU STUFFS :P
            
            if (plot.hasWon && !carlos){
            	inWin = true;
            	carlos = true;
            }
            
            if (inMenu){
            	if (Keyboard.isKeyDown(Keyboard.KEY_Q)){
            		//System.out.println("MENU");
            		Display.destroy();
            	}
            	if (Keyboard.isKeyDown(Keyboard.KEY_S)){
            		inMenu = false;
            		inWin = false;
            		inLose = false;
            		carlos = false;
            		plot.hasWon = false;
            		renderingTest.totalRestart();
            	}
            	renderDatScreen(0);
            }
            
            if (inWin){
            	if (Keyboard.isKeyDown(Keyboard.KEY_Q)){
            		//System.out.println("WIN");
            		Display.destroy();
            	}
            	if (Keyboard.isKeyDown(Keyboard.KEY_S)){
            		inWin = false;
            		inMenu = true;
            		Thread.sleep(125);
            	}
            	renderDatScreen(1);
            }
            
            if (inLose){
            	renderDatScreen(2);
            	if (Keyboard.isKeyDown(Keyboard.KEY_Q)){
            		//System.out.println("LOSE");
            		Display.destroy();
            	}
            	if (Keyboard.isKeyDown(Keyboard.KEY_S)){
            		inLose = false;
            		inMenu = true;
            		Thread.sleep(125);
            	}
            }
            
            
            if (Keyboard.isKeyDown(Keyboard.KEY_Q)||player.deaths==usefulNumbers.lives){
            	player.deaths = 0;
            	inLose = true;
            	Thread.sleep(125);
            }
            //end special thing for menu screen
            
   
			Display.update();
		}
			Display.destroy();
		}
}
