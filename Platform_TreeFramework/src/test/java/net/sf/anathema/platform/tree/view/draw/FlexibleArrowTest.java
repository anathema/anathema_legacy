package net.sf.anathema.platform.tree.view.draw;

import com.google.common.collect.ImmutableList;
import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.framework.ui.Width;
import net.sf.anathema.platform.tree.display.shape.Circle;
import net.sf.anathema.platform.tree.display.shape.TransformedShape;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FlexibleArrowTest {

  FlexibleArrow arrow = new FlexibleArrow();
  Canvas graphics = mock(Canvas.class);

  @Before
  public void setUp() throws Exception {
    arrow.addPoint(17, 6);
    arrow.addPoint(6, 10);
  }

  @Test
  public void connectsDots() throws Exception {
    arrow.paint(graphics);
    verify(graphics).drawPolyline(ImmutableList.of(new Coordinate(17, 6), new Coordinate(6, 10)));
  }

  @SuppressWarnings("unchecked")
  @Test
  public void drawsWide() throws Exception {
    arrow.paint(graphics);
    InOrder inOrder = inOrder(graphics);
    inOrder.verify(graphics).setStrokeWidth(new Width(6));
    inOrder.verify(graphics).drawPolyline(any(List.class));
  }

  @SuppressWarnings("unchecked")
  @Test
  public void drawsBlack() throws Exception {
    arrow.paint(graphics);
    InOrder inOrder = inOrder(graphics);
    inOrder.verify(graphics).setColor(RGBColor.Black);
    inOrder.verify(graphics).drawPolyline(any(List.class));
  }

  @Test
  public void hasDotAtBottom() throws Exception {
    arrow.paint(graphics);
    verify(graphics).fill(new TransformedShape(new Circle(11, 0, 12)));
  }

  @Test
  public void movesPoints() throws Exception {
    arrow.moveBy(1, 2);
    arrow.paint(graphics);
    verify(graphics).drawPolyline(ImmutableList.of(new Coordinate(17, 6), new Coordinate(6, 10)));
  }
}