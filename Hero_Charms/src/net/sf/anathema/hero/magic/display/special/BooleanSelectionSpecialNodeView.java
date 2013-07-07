package net.sf.anathema.hero.magic.display.special;

import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;
import net.sf.anathema.lib.workflow.booleanvalue.IBooleanValueView;
import net.sf.anathema.platform.tree.presenter.view.ISpecialNodeView;
import net.sf.anathema.platform.tree.presenter.view.SpecialCharmContainer;

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