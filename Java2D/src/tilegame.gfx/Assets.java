import java.awt.image.BufferedImage;

public class Assets 
{
  private static final int width = 32, height = 32;

  public static BufferedImage dirt, grass, stone, tree;
  public static BufferedImage[] player_down, player_up, player_left, player_right;

  public static void init()
  {
    // res\\texture folder must be in src folder
    SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("\\textures\\tutorialSpriteSheet.png"));

    player_down = new BufferedImage[2];
    player_up = new BufferedImage[2];
    player_left = new BufferedImage[2];
    player_right = new BufferedImage[2];

    player_down[0]  = sheet.crop(4 * width, 0 * height, width, height);
    player_down[1]  = sheet.crop(5 * width, 0 * height, width, height);
    player_up[0]    = sheet.crop(6 * width, 0 * height, width, height);
    player_up[1]    = sheet.crop(7 * width, 0 * height, width, height);
    player_left[0]  = sheet.crop(6 * width, 1 * height, width, height);
    player_left[1]  = sheet.crop(7 * width, 1 * height, width, height);
    player_right[0] = sheet.crop(4 * width, 1 * height, width, height);
    player_right[1] = sheet.crop(5 * width, 1 * height, width, height);


    dirt  = sheet.crop(1 * width, 0 * height, width, height);
    grass = sheet.crop(2 * width, 0 * height, width, height);
    stone = sheet.crop(3 * width, 0 * height, width, height);
    tree  = sheet.crop(0 * width, 0 * height, width, height);
  }  
}
