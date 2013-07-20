package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.platform.tree.display.shape.Polygon;
import net.sf.anathema.platform.tree.swing.SwingGraphicsCanvas;
import org.junit.Before;
import org.junit.Test;

import java.awt.Font;
import java.awt.Graphics2D;

import static java.awt.Color.RED;
import static org.mockito.Mockito.verify;

public class TextWriterTest {

  private final Polygon shape = new Polygon();
  private TextWriter writer = new TextWriter(shape);
  private Font font = new Font("SansSerif", Font.PLAIN, 15);
  private Graphics2D graphics = GraphicsMother.createForFont(font);
  private Canvas canvas = new SwingGraphicsCanvas(graphics);

  @Before
  public void setUp() throws Exception {
    shape.addPoint(0, 0);
    shape.addPoint(100, 100);
    writer.setText("ABC");
    writer.setStroke(RGBColor.Red);
  }

  @Test
  public void writesGivenText() throws Exception {
    writer.write(canvas);
    verify(graphics).drawString("ABC", 50, 46);
  }

  @Test
  public void usesGivenTextColor() throws Exception {
    writer.write(canvas);
    verify(graphics).setColor(RED);
  }

  @Test
  public void setsFontForText() throws Exception {
    writer.write(canvas);
    verify(graphics).setFont(font);
  }
}