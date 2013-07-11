package net.sf.anathema.hero.spells.display;

import net.sf.anathema.hero.magic.display.MagicLearnProperties;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.util.Identifier;

public interface ISpellViewProperties extends MagicLearnProperties {

  String getCircleLabel();

  AgnosticUIConfiguration<Identifier> getCircleSelectionRenderer();
}