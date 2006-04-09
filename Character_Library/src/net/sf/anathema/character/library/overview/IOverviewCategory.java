package net.sf.anathema.character.library.overview;

import java.awt.Component;

import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledValueView;

public interface IOverviewCategory {

  public ILabelledAlotmentView addAlotmentView(String labelText, int maxValueLength);

  public ILabelledValueView<Integer> addValueView(String labelText, int maxValueLength);

  public Component getComponent();
}