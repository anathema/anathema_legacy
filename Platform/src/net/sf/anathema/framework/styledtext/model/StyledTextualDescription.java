package net.sf.anathema.framework.styledtext.model;

import net.sf.anathema.framework.styledtext.presentation.TextFormat;
import net.sf.anathema.framework.styledtext.presentation.TextPart;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.workflow.textualdescription.model.AbstractTextualDescription;
import org.jmock.example.announcer.Announcer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StyledTextualDescription extends AbstractTextualDescription implements IStyledTextualDescription {

  private final Announcer<IStyledTextChangeListener> textListeners = Announcer.to(IStyledTextChangeListener.class);
  private ITextPart[] textParts = new ITextPart[0];
  private final Map<ObjectValueListener<String>, IStyledTextChangeListener> listenerMap = new HashMap<>();

  @Override
  public void setText(ITextPart[] textParts) {
    if (Arrays.equals(this.textParts, textParts)) {
      return;
    }
    this.textParts = textParts;
    setDirty(true);
  }

  @Override
  public ITextPart[] getTextParts() {
    return textParts;
  }

  @Override
  protected void fireChangedEvent() {
    textListeners.announce().textChanged(textParts);
  }

  @Override
  public void addTextChangedListener(IStyledTextChangeListener listener) {
    textListeners.addListener(listener);
  }

  @Override
  public void removeTextChangedListener(IStyledTextChangeListener listener) {
    textListeners.removeListener(listener);
  }

  @Override
  public boolean isEmpty() {
    return textParts.length == 0;
  }

  @Override
  public void setText(String text) {
    if (text == null) {
      setText(new ITextPart[0]);
    }
    else {
      setText(new ITextPart[] { new TextPart(text, new TextFormat()) });
    }
  }

  @Override
  public void addTextChangedListener(final ObjectValueListener<String> listener) {
    IStyledTextChangeListener styledListener = new IStyledTextChangeListener() {
      @Override
      public void textChanged(ITextPart[] newParts) {
        listener.valueChanged(getText(newParts));
      }
    };
    addTextChangedListener(styledListener);
    listenerMap.put(listener, styledListener);
  }

  @Override
  public void removeTextChangeListener(ObjectValueListener<String> listener) {
    removeTextChangedListener(listenerMap.get(listener));
  }

  private String getText(ITextPart[] parts) {
    StringBuilder builder = new StringBuilder();
    for (ITextPart part : parts) {
      builder.append(part.getText());
    }
    return builder.toString();
  }

  @Override
  public String getText() {
    return getText(getTextParts());
  }
}