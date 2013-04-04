package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.sf.anathema.lib.gui.ui.ObjectUi;
import net.sf.anathema.lib.resources.Resources;

public class WeaponDamageProperties extends AbstractProperties {

  private final Resources resources;

  public WeaponDamageProperties(Resources resources) {
    super(resources);
    this.resources = resources;
  }
  
  public String getMinDamageLabel() {
	return getLabelString("Equipment.Stats.Long.MinDamage");
  }

  public String getDamageLabel() {
    return getLabelString("Equipment.Stats.Long.Damage");
  }

  public ObjectUi<Object> getHealthTypeUi() {
    return new HealthTypeUi(resources);
  }
}