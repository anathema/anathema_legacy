package net.sf.anathema.character.main.charmtree.builder.stringbuilder;

import net.sf.anathema.character.main.magic.general.ICost;

public interface ICostStringBuilder<T extends ICost> {

  String getCostString(T cost);
}