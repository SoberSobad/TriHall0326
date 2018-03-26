import greenfoot.*; 


public class Camera extends Actor
{
    private Room room;
    private int xx, yy;
    private int roomWidth, roomHeight;
    
    public Camera(int xx, int yy, Room room){
        this.room = room;
        this.xx = xx;
        this.yy = yy;
        this.roomWidth = room.getRoomWidth();
        this.roomHeight = room.getRoomHeight();
        setLocation(xx, yy);
    }
    
    public int getXX(){     //return position coordinate to room width
        return xx;
    }
    public int getYY(){     //return position coordinate to room height
        return yy;
    }
    private void boundary(){
        if(xx < room.camWidth /2) xx = room.camWidth /2;
        if(yy < room.camHeight/2) yy = room.camHeight/2;
        if(xx > roomWidth - (room.camWidth/2)) xx = roomWidth - (room.camWidth/2);
        if(yy > roomHeight - (room.camHeight/2)) yy = roomHeight - (room.camHeight/2);
    }
    
    public void act() 
    {
        if(Greenfoot.isKeyDown("s")) yy += 5;
        if(Greenfoot.isKeyDown("w")) yy -= 5;
        
        boundary();
    }
}
