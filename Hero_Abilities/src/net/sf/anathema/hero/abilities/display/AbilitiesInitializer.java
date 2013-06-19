package net.sf.anathema.hero.abilities.display;

import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.initializers.CharacterModelInitializer;
import net.sf.anathema.character.presenter.initializers.RegisteredInitializer;
import net.sf.anathema.character.view.IGroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.hero.display.HeroModelGroup.NaturalTraits;

@RegisteredInitializer(NaturalTraits)
@Weight(weight = 100)
public class AbilitiesInitializer implements CharacterModelInitializer {
  @SuppressWarnings("UnusedParameters")
  public AbilitiesInitializer(IApplicationModel applicationModel) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, ICharacter character, Resources resources) {
    String abilityHeader = resources.getString("CardView.AbilityConfiguration.Title");
    IGroupedFavorableTraitConfigurationView abilityView = sectionView
            .addView(abilityHeader, IGroupedFavorableTraitConfigurationView.class, character.getTemplate().getTemplateType().getCharacterType());
    new AbilitiesPresenter(character, resources, abilityView).initPresentation();
  }
}