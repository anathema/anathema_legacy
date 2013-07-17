package net.sf.anathema.character.main.magic.charmtree.builder.stringbuilder;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.special.charms.ISpecialCharm;
import net.sf.anathema.lib.gui.ConfigurableTooltip;

public interface CharmTooltipBuilder {
  void configureTooltipWithoutSpecials(Charm charm, ConfigurableTooltip tooltip);

  void configureTooltipWithSpecials(Charm charm, ISpecialCharm special, ConfigurableTooltip tooltip);
}