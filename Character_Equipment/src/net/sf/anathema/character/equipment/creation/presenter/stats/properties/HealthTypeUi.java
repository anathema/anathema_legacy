package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.lib.gui.ui.ObjectUi;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.Icon;

public class HealthTypeUi implements ObjectUi<Object> {

  private final Resources resources;

  public HealthTypeUi(Resources resources) {
    this.resources = resources;
  }

  @Override
  public Icon getIcon(Object value) {
    return null;
  }

  @Override
  public String getLabel(Object value) {
    if (value == null) {
      return resources.getString("ComboBox.SelectLabel");
    }
    HealthType healthType = (HealthType) value;
    return resources.getString("HealthType." + healthType.name() + ".Capitalized");
  }

  @Override
  public String getToolTipText(Object value) {
    return null;
  }
}