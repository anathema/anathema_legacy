package net.sf.anathema.hero.combos.display.presenter;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charmtree.builder.MagicDisplayLabeler;
import net.sf.anathema.character.main.magic.model.charmtree.builder.stringbuilder.CharmTooltipBuilder;
import net.sf.anathema.lib.gui.AbstractUIConfiguration;
import net.sf.anathema.lib.gui.ConfigurableTooltip;
import net.sf.anathema.lib.resources.Resources;

public class CharmUiConfigurationWithoutSpecials extends AbstractUIConfiguration<Charm> {
  private final Resources resources;
  private final CharmTooltipBuilder charmInfoStringProvider;

  public CharmUiConfigurationWithoutSpecials(Resources resources, CharmTooltipBuilder charmInfoStringProvider) {
    this.resources = resources;
    this.charmInfoStringProvider = charmInfoStringProvider;
  }

  @Override
  protected String labelForExistingValue(Charm value) {
    return new MagicDisplayLabeler(resources).getLabelForMagic(value);
  }

  @Override
  protected void configureTooltipForExistingValue(Charm value, ConfigurableTooltip configurableTooltip) {
    charmInfoStringProvider.configureTooltipWithoutSpecials(value, configurableTooltip);
  }
}