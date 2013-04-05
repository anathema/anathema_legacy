package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.TechnologyAgnosticUIConfiguration;
import net.sf.anathema.lib.resources.Resources;

public class MaterialUi implements TechnologyAgnosticUIConfiguration<MagicalMaterial> {
  private Resources resources;

  public MaterialUi(Resources resources) {
    this.resources = resources;
  }

  @Override
  public RelativePath getIconsRelativePath(MagicalMaterial value) {
    return NO_ICON;
  }

  @Override
  public String getLabel(MagicalMaterial value) {
    if (value == null) {
      return NO_LABEL;
    }
    return resources.getString("MagicMaterial." + value.getId());
  }

  @Override
  public String getToolTipText(MagicalMaterial value) {
    return NO_TOOLTIP;
  }
}
