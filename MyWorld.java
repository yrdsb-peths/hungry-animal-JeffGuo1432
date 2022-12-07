import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The world that Elephant lives in
 * 
 * @author Jeff
 * @version November 28 2022
 */
public class MyWorld extends World
{
    public int score=0;
    public int applesCount=0;
    public String gamePhase= "normal";
    Label scoreLabel;
    Label appleWaveSummoned;
    int[] applesWaveX;

    int[] applesWaveY;
    public int clock=0;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        
        super(600, 400, 1, false); 
        Elephant elephant = new Elephant();
        addObject(elephant, 300, 300);
        // createGoldenApple();
        //summonAppleWave(100);
        //createGoldenApple();
        scoreLabel = new Label(0,60);
        addObject(scoreLabel, 50, 50);
    }
    public void act(){
        clock++;
        int x = Greenfoot.getRandomNumber(600);
        if((clock%600==0)&&(clock%2400!=0)){
            createSnake(x-20,0);
        }
        if(clock%2400==0){
            createSnake(Greenfoot.getRandomNumber(100)+500,0);
            createSnake(Greenfoot.getRandomNumber(100),0);
        }
        if(clock%3600==0){
            createSnake(x,0);
            createSnake(x-100,0);
            createSnake(x+100,0);
        }
        if(clock%80==0&gamePhase=="normal"){
            createApple(x,0);
        }
    }
    /**
     * End the game and draw 'Gameover'
     */
    public void gameOver()
    {
        Label gameOverLabel = new Label("game over",80);
        addObject(gameOverLabel,300,200);
        gamePhase="over";
    }
    
    public void increaseScore()
    {
        score++;
        scoreLabel.setValue(score);
    }
    
    public void createApple()
    {
        Apple apple = new Apple();
        applesCount++;
        int x = Greenfoot.getRandomNumber(600);
        int y = 0;
        addObject(apple, x, y);
    }
    /**
    public void createApple(int count, int x, int y, int rad){
        for(int i = 0 ; i < rad*2 ; i++){
            for(int j = 0; j <
            createApple();
        }
    }
    **/
    public void createGoldenApple(){
        GoldenApple gApple = new GoldenApple();
        applesCount++;
        int x = Greenfoot.getRandomNumber(600);
        int y = 0;
        addObject(gApple,x,y);
    }
    public void createSpeedApple(){
        int xDirection= Greenfoot.getRandomNumber(2)+1;
        SpeedApple spApple = new SpeedApple(xDirection);
        applesCount++;
        int x = Greenfoot.getRandomNumber(600);
        int y = 0;
        addObject(spApple,x,y);
    }
    public void createApple(int count)
    {
        for(int i = 0 ; i < count-1 ; i++){
            createApple();
        }
    }
    public void createApple(int x,int y)
    {
        Apple apple = new Apple();
        applesCount++;
        addObject(apple, x, y);
    }
    public void createSnake(int x, int y)
    {
        Snake snake = new Snake();
        addObject(snake, x, y);
        
    }
    public void summonAppleWave(int count)
    {
        gamePhase="wave";
        int x;
        int y;
       for(int i = 0 ; i < count; i++){
            double waveX = 200*Math.sin(i*14748364)+300;   
            x = (int) Math.round(waveX);
            y = -15*i;
            createApple(x,y);
        }
        EndingApple endWave = new EndingApple();
        applesCount++;
        double waveX = 200*Math.sin(count*14748364)+300;   
        x = (int) Math.round(waveX);
        y = -15*count;
        
        addObject(endWave, x, y);
    }
    
    
}
