package net.sf.anathema.hero.concept.display.caste.presenter;

import net.sf.anathema.hero.display.presenter.HeroModelInitializer;
import net.sf.anathema.hero.display.presenter.RegisteredInitializer;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.character.main.view.SectionView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.hero.concept.HeroConceptFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.hero.display.HeroModelGroup.Outline;

@RegisteredInitializer(Outline)
@Weight(weight = 100)
public class CasteInitializer implements HeroModelInitializer {

  @SuppressWarnings("UnusedParameters")
  public CasteInitializer(IApplicationModel applicationModel) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, Resources resources) {
    if (HeroConceptFetcher.fetch(hero).getCasteCollection().isEmpty()){
      return;
    }
    String conceptHeader = resources.getString("CardView.CharacterConcept.Title");
    ICharacterType characterType = hero.getTemplate().getTemplateType().getCharacterType();
    CasteView conceptView = sectionView.addView(conceptHeader, CasteView.class, characterType);
    new CastePresenter(hero, conceptView, resources).initPresentation();
  }
}