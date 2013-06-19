package net.sf.anathema.character.presenter.overview;

import net.sf.anathema.character.change.ChangeFlavor;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.main.hero.change.FlavoredChangeListener;
import net.sf.anathema.character.main.model.experience.ExperienceModelFetcher;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.advance.IExperiencePointConfigurationListener;
import net.sf.anathema.character.model.advance.IExperiencePointEntry;
import net.sf.anathema.character.model.advance.IExperiencePointManagement;
import net.sf.anathema.character.view.overview.CategorizedOverview;
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
  private final ICharacter character;
  private final Resources resources;
  private final List<IOverviewSubPresenter> presenters = new ArrayList<>();

  private ILabelledAlotmentView totalView;

  public ExperiencedOverviewPresenter(Resources resources, final ICharacter character, CategorizedOverview overview,
                                      IExperiencePointManagement experiencePoints) {
    this.resources = resources;
    this.character = character;
    character.getChangeAnnouncer().addListener(new FlavoredChangeListener() {
      @Override
      public void changeOccurred(ChangeFlavor flavor) {
        if (ExperienceModelFetcher.fetch(character).isExperienced()) {
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
    ExperienceModelFetcher.fetch(character).getExperiencePoints()
                          .addExperiencePointConfigurationListener(new IExperiencePointConfigurationListener() {
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
    return ExperienceModelFetcher.fetch(character).getExperiencePoints().getTotalExperiencePoints() + management.getMiscGain();
  }

  private String getString(String string) {
    return resources.getString(string);
  }
}
