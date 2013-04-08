package net.sf.anathema.character.equipment.character;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.TechnologyAgnosticUIConfiguration;
import net.sf.anathema.lib.resources.Resources;

public final class MagicMaterialCellRenderer implements TechnologyAgnosticUIConfiguration<MagicalMaterial> {
  private final Resources resources;

  public MagicMaterialCellRenderer(Resources resources) {
    this.resources = resources;
  }

  @Override
  public RelativePath getIconsRelativePath(MagicalMaterial value) {
    return NO_ICON;
  }

  @Override
  public String getLabel(MagicalMaterial value) {
    if (value == null) {
      return resources.getString("MagicMaterial.Unavailable");
    } else {
      return resources.getString("MagicMaterial." + value.name());
    }
  }

  @Override
  public String getToolTipText(MagicalMaterial value) {
    return NO_TOOLTIP;
  }
}