package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.sf.anathema.hero.health.HealthType;
import net.sf.anathema.lib.gui.AbstractUIConfiguration;
import net.sf.anathema.framework.environment.Resources;

public class HealthTypeUi extends AbstractUIConfiguration<HealthType> {

  private final Resources resources;

  public HealthTypeUi(Resources resources) {
    this.resources = resources;
  }

  @Override
  public String getLabel(HealthType value) {
    if (value == null) {
      return resources.getString("ComboBox.SelectLabel");
    }
    return resources.getString("HealthType." + value.name() + ".Capitalized");
  }
}