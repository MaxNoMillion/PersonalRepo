import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile 
{
  // Static stuff here
  public static Tile[] tiles = new Tile[256];
  public static Tile grassTile = new GrassTile(0);
  public static Tile dirtTile = new DirtTile(1);
  public static Tile rockTile = new RockTile(2);

  // Class
  public static final int TILEWIDTH = 64, TILEHEIGHT = 64;

  protected BufferedImage texture;
  protected final int id;

  public Tile(BufferedImage texture, int id)
  {
    this.texture = texture;
    this.id = id;

    tiles[id] = this; // constructor inputs tile into arr at index 'id' everytime tile is created
  }  

  public void tick()
  {

  }

  public void render(Graphics g, int x, int y)
  {
    g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
  }

  public boolean isSolid()
  {
    return false;
  }

  public int getId()
  {
    return id;
  }
}
