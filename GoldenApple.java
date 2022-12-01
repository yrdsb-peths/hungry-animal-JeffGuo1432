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
    public void act()
    {
            
            int x =getX();
            int y= getY()+3;
    
            setLocation(x,y);
            MyWorld world = (MyWorld) getWorld();
            System.out.println(y);
            if(getY()>=world.getHeight())
            {
                world.removeObject(this);
                world.applesCount-=1;
                if (world.applesCount<=0)
                {
                    world.gameOver();
                }
            }
            if(world.gamePhase=="wave")
            {
                world.removeObject(this);
                world.applesCount-=1;
            }
    }
}
