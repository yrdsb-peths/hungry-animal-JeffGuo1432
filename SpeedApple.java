import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SpeedApple here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpeedApple extends Apple
{
    /**
     * Act - do whatever the SpeedApple wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    int xDirection=1;
    
    
    public SpeedApple(int direction)
    {
        if (direction==1){
            xDirection=-1;
        }
        else{
            xDirection=1;
        }
    }
    public void act()
    {
        int xVelocity = xDirection*5;
        int x =getX()+xVelocity;
        int y= getY()+2;
        
        setLocation(x,y);
        if (x>=600){
            xDirection=-1;
        }
        if(x<=0){
            xDirection=1;
        }
        MyWorld world = (MyWorld) getWorld();
        
        if(getY()>=world.getHeight())
        {
            world.removeObject(this);
            world.applesCount-=1;
        }
        
        
    }
}
