package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.lib.resources.IResources;

public class ScreenDisplayInfoStringBuilder extends MagicInfoStringBuilder implements IMagicTooltipStringBuilder {

  private IResources resources;
	
  public ScreenDisplayInfoStringBuilder(IResources resources) {
    super(resources, new CostStringBuilder(resources, "CharmTreeView.ToolTip.Mote", "CharmTreeView.ToolTip.Motes"), //$NON-NLS-1$ //$NON-NLS-2$
        new CostStringBuilder(resources, "WillpowerType.Name"), //$NON-NLS-1$
        new HealthCostStringBuilder(resources, "CharmTreeView.ToolTip.HealthLevel", //$NON-NLS-1$
            "CharmTreeView.ToolTip.HealthLevels"), //$NON-NLS-1$
        new CostStringBuilder(
            resources,
            "CharmTreeView.ToolTip.ExperiencePoint", "CharmTreeView.ToolTip.ExperiencePoints"));//$NON-NLS-1$//$NON-NLS-2$
    this.resources = resources;
  }

	@Override
	public void buildStringForMagic(StringBuilder builder, IMagic magic, Object details) {
		builder.append(resources.getString("CharmTreeView.ToolTip.Cost"));
		builder.append(ColonSpace);
		builder.append(createCostString(magic));
		builder.append(HtmlLineBreak);
	}
  
  
}
