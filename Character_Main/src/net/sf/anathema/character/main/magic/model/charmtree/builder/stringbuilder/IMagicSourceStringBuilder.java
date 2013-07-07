package net.sf.anathema.character.main.magic.model.charmtree.builder.stringbuilder;

import net.sf.anathema.character.main.magic.model.magic.IMagic;

public interface IMagicSourceStringBuilder<T extends IMagic> {

  String createSourceString(T magic);

  String createShortSourceString(T charm);
}