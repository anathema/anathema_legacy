package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.lib.gui.ui.ObjectUi;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.Icon;

public class HealthTypeUi implements ObjectUi<Object> {

  private final IResources resources;

  public HealthTypeUi(IResources resources) {
    this.resources = resources;
  }

  @Override
  public Icon getIcon(Object value) {
    return null;
  }

  @Override
  public String getLabel(Object value) {
    if (value == null) {
      return resources.getString("ComboBox.SelectLabel"); //$NON-NLS-1$
    }
    HealthType healthType = (HealthType) value;
    return resources.getString("HealthType." + healthType.name() + ".Capitalized"); //$NON-NLS-1$//$NON-NLS-2$
  }

  @Override
  public String getToolTipText(Object value) {
    return null;
  }
}