package net.sf.anathema.platform.tree.view;

import net.sf.anathema.platform.svgtree.document.CascadeBuilder;
import net.sf.anathema.platform.tree.view.container.AggregatedCascade;
import net.sf.anathema.platform.tree.view.container.Cascade;
import net.sf.anathema.platform.tree.view.container.ContainerCascade;

public class SwingCascadeBuilder implements CascadeBuilder<ContainerCascade, Cascade> {

  private final AggregatedCascade aggregatedCascade = new AggregatedCascade();

  @Override
  public void initialize() {
    //nothing to do
  }

  @Override
  public void add(ContainerCascade cascade) {
    aggregatedCascade.add(cascade);
  }

  @Override
  public void applyFinalTouch(double currentWidth, double maximumHeight) {
    //nothing to do
  }

  @Override
  public Cascade create() {
    return aggregatedCascade;
  }
}
