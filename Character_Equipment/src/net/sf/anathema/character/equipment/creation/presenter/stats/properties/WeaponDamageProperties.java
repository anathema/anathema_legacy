package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
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

  public AgnosticUIConfiguration<HealthType> getHealthTypeUi() {
    return new HealthTypeUi(resources);
  }
}