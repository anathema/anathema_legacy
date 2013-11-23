package net.sf.anathema.hero.charms.display;

import net.sf.anathema.character.main.view.SubViewFactory;
import net.sf.anathema.framework.util.Produces;
import net.sf.anathema.framework.value.IntValueView;
import net.sf.anathema.hero.charms.display.view.CharmView;
import net.sf.anathema.hero.charms.display.view.FxCharmView;
import net.sf.anathema.lib.workflow.booleanvalue.IBooleanValueView;

@Produces(CharmView.class)
public class CharmViewFactory implements SubViewFactory {
  //The special types are registered here so cascades don't need a character type as well.
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    FxCharmView fxView = new FxCharmView();
    fxView.registerSpecialType(IntValueView.class, new FxIntDisplayFactory());
    fxView.registerSpecialType(IBooleanValueView.class, new FxBooleanDisplayFactory());
    return (T) fxView;
  }
}
