package net.sf.anathema.character.main.magic.model.charmtree.builder.stringbuilder;

import net.sf.anathema.character.main.magic.model.magic.cost.ICost;

public interface ICostStringBuilder<T extends ICost> {

  String getCostString(T cost);
}