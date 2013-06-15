package net.sf.anathema.charmtree.builder.stringbuilder;

import net.sf.anathema.character.generic.magic.IMagic;

public interface IMagicSourceStringBuilder<T extends IMagic> {

  String createSourceString(T magic);

  String createShortSourceString(T charm);
}