package net.sf.anathema.character.view.magic;

import net.sf.anathema.lib.workflow.booleanvalue.IBooleanValueView;
import net.sf.anathema.platform.tree.presenter.view.ContentFactory;

public class SpecialBooleanDisplayFactory implements ContentFactory {

  @Override
  public IBooleanValueView create(Object... parameters) {
    String label = (String) parameters[0];
    return new CheckMenuItemView(label);
  }
}
