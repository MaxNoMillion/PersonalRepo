import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player extends Creature 
{
  // Animations
  private Animation animDown, animUp, animLeft, animRight;

  public Player(Handler handler, float x, float y) // needs this constructor because creature is a subclass of entity
  {
    // we know that to player is going to always be the default size, so we can just send in super fn
    super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

    bounds.x = 20;
    bounds.y = 32;
    bounds.width = 28;
    bounds.height = 32;

    // Animations
    animDown = new Animation(500, Assets.player_down);
    animUp = new Animation(500, Assets.player_up);
    animLeft = new Animation(500, Assets.player_left);
    animRight = new Animation(500, Assets.player_right);
  }

  @Override
  public void tick()
  {
    // Animations
    animDown.tick();
    animUp.tick();
    animLeft.tick();
    animRight.tick();

    // Movement
    getInput();
    move();
    handler.getGameCamera().centerOnEntity(this);
  }

  private void getInput()
  {
    xMove = 0;  // must set x and y move back to zero
    yMove = 0;

    if (handler.getKeyManager().up)
      yMove = -speed;
    if (handler.getKeyManager().down)
      yMove = speed;
    if (handler.getKeyManager().left)
      xMove = -speed;
    if (handler.getKeyManager().right)
      xMove = speed;
  }
  
  @Override
  public void render(Graphics g)
  {
    // the width and height scale the Image input
    g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getXOffset()), (int) (y - handler.getGameCamera().getYOffset()), width, height, null);
    g.setColor(Color.red);
    // BOUNDARY BOX VISUALS ////
    // g.fillRect((int) (x + bounds.x - handler.getGameCamera().getXOffset()),
    //         (int) (y + bounds.y - handler.getGameCamera().getYOffset()),
    //         bounds.width, bounds.height);
    /////////////////////////////

  }

  private BufferedImage getCurrentAnimationFrame()
  {
    if (xMove < 0)
      return animLeft.getCurrentFrame();
    else if (xMove > 0)
      return animRight.getCurrentFrame();
    else if (yMove < 0)
      return animUp.getCurrentFrame();
    else
      return animDown.getCurrentFrame();
  }
}
