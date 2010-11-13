package net.sf.anathema.character.equipment.item;

import javax.swing.Icon;

import net.disy.commons.swing.ui.IObjectUi;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.lib.resources.IResources;

public final class ExaltedRuleSetUi implements IObjectUi {
  private final IResources resources;

  public ExaltedRuleSetUi(IResources resources) {
    this.resources = resources;
  }

  public Icon getIcon(Object value) {
    return null;
  }

  public String getLabel(Object value) {
    if (value == null) {
      return resources.getString("ComboBox.SelectLabel"); //$NON-NLS-1$
    }
    return resources.getString("Ruleset." + ((IExaltedRuleSet) value).getId()); //$NON-NLS-1$
  }
}