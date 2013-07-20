package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.framework.ui.FontStyle;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.platform.tree.display.shape.Polygon;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TextWriterTest {

  private final Polygon shape = new Polygon();
  private TextWriter writer = new TextWriter(shape);
  private Canvas canvas = mock(Canvas.class);

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
    verify(canvas).drawString("ABC", new Coordinate(50, 46));
  }

  @Test
  public void usesGivenTextColor() throws Exception {
    writer.write(canvas);
    verify(canvas).setColor(RGBColor.Red);
  }

  @Test
  public void setsFontForText() throws Exception {
    writer.write(canvas);
    verify(canvas).setFontStyle(FontStyle.Plain, 15);
  }
}