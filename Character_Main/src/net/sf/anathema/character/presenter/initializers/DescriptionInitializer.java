package net.sf.anathema.character.presenter.initializers;

import net.sf.anathema.character.main.concept.model.CharacterConcept;
import net.sf.anathema.character.main.concept.model.CharacterConceptFetcher;
import net.sf.anathema.character.main.description.model.CharacterDescription;
import net.sf.anathema.character.main.description.model.CharacterDescriptionFetcher;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.CharacterDescriptionPresenter;
import net.sf.anathema.character.presenter.DescriptionDetails;
import net.sf.anathema.character.view.ICharacterDescriptionView;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.character.main.model.CharacterModelGroup.Outline;

@RegisteredInitializer(Outline)
@Weight(weight = 0)
public class DescriptionInitializer implements CharacterModelInitializer {

  @SuppressWarnings("UnusedParameters")
  public DescriptionInitializer(IApplicationModel applicationModel) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, ICharacter character, Resources resources) {
    String descriptionHeader = resources.getString("CardView.CharacterDescription.Title");
    ICharacterDescriptionView descriptionView = sectionView.addView(descriptionHeader, ICharacterDescriptionView.class, character.getCharacterType());
    DescriptionDetails descriptionDetails = createDescriptionDetails(character);
    new CharacterDescriptionPresenter(descriptionDetails, resources, descriptionView).initPresentation();
  }

  private DescriptionDetails createDescriptionDetails(ICharacter character) {
    CharacterDescription characterDescription = CharacterDescriptionFetcher.fetch(character);
    CharacterConcept characterConcept = CharacterConceptFetcher.fetch(character);
    boolean isExalt = character.getCharacterType().isExaltType();
    return new DescriptionDetails(characterDescription, characterConcept, isExalt);
  }
}