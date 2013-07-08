package net.sf.anathema.hero.concept.display.description.presenter;

import net.sf.anathema.character.main.presenter.DescriptionDetails;
import net.sf.anathema.character.main.presenter.initializers.HeroModelInitializer;
import net.sf.anathema.character.main.presenter.initializers.RegisteredInitializer;
import net.sf.anathema.character.main.view.SectionView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.hero.concept.HeroConcept;
import net.sf.anathema.hero.concept.HeroConceptFetcher;
import net.sf.anathema.hero.configurableview.ConfigurableCharacterView;
import net.sf.anathema.hero.description.HeroDescription;
import net.sf.anathema.hero.description.HeroDescriptionFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.hero.display.HeroModelGroup.Outline;

@RegisteredInitializer(Outline)
@Weight(weight = 0)
public class DescriptionInitializer implements HeroModelInitializer {

  @SuppressWarnings("UnusedParameters")
  public DescriptionInitializer(IApplicationModel applicationModel) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, Resources resources) {
    String descriptionHeader = resources.getString("CardView.CharacterDescription.Title");
    ConfigurableCharacterView descriptionView =
            sectionView.addView(descriptionHeader, ConfigurableCharacterView.class, hero.getTemplate().getTemplateType().getCharacterType());
    DescriptionDetails descriptionDetails = createDescriptionDetails(hero);
    new DescriptionPresenter(descriptionDetails, resources, descriptionView).initPresentation();
  }

  private DescriptionDetails createDescriptionDetails(Hero hero
  ) {
    HeroDescription characterDescription = HeroDescriptionFetcher.fetch(hero);
    HeroConcept heroConcept = HeroConceptFetcher.fetch(hero);
    boolean isExalt = hero.getTemplate().getTemplateType().getCharacterType().isExaltType();
    return new DescriptionDetails(characterDescription, heroConcept, isExalt);
  }
}