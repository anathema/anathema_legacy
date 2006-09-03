package net.sf.anathema.character.equipment.item;

import javax.swing.Icon;

import net.disy.commons.swing.ui.IObjectUi;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public final class ExaltedRuleSetUi implements IObjectUi {
  public Icon getIcon(Object value) {
    return null;
  }

  public String getLabel(Object value) {
    if (value == null) {
      return "Select...";
    }
    return ((IExaltedRuleSet) value).getId();
  }
}