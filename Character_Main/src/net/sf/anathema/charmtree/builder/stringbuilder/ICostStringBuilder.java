package net.sf.anathema.charmtree.builder.stringbuilder;

import net.sf.anathema.character.generic.magic.general.ICost;

public interface ICostStringBuilder<T extends ICost> {

  String getCostString(T cost);
}