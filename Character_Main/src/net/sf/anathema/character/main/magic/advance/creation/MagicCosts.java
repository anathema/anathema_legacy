package net.sf.anathema.character.main.magic.advance.creation;

import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.character.main.template.experience.ICostAnalyzer;

public interface MagicCosts {

  int getMagicCosts(Magic magic, ICostAnalyzer analyzer);
}
