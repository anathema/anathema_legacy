package net.sf.anathema.platform.tree.view;

import com.google.common.collect.Iterables;
import net.sf.anathema.platform.tree.view.draw.FilledPolygon;
import net.sf.anathema.platform.tree.view.draw.PolygonMother;
import org.junit.Test;

import java.awt.Point;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class NodeContainerTest {

  NodeContainer container = new NodeContainer();

  @Test
  public void iteratesOverAll() throws Exception {
    FilledPolygon firstPolygon = PolygonMother.any();
    FilledPolygon secondPolygon = PolygonMother.any();
    container.add(firstPolygon);
    container.add(secondPolygon);
    assertThat(Iterables.contains(container, firstPolygon), is(true));
    assertThat(Iterables.contains(container, secondPolygon), is(true));
  }

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
