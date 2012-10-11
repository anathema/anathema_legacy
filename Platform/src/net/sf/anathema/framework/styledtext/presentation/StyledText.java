package net.sf.anathema.framework.styledtext.presentation;

import net.sf.anathema.framework.styledtext.model.IStyledTextChangeListener;
import net.sf.anathema.framework.styledtext.model.ITextPart;

public interface StyledText {

  void setText(ITextPart[] textParts);

  void addStyledTextListener(IStyledTextChangeListener listener);
}