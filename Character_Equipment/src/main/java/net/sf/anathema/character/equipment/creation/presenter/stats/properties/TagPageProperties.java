package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.sf.anathema.character.equipment.creation.presenter.IWeaponTag;
import net.sf.anathema.framework.environment.Resources;

public class TagPageProperties {

  private final Resources resources;

  public TagPageProperties(Resources resources) {
    this.resources = resources;
  }

  public String getLabel(IWeaponTag tag) {
    return resources.getString("Equipment.Tag." + tag.getId());
  }

  public String getToolTip(IWeaponTag tag) {
    String abbreviation = resources.getString("Weapons.Tags." + tag.getId() + ".Short");
    String explanation = resources.getString("Equipment.Tag.Tooltip." + tag.getId());
    return explanation + " (" + abbreviation + ")";
  }
}