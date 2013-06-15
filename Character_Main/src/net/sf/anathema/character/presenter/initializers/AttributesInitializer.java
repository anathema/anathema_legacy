package net.sf.anathema.character.presenter.initializers;

import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.AttributesPresenter;
import net.sf.anathema.character.view.IGroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.character.model.CharacterModelGroup.NaturalTraits;

@RegisteredInitializer(NaturalTraits)
@Weight(weight = 0)
public class AttributesInitializer implements CharacterModelInitializer {

  @SuppressWarnings("UnusedParameters")
  public AttributesInitializer(IApplicationModel applicationModel) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, ICharacter character, Resources resources) {
    String attributeHeader = resources.getString("CardView.AttributeConfiguration.Title");
    IGroupedFavorableTraitConfigurationView attributeView =
            sectionView.addView(attributeHeader, IGroupedFavorableTraitConfigurationView.class, character.getCharacterType());
    new AttributesPresenter(character, resources, attributeView).initPresentation();
  }
}