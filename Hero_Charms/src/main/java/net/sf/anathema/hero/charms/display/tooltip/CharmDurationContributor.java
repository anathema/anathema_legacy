package net.sf.anathema.hero.charms.display.tooltip;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.basic.Magic;
import net.sf.anathema.lib.gui.ConfigurableTooltip;
import net.sf.anathema.framework.environment.Resources;

public class CharmDurationContributor implements MagicTooltipContributor {
  private final Resources resources;

  public CharmDurationContributor(Resources resources) {
    this.resources = resources;
  }

  @Override
  public void buildStringForMagic(ConfigurableTooltip tooltip, Magic magic, Object details) {
    if (magic instanceof Charm) {
      String durationLabel = resources.getString("CharmTreeView.ToolTip.Duration");
      String durationText = ((Charm) magic).getDuration().getText(resources);
      tooltip.appendLine(durationLabel, durationText);
    }
  }
}
