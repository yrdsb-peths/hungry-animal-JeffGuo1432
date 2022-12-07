import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GoldenApple here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GoldenApple extends Apple
{
    /**
     * Act - do whatever the GoldenApple wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int ySpeed=1;
    public void act()
    {
            
            int x =getX();
            int y= getY()+ySpeed;
    
            setLocation(x,y);
            MyWorld world = (MyWorld) getWorld();
            if(getY()==10){
                ySpeed=5;
            }
            if(getY()>=world.getHeight())
            {
                world.removeObject(this);
                world.applesCount-=1;
            }
            if(world.gamePhase=="wave")
            {
                world.removeObject(this);
                world.applesCount-=1;
            }
    }
}
