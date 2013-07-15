package net.sf.anathema.character.main.library.overview;

import net.sf.anathema.character.main.view.labelledvalue.IValueView;
import net.sf.anathema.character.main.view.labelledvalue.LabelledAllotmentView;

public interface OverviewCategory {

  LabelledAllotmentView addAlotmentView(String labelText, int maxValueLength);

  IValueView<Integer> addIntegerValueView(String labelText, int maxValueLength);

  IValueView<String> addStringValueView(String labelText);
}