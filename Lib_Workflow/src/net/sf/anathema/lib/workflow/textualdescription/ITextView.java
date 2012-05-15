package net.sf.anathema.lib.workflow.textualdescription;

import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.IView;

public interface ITextView extends IView {

  void addTextChangedListener(ObjectValueListener<String> listener);

  void setEnabled(boolean enabled);

  void setText(String text);
}