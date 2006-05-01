package net.sf.anathema.character.equipment.creation.properties;

import net.disy.commons.swing.ui.IObjectUi;
import net.sf.anathema.lib.resources.IResources;

public class WeaponDamageProperties {

  private final IResources resources;

  public WeaponDamageProperties(IResources resources) {
    this.resources = resources;
  }

  public String getDamageLabel() {
    return "Damage:";
  }
  
  public IObjectUi getHealthTypeUi() {
    return new HealthTypeUi(resources);
  }
}