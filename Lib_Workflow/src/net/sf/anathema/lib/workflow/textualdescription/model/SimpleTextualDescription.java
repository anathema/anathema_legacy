package net.sf.anathema.lib.workflow.textualdescription.model;

import net.disy.commons.core.util.ObjectUtilities;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import org.jmock.example.announcer.Announcer;

public class SimpleTextualDescription extends AbstractTextualDescription implements ITextualDescription {

  private final Announcer<IObjectValueChangedListener> control = Announcer.to(IObjectValueChangedListener.class);
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
    control.announce().valueChanged(text);
  }

  @Override
  public void addTextChangedListener(IObjectValueChangedListener<String> listener) {
    control.addListener(listener);
  }

  @Override
  public void removeTextChangeListener(IObjectValueChangedListener<String> listener) {
    control.removeListener(listener);
  }

  @Override
  public boolean isEmpty() {
    return StringUtilities.isNullOrEmpty(getText());
  }
}