package net.sf.anathema.character.intimacies.view;

import java.awt.Component;

import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledValueView;

public interface IOverviewView {

  public ILabelledAlotmentView addAlotmentView(String labelText, int maxValueLength);

  public ILabelledValueView<Integer> addValueView(String labelText, int maxValueLength);

  public Component getComponent();
}