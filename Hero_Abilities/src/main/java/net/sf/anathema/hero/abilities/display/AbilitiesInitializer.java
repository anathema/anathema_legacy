package net.sf.anathema.hero.abilities.display;

import net.sf.anathema.hero.traits.display.GroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.framework.display.SectionView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.hero.display.presenter.HeroModelInitializer;
import net.sf.anathema.hero.display.presenter.RegisteredInitializer;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.framework.environment.dependencies.Weight;

import static net.sf.anathema.hero.display.HeroModelGroup.NaturalTraits;

@RegisteredInitializer(NaturalTraits)
@Weight(weight = 100)
public class AbilitiesInitializer implements HeroModelInitializer {
  @SuppressWarnings("UnusedParameters")
  public AbilitiesInitializer(IApplicationModel applicationModel) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, Environment environment) {
    String abilityHeader = environment.getString("CardView.AbilityConfiguration.Title");
    GroupedFavorableTraitConfigurationView abilityView = sectionView
            .addView(abilityHeader, GroupedFavorableTraitConfigurationView.class);
    new AbilitiesPresenter(hero, environment, abilityView).initPresentation();
  }
}