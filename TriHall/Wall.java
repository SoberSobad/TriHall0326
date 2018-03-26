import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Wall extends GameObject
{
    boolean begin = false;
    
    public Wall(int xx, int yy, Room room){
        super(xx,yy,room);
        //getWorld().addObject(new Wall((int)Math.floor(xx-50),(int)Math.floor(yy-50),room),0,0);
            
    }
    
    
    public void act() 
    {
        if(!begin){
            Text position = new Text((int)Math.floor(xx-50),(int)Math.floor(yy-40),room,xx+", "+yy);
            //position = new Text(50,50,room,"KaBoom !!");
            room.addObject(position,0,0);
            //Wall newWall = new Wall((int)Math.floor(xx-50),(int)Math.floor(yy-50),room);
            //getWorld().addObject(new Wall((int)Math.floor(xx-50),(int)Math.floor(yy-50),room),0,0);
            begin = true;
        }
        camera();
    }    
}
