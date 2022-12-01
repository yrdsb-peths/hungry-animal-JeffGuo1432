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
        MyWorld world = (MyWorld) getWorld();
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
        int deathSpinRadius=1;
        if(world.gamePhase=="over"&&getY()<400){
    
            setRotation(getRotation()-25);
            setLocation(getX()-8,getY()-6);
            move(deathSpinRadius);
        }
    }
    /**
     * Eats the apple and spawns a new apple if the apple is eaten
     */
    public void eat()
    {
        MyWorld world = (MyWorld) getWorld();
        int goldenAppleChance = 20;
        int goldenAppleDraw = Greenfoot.getRandomNumber(goldenAppleChance);
        if(isTouching(Apple.class))
        {
            if(isTouching(GoldenApple.class))
            {
                world.summonAppleWave(50);
            }
            removeTouching(Apple.class);
    
            world.applesCount-=1;
            world.increaseScore();
            if (world.gamePhase=="normal"){
                if((world.applesCount<=4)&&(goldenAppleDraw==0))
                {
                    world.createGoldenApple();
                    goldenAppleChance--;
                }
                else{
                    world.createApple();
                }
            }               
            System.out.println(world.applesCount);
        }
    }
}
