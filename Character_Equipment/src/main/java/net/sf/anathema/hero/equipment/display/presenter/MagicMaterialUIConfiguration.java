package net.sf.anathema.hero.equipment.display.presenter;

import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.lib.gui.AbstractUIConfiguration;
import net.sf.anathema.framework.environment.Resources;

public class MagicMaterialUIConfiguration extends AbstractUIConfiguration<MagicalMaterial> {
  private final Resources resources;

  public MagicMaterialUIConfiguration(Resources resources) {
    this.resources = resources;
  }

  @Override
  public String getLabel(MagicalMaterial value) {
    if (value == null) {
      return resources.getString("MagicMaterial.Unavailable");
    } else {
      return resources.getString("MagicMaterial." + value.name());
    }
  }
}