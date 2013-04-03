package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.lib.resources.IResources;

public class CompositionUi implements TechnologyAgnosticUIConfiguration<MaterialComposition> {
  private IResources resources;

  public CompositionUi(IResources resources) {
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