public abstract class Creature extends Entity 
{
  public static final int DEFAULT_HEALTH = 10;
  public static final float DEFAULT_SPEED = 3.0f;
  public static final int DEFAULT_CREATURE_WIDTH = 64,
                          DEFAULT_CREATURE_HEIGHT = 64;

  protected int health;
  protected float speed;
  protected float xMove;
  protected float yMove;

  public Creature(Handler handler, float x, float y, int width, int height) // needs this constructor because creature is a subclass of entity
  {
    super(handler, x, y, width, height);
    health = DEFAULT_HEALTH;
    speed = DEFAULT_SPEED;
    xMove = 0;
    yMove = 0;
  }

  public void move()
  {
    moveX();
    moveY();
  }

  public void moveX()
  {
    if (xMove > 0)  // moving right
    {
      int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;

      if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
            !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEWIDTH))
        x += xMove;
      else
        x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
    }
    else if (xMove < 0) // moving left
    {
      int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;

      if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
            !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEWIDTH))
        x += xMove;
      else
        x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
    }
    
  }

  public void moveY()
  {
    if (yMove < 0)  // moving up
    {
      int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;

      if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) && 
            !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty))
        y += yMove;
      else
        y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
    }
    else if (yMove > 0) // moving down
    {
      int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;

      if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) && 
            !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty))
        y += yMove;
      else
        y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
    }
  }

  protected boolean collisionWithTile(int x, int y)
  {
    return handler.getWorld().getTile(x, y).isSolid();
  }
  
  // getters and setters
  public int getHealth() {
    return this.health;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public float getSpeed() {
    return this.speed;
  }

  public void setSpeed(float speed) {
    this.speed = speed;
  }

  public float getXMove() {
    return this.xMove;
  }

  public void setXMove(float xMove) {
    this.xMove = xMove;
  }

  public float getYMove() {
    return this.yMove;
  }

  public void setYMove(float yMove) {
    this.yMove = yMove;
  }

}
