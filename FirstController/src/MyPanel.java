import java.awt.*;
import javax.swing.*;

public class MyPanel extends JPanel
{
  MyPanel()
  {
    int PanWidth = 750;
    int PanHeight = 750;
    this.setPreferredSize(new Dimension(PanWidth, PanHeight));
  }

  public void paint(Graphics g)
  {

    Graphics2D player = (Graphics2D) g;

    player.setPaint(Color.red);
    player.fillRect(300, 300, 75, 75);
  }
}
