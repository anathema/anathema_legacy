package net.sf.anathema.character.main.magic.model.charmtree.builder.stringbuilder;

import net.sf.anathema.character.main.magic.model.charmtree.builder.MagicDisplayLabeler;
import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.lib.gui.ConfigurableTooltip;
import net.sf.anathema.lib.resources.Resources;

public class MagicNameContributor implements MagicTooltipContributor {
  private final MagicDisplayLabeler labeler;

  public MagicNameContributor(Resources resources) {
    this.labeler = new MagicDisplayLabeler(resources);
  }

  @Override
  public void buildStringForMagic(ConfigurableTooltip tooltip, Magic magic, Object details) {
    tooltip.appendTitleLine(labeler.getLabelForMagic(magic));
  }
}