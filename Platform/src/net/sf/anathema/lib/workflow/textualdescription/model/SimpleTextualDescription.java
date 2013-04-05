package net.sf.anathema.lib.workflow.textualdescription.model;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import org.jmock.example.announcer.Announcer;

public class SimpleTextualDescription extends AbstractTextualDescription implements ITextualDescription {

  private final Announcer<ObjectValueListener> control = Announcer.to(ObjectValueListener.class);
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
    if (Objects.equal(this.text, text)) {
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
  public void addTextChangedListener(ObjectValueListener<String> listener) {
    control.addListener(listener);
  }

  @Override
  public void removeTextChangeListener(ObjectValueListener<String> listener) {
    control.removeListener(listener);
  }

  @Override
  public boolean isEmpty() {
    return Strings.isNullOrEmpty(getText());
  }
}