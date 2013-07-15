package net.sf.anathema.character.main.magic.model.charmtree.builder.stringbuilder;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.lib.gui.ConfigurableTooltip;
import net.sf.anathema.lib.resources.Resources;

public class CharmPrerequisitesContributor implements MagicTooltipContributor {
  private final Resources resources;

  public CharmPrerequisitesContributor(Resources resources) {
    this.resources = resources;
  }

  @Override
  public void buildStringForMagic(ConfigurableTooltip tooltip, Magic magic, Object details) {
    if (magic instanceof Charm) {
      Charm charm = (Charm) magic;
      createPrerequisiteLines(tooltip, charm.getPrerequisites());
      createPrerequisiteLines(tooltip, charm.getEssence());
    }
  }

  private void createPrerequisiteLines(ConfigurableTooltip tooltip, ValuedTraitType... prerequisites) {
    for (ValuedTraitType prerequisite : prerequisites) {
      if (prerequisite.getCurrentValue() == 0) {
        continue;
      }
      String label = resources.getString("CharmTreeView.ToolTip.Minimum")+" "+ resources.getString(prerequisite.getType().getId());
      String value = String.valueOf(prerequisite.getCurrentValue());
      tooltip.appendLine(label, value);
    }
  }
}