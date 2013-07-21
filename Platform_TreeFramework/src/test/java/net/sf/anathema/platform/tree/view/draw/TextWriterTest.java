package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.framework.ui.Area;
import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.framework.ui.FontStyle;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.platform.tree.display.shape.AgnosticShape;
import net.sf.anathema.platform.tree.display.shape.Polygon;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static net.sf.anathema.platform.tree.view.draw.FilledPolygon_GraphicsTest.ANY_AREA_WITH_CONTENT;
import static net.sf.anathema.platform.tree.view.draw.FilledPolygon_GraphicsTest.ANY_RECTANGLE;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Ignore
public class TextWriterTest {

  private final Polygon shape = new Polygon();
  private final LineSuggestion suggestion = mock(LineSuggestion.class);
  private TextWriter writer = new TextWriter(shape, suggestion);
  private Canvas canvas = mock(Canvas.class);

  @Before
  public void createShape() throws Exception {
    shape.addPoint(0, 0);
    shape.addPoint(100, 100);
  }

  @Before
  public void configureLines() throws Exception {
    when(suggestion.suggestNumberOfLines(any(Area.class))).thenReturn(1);
  }

  @Before
  public void configureWriter() throws Exception {
    writer.setText("ABC");
    writer.setStroke(RGBColor.Red);
  }

  @Before
  public void configureCanvas() throws Exception {
    when(canvas.measureText(anyString())).thenReturn(ANY_AREA_WITH_CONTENT);
    when(canvas.calculateBounds(any(AgnosticShape.class))).thenReturn(ANY_RECTANGLE);
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