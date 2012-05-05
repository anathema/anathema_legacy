package net.sf.anathema.platform.tree.view;

import net.sf.anathema.platform.svgtree.presenter.view.NodeInteractionListener;
import net.sf.anathema.platform.tree.view.container.Cascade;
import net.sf.anathema.platform.tree.view.container.ContainerCascade;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SwingCascadeBuilderTest {

  @Test
  public void createsCascadeWithAllElements() throws Exception {
    ContainerCascade cascade = mock(ContainerCascade.class);
    SwingCascadeBuilder builder = new SwingCascadeBuilder();
    builder.add(cascade);
    Cascade result = builder.create();
    NodeInteractionListener listener = mock(NodeInteractionListener.class);
    result.addInteractionListener(listener);
    verify(cascade).addInteractionListener(listener);
  }
}
