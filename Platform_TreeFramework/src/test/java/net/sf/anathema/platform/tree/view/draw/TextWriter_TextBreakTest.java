package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.framework.ui.Area;
import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.platform.tree.display.shape.AgnosticShape;
import net.sf.anathema.platform.tree.display.shape.Polygon;
import org.junit.Before;
import org.junit.Test;

import static net.sf.anathema.platform.tree.view.draw.FilledPolygon_GraphicsTest.ANY_AREA_WITH_CONTENT;
import static net.sf.anathema.platform.tree.view.draw.FilledPolygon_GraphicsTest.ANY_RECTANGLE;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TextWriter_TextBreakTest {

  private LineSuggestion lineSuggestion = mock(LineSuggestion.class);
  private final Polygon shape = new Polygon();
  private TextWriter writer = new TextWriter(shape, lineSuggestion);
  private Canvas canvas = mock(Canvas.class);

  @Before
  public void setUp() throws Exception {
    shape.addPoint(0, 0);
    shape.addPoint(100, 100);
    writer.setText("Forceful Arrow");
  }

  @Before
  public void configureCanvas() throws Exception {
    when(canvas.calculateBounds(any(AgnosticShape.class))).thenReturn(ANY_RECTANGLE);
    when(canvas.measureText(anyString())).thenReturn(ANY_AREA_WITH_CONTENT);
  }


  @Test
  public void breaksText() throws Exception {
    when(lineSuggestion.suggestNumberOfLines(any(Area.class))).thenReturn(2);
    writer.write(canvas);
    verify(canvas).drawString("Forceful", new Coordinate(50, 38));
  }

  @Test
  public void keepsBreaksOnceEstablished() throws Exception {
    when(lineSuggestion.suggestNumberOfLines(any(Area.class))).thenReturn(2, 1);
    writer.write(canvas);
    writer.write(canvas);
    verify(canvas, times(2)).drawString("Forceful", new Coordinate(50, 38));
  }
}