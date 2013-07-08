package net.sf.anathema.character.main.library.overview;

import net.sf.anathema.character.main.view.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.character.main.view.labelledvalue.IValueView;

public interface OverviewCategory {

  ILabelledAlotmentView addAlotmentView(String labelText, int maxValueLength);

  IValueView<Integer> addIntegerValueView(String labelText, int maxValueLength);

  IValueView<String> addStringValueView(String labelText);
}