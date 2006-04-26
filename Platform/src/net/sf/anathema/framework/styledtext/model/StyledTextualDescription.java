package net.sf.anathema.framework.styledtext.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.framework.styledtext.presentation.TextFormat;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.lang.ArrayUtilities;
import net.sf.anathema.lib.workflow.textualdescription.model.AbstractTextualDescription;

public class StyledTextualDescription extends AbstractTextualDescription implements IStyledTextualDescription {

  private final List<IStyledTextChangeListener> textListeners = new ArrayList<IStyledTextChangeListener>();
  private ITextPart[] textParts = new ITextPart[0];

  public void setText(ITextPart[] textParts) {
    if (ArrayUtilities.equals(this.textParts, textParts)) {
      return;
    }
    this.textParts = textParts;
    fireTextChangedEvent(textParts);
    setDirty(true);
  }

  public ITextPart[] getTextParts() {
    return textParts;
  }

  private synchronized final void fireTextChangedEvent(final ITextPart[] parts) {
    for (IStyledTextChangeListener listener : new ArrayList<IStyledTextChangeListener>(textListeners)) {
      listener.textChanged(parts);
    }
  }

  public synchronized void addTextChangedListener(IStyledTextChangeListener listener) {
    textListeners.add(listener);
  }

  public synchronized void removeTextChangedListener(IStyledTextChangeListener listener) {
    textListeners.remove(listener);
  }

  public boolean isEmpty() {
    return textParts.length == 0;
  }

  public void setText(String text) {
    setText(new ITextPart[] { new net.sf.anathema.framework.styledtext.presentation.TextPart(text, new TextFormat()) });
  }

  public void addTextChangedListener(final IObjectValueChangedListener<String> listener) {
    addTextChangedListener(new IStyledTextChangeListener() {
      public void textChanged(ITextPart[] newParts) {
        listener.valueChanged(getText(newParts));
      }
    });
  }

  private String getText(ITextPart[] parts) {
    StringBuffer buffer = new StringBuffer();
    for (ITextPart part : parts) {
      buffer.append(part.getText());
    }
    return buffer.toString();
  }

  public String getText() {
    return getText(getTextParts());
  }
}