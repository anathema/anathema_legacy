package net.sf.anathema.lib.workflow.textualdescription;

import net.sf.anathema.lib.control.ObjectValueListener;

public interface ITextualDescription {

  boolean isDirty();

  void setDirty(boolean isDirty);

  boolean isEmpty();

  void setText(String text);

  void addTextChangedListener(ObjectValueListener<String> listener);

  @SuppressWarnings("UnusedDeclaration")
  void removeTextChangeListener(ObjectValueListener<String> listener);

  String getText();
}