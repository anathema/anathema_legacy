package net.sf.anathema.hero.equipment.display.view;

import net.sf.anathema.character.framework.display.SubViewFactory;
import net.sf.anathema.framework.util.Produces;
import net.sf.anathema.hero.equipment.display.presenter.EquipmentView;
import net.sf.anathema.platform.fx.Stylesheet;

@Produces(EquipmentView.class)
public class EquipmentViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    FxEquipmentView fxView = new FxEquipmentView();
    new Stylesheet("skin/platform/tooltip.css").applyToParent(fxView.getNode());
    return (T) fxView;
  }
}