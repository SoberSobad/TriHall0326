import greenfoot.*;


public class GameObject extends Actor
{
    protected int x, y;   //x,y is coordinate to camera   
    protected double  xx, yy, lastXX, lastYY;    //xx,yy is coordinate to room
    protected GreenfootImage invisible;
    protected GreenfootImage image;
    protected Room room;
    Camera camera;
    
    protected boolean visible;     //debug
    
    public GameObject(int xx, int yy, Room room){
        this.xx = xx;
        this.yy = yy;
        this.room = room;
        this.camera = room.getCamera();
        image = getImage();
        invisible = new GreenfootImage(getImage().getWidth(),getImage().getHeight());
    }
    
    protected void camera(){
        if( (yy > camera.getYY() + (room.camHeight/2)) || (yy < camera.getYY() - (room.camHeight/2)) ||
            (xx > camera.getXX() + (room.camWidth/2))  || (xx < camera.getXX() - (room.camWidth/2))   )   {
            setImage(invisible);
            visible = false;
        }
        else{
            setImage(image);
            x = room.camWidth/2 + (((int)Math.floor(xx))-camera.getXX());
            y = room.camHeight/2 + (((int)Math.floor(yy))-camera.getYY());
            setLocation(x,y);
            visible = true;
        }
    }
    protected void calXY(double xx, double yy){
        x = room.camWidth/2 + (((int)Math.floor(xx))-camera.getXX());
        y = room.camHeight/2 + (((int)Math.floor(yy))-camera.getYY());
    }
    
    public void act() 
    {
        camera();
        
    }    
}
