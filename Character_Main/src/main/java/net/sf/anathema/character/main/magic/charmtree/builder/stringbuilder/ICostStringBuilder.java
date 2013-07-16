package net.sf.anathema.character.main.magic.charmtree.builder.stringbuilder;

import net.sf.anathema.character.main.magic.model.cost.Cost;

public interface ICostStringBuilder<T extends Cost> {

  String getCostString(T cost);
}