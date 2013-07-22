package net.sf.anathema.hero.charms.display.tooltip;

import net.sf.anathema.character.main.magic.basic.Magic;

public interface IMagicInfoStringBuilder {

  String createCostString(Magic magic);
}