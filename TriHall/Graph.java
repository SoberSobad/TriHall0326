import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Graph here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Graph extends Actor
{
    int x, y;
    int width, height, max, min;
    int data, prevData;
    int dotCount;
    Room room;
    
    public Graph(int x, int y, Room room, int width, int height, int max, int min){
        this.x = x;
        this.y = y;
        this.room = room;
        setLocation(x,y);
        this.width = width;
        this.height = height;
        this.max = max;
        this.min = min;
    }
    
    public void update(int n){
        prevData = data;
        data = n;
    }
    public int getData(){
        return data;
    }
    public int getPrevData(){
        return prevData;
    }
    public int getMax(){
        return max;
    }
    public int getMin(){
        return min;
    }
    
    public void act() 
    {
        if(dotCount<width){
            double h = ( ((double)data/(double)(max-min)) * (y-(y+height)) ) + (y+height);
            OrangeDot newDot = new OrangeDot(x+width,(int)Math.floor(h), x, x+width, y, y+height, this);
            room.addObject(newDot,x+width,(int)Math.floor(h));
            dotCount++;
        }
    }    
}
