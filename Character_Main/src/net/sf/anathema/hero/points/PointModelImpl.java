package net.sf.anathema.hero.points;

import net.sf.anathema.character.generic.additionaltemplate.HeroModelBonusPointCalculator;
import net.sf.anathema.character.generic.additionaltemplate.HeroModelExperienceCalculator;
import net.sf.anathema.character.presenter.overview.IValueModel;
import net.sf.anathema.hero.change.ChangeAnnouncer;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.InitializationContext;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class PointModelImpl implements PointsModel {

  private final List<HeroModelExperienceCalculator> experienceCalculators = new ArrayList<>();
  private final List<IValueModel<Integer>> experienceOverviewModels = new ArrayList<>();
  private final List<HeroModelBonusPointCalculator> bonusPointCalculators = new ArrayList<>();

  @Override
  public void initialize(InitializationContext context, Hero hero) {
    // nothing to do
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    // nothing to do
  }

  @Override
  public void addBonusPointCalculator(HeroModelBonusPointCalculator calculator) {
    bonusPointCalculators.add(calculator);
  }

  @Override
  public void addToExperienceOverview(IValueModel<Integer> model) {
    experienceOverviewModels.add(model);
  }

  @Override
  public void addExperienceCalculator(HeroModelExperienceCalculator calculator) {
    experienceCalculators.add(calculator);
  }

  @Override
  public Iterable<HeroModelBonusPointCalculator> getBonusPointCalculators() {
    return bonusPointCalculators;
  }

  @Override
  public Iterable<HeroModelExperienceCalculator> getExperienceCalculators() {
    return experienceCalculators;
  }

  @Override
  public Iterable<IValueModel<Integer>> getExperienceOverviewModels() {
    return experienceOverviewModels;
  }

  @Override
  public Identifier getId() {
    return ID;
  }
}
