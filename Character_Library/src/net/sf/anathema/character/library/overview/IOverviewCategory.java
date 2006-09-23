package net.sf.anathema.character.library.overview;

import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;

public interface IOverviewCategory extends IView {

  public ILabelledAlotmentView addAlotmentView(String labelText, int maxValueLength);

  public IValueView<Integer> addIntegerValueView(String labelText, int maxValueLength);

  public IValueView<String> addStringValueView(String labelText);

  public IAdditionalAlotmentView addAdditionalAlotmentView(String labelText, int maxValueLength);
}