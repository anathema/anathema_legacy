package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.lib.gui.TechnologyAgnosticUIConfiguration;
import net.sf.anathema.lib.resources.Resources;

public class CompositionUi implements TechnologyAgnosticUIConfiguration<MaterialComposition> {
  private Resources resources;

  public CompositionUi(Resources resources) {
    this.resources = resources;
  }

  @Override
  public String getIconsRelativePath(MaterialComposition value) {
    return NO_ICON;
  }

  @Override
  public String getLabel(MaterialComposition value) {
    return resources.getString("MaterialComposition." + value.getId());
  }

  @Override
  public String getToolTipText(MaterialComposition value) {
    return NO_TOOLTIP;
  }
}