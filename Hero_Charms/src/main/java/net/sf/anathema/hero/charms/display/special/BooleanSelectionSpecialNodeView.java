package net.sf.anathema.hero.charms.display.special;

import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.lib.workflow.booleanvalue.IBooleanValueView;
import net.sf.anathema.platform.tree.display.ISpecialNodeView;
import net.sf.anathema.platform.tree.display.SpecialCharmContainer;

import java.util.ArrayList;
import java.util.List;

public class BooleanSelectionSpecialNodeView implements ISpecialNodeView, ToggleButtonSpecialNodeView {
  private final ISpecialCharm charm;
  private final List<ProxyBooleanValueView> views = new ArrayList<>();

  public BooleanSelectionSpecialNodeView(ISpecialCharm charm) {
    this.charm = charm;
  }

  @Override
  public String getNodeId() {
    return charm.getCharmId();
  }

  @Override
  public void showIn(SpecialCharmContainer container) {
    for (ProxyBooleanValueView view : views) {
      IBooleanValueView actualView = container.add(IBooleanValueView.class, view.getLabel());
      view.setActualView(actualView);
    }
  }

  @Override
  public IBooleanValueView addSubeffect(String label) {
    ProxyBooleanValueView proxyBooleanValueView = new ProxyBooleanValueView(label);
    views.add(proxyBooleanValueView);
    return proxyBooleanValueView;
  }
}