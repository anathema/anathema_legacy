package net.sf.anathema.character.impl.view.overview;

import net.sf.anathema.character.view.overview.IExperienceOverviewView;
import net.sf.anathema.character.view.overview.IOverviewViewProperties;
import net.sf.anathema.lib.gui.gridlayout.DefaultGridDialogPanel;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledValueView;

public class ExperienceOverviewView extends AbstractOverviewView implements IExperienceOverviewView {
  private final IGridDialogPanel experiencePanel = new DefaultGridDialogPanel(false);

  public ILabelledAlotmentView addTotalExperienceOverviewCategory(String labelText) {
    return addCategoryView(labelText, 0, 0, experiencePanel, 4);
  }

  public ILabelledValueView<Integer> addExperiencePointCategory(String labelText) {
    return addDerivedView(labelText, 0, experiencePanel, 3);
  }

  public void initGui(IOverviewViewProperties properties) {
    addOverviewPanel(properties.getExperienceTitle(), experiencePanel);
  }
}