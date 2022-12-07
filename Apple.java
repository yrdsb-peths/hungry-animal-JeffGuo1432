import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Food for the elephant
 * 
 * @author Jeff 
 * @version November 28 2022
 */
public class Apple extends Actor
{
    public int appleSpeed;
    /**
     * Act - do whatever the Apple wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        int x =getX();
        int y= getY()+3;
        
        setLocation(x,y);
        MyWorld world = (MyWorld) getWorld();
        
        if(getY()>=world.getHeight())
        {
            world.removeObject(this);
            world.applesCount-=1;
            
        }
        
        
    }
    
}
