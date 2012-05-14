package net.sf.anathema.lib.workflow.textualdescription;

import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.IView;

public interface ITextView extends IView {

  public void addTextChangedListener(ObjectValueListener<String> listener);

  public void setEnabled(boolean enabled);

  public void setText(String text);
}