package net.sf.anathema.charmtree.builder.stringbuilder;

import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.lib.gui.TooltipBuilder;
import net.sf.anathema.lib.resources.Resources;

public class ScreenDisplayInfoStringBuilder extends MagicInfoStringBuilder implements IMagicTooltipStringBuilder {

  private Resources resources;

  public ScreenDisplayInfoStringBuilder(Resources resources) {
    super(resources, new CostStringBuilder(resources, "CharmTreeView.ToolTip.Mote", "CharmTreeView.ToolTip.Motes"),
            new CostStringBuilder(resources, "WillpowerType.Name"),
            new HealthCostStringBuilder(resources, "CharmTreeView.ToolTip.HealthLevel", "CharmTreeView.ToolTip.HealthLevels"),
            new CostStringBuilder(resources, "CharmTreeView.ToolTip.ExperiencePoint", "CharmTreeView.ToolTip.ExperiencePoints"));
    this.resources = resources;
  }

  @Override
  public void buildStringForMagic(StringBuilder builder, IMagic magic, Object details) {
    builder.append(resources.getString("CharmTreeView.ToolTip.Cost"));
    builder.append(TooltipBuilder.ColonSpace);
    builder.append(createCostString(magic));
    builder.append(TooltipBuilder.HtmlLineBreak);
  }

}
