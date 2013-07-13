package net.sf.anathema.character.main.magic.model.charmtree.builder.stringbuilder;

import net.sf.anathema.character.main.magic.description.MagicDescription;
import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.lib.gui.ConfigurableTooltip;
import net.sf.anathema.lib.resources.Resources;

public class MagicDescriptionContributor implements MagicTooltipContributor {
  private final Resources resources;
  private MagicDescriptionProvider magicDescriptionProvider;

  public MagicDescriptionContributor(Resources resources, MagicDescriptionProvider magicDescriptionProvider) {
    this.resources = resources;
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
    String[] paragraphs = charmDescription.getParagraphs();
    String descriptionLabel = resources.getString("CharmTreeView.ToolTip.Description");
    tooltip.appendLine(descriptionLabel, paragraphs[0]);
    if (paragraphs.length > 1) {
      for (int i = 1; i < paragraphs.length; i++) {
        String paragraph = paragraphs[i];
        tooltip.appendLine(paragraph);

      }
    }
  }
}