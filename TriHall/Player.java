import greenfoot.*;


public class Player extends GameObject
{
    private double xspeed, yspeed, friction = 0.25, gravity = 0.5;
    private int xspdLimit = 12, yspdLimit = 12;
    private Text privateTxt;
    
    public Player (int xx, int yy, Room room){
        super(xx,yy,room);
        privateTxt = new Text(xx-25,yy-25,room,yspeed+"");
        room.addObject(privateTxt,0,0);
    }
    //*************************************** Return private variable ***********
    public double getXX(){
        return xx;
    }
    public int getXspdLimit(){
        return xspdLimit;
    }
    public double getXspd(){
        return xspeed;
    }
    public double getYspd(){
        return yspeed;
    }
    //**************************************** Movement
    private void move(){
        if(Math.abs(xspeed)>xspdLimit) xspeed = xspdLimit * Math.signum(xspeed);
        if(Math.abs(yspeed)>yspdLimit) yspeed = yspdLimit * Math.signum(yspeed);
        boolean xDone = false;
        boolean yDone = false;
        int xTmp = (int)Math.floor(xx) , yTmp = (int)Math.floor(yy);
        while(visible && !(xDone & yDone)){
            if(xTmp != (int)Math.floor(xx+xspeed) && !xDone){
                setLocation(room.camWidth/2 + xTmp+(int)Math.signum((int)Math.floor(xx+xspeed)-xTmp) - camera.getXX(),y);
                if(!isTouching(Wall.class)) {
                    xTmp += (int)Math.signum((int)Math.floor(xx+xspeed)-xTmp);
                    x = room.camWidth/2 + (((int)Math.floor(xTmp))-camera.getXX());
                }
                else {
                    xDone = true; 
                    setLocation(xTmp,y); 
                    xspeed = 0; 
                    xx = xTmp;
                    x = room.camWidth/2 + (((int)Math.floor(xx))-camera.getXX());
                }
            }
            else {
                xx = xTmp;
                x = room.camWidth/2 + (((int)Math.floor(xx))-camera.getXX());
                xDone = true;
            }
            if(yTmp != (int)Math.floor(yy+yspeed) && !yDone){
                setLocation(x, room.camHeight/2 + yTmp+(int)Math.signum((int)Math.floor(yy+yspeed)-yTmp) - camera.getYY());
                if(!isTouching(Wall.class)) {
                    yTmp += (int)Math.signum((int)Math.floor(yy+yspeed)-yTmp);
                    y = room.camHeight/2 + (((int)Math.floor(yTmp))-camera.getYY());
                }
                else {
                    yDone = true; 
                    setLocation(x,yTmp); 
                    yspeed = 0; 
                    yy = yTmp;
                    y = room.camHeight/2 + (((int)Math.floor(yy))-camera.getYY());
                }
            }
            else {
                yy = yTmp; 
                y = room.camHeight/2 + (((int)Math.floor(yy))-camera.getYY());
                yDone = true;}
            if(xDone & yDone) {
                //setLocation(room.camWidth/2 + (((int)Math.floor(stX))-camera.getXX()), room.camHeight/2 + (((int)Math.floor(stY))-camera.getYY()));
                break;
            }
        }
    }
    private boolean isGround(){
        setLocation(x,y+1);
        if(isTouching(Wall.class)){
            setLocation(x,y);
            return true;
        }
        setLocation(x,y);
        return false;
    }
    private boolean isStruck(){
        if(isTouching(Wall.class)){
            return true;
        }
        return false;
    }
    //*************************************** Action ******************************************************
    private void boundary(){
        if(xx < camera.getXX()-room.camWidth/2 + 75) {xx = camera.getXX()-room.camWidth/2 + 75; xspeed = 0;}
        if(xx > camera.getXX()+room.camWidth/2 - 75) {xx = camera.getXX()+room.camWidth/2 - 75; xspeed = 0;}
        if(yy < camera.getYY()-room.camHeight/2 + 75) {yy = camera.getYY()-room.camHeight/2 + 75; yspeed = 0;}
        if(yy > camera.getYY()+room.camHeight/2 - 75) {yy = camera.getYY()+room.camHeight/2 - 75; yspeed = 0;}
    }
    
    //*****************************************************************************************************
    public void act() 
    {
        if(!isGround() && visible) yspeed += gravity;
        else yspeed = 0;
        if(Greenfoot.isKeyDown("up") & isGround()){ yspeed -= 12; }
        if(Greenfoot.isKeyDown("left") && !Greenfoot.isKeyDown("right")){ xspeed-=0.5; }
        else if(Greenfoot.isKeyDown("right") && !Greenfoot.isKeyDown("left")){ xspeed+=0.5; }
        else {
            if(Math.abs(xspeed)-friction < 0) xspeed = 0; 
            else xspeed -= (friction*Math.signum(xspeed));
        }
        lastXX = xx;
        lastYY = yy;
        move();
        boundary();
        privateTxt.setText(xx+", "+yy);
        privateTxt.setPosition((int)xx-25,(int)yy-30);
        camera();
        
        //privateTxt.setText(((int)Math.floor(yy+yspeed)-y) +"");
    }    
}
