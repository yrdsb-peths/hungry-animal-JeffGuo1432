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
    public int appleTextTime;
    int count;
    private boolean bubbled=false;
    GreenfootImage bubble = new GreenfootImage("images/elephantBubble.png");
    GreenfootImage nobubble = new GreenfootImage("images/elephant.png");
    GreenfootImage rollingImage = new GreenfootImage("images/rollingElephant.png");
    GreenfootSound rollSound = new GreenfootSound("sounds/roll.wav");
    
    GreenfootSound goldenAppleSound = new GreenfootSound("sounds/goldenApple.wav");
    private int doneRolling;
    private boolean killMode=false;
    
    public void act()
    {
        count++;
        MyWorld world = (MyWorld) getWorld();
        Snake snake = new Snake();
        rollCharge=initRollCharge;
        rollCooldown-=1;
        speedCooldown-=1;
        setLocation(getX()+xVelocity,getY());
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(bubbled==true){
            setImage(bubble);
        }
        else
        {
            setImage(nobubble);
        }
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
            killMode=true;
            rollCooldown=130;
        }
        if(rollCooldown==130){
            rollSound.play();
        }
        if(rolling==true)
        {
            setImage(rollingImage);
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
            doneRolling=count;
            rollCooldown=20;
            rolling=false;
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
        if(doneRolling+30==count){
            killMode=false;
        }
        eat();
        
        if(world.gamePhase=="over"){
            
            setRotation(180);
            setLocation(getX(),getY()+1);
        }
    }
    public boolean getKillMode(){
        return killMode;
    }
    /**
     * Eats the apple and spawns a new apple if the apple is eaten
     */

    public void eat()
    {
        MyWorld world = (MyWorld) getWorld();
        if(count==appleTextTime+50){
            world.removeObject(world.appleWaveSummoned);
        }
        int goldenAppleDraw = Greenfoot.getRandomNumber(goldenAppleChance);
        if(isTouching(Bubble.class))
        {
            removeTouching(Bubble.class);
            bubbled=true;
        }
        if(isTouching(Apple.class))
        {
            
            if(isTouching(GoldenApple.class))
            {
                world.summonAppleWave(50);
                goldenAppleSound.play();
                world.appleWaveSummoned = new Label("apple wave summoned",30);
                world.appleWaveSummoned.setLineColor(Color.YELLOW);
                world.appleWaveSummoned.setFillColor(Color.YELLOW);
                world.addObject(world.appleWaveSummoned, getX(), getY());
                appleTextTime=count;
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
