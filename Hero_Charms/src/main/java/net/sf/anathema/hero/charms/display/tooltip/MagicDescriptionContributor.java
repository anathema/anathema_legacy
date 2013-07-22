package net.sf.anathema.hero.charms.display.tooltip;

import net.sf.anathema.character.main.magic.description.MagicDescription;
import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.main.magic.basic.Magic;
import net.sf.anathema.lib.gui.ConfigurableTooltip;

public class MagicDescriptionContributor implements MagicTooltipContributor {
  private MagicDescriptionProvider magicDescriptionProvider;

  public MagicDescriptionContributor(MagicDescriptionProvider magicDescriptionProvider) {
    this.magicDescriptionProvider = magicDescriptionProvider;
  }

  public void buildStringForMagicWithoutSpecials(ConfigurableTooltip tooltip, Magic magic) {
    this.buildStringForMagic(tooltip, magic, null);
  }

  @Override
  public void buildStringForMagic(ConfigurableTooltip tooltip, Magic magic, Object specialDetails) {
    MagicDescription charmDescription = magicDescriptionProvider.getCharmDescription(magic);
    if (charmDescription.isEmpty()) {
      return;
    }
    for (String paragraph : charmDescription.getParagraphs()) {
      tooltip.appendDescriptiveLine(paragraph);
    }
  }
}