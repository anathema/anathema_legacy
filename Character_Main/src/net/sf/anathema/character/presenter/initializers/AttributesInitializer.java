package net.sf.anathema.character.presenter.initializers;

import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.AttributesPresenter;
import net.sf.anathema.character.view.IGroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.lib.resources.Resources;

public class AttributesInitializer implements CoreModelInitializer {

  @Override
  public void initialize(SectionView sectionView, ICharacter character, Resources resources) {
    String attributeHeader = resources.getString("CardView.AttributeConfiguration.Title");
    IGroupedFavorableTraitConfigurationView attributeView =
            sectionView.addView(attributeHeader, IGroupedFavorableTraitConfigurationView.class, character.getCharacterType());
    new AttributesPresenter(character, resources, attributeView).initPresentation();
  }
}