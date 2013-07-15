package net.sf.anathema.character.main.magic.model.charmtree.builder.stringbuilder;

import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.lib.gui.ConfigurableTooltip;

public interface MagicTooltipContributor {

  void buildStringForMagic(ConfigurableTooltip tooltip, Magic magic, Object specialDetails);
}
