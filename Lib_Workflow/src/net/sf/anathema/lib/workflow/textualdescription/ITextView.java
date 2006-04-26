package net.sf.anathema.lib.workflow.textualdescription;

import javax.swing.JComponent;

import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.IView;

public interface ITextView extends IView {

  public void addTextChangedListener(IObjectValueChangedListener<String> listener);

  public JComponent getComponent();

  public void setEnabled(boolean enabled);

  public void setText(String text);
}