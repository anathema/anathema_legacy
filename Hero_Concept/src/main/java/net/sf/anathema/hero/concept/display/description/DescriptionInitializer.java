package net.sf.anathema.hero.concept.display.description;

import net.sf.anathema.character.framework.display.SectionView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.hero.concept.HeroConcept;
import net.sf.anathema.hero.concept.HeroConceptFetcher;
import net.sf.anathema.hero.description.HeroDescription;
import net.sf.anathema.hero.description.HeroDescriptionFetcher;
import net.sf.anathema.hero.display.configurableview.ConfigurableCharacterView;
import net.sf.anathema.hero.display.presenter.HeroModelInitializer;
import net.sf.anathema.hero.display.presenter.RegisteredInitializer;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.framework.environment.dependencies.Weight;

import static net.sf.anathema.hero.display.HeroModelGroup.Outline;

@RegisteredInitializer(Outline)
@Weight(weight = 0)
public class DescriptionInitializer implements HeroModelInitializer {

  @SuppressWarnings("UnusedParameters")
  public DescriptionInitializer(IApplicationModel applicationModel) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, Environment environment) {
    String descriptionHeader = environment.getString("CardView.CharacterDescription.Title");
    ConfigurableCharacterView descriptionView =
            sectionView.addView(descriptionHeader, ConfigurableCharacterView.class);
    DescriptionDetails descriptionDetails = createDescriptionDetails(hero);
    new DescriptionPresenter(descriptionDetails, environment, descriptionView).initPresentation();
  }

  private DescriptionDetails createDescriptionDetails(Hero hero
  ) {
    HeroDescription characterDescription = HeroDescriptionFetcher.fetch(hero);
    HeroConcept heroConcept = HeroConceptFetcher.fetch(hero);
    boolean isExalt = hero.getTemplate().getTemplateType().getCharacterType().isExaltType();
    return new DescriptionDetails(characterDescription, heroConcept, isExalt);
  }
}