package net.sf.anathema.platform.tree.view;

import net.sf.anathema.platform.tree.display.NodePresentationProperties;
import net.sf.anathema.platform.tree.view.container.Cascade;
import net.sf.anathema.platform.tree.view.container.ContainerCascade;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AgnosticCascadeBuilderTest {

  @Test
  public void createsCascadeWithAllElements() throws Exception {
    ContainerCascade cascade = mock(ContainerCascade.class);
    AgnosticCascadeBuilder builder = new AgnosticCascadeBuilder();
    builder.add(cascade);
    Cascade result = builder.create();
    NodePresentationProperties properties = mock(NodePresentationProperties.class);
    result.initNodeNames(properties);
    verify(cascade).initNodeNames(properties);
  }
}