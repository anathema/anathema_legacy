package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import net.sf.anathema.character.generic.magic.IMagic;

public interface IMagicTooltipStringBuilder {
  public static final String HtmlLineBreak = "<br>"; //$NON-NLS-1$
  public static final String CommaSpace = ", "; //$NON-NLS-1$
  public static final String Space = " "; //$NON-NLS-1$
  public static final String ColonSpace = ": "; //$NON-NLS-1$

  public static final int DEFAULT_TOOLTIP_WIDTH = 80;

  public void buildStringForMagic(StringBuilder builder, IMagic magic, Object specialDetails);
}
