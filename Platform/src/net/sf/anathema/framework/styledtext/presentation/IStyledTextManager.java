package net.sf.anathema.framework.styledtext.presentation;

import net.sf.anathema.framework.styledtext.model.IStyledTextChangeListener;
import net.sf.anathema.framework.styledtext.model.ITextPart;

public interface IStyledTextManager {

  public void setText(ITextPart[] textParts);

  public ITextPart[] getTextParts();

  public void addStyledTextListener(IStyledTextChangeListener listener);
}