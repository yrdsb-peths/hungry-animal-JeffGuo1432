import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SnakeBoss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SnakeBoss extends Actor
{
    /**
     * Act - do whatever the SnakeBoss wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int direction=-1;
    private int count=50;
    public void act()
    {
        // Add your action code here.
        count++;
        if (count%100==0){
            direction=direction*-1;
        }
        setLocation(getX()+direction,getY());
    }
}
