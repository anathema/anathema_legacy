package net.sf.anathema.character.main.magic.model.charmtree.builder.stringbuilder;

import net.sf.anathema.character.main.magic.model.magic.IMagic;

public interface IMagicTooltipStringBuilder {

  void buildStringForMagic(StringBuilder builder, IMagic magic, Object specialDetails);
}
