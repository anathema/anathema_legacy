package net.sf.anathema.character.main.presenter.overview;

import net.sf.anathema.character.main.library.overview.IOverviewCategory;
import net.sf.anathema.character.main.model.experience.ExperienceModelFetcher;
import net.sf.anathema.character.main.advance.ExperiencePointConfigurationListener;
import net.sf.anathema.character.main.advance.IExperiencePointEntry;
import net.sf.anathema.character.main.advance.IExperiencePointManagement;
import net.sf.anathema.character.model.view.overview.CategorizedOverview;
import net.sf.anathema.hero.change.ChangeFlavor;
import net.sf.anathema.hero.change.FlavoredChangeListener;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.points.overview.IValueModel;
import net.sf.anathema.lib.control.legality.LegalityColorProvider;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;

import java.util.ArrayList;
import java.util.List;

public class ExperiencedOverviewPresenter implements Presenter {

  private final IExperiencePointManagement management;
  private final CategorizedOverview view;
  private final Hero hero;
  private final Resources resources;
  private final List<IOverviewSubPresenter> presenters = new ArrayList<>();

  private ILabelledAlotmentView totalView;

  public ExperiencedOverviewPresenter(Resources resources, final Hero hero, CategorizedOverview overview,
                                      IExperiencePointManagement experiencePoints) {
    this.resources = resources;
    this.hero = hero;
    hero.getChangeAnnouncer().addListener(new FlavoredChangeListener() {
      @Override
      public void changeOccurred(ChangeFlavor flavor) {
        if (ExperienceModelFetcher.fetch(hero).isExperienced()) {
          calculateXPCost();
        }
      }
    });
    this.management = experiencePoints;
    this.view = overview;
  }

  @Override
  public void initPresentation() {
    IOverviewCategory category = view.addOverviewCategory(getString("Overview.Experience.Title"));
    for (IValueModel<Integer> model : management.getAllModels()) {
      IValueView<Integer> valueView = category.addIntegerValueView(getString("Overview.Experience." + model.getId()), 2);
      presenters.add(new ValueSubPresenter(model, valueView));
    }
    initTotal(category);
    calculateXPCost();
  }

  private void initTotal(IOverviewCategory category) {
    totalView = category.addAlotmentView(getString("Overview.Experience.Total"), 4);
    ExperienceModelFetcher.fetch(hero).getExperiencePoints().addExperiencePointConfigurationListener(new ExperiencePointConfigurationListener() {
      @Override
      public void entryAdded(IExperiencePointEntry entry) {
        calculateXPCost();
      }

      @Override
      public void entryRemoved(IExperiencePointEntry entry) {
        calculateXPCost();
      }

      @Override
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
    return ExperienceModelFetcher.fetch(hero).getExperiencePoints().getTotalExperiencePoints() + management.getMiscGain();
  }

  private String getString(String string) {
    return resources.getString(string);
  }
}
