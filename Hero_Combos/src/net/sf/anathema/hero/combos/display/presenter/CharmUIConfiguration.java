package net.sf.anathema.hero.combos.display.presenter;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charmtree.builder.MagicDisplayLabeler;
import net.sf.anathema.character.main.magic.model.charmtree.builder.stringbuilder.ICharmInfoStringBuilder;
import net.sf.anathema.lib.gui.AbstractUIConfiguration;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;

public class CharmUIConfiguration extends AbstractUIConfiguration<Identifier> {
  private final Resources resources;
  private final ICharmInfoStringBuilder charmInfoStringProvider;

  public CharmUIConfiguration(Resources resources, ICharmInfoStringBuilder charmInfoStringProvider) {
    this.resources = resources;
    this.charmInfoStringProvider = charmInfoStringProvider;
  }

  @Override
  public String getLabel(Identifier value) {
    return new MagicDisplayLabeler(resources).getLabelForMagic((Charm) value);
  }

  @Override
  public String getToolTipText(Identifier value) {
    return charmInfoStringProvider.getInfoString((Charm) value, null);
  }
}