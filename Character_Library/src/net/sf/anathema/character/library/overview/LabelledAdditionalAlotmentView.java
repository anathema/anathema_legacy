package net.sf.anathema.character.library.overview;

import net.sf.anathema.lib.workflow.labelledvalue.view.LabelledAlotmentView;

public class LabelledAdditionalAlotmentView extends LabelledAlotmentView implements IAdditionalAlotmentView {

  public LabelledAdditionalAlotmentView(String labelText, int maxValueLength) {
    super(labelText, 0, 0, maxValueLength);
  }

  public void setAlotment(int alotment, int additionalAlotment) {
    maxPointLabel.setText(String.valueOf(alotment) + "+" + String.valueOf(additionalAlotment)); //$NON-NLS-1$
  }

  public void setValue(int value, int additionalValue) {
    valueLabel.setText(String.valueOf(value) + "+" + String.valueOf(additionalValue)); //$NON-NLS-1$
  }
}