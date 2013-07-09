package net.sf.anathema.character.main.magic.model.charmtree.builder.stringbuilder;

import net.sf.anathema.character.main.magic.model.magic.Magic;

public interface IMagicTooltipStringBuilder {

  void buildStringForMagic(StringBuilder builder, Magic magic, Object specialDetails);
}
