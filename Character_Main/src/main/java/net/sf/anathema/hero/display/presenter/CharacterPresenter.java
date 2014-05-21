package net.sf.anathema.hero.display.presenter;

import net.sf.anathema.character.framework.display.CharacterView;
import net.sf.anathema.character.framework.display.SectionView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.hero.display.HeroModelGroup;
import net.sf.anathema.hero.model.Hero;

import static net.sf.anathema.hero.display.HeroModelGroup.Magic;
import static net.sf.anathema.hero.display.HeroModelGroup.Miscellaneous;
import static net.sf.anathema.hero.display.HeroModelGroup.NaturalTraits;
import static net.sf.anathema.hero.display.HeroModelGroup.Outline;
import static net.sf.anathema.hero.display.HeroModelGroup.SpiritualTraits;

public class CharacterPresenter {

  private final InitializerList initializerList;
  private final Hero hero;
  private final CharacterView characterView;
  private final Environment environment;

  public CharacterPresenter(Hero hero, CharacterView view, Environment environment, IApplicationModel model) {
    this.initializerList = new InitializerList(environment, model);
    this.hero = hero;
    this.characterView = view;
    this.environment = environment;
  }
  
  public void initPresentation() {
    initializeSection("CardView.Outline.Title", Outline);
    initializeSection("CardView.NaturalTraits.Title", NaturalTraits);
    initializeSection("CardView.SpiritualTraits.Title", SpiritualTraits);
    initializeSection("CardView.CharmConfiguration.Title", Magic);
    initializeSection("CardView.MiscellaneousConfiguration.Title", Miscellaneous);
  }

  private void initializeSection(String titleKey, HeroModelGroup group) {
    SectionView sectionView = prepareSection(titleKey);
    initializeGroup(group, sectionView);
    sectionView.finishInitialization();
  }

  private SectionView prepareSection(String titleKey) {
    String sectionTitle = environment.getString(titleKey);
    return characterView.addSection(sectionTitle);
  }

  private void initializeGroup(HeroModelGroup group, SectionView sectionView) {
    for (HeroModelInitializer initializer : initializerList.getInOrderFor(group)) {
      initializer.initialize(sectionView, hero, environment);
    }
  }
}