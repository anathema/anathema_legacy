package net.sf.anathema.lib.workflow.textualdescription;

import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public interface ITextualDescription {

  public boolean isDirty();

  public void setDirty(boolean isDirty);

  public boolean isEmpty();

  public void setText(String text);

  public void addTextChangedListener(IObjectValueChangedListener<String> listener);

  public void removeTextChangeListener(IObjectValueChangedListener<String> listener);

  public String getText();
}