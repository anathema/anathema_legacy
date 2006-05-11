package net.sf.anathema.charmentry.view;

import javax.swing.JComponent;
import javax.swing.JLabel;

import net.sf.anathema.charmentry.presenter.view.ICostEntryView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

public class CostEntryView implements ICostEntryView {

  private final String valueString;
  private final String additionalTextString;
  private final LineTextView valueField = new LineTextView(10);
  private final LineTextView textField = new LineTextView(30);

  public CostEntryView(String valueString, String additionalTextString) {
    this.valueString = valueString;
    this.additionalTextString = additionalTextString;
  }

  public ITextView getValueView() {
    return valueField;
  }

  public ITextView getTextView() {
    return textField;
  }

  public void addTo(JComponent component) {    
    component.add(new JLabel(valueString));
    component.add(valueField.getComponent());
    component.add(new JLabel(additionalTextString));
    component.add(textField.getComponent());
  }
}