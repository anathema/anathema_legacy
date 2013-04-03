package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.lib.resources.IResources;

public class MaterialUi implements TechnologyAgnosticUIConfiguration<MagicalMaterial> {
  private IResources resources;

  public MaterialUi(IResources resources) {
    this.resources = resources;
  }

  @Override
  public String getIconsRelativePath(MagicalMaterial value) {
    return NO_ICON;
  }

  @Override
  public String getLabel(MagicalMaterial value) {
    return resources.getString("MagicMaterial." + value.getId());
  }

  @Override
  public String getToolTipText(MagicalMaterial value) {
    return NO_TOOLTIP;
  }
}
