package net.sf.anathema.character.library.overview;

import net.sf.anathema.lib.workflow.labelledvalue.IStyleableView;

public interface IAdditionalAlotmentView extends IStyleableView {

  void setAlotment(int alotment, int additionalAlotment);

  void setValue(int value, int additionalValue);
}