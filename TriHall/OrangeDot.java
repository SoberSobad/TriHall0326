import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class OrangeDot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OrangeDot extends Actor
{
    private int x,y,leftBound,rightBound,upperBound,lowerBound,origHeight,origWidth;
    private Graph graph;
    
    public OrangeDot(int x, int y, int leftBound, int rightBound, int upperBound, int lowerBound, Graph graph){
        this.x = x;
        this.y = y;
        setLocation(x,y);
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
        this.graph = graph;
        origHeight = getImage().getHeight();
        origWidth = getImage().getWidth();
    }
    
    public void act() 
    {
        x--;
        if(x < leftBound){
            double h = ( ((double)graph.getData()/(double)(graph.getMax()-graph.getMin()) ) * (upperBound-lowerBound) ) + lowerBound;
            double prevH = ( ((double)graph.getPrevData()/(double)(graph.getMax()-graph.getMin()) ) * (upperBound-lowerBound) ) + lowerBound;
            x = rightBound; y = (int)Math.floor( (((double)(h - prevH)) / 2 ) + prevH );
            getImage().scale(origWidth, origHeight);
            if((int)Math.floor((double)(h-prevH)) != 0)getImage().scale(getImage().getWidth() ,Math.abs((int)Math.floor((double)(h-prevH))) );
        }
        setLocation(x,y);
    }    
}
