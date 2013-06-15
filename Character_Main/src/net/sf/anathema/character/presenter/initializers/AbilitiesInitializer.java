package net.sf.anathema.character.presenter.initializers;

import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.AbilitiesPresenter;
import net.sf.anathema.character.view.IGroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.lib.resources.Resources;

public class AbilitiesInitializer implements CoreModelInitializer {
  @Override
  public void initialize(SectionView sectionView, ICharacter character, Resources resources) {
    String abilityHeader = resources.getString("CardView.AbilityConfiguration.Title");
    IGroupedFavorableTraitConfigurationView abilityView =
            sectionView.addView(abilityHeader, IGroupedFavorableTraitConfigurationView.class, character.getCharacterType());
    new AbilitiesPresenter(character, resources, abilityView).initPresentation();
  }
}