package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.framework.ui.Area;
import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.framework.ui.FontStyle;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.framework.ui.Rectangle;
import net.sf.anathema.platform.tree.display.shape.AgnosticShape;
import net.sf.anathema.platform.tree.display.shape.Polygon;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TextWriterTest {

  private static final int SHAPE_WIDTH = 100;
  private static final int SHAPE_HEIGHT = 100;
  public static final int TEXT_WIDTH = 50;
  public static final int TEXT_HEIGHT = 15;

  private final Polygon shape = new Polygon();
  private final LineSuggestion suggestion = mock(LineSuggestion.class);
  private TextWriter writer = new TextWriter(shape, suggestion);
  private Canvas canvas = mock(Canvas.class);

  @Before
  public void createShape() throws Exception {
    shape.addPoint(0, 0);
    shape.addPoint(SHAPE_WIDTH, SHAPE_HEIGHT);
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
    when(canvas.measureText(anyString())).thenReturn(new Area(TEXT_WIDTH, TEXT_HEIGHT));
    when(canvas.calculateBounds(any(AgnosticShape.class))).thenReturn(new Rectangle(new Coordinate(0, 0), new Area(SHAPE_WIDTH, SHAPE_HEIGHT)));
  }


  @Test
  public void writesGivenText() throws Exception {
    writer.write(canvas);
    int expectedX = SHAPE_WIDTH / 2 - TEXT_WIDTH / 2;
    int expectedY = 53;
    verify(canvas).drawString("ABC", new Coordinate(expectedX, expectedY));
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