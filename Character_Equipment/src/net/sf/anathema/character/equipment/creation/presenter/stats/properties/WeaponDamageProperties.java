package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.sf.anathema.lib.gui.ui.ObjectUi;
import net.sf.anathema.lib.resources.IResources;

public class WeaponDamageProperties extends AbstractProperties {

  private final IResources resources;

  public WeaponDamageProperties(IResources resources) {
    super(resources);
    this.resources = resources;
  }
  
  public String getMinDamageLabel() {
	return getLabelString("Equipment.Stats.Long.MinDamage"); //$NON-NLS-1$
  }

  public String getDamageLabel() {
    return getLabelString("Equipment.Stats.Long.Damage"); //$NON-NLS-1$
  }

  public ObjectUi<Object> getHealthTypeUi() {
    return new HealthTypeUi(resources);
  }
}