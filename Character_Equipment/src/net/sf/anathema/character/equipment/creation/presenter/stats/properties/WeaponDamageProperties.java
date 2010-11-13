package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.disy.commons.swing.ui.IObjectUi;
import net.sf.anathema.lib.resources.IResources;

public class WeaponDamageProperties extends AbstractProperties {

  private final IResources resources;

  public WeaponDamageProperties(IResources resources) {
    super(resources);
    this.resources = resources;
  }

  public String getDamageLabel() {
    return getLabelString("Equipment.Stats.Long.Damage"); //$NON-NLS-1$
  }

  public IObjectUi getHealthTypeUi() {
    return new HealthTypeUi(resources);
  }
}