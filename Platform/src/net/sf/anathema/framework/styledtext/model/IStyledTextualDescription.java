package net.sf.anathema.framework.styledtext.model;

import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface IStyledTextualDescription extends ITextualDescription {

  void setText(ITextPart[] textParts);

  ITextPart[] getTextParts();

  void addTextChangedListener(IStyledTextChangeListener listener);

  void removeTextChangedListener(IStyledTextChangeListener listener);
}