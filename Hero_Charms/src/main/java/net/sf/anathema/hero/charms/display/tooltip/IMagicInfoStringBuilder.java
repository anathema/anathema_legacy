package net.sf.anathema.hero.charms.display.tooltip;

import net.sf.anathema.character.main.magic.model.Magic;

public interface IMagicInfoStringBuilder {

  String createCostString(Magic magic);
}