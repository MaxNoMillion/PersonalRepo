import java.awt.Graphics;
import java.awt.image.BufferStrategy;

// very important class
// this is where the game will run
public class Game implements Runnable //the 'implements Runnable' tag allows this class to run on a thread
{
  private Display display;
  private int width, height;
  public String title;

  private boolean running = false;
  private Thread thread;

  private BufferStrategy bs;
  private Graphics g;

  //States
  private State gameState;
  private State menuState;
  private State settingsState;

  //Inputs
  private KeyManager keyManager;

  // Camera
  private GameCamera gameCamera;

  // Handler
  private Handler handler;

  //constructor
  public Game(String title, int width, int height)
  {
    this.width = width;
    this.height = height;
    this.title = title;
    keyManager = new KeyManager();
  }

  // only runs once // initiallizes game startup
  private void init()
  {
    display = new Display(title, width, height);  // creates new display
    display.getFrame().addKeyListener(keyManager);
    Assets.init();  // loads in all spritesheet images

    handler = new Handler(this);
    gameCamera = new GameCamera(handler, 0, 0);

    gameState = new GameState(handler);    // passing a game object into all state classes
    menuState = new MenuState(handler);
    settingsState = new SettingsState(handler);
    State.setState(gameState);

    /////// TEST CODE BUT STILL VERY IMPORTANT FOR PATHING SAKE //////////////////
    // testImage = ImageLoader.loadImage("\\textures\\groundTileSpriteSheet.jpg"); 
    // // MAKE SURE PATHING I CORRECT
    // // in this project "res" is added to source path
    // sheet = new SpriteSheet(testImage);
    ///////////////////////////////////////////////////////////////////////////////
  }
  
  // updates game stuff
  private void tick()
  {
    keyManager.tick();  // ticks to detect key presses
    
    if (State.getState() != null)
      State.getState().tick();
  }

  // renders game updates
  private void render()
  {
    // bufferStrategy tells the computer how to draw things on the screen
    // A buffer is essentially a 'hidden' screen within your computer
    bs = display.getCanvas().getBufferStrategy(); // sets bs to current buffStrat of our canvas
    if (bs == null)
    {
      display.getCanvas().createBufferStrategy(3); // tell computer to create 3 buffers
      return; // so the rest of the method doesnt run
    }
    g = bs.getDrawGraphics();
    // Clear Screen
    g.clearRect(0, 0, width, height);

    // Draw Here!

    if (State.getState() != null)
      State.getState().render(g);

    // End Drawing!
    bs.show();    // tell java we are done with drawing and shows
    g.dispose();  // makes sure g object is dealt with
  }

  // our main game loop
  public void run() // we must have a run method if the class is running on a thread
  {
    init();

    int fps = 60;
    double timePerTick = 1000000000 / fps; //the limited amount of time tick and render can update in nano seconds
    double delta = 0;
    long now;
    long lastTime = System.nanoTime();
    long timer = 0;
    int ticks = 0;

    while(running)
    {
      now = System.nanoTime();
      delta += (now - lastTime) / timePerTick; //normalize allapsed time to 1
      timer += now - lastTime;  // amount of time past since last iteration
      lastTime = now;

      if (delta >= 1) // with the normalized time, we just dectect if delta is greater than 1
      {
      tick();   // updates 
      render(); // renders
      ticks++;
      delta--;
      }

      // prints the current fps in terminal
      if (timer >= 1000000000)
      {
        //System.out.println("Ticks and Frames: " + ticks); // FPS in terminal
        ticks = 0;
        timer = 0;
      }
    }

    //stops thread
    stop();
  }

  public KeyManager getKeyManager()
  {
    return keyManager;
  }

  public GameCamera getGameCamera()
  {
    return gameCamera;
  }

  public int getWidth()
  {
    return width;
  }

  public int getHeight()
  {
    return height;
  }

  // this starts our thread
  // the 'synchrinized' keyword is used when working with a thread directly
    // it basically makes sure nothing breaks
  public synchronized void start()
  {
    if (running) // we do this incase start up method is call while game is running
      return;
    running = true;
    thread = new Thread(this);           // must pass in the class we want to run on the thread
    thread.start(); // calls run method    // in our case it is the Game class so we pass in 'this'
  }                               

  // this stops our thread
  public synchronized void stop()
  {
    if (!running) // we do this incase stop method is call while game is running
      return;
    running = false;
    // stops thread but must be surrounded by try catch statement
    try {
      thread.join();  
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
