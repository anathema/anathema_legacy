package net.sf.anathema.lib.workflow.textualdescription.view;

import net.sf.anathema.lib.control.stringvalue.IStringValueChangedListener;

public interface ILabelTextView {
  public void setText(String text);

  public void addTextChangedListener(final IStringValueChangedListener listener);
}