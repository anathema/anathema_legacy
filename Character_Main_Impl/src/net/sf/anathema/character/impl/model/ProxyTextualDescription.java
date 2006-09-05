package net.sf.anathema.character.impl.model;

import java.util.ArrayList;
import java.util.List;

import com.sun.crypto.provider.DESCipher;

import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public class ProxyTextualDescription implements ITextualDescription {

  private final ITextualDescription[] descriptions;
  private final List<IObjectValueChangedListener<String>> listeners = new ArrayList<IObjectValueChangedListener<String>>();
  private int currentDescriptionIndex = 0;

  public ProxyTextualDescription(ITextualDescription... descriptions) {
    this.descriptions = descriptions;
  }

  public void setCurrentDescription(int index) {
    ITextualDescription old = getCurrent();
    for (IObjectValueChangedListener<String> listener : listeners) {
      old.removeTextChangeListener(listener);
    }
    this.currentDescriptionIndex = index;
    ITextualDescription newDescription = getCurrent();
    for (IObjectValueChangedListener<String> listener : listeners) {
      newDescription.addTextChangedListener(listener);
    }
  }

  public void addTextChangedListener(IObjectValueChangedListener<String> listener) {
    listeners.add(listener);
    getCurrent().addTextChangedListener(listener);
  }

  public void removeTextChangeListener(IObjectValueChangedListener<String> listener) {
    listeners.remove(listener);
    getCurrent().removeTextChangeListener(listener);
  }

  public String getText() {
    return getCurrent().getText();
  }

  private ITextualDescription getCurrent() {
    return descriptions[currentDescriptionIndex];
  }

  public boolean isDirty() {
    return getCurrent().isDirty();
  }

  public boolean isEmpty() {
    return getCurrent().isEmpty();
  }

  public void setDirty(boolean isDirty) {
    getCurrent().setDirty(isDirty);
  }

  public void setText(String text) {
    getCurrent().setText(text);
  }
}