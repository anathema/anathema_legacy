package net.sf.anathema.character.view.overview;

import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledValueView;

public interface IExperienceOverviewView extends IOverviewView {

  public ILabelledAlotmentView addTotalExperienceOverviewCategory(String labelText);

  public ILabelledValueView<Integer> addExperiencePointCategory(String labelText);
}