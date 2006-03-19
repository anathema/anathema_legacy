package net.sf.anathema.lib.workflow.textualdescription;

import javax.swing.JComponent;
import javax.swing.text.JTextComponent;

import net.sf.anathema.lib.control.stringvalue.IStringValueChangedListener;

public interface ITextView {

  public void setText(String text);

  public void addTextChangedListener(IStringValueChangedListener listener);

  public JComponent getComponent();

  public JTextComponent getTextComponent();

  public void setEnabled(boolean enabled);
}