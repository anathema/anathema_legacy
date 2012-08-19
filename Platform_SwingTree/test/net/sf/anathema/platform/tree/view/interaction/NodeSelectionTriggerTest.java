package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.presenter.view.NodeInteractionListener;
import net.sf.anathema.platform.tree.view.container.DefaultContainerCascade;
import net.sf.anathema.platform.tree.view.container.IdentifiedPolygon;
import net.sf.anathema.platform.tree.view.draw.FilledPolygon;
import net.sf.anathema.platform.tree.view.draw.PolygonMother;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class NodeSelectionTriggerTest {

  private NodeInteractionListener listener = mock(NodeInteractionListener.class);
  private DefaultContainerCascade cascade = new DefaultContainerCascade();
  private FilledPolygon polygon = PolygonMother.squareAtOriginWithLength2();
  private IdentifiedPolygon identifiedPolygon = new IdentifiedPolygon(polygon, "X");
  private NodeSelectionTrigger trigger = new NodeSelectionTrigger(cascade, listener);

  @Before
  public void setUp() throws Exception {
    cascade.add(identifiedPolygon);
  }

  @Test
  public void triggersSelectionOfIdOnListener() throws Exception {
    trigger.execute(polygon);
    verify(listener).nodeSelected("X");
  }

  @Test
  public void breaksUpConnectionToPolygonOnceTaskIsDone() throws Exception {
    trigger.execute(polygon);
    polygon.toggle();
    verify(listener, times(1)).nodeSelected("X");
  }
}
