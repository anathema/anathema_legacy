package net.sf.anathema.character.library.overview;

import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;

public interface IOverviewCategory {

  ILabelledAlotmentView addAlotmentView(String labelText, int maxValueLength);

  IValueView<Integer> addIntegerValueView(String labelText, int maxValueLength);

  IValueView<String> addStringValueView(String labelText);

  IAdditionalAlotmentView addAdditionalAlotmentView(String labelText, int maxValueLength);
}