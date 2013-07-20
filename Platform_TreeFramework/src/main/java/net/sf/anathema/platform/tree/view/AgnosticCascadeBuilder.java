package net.sf.anathema.platform.tree.view;

import net.sf.anathema.platform.tree.document.CascadeBuilder;
import net.sf.anathema.platform.tree.view.container.AggregatedCascade;
import net.sf.anathema.platform.tree.view.container.Cascade;
import net.sf.anathema.platform.tree.view.container.ContainerCascade;

public class AgnosticCascadeBuilder implements CascadeBuilder<ContainerCascade, Cascade> {

  private final AggregatedCascade aggregatedCascade = new AggregatedCascade();

  @Override
  public void add(ContainerCascade cascade) {
    aggregatedCascade.add(cascade);
  }

  @Override
  public Cascade create() {
    return aggregatedCascade;
  }
}
