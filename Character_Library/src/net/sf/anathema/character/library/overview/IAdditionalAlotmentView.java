package net.sf.anathema.character.library.overview;

import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;

public interface IAdditionalAlotmentView extends ILabelledAlotmentView {

  public void setAlotment(int alotment, int additionalAlotment);

  public void setValue(int value, int additionalValue);

}