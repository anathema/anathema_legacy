package net.sf.anathema.character.presenter.magic.charm;

import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.control.IIntValueChangedListener;
import org.jmock.example.announcer.Announcer;

public class ProxyIntValueView implements IIntValueView {
  private final String labelText;
  private int maxValue;
  private final Announcer<IIntValueChangedListener> valueListeners = new Announcer<>(
          IIntValueChangedListener.class);
  private int value;
  private IIntValueView actualView;

  public ProxyIntValueView(String labelText, int maxValue, int value) {
    this.labelText = labelText;
    this.maxValue = maxValue;
    this.value = value;
  }

  public void setActualView(IIntValueView actualView) {
    this.actualView = actualView;
    actualView.addIntValueChangedListener(new IIntValueChangedListener() {
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
  public void addIntValueChangedListener(IIntValueChangedListener listener) {
    if (actualView != null) {
      actualView.addIntValueChangedListener(listener);
      return;
    }
    valueListeners.addListener(listener);
  }

  @Override
  public void removeIntValueChangedListener(IIntValueChangedListener listener) {
    if (actualView != null) {
      actualView.removeIntValueChangedListener(listener);
      return;
    }
    valueListeners.removeListener(listener);
  }

  @Override
  public void setMaximum(int maximalValue) {
    if (actualView != null) {
      actualView.setMaximum(maximalValue);
      return;
    }
    this.maxValue = maximalValue;
    if (value > maxValue) {
      setValue(maximalValue);
    }
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
