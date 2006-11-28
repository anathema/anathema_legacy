package net.sf.anathema.framework.styledtext.model;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.framework.styledtext.presentation.TextFormat;
import net.sf.anathema.framework.styledtext.presentation.TextPart;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.lang.ArrayUtilities;
import net.sf.anathema.lib.workflow.textualdescription.model.AbstractTextualDescription;

public class StyledTextualDescription extends AbstractTextualDescription implements IStyledTextualDescription {

  private final GenericControl<IStyledTextChangeListener> textListeners = new GenericControl<IStyledTextChangeListener>();
  private ITextPart[] textParts = new ITextPart[0];
  private final Map<IObjectValueChangedListener<String>, IStyledTextChangeListener> listenerMap = new HashMap<IObjectValueChangedListener<String>, IStyledTextChangeListener>();

  public void setText(ITextPart[] textParts) {
    if (ArrayUtilities.equals(this.textParts, textParts)) {
      return;
    }
    this.textParts = textParts;
    setDirty(true);
  }

  public ITextPart[] getTextParts() {
    return textParts;
  }

  @Override
  protected void fireChangedEvent() {
    textListeners.forAllDo(new IClosure<IStyledTextChangeListener>() {
      public void execute(IStyledTextChangeListener input) {
        input.textChanged(textParts);
      }
    });
  }

  public void addTextChangedListener(IStyledTextChangeListener listener) {
    textListeners.addListener(listener);
  }

  public void removeTextChangedListener(IStyledTextChangeListener listener) {
    textListeners.removeListener(listener);
  }

  public boolean isEmpty() {
    return textParts.length == 0;
  }

  public void setText(String text) {
    if (text == null) {
      setText(new ITextPart[0]);
    }
    else {
      setText(new ITextPart[] { new TextPart(text, new TextFormat()) });
    }
  }

  public void addTextChangedListener(final IObjectValueChangedListener<String> listener) {
    IStyledTextChangeListener styledListener = new IStyledTextChangeListener() {
      public void textChanged(ITextPart[] newParts) {
        listener.valueChanged(getText(newParts));
      }
    };
    addTextChangedListener(styledListener);
    listenerMap.put(listener, styledListener);
  }

  public void removeTextChangeListener(IObjectValueChangedListener<String> listener) {
    removeTextChangedListener(listenerMap.get(listener));
  }

  private String getText(ITextPart[] parts) {
    StringBuilder builder = new StringBuilder();
    for (ITextPart part : parts) {
      builder.append(part.getText());
    }
    return builder.toString();
  }

  public String getText() {
    return getText(getTextParts());
  }
}