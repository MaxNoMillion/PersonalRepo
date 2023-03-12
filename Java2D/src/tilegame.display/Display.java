import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;

public class Display 
{
  private JFrame frame;   // draw our images on the canvas and then
  private Canvas canvas;    // display them using frame

  private String title;
  private int width, height;
  
  // constructor
  public Display(String title, int width, int height)
  {
    this.title = title;
    this.width = width;
    this.height = height;

    createDisplay();
  }

  private void createDisplay()
  {
    frame = new JFrame(title);  // creates window or JFrame
    frame.setSize(width, height); // sets size of window
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // insures game is closed whe window is closed
                                                            // and not running in the background
    frame.setResizable(false); // window cant be resized by user
    frame.setLocationRelativeTo(null); // window appears in center of the screen
    frame.setVisible(true); // makes window visable because JFrames are not visible by default

    canvas = new Canvas();
    canvas.setPreferredSize(new Dimension(width, height)); // sets size of canvas object
    canvas.setMaximumSize(new Dimension(width, height));  // insures the canvas will stay
    canvas.setMinimumSize(new Dimension(width, height));    // ou prefered size
    canvas.setFocusable(false); // allows application to focas and not the thing being drawn

    frame.add(canvas);  // adds canvas to frame to display
    frame.pack(); // resizes canvas to fit it into frame
  }

  // getter for Canvas
  // allows us to access our Canvas object from other classes
  public Canvas getCanvas()
  {
    return canvas;
  }

  public JFrame getFrame()
  {
    return frame;
  }
}
