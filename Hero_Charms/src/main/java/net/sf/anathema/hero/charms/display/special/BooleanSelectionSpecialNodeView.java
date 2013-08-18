package net.sf.anathema.hero.charms.display.special;

import net.sf.anathema.lib.workflow.booleanvalue.IBooleanValueView;
import net.sf.anathema.platform.tree.display.SpecialCharmContainer;

import java.util.ArrayList;
import java.util.List;

public class BooleanSelectionSpecialNodeView implements ToggleButtonSpecialNodeView {
  private final List<ProxyBooleanValueView> views = new ArrayList<>();
  private String id;

  @Override
  public String getNodeId() {
    return id;
  }

  @Override
  public void setCharmId(String charmId) {
    this.id = charmId;
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