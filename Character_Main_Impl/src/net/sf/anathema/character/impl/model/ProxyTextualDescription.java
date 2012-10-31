package net.sf.anathema.character.impl.model;

import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

import java.util.ArrayList;
import java.util.List;

public class ProxyTextualDescription implements ITextualDescription {

  private final ITextualDescription[] descriptions;
  private final List<ObjectValueListener<String>> listeners = new ArrayList<>();
  private int currentDescriptionIndex = 0;

  public ProxyTextualDescription(ITextualDescription... descriptions) {
    this.descriptions = descriptions;
  }

  public void setCurrentDescription(int index) {
    ITextualDescription old = getCurrent();
    for (ObjectValueListener<String> listener : listeners) {
      old.removeTextChangeListener(listener);
    }
    this.currentDescriptionIndex = index;
    ITextualDescription newDescription = getCurrent();
    for (ObjectValueListener<String> listener : listeners) {
      newDescription.addTextChangedListener(listener);
    }
  }

  @Override
  public void addTextChangedListener(ObjectValueListener<String> listener) {
    listeners.add(listener);
    getCurrent().addTextChangedListener(listener);
  }

  @Override
  public void removeTextChangeListener(ObjectValueListener<String> listener) {
    listeners.remove(listener);
    getCurrent().removeTextChangeListener(listener);
  }

  @Override
  public String getText() {
    return getCurrent().getText();
  }

  private ITextualDescription getCurrent() {
    return descriptions[currentDescriptionIndex];
  }

  @Override
  public boolean isDirty() {
    return getCurrent().isDirty();
  }

  @Override
  public boolean isEmpty() {
    return getCurrent().isEmpty();
  }

  @Override
  public void setDirty(boolean isDirty) {
    getCurrent().setDirty(isDirty);
  }

  @Override
  public void setText(String text) {
    getCurrent().setText(text);
  }
}