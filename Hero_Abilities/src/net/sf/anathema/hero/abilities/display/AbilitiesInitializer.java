package net.sf.anathema.hero.abilities.display;

import net.sf.anathema.character.main.library.trait.view.GroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.main.presenter.initializers.HeroModelInitializer;
import net.sf.anathema.character.main.presenter.initializers.RegisteredInitializer;
import net.sf.anathema.character.model.view.SectionView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.hero.display.HeroModelGroup.NaturalTraits;

@RegisteredInitializer(NaturalTraits)
@Weight(weight = 100)
public class AbilitiesInitializer implements HeroModelInitializer {
  @SuppressWarnings("UnusedParameters")
  public AbilitiesInitializer(IApplicationModel applicationModel) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, Resources resources) {
    String abilityHeader = resources.getString("CardView.AbilityConfiguration.Title");
    GroupedFavorableTraitConfigurationView abilityView = sectionView
            .addView(abilityHeader, GroupedFavorableTraitConfigurationView.class, hero.getTemplate().getTemplateType().getCharacterType());
    new AbilitiesPresenter(hero, resources, abilityView).initPresentation();
  }
}