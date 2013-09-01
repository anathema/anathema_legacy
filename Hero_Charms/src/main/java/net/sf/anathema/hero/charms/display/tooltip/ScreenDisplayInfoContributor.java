package net.sf.anathema.hero.charms.display.tooltip;

import net.sf.anathema.character.main.magic.basic.Magic;
import net.sf.anathema.lib.gui.ConfigurableTooltip;
import net.sf.anathema.framework.environment.Resources;

public class ScreenDisplayInfoContributor extends MagicInfoStringBuilder implements MagicTooltipContributor {

  private Resources resources;

  public ScreenDisplayInfoContributor(Resources resources) {
    super(resources, new CostStringBuilder(resources, "CharmTreeView.ToolTip.Mote", "CharmTreeView.ToolTip.Motes"),
            new CostStringBuilder(resources, "WillpowerType.Name"),
            new HealthCostStringBuilder(resources, "CharmTreeView.ToolTip.HealthLevel", "CharmTreeView.ToolTip.HealthLevels"),
            new CostStringBuilder(resources, "CharmTreeView.ToolTip.ExperiencePoint", "CharmTreeView.ToolTip.ExperiencePoints"));
    this.resources = resources;
  }

  @Override
  public void buildStringForMagic(ConfigurableTooltip tooltip, Magic magic, Object details) {
    String label = resources.getString("CharmTreeView.ToolTip.Cost");
    String text = createCostString(magic);
    tooltip.appendLine(label, text);
  }
}