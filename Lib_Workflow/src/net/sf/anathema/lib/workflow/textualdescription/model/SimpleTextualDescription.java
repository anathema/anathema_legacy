package net.sf.anathema.lib.workflow.textualdescription.model;

import net.disy.commons.core.util.ObjectUtilities;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.control.objectvalue.ObjectValueControl;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public class SimpleTextualDescription extends AbstractTextualDescription implements ITextualDescription {

  private final ObjectValueControl<String> textControl = new ObjectValueControl<String>();
  private String text;

  public SimpleTextualDescription() {
    // Nothing to do
  }

  public SimpleTextualDescription(String text) {
    this.text = text;
  }

  @Override
  public String getText() {
    return text;
  }

  @Override
  public void setText(String text) {
    if (ObjectUtilities.equals(this.text, text)) {
      return;
    }
    this.text = text;
    setDirty(true);
  }

  @Override
  protected void fireChangedEvent() {
    textControl.fireValueChangedEvent(text);
  }

  @Override
  public void addTextChangedListener(IObjectValueChangedListener<String> listener) {
    textControl.addObjectValueChangeListener(listener);
  }

  @Override
  public void removeTextChangeListener(IObjectValueChangedListener<String> listener) {
    textControl.removeObjectValueChangeListener(listener);
  }

  @Override
  public boolean isEmpty() {
    return StringUtilities.isNullOrEmpty(getText());
  }
}