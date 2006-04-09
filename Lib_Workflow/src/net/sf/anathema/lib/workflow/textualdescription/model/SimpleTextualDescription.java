package net.sf.anathema.lib.workflow.textualdescription.model;

import net.disy.commons.core.util.ObjectUtilities;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.lib.control.stringvalue.IStringValueChangedListener;
import net.sf.anathema.lib.control.stringvalue.StringValueControl;
import net.sf.anathema.lib.workflow.textualdescription.ISimpleTextualDescription;

public class SimpleTextualDescription extends AbstractTextualDescription implements ISimpleTextualDescription {

  private final StringValueControl textControl = new StringValueControl();
  private String text;

  public SimpleTextualDescription() {
    // Nothing to do
  }

  public SimpleTextualDescription(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    if (ObjectUtilities.equals(this.text, text)) {
      return;
    }
    this.text = text;
    textControl.fireValueChangedEvent(text);
    setDirty(true);
  }

  public void addTextChangedListener(IStringValueChangedListener listener) {
    textControl.addStringValueChangeListener(listener);
  }

  public boolean isEmpty() {
    return StringUtilities.isNullOrEmpty(getText());
  }
}