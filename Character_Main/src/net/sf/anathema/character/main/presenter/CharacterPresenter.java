package net.sf.anathema.character.main.presenter;

import net.sf.anathema.character.main.presenter.initializers.HeroModelInitializer;
import net.sf.anathema.character.main.presenter.initializers.InitializerList;
import net.sf.anathema.character.main.view.CharacterView;
import net.sf.anathema.character.main.view.SectionView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.hero.display.HeroModelGroup;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.hero.display.HeroModelGroup.Magic;
import static net.sf.anathema.hero.display.HeroModelGroup.Miscellaneous;
import static net.sf.anathema.hero.display.HeroModelGroup.NaturalTraits;
import static net.sf.anathema.hero.display.HeroModelGroup.Outline;
import static net.sf.anathema.hero.display.HeroModelGroup.SpiritualTraits;

public class CharacterPresenter implements Presenter {

  private final InitializerList initializerList;
  private final Hero hero;
  private final CharacterView characterView;
  private final Resources resources;

  public CharacterPresenter(Hero hero, CharacterView view, Resources resources, IApplicationModel applicationModel) {
    this.initializerList = new InitializerList(applicationModel);
    this.hero = hero;
    this.characterView = view;
    this.resources = resources;
  }

  @Override
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
    String sectionTitle = resources.getString(titleKey);
    return characterView.addSection(sectionTitle);
  }

  private void initializeGroup(HeroModelGroup group, SectionView sectionView) {
    for (HeroModelInitializer initializer : initializerList.getInOrderFor(group)) {
      initializer.initialize(sectionView, hero, resources);
    }
  }
}