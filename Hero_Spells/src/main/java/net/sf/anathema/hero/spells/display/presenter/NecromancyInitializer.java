package net.sf.anathema.hero.spells.display.presenter;

import net.sf.anathema.character.framework.display.SectionView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.environment.dependencies.Weight;
import net.sf.anathema.hero.display.presenter.HeroModelInitializer;
import net.sf.anathema.hero.display.presenter.RegisteredInitializer;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.spells.model.CircleModel;
import net.sf.anathema.hero.spells.model.SpellsModel;
import net.sf.anathema.hero.spells.model.SpellsModelFetcher;

import static net.sf.anathema.hero.display.HeroModelGroup.Magic;

@RegisteredInitializer(Magic)
@Weight(weight = 300)
public class NecromancyInitializer implements HeroModelInitializer {

  private IApplicationModel applicationModel;

  public NecromancyInitializer(IApplicationModel applicationModel) {
    this.applicationModel = applicationModel;
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, Environment environment) {
    SpellsModel spellsModel = SpellsModelFetcher.fetch(hero);
    if (spellsModel == null) {
      return;
    }
    boolean canLeanNecromancy = spellsModel.canLearnNecromancy();
    if (canLeanNecromancy) {
      String titleKey = "CardView.CharmConfiguration.Necromancy.Title";
      CircleModel circleModel = new CircleModel(spellsModel.getNecromancyCircles());
      new SpellInitializer(applicationModel, titleKey, circleModel).initialize(sectionView, hero, environment);
    }
  }
}
