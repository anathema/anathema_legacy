package net.sf.anathema.hero.charms.display.special;

import net.sf.anathema.lib.workflow.booleanvalue.IBooleanValueView;
import net.sf.anathema.platform.tree.display.ContentFactory;

public class SpecialBooleanDisplayFactory implements ContentFactory {

  @SuppressWarnings("unchecked")
  @Override
  public IBooleanValueView create(Object... parameters) {
    String label = (String) parameters[0];
    return new CheckMenuItemView(label);
  }
}
