package net.sf.anathema.character.library.overview;

import java.awt.Component;

import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;

public interface IOverviewCategory {

  public ILabelledAlotmentView addAlotmentView(String labelText, int maxValueLength);

  public IValueView<Integer> addIntegerValueView(String labelText, int maxValueLength);

  public IValueView<String> addStringValueView(String labelText);

  public Component getComponent();
}