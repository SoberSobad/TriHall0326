import greenfoot.*;
//import java.io.PrintWriter;
//import java.io.FileOutputStream;
//import java.io.FileNotFoundException;
import java.io.*;
import java.util.List;

public class Room extends World
{

    public static final int roomWidth = 1280;
    public static final int roomHeight = 5000;
    public static final int camWidth = 1280;
    public static final int camHeight = 720;
    private Camera camera = new Camera(roomWidth/2, 5000, this);
    private Player player = new Player(roomWidth/2, 4500, this);
    private int mouseX, mouseY;
    MouseInfo mouse;
    
    Graph playerXspdGraph = new Graph(25,camHeight-600,this,225,200,12,0);      //debug
    Graph playerYspdGraph = new Graph(25,camHeight-400,this,225,200,12,-12);      //debug
    
    public Room()
    {    
        super(camWidth, camHeight, 1); 
        addObject(camera, camWidth/2, camHeight/2);
        /*
        for(int i=25 ; i<=roomWidth ; i+=50){
            Wall wall = new Wall(i,roomHeight-25,this);
            addObject(wall,0,0);
        }*/
        Wall wall = new Wall(roomWidth/2,roomHeight-25,this);
        wall.getImage().scale(roomWidth,50);
        addObject(wall,0,0);
        wall = new Wall(300,4765,this);
        addObject(wall,0,0);
        wall = new Wall(940,4840,this);
        addObject(wall,0,0);
        
        addObject(player,0,0);
        addObject(playerXspdGraph,50,camHeight-400);
        addObject(playerYspdGraph,50,camHeight-400);
    }
    
    public int getRoomWidth(){
        return roomWidth;
    }
    public int getRoomHeight(){
        return roomHeight;
    }
    public Camera getCamera(){
        return camera;
    }
    public void exportRoom(String str){
        try{//FileOutputStream out = new FileOutputStream(str+".txt");
            FileWriter out = new FileWriter(str+".txt");
            PrintWriter txt = new PrintWriter(out);
            txt.flush();
            
            List<Wall> allWall = getObjects(Wall.class);
            for(int i=0 ; i<allWall.size() ; i++){
                txt.println();
            }
            txt.close();
        //}catch(FileNotFoundException e){}
        }catch(IOException e){}
        
        //List<Wall> allWall = getObjects(Wall.class);
    }
    
    public void act(){
        showText(camera.getXX()+", "+camera.getYY(), getWidth()/2, (getHeight()/2)+20);      //debug
        showText(mouseX+", "+mouseY, getWidth()/2, (getHeight()/2)+40);
        showText(player.getXX()+"",100,100);
        playerXspdGraph.update(Math.abs((int)Math.floor(player.getXspd())));        //debug
        playerYspdGraph.update( (int)Math.floor(player.getYspd()) *-1 );        //debug
        mouse = Greenfoot.getMouseInfo();
        if(mouse!=null){
           mouseX = mouse.getX();
           mouseY = mouse.getY();
        }
        if(Greenfoot.mouseClicked(null)){
            Wall wall = new Wall(camera.getXX()-(camWidth/2)+mouseX, camera.getYY()-(camHeight/2)+mouseY,this);
            addObject(wall,0,0);
        }
        
        if(Greenfoot.isKeyDown("e")){
            exportRoom("TestRoom");
        }
    }
    
}
