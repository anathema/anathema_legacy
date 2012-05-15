package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import net.sf.anathema.character.generic.magic.IMagic;

public interface IMagicTooltipStringBuilder {
  String HtmlLineBreak = "<br>"; //$NON-NLS-1$
  String CommaSpace = ", "; //$NON-NLS-1$
  String Space = " "; //$NON-NLS-1$
  String ColonSpace = ": "; //$NON-NLS-1$

  int DEFAULT_TOOLTIP_WIDTH = 80;

  void buildStringForMagic(StringBuilder builder, IMagic magic, Object specialDetails);
}
