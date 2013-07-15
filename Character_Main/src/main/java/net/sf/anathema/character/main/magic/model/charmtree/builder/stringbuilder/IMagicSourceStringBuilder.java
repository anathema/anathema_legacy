package net.sf.anathema.character.main.magic.model.charmtree.builder.stringbuilder;

import net.sf.anathema.character.main.magic.model.magic.Magic;

public interface IMagicSourceStringBuilder<T extends Magic> {

  String createSourceString(T magic);

  String createShortSourceString(T charm);
}