package net.sf.anathema.framework.styledtext.model;

import net.sf.anathema.lib.workflow.textualdescription.model.ITextualDescription;

public interface IStyledTextualDescription extends ITextualDescription {

  public void setText(ITextPart[] textParts);

  public ITextPart[] getText();

  public void addTextChangedListener(IStyledTextChangeListener listener);

  public void removeTextChangedListener(IStyledTextChangeListener listener);

  public boolean isEmpty();
}