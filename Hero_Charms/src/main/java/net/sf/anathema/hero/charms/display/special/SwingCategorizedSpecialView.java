package net.sf.anathema.hero.charms.display.special;

import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;
import net.sf.anathema.framework.value.IntValueView;
import net.sf.anathema.platform.tree.presenter.view.CategorizedSpecialNodeView;
import net.sf.anathema.platform.tree.presenter.view.ISpecialNodeView;
import net.sf.anathema.platform.tree.presenter.view.SpecialCharmContainer;

import java.util.ArrayList;
import java.util.List;

public class SwingCategorizedSpecialView implements CategorizedSpecialNodeView, ISpecialNodeView {
  private final ISpecialCharm visitedCharm;
  private final List<ProxyIntValueView> views = new ArrayList<>();

  public SwingCategorizedSpecialView(ISpecialCharm visitedCharm) {
    this.visitedCharm = visitedCharm;
  }

  @Override
  public IntValueView addCategory(String labelText, int maxValue, int value) {
    ProxyIntValueView proxyIntValueView = new ProxyIntValueView(labelText, maxValue, value);
    views.add(proxyIntValueView);
    return proxyIntValueView;
  }

  @Override
  public String getNodeId() {
    return visitedCharm.getCharmId();
  }

  @Override
  public void showIn(SpecialCharmContainer container) {
    for (ProxyIntValueView view : views) {
      IntValueView actualView = container.add(IntValueView.class, view.getLabel(), view.getValue(), view.getMaxValue());
      view.setActualView(actualView);
    }
  }
}