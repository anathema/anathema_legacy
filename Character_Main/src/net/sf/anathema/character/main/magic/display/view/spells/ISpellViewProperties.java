package net.sf.anathema.character.main.magic.display.view.spells;

import net.sf.anathema.character.main.magic.display.view.magic.IMagicLearnProperties;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.util.Identifier;

public interface ISpellViewProperties extends IMagicLearnProperties {

  String getCircleLabel();

  AgnosticUIConfiguration<Identifier> getCircleSelectionRenderer();
}