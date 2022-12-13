import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TutorialScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TutorialScreen extends World
{

    /**
     * Constructor for objects of class TutorialScreen.
     * 
     */
    public boolean ready=false;
    public TutorialScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
    }
    public void act()
    {
        if(Greenfoot.isKeyDown("space")==false){
            ready=true;
            
        }
        if(Greenfoot.isKeyDown("space")&ready==true){
                MyWorld gameWorld = new MyWorld();
                Greenfoot.setWorld(gameWorld);
            }
    }
}
