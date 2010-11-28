package net.sf.anathema.character.presenter.overview;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.GlobalCharacterChangeAdapter;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.advance.IExperiencePointConfigurationListener;
import net.sf.anathema.character.model.advance.IExperiencePointEntry;
import net.sf.anathema.character.model.advance.IExperiencePointManagement;
import net.sf.anathema.character.view.overview.IOverviewView;
import net.sf.anathema.lib.control.legality.LegalityColorProvider;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;

public class ExperiencedOverviewPresenter implements IPresenter {

  private final IExperiencePointManagement management;
  private final IOverviewView view;
  private final ICharacterStatistics statistics;
  private final IResources resources;
  private final List<IOverviewSubPresenter> presenters = new ArrayList<IOverviewSubPresenter>();

  private ILabelledAlotmentView totalView;

  public ExperiencedOverviewPresenter(
      IResources resources,
      final ICharacterStatistics statistics,
      IOverviewView experiencePointView,
      IExperiencePointManagement management) {
    this.resources = resources;
    this.statistics = statistics;
    statistics.getCharacterContext().getCharacterListening().addChangeListener(new GlobalCharacterChangeAdapter() {
      @Override
      public void characterChanged() {
        if (statistics.isExperienced()) {
          calculateXPCost();
        }
      }
    });
    this.management = management;
    this.view = experiencePointView;
  }

  public void initPresentation() {
    IOverviewCategory category = view.addOverviewCategory(getString("Overview.Experience.Title")); //$NON-NLS-1$
    for (IValueModel<Integer> model : management.getAllModels()) {
      IValueView<Integer> valueView = category.addIntegerValueView(getString("Overview.Experience." + model.getId()), 2); //$NON-NLS-1$
      presenters.add(new ValueSubPresenter(model, valueView));
    }
    initTotal(category);
    calculateXPCost();
  }

  private void initTotal(IOverviewCategory category) {
    totalView = category.addAlotmentView(getString("Overview.Experience.Total"), 4); //$NON-NLS-1$
    statistics.getExperiencePoints().addExperiencePointConfigurationListener(
        new IExperiencePointConfigurationListener() {
          public void entryAdded(IExperiencePointEntry entry) {
            calculateXPCost();
          }

          public void entryRemoved(IExperiencePointEntry entry) {
            calculateXPCost();
          }

          public void entryChanged(IExperiencePointEntry entry) {
            calculateXPCost();
          }
        });
  }

  private void calculateXPCost() {
    for (IOverviewSubPresenter presenter : presenters) {
      presenter.update();
    }
    totalView.setAlotment(getTotalXP());
    setTotalViewColor();
    totalView.setValue(management.getTotalCosts());
    setTotalViewColor();
  }

  private void setTotalViewColor() {
    boolean overspent = management.getTotalCosts() > getTotalXP();
    totalView.setTextColor(overspent ? LegalityColorProvider.COLOR_HIGH : LegalityColorProvider.COLOR_OKAY);
  }

  private int getTotalXP() {
    return statistics.getExperiencePoints().getTotalExperiencePoints() + management.getMiscGain();
  }

  private String getString(String string) {
    return resources.getString(string);
  }
}