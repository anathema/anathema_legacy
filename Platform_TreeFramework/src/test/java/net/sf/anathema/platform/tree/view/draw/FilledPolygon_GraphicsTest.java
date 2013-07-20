package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.framework.ui.Area;
import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.framework.ui.Rectangle;
import net.sf.anathema.platform.tree.display.shape.AgnosticShape;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FilledPolygon_GraphicsTest {

  public static final Rectangle ANY_RECTANGLE = new Rectangle(new Coordinate(0, 0), new Area(100, 100));
  public static final Area ANY_AREA_WITH_CONTENT = new Area(100, 100);

  private final FilledPolygon filledPolygon = new FilledPolygon();
  private final Canvas canvas = mock(Canvas.class);


  @Before
  public void createSquare() throws Exception {
    filledPolygon.addPoint(0, 0);
    filledPolygon.addPoint(0, 2);
    filledPolygon.addPoint(2, 2);
    filledPolygon.addPoint(2, 0);
  }

  @Before
  public void configureSizes() throws Exception {
    when(canvas.measureText(anyString())).thenReturn(ANY_AREA_WITH_CONTENT);
    when(canvas.calculateBounds(any(AgnosticShape.class))).thenReturn(ANY_RECTANGLE);
  }

  @Test
  public void fillsWithSetColor() throws Exception {
    filledPolygon.fill(RGBColor.Pink);
    filledPolygon.paint(canvas);
    verify(canvas).setColor(RGBColor.Pink);
  }

  @Test
  public void changesAlphaOfFill() throws Exception {
    filledPolygon.fill(RGBColor.Pink);
    filledPolygon.setAlpha(100);
    filledPolygon.paint(canvas);
    verify(canvas).setColor(new RGBColor(RGBColor.Pink, 100));
  }

  @Test
  public void changesAlphaOfStroke() throws Exception {
    filledPolygon.setAlpha(17);
    filledPolygon.paint(canvas);
    verify(canvas, atLeastOnce()).setColor(new RGBColor(RGBColor.Black, 17));
  }

  @Test
  public void writesSetTextAndSetsColor() throws Exception {
    filledPolygon.setAlpha(17);
    filledPolygon.setText("A");
    filledPolygon.paint(canvas);
    verify(canvas, atLeast(2)).setColor(new RGBColor(RGBColor.Black, 17));
    verify(canvas, atLeastOnce()).drawString(eq("A"), any(Coordinate.class));
  }
}
