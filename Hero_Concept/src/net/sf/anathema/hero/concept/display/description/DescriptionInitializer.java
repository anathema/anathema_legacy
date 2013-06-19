package net.sf.anathema.hero.concept.display.description;

import net.sf.anathema.character.main.model.concept.HeroConcept;
import net.sf.anathema.character.main.model.concept.HeroConceptFetcher;
import net.sf.anathema.character.main.model.description.HeroDescription;
import net.sf.anathema.character.main.model.description.HeroDescriptionFetcher;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.DescriptionDetails;
import net.sf.anathema.character.presenter.initializers.CharacterModelInitializer;
import net.sf.anathema.character.presenter.initializers.RegisteredInitializer;
import net.sf.anathema.character.view.ICharacterDescriptionView;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.character.main.hero.CharacterModelGroup.Outline;

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
    new DescriptionPresenter(descriptionDetails, resources, descriptionView).initPresentation();
  }

  private DescriptionDetails createDescriptionDetails(ICharacter character) {
    HeroDescription characterDescription = HeroDescriptionFetcher.fetch(character);
    HeroConcept heroConcept = HeroConceptFetcher.fetch(character);
    boolean isExalt = character.getCharacterType().isExaltType();
    return new DescriptionDetails(characterDescription, heroConcept, isExalt);
  }
}