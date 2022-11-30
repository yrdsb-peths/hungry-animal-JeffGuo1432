import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Elephant
 * 
 * @author Jeff
 * @version November 28 2022
 */
public class Elephant extends Actor
{
    /**
     * Act - do whatever the Elephant wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
    */
    public int speed = 3;
    public void act()
    {
        
        // Add your action code here.
        if(Greenfoot.isKeyDown("left"))
        {
            move(-1*speed);
        }
        if(Greenfoot.isKeyDown("right"))
        {
            move(speed);
        }
        eat();
    }
    /**
     * Eats the apple and spawns a new apple if the apple is eaten
     */
    public void eat()
    {
        MyWorld world = (MyWorld) getWorld();
        
        if(isTouching(Apple.class))
        {
            removeTouching(Apple.class);
            
            world.applesCount-=1;
            world.increaseScore();
            if (world.gamePhase=="normal"){
                world.createApple();
            }               
        }
    }
}
