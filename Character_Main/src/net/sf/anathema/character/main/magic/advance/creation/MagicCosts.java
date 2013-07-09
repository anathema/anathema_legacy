package net.sf.anathema.character.main.magic.advance.creation;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.character.main.template.experience.ICostAnalyzer;

public interface MagicCosts {

  int getCharmCosts(Charm charm, ICostAnalyzer analyzer);

  int getSpellCosts(ICostAnalyzer costMapping);

  int getMagicCosts(Magic magic, ICostAnalyzer analyzer);
}
