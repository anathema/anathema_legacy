package net.sf.anathema.hero.charms.display.special;

import net.sf.anathema.framework.value.IntValueView;
import net.sf.anathema.platform.tree.display.CategorizedSpecialNodeView;
import net.sf.anathema.platform.tree.display.SpecialCharmContainer;

import java.util.ArrayList;
import java.util.List;

public class CategorizedSpecialView implements CategorizedSpecialNodeView {
  private final List<ProxyIntValueView> views = new ArrayList<>();
  private String id;

  @Override
  public IntValueView addCategory(String labelText, int maxValue, int value) {
    ProxyIntValueView proxyIntValueView = new ProxyIntValueView(labelText, maxValue, value);
    views.add(proxyIntValueView);
    return proxyIntValueView;
  }

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
    for (ProxyIntValueView view : views) {
      IntValueView actualView = container.add(IntValueView.class, view.getLabel(), view.getValue(), view.getMaxValue());
      view.setActualView(actualView);
    }
  }
}