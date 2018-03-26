import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Text here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Text extends GameObject
{
    //GreenfootImage text;
    
    public Text(int xx, int yy, Room room, String str){
        super(xx,yy,room);
        
        image = new GreenfootImage(str.length()*5+20,20);
        Color col = new Color(220,20,60);
        image.setColor(col);
        image.drawString(str,10,15);
        setImage(image);
    }
    public void setText(String str){
        image = new GreenfootImage(str.length()*5+20,20);
        Color col = new Color(220,20,60);
        image.setColor(col);
        image.drawString(str,10,15);
        setImage(image);
    }
    public void setPosition(int xx, int yy){
        this.xx = xx;
        this.yy = yy;
    }
    public void act() 
    {
        camera();
        
    }    
}
