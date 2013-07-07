package net.sf.anathema.swing.character.perspective;

import net.sf.anathema.character.generic.framework.CharacterGenericsExtractor;
import net.sf.anathema.character.model.advance.ExperiencePointManagement;
import net.sf.anathema.character.model.advance.IExperiencePointManagement;
import net.sf.anathema.character.model.creation.IBonusPointManagement;
import net.sf.anathema.character.model.creation.bonus.BonusPointManagement;
import net.sf.anathema.character.main.presenter.CharacterPresenter;
import net.sf.anathema.character.main.presenter.OverviewPresenter;
import net.sf.anathema.character.model.view.CharacterView;
import net.sf.anathema.character.model.view.OverviewContainer;
import net.sf.anathema.character.model.view.SubViewMap;
import net.sf.anathema.character.model.view.SubViewRegistry;
import net.sf.anathema.character.model.view.TaskedCharacterView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.repository.Item;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.resources.Resources;

public class CharacterViewFactory {
  private final Resources resources;
  private final IApplicationModel model;

  public CharacterViewFactory(Resources resources, IApplicationModel model) {
    this.resources = resources;
    this.model = model;
  }

  public IView createView(Item item) {
    Hero hero = (Hero) item.getItemData();
    SubViewRegistry viewFactory = new SubViewMap(CharacterGenericsExtractor.getGenerics(model).getInstantiater());
    CharacterView characterView = new TaskedCharacterView(viewFactory);
    new CharacterPresenter(hero, characterView, resources, model).initPresentation();
    initOverviewPresentation(hero, characterView, resources);
    item.getChangeManagement().setClean();
    return characterView;
  }

  private void initOverviewPresentation(Hero hero, OverviewContainer container, Resources resources) {
    IBonusPointManagement bonusPointManagement = new BonusPointManagement(hero);
    IExperiencePointManagement experiencePointManagement = new ExperiencePointManagement(hero);
    new OverviewPresenter(resources, hero, container, bonusPointManagement, experiencePointManagement).initPresentation();
  }
}