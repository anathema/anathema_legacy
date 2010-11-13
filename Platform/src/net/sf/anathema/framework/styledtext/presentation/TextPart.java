package net.sf.anathema.framework.styledtext.presentation;

import net.sf.anathema.framework.styledtext.model.ITextFormat;
import net.sf.anathema.framework.styledtext.model.ITextPart;

public class TextPart implements ITextPart {

  private final ITextFormat format;
  private final String text;

  public TextPart(String text, ITextFormat format) {
    this.text = text;
    this.format = format;
  }

  public String getText() {
    return text;
  }

  public ITextFormat getFormat() {
    return format;
  }

  @Override
  public String toString() {
    return "[" + text + ", " + format + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  }
}