import java.awt.Graphics;

public class World 
{
  private Handler handler;
  private int width, height;
  private int spawnX, spawnY;
  private int[][] tiles;
  
  public World(Handler handler, String path)
  {
    this.handler = handler;
    loadWorld(path);
  }

  public void tick()
  {

  }

  public void render(Graphics g)
  {
    // added for efficiency in rendering world
    int xStart = (int) Math.max(0, handler.getGameCamera().getXOffset() / Tile.TILEWIDTH);
    int xEnd = (int) Math.min(width, (handler.getGameCamera().getXOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
    int yStart = (int) Math.max(0, handler.getGameCamera().getYOffset() / Tile.TILEHEIGHT);
    int yEnd = (int) Math.min(height, (handler.getGameCamera().getYOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

    // the order of these for loops prevents a few problems but is not manditory
    for (int y = yStart; y < yEnd; y++)
    {
      for (int x = xStart; x < xEnd; x++)
      {
        // renders all tiles on the screen every tick and takes in account the game camera offset
        getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getXOffset()), 
            (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getYOffset()));
      }
    }
  }

  public Tile getTile(int x, int y)
  {
    if (x < 0 || y < 0 || x >= width || y >= height)  // make sure game doesnt crash if player outside map
      return Tile.grassTile;

    Tile t = Tile.tiles[tiles[x][y]];  // tiles[] is the array that holds each unique tile object we have with their 'id' as the index
    if (t == null)                     // tiles[][] is what type of tile we want in a specific point in the world
      return Tile.dirtTile;
    return t;
  }

  private void loadWorld(String path)
  {
    String file = Utils.loadFileAsString(path);
    String[] tokens = file.split("\\s+");
    width = Utils.parseInt(tokens[0]);
    height = Utils.parseInt(tokens[1]);
    spawnX = Utils.parseInt(tokens[2]);
    spawnY = Utils.parseInt(tokens[3]);

    tiles = new int[width][height];
    for (int y = 0; y < height; y++)
    {
      for (int x = 0; x < width; x++)
      {
        // this line goes through each line of the world text file
        tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]); // we add 4 because first 4 strings are size and play position
      }
    }
  }

  // getters to allow us to access outside of this class
  public int getWidth()
  {
    return width;
  }

  public int getHeight()
  {
    return height;
  }
}
