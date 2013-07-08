package net.sf.anathema.hero.magic.display.special;

import net.sf.anathema.character.main.magic.display.view.charms.CheckMenuItemView;
import net.sf.anathema.lib.workflow.booleanvalue.IBooleanValueView;
import net.sf.anathema.platform.tree.presenter.view.ContentFactory;

public class SpecialBooleanDisplayFactory implements ContentFactory {

  @Override
  public IBooleanValueView create(Object... parameters) {
    String label = (String) parameters[0];
    return new CheckMenuItemView(label);
  }
}
