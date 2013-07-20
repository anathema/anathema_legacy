package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.framework.ui.Area;
import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.platform.tree.display.shape.Polygon;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.isA;
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

  @Test
  public void breaksText() throws Exception {
    when(lineSuggestion.suggestNumberOfLines(isA(Area.class))).thenReturn(2);
    writer.write(canvas);
    verify(canvas).drawString("Forceful", new Coordinate(50, 38));
  }

  @Test
  public void keepsBreaksOnceEstablished() throws Exception {
    when(lineSuggestion.suggestNumberOfLines(isA(Area.class))).thenReturn(2, 1);
    writer.write(canvas);
    writer.write(canvas);
    verify(canvas, times(2)).drawString("Forceful", new Coordinate(50, 38));
  }
}