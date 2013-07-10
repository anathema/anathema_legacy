package net.sf.anathema.hero.spells.display;

import net.sf.anathema.character.main.magic.display.view.magic.IMagicLearnProperties;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.util.Identifier;

public interface ISpellViewProperties extends IMagicLearnProperties {

  String getCircleLabel();

  AgnosticUIConfiguration<Identifier> getCircleSelectionRenderer();
}