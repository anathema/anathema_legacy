package net.sf.anathema.lib.workflow.textualdescription.view;

import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public interface ILabelTextView {
  public void setText(String text);

  public void addTextChangedListener(final IObjectValueChangedListener<String> listener);
}