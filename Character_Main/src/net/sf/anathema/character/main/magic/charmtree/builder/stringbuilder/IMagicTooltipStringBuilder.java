package net.sf.anathema.character.main.magic.charmtree.builder.stringbuilder;

import net.sf.anathema.character.main.magic.IMagic;

public interface IMagicTooltipStringBuilder {

  void buildStringForMagic(StringBuilder builder, IMagic magic, Object specialDetails);
}
