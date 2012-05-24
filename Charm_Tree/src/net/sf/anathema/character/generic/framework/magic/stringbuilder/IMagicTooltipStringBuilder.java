package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import net.sf.anathema.character.generic.magic.IMagic;

public interface IMagicTooltipStringBuilder {

  void buildStringForMagic(StringBuilder builder, IMagic magic, Object specialDetails);
}
