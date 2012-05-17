package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import net.sf.anathema.character.generic.magic.IMagic;

public interface IMagicTooltipStringBuilder {

  int DEFAULT_TOOLTIP_WIDTH = 80;

  void buildStringForMagic(StringBuilder builder, IMagic magic, Object specialDetails);
}
