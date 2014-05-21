package net.sf.anathema.hero.charms.display.tooltip;

import net.sf.anathema.character.magic.basic.Magic;

public interface IMagicSourceStringBuilder<T extends Magic> {

  String createSourceString(T magic);

  String createShortSourceString(T charm);
}