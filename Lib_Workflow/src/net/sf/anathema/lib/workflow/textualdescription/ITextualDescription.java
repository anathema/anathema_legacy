package net.sf.anathema.lib.workflow.textualdescription;

import net.sf.anathema.lib.control.ObjectValueListener;

public interface ITextualDescription {

  public boolean isDirty();

  public void setDirty(boolean isDirty);

  public boolean isEmpty();

  public void setText(String text);

  public void addTextChangedListener(ObjectValueListener<String> listener);

  public void removeTextChangeListener(ObjectValueListener<String> listener);

  public String getText();
}