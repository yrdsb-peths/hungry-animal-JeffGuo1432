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
    int goldenAppleChance = 10;
    public int speed = 3;
    public int direction=1;
    int rollCooldown=10;
    int initRollCharge=50;
    int rollCharge;
    int xVelocity;
    private boolean rolling;
    int rollTicks=40;
    int speedCooldown=0;
    boolean bouncing=false;
    
    
    public void act()
    {
        MyWorld world = (MyWorld) getWorld();
        Snake snake = new Snake();
        rollCharge=initRollCharge;
        rollCooldown-=1;
        speedCooldown-=1;
        setLocation(getX()+xVelocity,getY());
        MouseInfo mouse = Greenfoot.getMouseInfo();
        
        if(speedCooldown>=0){
            speed=8;
        }
        else{
            speed=5;
        }
        if(rolling==false){
            if(Greenfoot.isKeyDown("left"))
            {
                if (direction==1){
                    getImage().mirrorHorizontally();
                }
                direction=-1;
                xVelocity=direction*speed;
                if(getX()<0){
                    setLocation(3,getY());
                }
            }
            if(Greenfoot.isKeyDown("right"))
            {
                if (direction==-1){
                    getImage().mirrorHorizontally();
                }
                direction=1;
                xVelocity=direction*speed;
                if(getX()>600){
                    setLocation(599,getY());
                }
            }
        }
        if(!Greenfoot.isKeyDown("right")&!Greenfoot.isKeyDown("left")&!Greenfoot.isKeyDown("down"))
        {
            xVelocity=0;
        }
        if(Greenfoot.isKeyDown("down")&(rollCooldown<=0))
        {
            rolling=true;
            rollCooldown=100;
        }
        if(rolling==true)
        {
            rollTicks-=5;
            xVelocity=rollTicks*direction;
            setRotation(getRotation()-45*direction);
            if(getX()<2||getX()>598){
                direction=direction*-1;
                xVelocity=rollTicks*direction*3;
                rollTicks+=5;
                bouncing=true;
            }
        }
        if(rollTicks<=0)
        {
            
            rollTicks=50;
            setRotation(0);
            rolling=false;
            rollCooldown=20;
            if(bouncing==true){
                direction=direction*-1;
                bouncing=false;
            }
            if(getX()<0){
                    setLocation(3,getY());
                }
            if(getX()>600){
                    setLocation(597,getY());
                }
        }
        eat();
        int deathSpinRadius=1;
        if(world.gamePhase=="over"){
            
            deathSpinRadius+=20;
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
        
        int goldenAppleDraw = Greenfoot.getRandomNumber(goldenAppleChance);
        if(isTouching(Apple.class))
        {
            
            
            if(isTouching(GoldenApple.class))
            {
                world.summonAppleWave(50);
            }
            if(isTouching(SpeedApple.class))
            {
                //speedCooldown=500;
            }
            removeTouching(Apple.class);
    
            world.applesCount-=1;
            world.increaseScore();
            if (world.gamePhase=="normal"){
                if(world.applesCount<=4){
                    if((goldenAppleDraw==0))
                    {
                        world.createGoldenApple();
                        goldenAppleChance = 50;
                        
                    }
                    else
                    {
                        goldenAppleChance-=1;
                        world.createApple();
                    }
                }
            }               

        }
    }
    public boolean getRolling()
    {
        return rolling;
    }
    public int getDirection()
    {
        return direction;
    }
}
