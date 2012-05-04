package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.view.draw.FilledPolygon;
import net.sf.anathema.platform.tree.view.draw.PolygonMother;
import org.junit.Test;

import java.awt.Point;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class NodeContainerTest {

  NodeContainer container = new NodeContainer();

  @Test
  public void actsOnPolygonAtPoint() throws Exception {
    FilledPolygon polygon = PolygonMother.squareAtOriginWithLength2();
    container.add(polygon);
    Closure closure = mock(Closure.class);
    container.onPolygonAtPoint(new Point(1, 1)).perform(closure);
    verify(closure).execute(polygon);
  }

  @Test
  public void performsDefaultOperationOtherwise() throws Exception {
    FilledPolygon polygon = PolygonMother.squareAtOriginWithLength2();
    container.add(polygon);
    Closure closure = mock(Closure.class);
    Runnable defaultAction = mock(Runnable.class);
    container.onPolygonAtPoint(new Point(3, 3)).perform(closure).orFallBackTo(defaultAction);
    verify(defaultAction).run();
  }
}