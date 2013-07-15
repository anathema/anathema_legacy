package net.sf.anathema.framework.view;

import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public class NullTextView implements ITextView {

  @Override
  public void addTextChangedListener(ObjectValueListener<String> listener) {
    //nothing to do;
  }

  @Override
  public void setEnabled(boolean enabled) {
    //nothing to do;
  }

  @Override
  public void setText(String text) {
    //nothing to do;
  }
}
