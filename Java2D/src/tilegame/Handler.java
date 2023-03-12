
// allows us to access a lot of objects by only passing the handler object
public class Handler 
{
  private Game game;
  private World world;

  public Handler(Game game)
  {
    this.game = game;
  } 

  public KeyManager getKeyManager()
  {
    return game.getKeyManager();
  }

  public GameCamera getGameCamera()
  {
    return game.getGameCamera();
  }

  public int getWidth()
  {
    return game.getWidth();
  }

  public int getHeight()
  {
    return game.getHeight();
  }
  
  // getters and setters
  public Game getGame() {
    return this.game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public World getWorld() {
    return this.world;
  }

  public void setWorld(World world) {
    this.world = world;
  }
}
