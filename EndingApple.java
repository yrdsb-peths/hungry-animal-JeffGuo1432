import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndingApple here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EndingApple extends Apple
{
    /**
     * Act - do whatever the EndingApple wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        int x =getX();
        int y= getY()+8;

        setLocation(x,y);
        MyWorld world = (MyWorld) getWorld();
    
        if(waveEnd()){
            world.removeObject(this);
            world.applesCount-=1;
            world.gamePhase="normal";
        }
    }
    public boolean waveEnd()
    {
        return getY()>=0;
    }
}
