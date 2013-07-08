package net.sf.anathema.hero.magic.display.special;

import net.sf.anathema.framework.value.IntValueView;
import net.sf.anathema.lib.control.IntValueChangedListener;
import org.jmock.example.announcer.Announcer;

public class ProxyIntValueView implements IntValueView {
  private final String labelText;
  private int maxValue;
  private final Announcer<IntValueChangedListener> valueListeners = new Announcer<>(IntValueChangedListener.class);
  private int value;
  private IntValueView actualView;

  public ProxyIntValueView(String labelText, int maxValue, int value) {
    this.labelText = labelText;
    this.maxValue = maxValue;
    this.value = value;
  }

  public void setActualView(IntValueView actualView) {
    this.actualView = actualView;
    actualView.addIntValueChangedListener(new IntValueChangedListener() {
      @Override
      public void valueChanged(int newValue) {
        valueListeners.announce().valueChanged(newValue);
      }
    });
  }

  @Override
  public void setValue(int newValue) {
    if (actualView != null) {
      actualView.setValue(newValue);
      return;
    }
    if (newValue > maxValue) {
      return;
    }
    this.value = newValue;
    valueListeners.announce().valueChanged(value);
  }

  @Override
  public void addIntValueChangedListener(IntValueChangedListener listener) {
    if (actualView != null) {
      actualView.addIntValueChangedListener(listener);
      return;
    }
    valueListeners.addListener(listener);
  }

  @Override
  public void removeIntValueChangedListener(IntValueChangedListener listener) {
    if (actualView != null) {
      actualView.removeIntValueChangedListener(listener);
      return;
    }
    valueListeners.removeListener(listener);
  }

  public int getMaxValue() {
    return maxValue;
  }

  public int getValue() {
    return value;
  }

  public String getLabel() {
    return labelText;
  }
}
