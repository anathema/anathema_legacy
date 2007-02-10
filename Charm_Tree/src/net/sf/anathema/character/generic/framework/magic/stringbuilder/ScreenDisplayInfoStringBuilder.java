package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import net.sf.anathema.lib.resources.IResources;

public class ScreenDisplayInfoStringBuilder extends MagicInfoStringBuilder {

  public ScreenDisplayInfoStringBuilder(IResources resources) {
    super(resources, new CostStringBuilder(resources, "CharmTreeView.ToolTip.Mote", "CharmTreeView.ToolTip.Motes"), //$NON-NLS-1$ //$NON-NLS-2$
        new CostStringBuilder(resources, "WillpowerType.Name"), //$NON-NLS-1$
        new HealthCostStringBuilder(resources, "CharmTreeView.ToolTip.HealthLevel", //$NON-NLS-1$
            "CharmTreeView.ToolTip.HealthLevels"), //$NON-NLS-1$
        new CostStringBuilder(
            resources,
            "CharmTreeView.ToolTip.ExperiencePoint", "CharmTreeView.ToolTip.ExperiencePoints"));//$NON-NLS-1$//$NON-NLS-2$
  }
}
