package net.sf.anathema.character.platform.module;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.impl.model.advance.ExperiencePointManagement;
import net.sf.anathema.character.impl.model.creation.bonus.BonusPointManagement;
import net.sf.anathema.character.impl.view.TaskedCharacterView;
import net.sf.anathema.character.library.intvalue.IntValueDisplayFactoryPrototype;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.advance.IExperiencePointManagement;
import net.sf.anathema.character.model.creation.IBonusPointManagement;
import net.sf.anathema.character.presenter.CharacterPresenter;
import net.sf.anathema.character.presenter.PlayerCharacterPointPresentation;
import net.sf.anathema.character.presenter.PointPresentationStrategy;
import net.sf.anathema.character.view.CharacterView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.swing.character.perspective.ItemViewFactory;

public class CharacterViewFactory implements ItemViewFactory {
  private final Resources resources;
  private final IApplicationModel model;

  public CharacterViewFactory(Resources resources, IApplicationModel model) {
    this.resources = resources;
    this.model = model;
  }

  @Override
  public IView createView(IItem item) {
    ICharacter character = (ICharacter) item.getItemData();
    ICharacterType characterType = character.getCharacterTemplate().getTemplateType().getCharacterType();
    IntegerViewFactory intValueDisplayFactory = IntValueDisplayFactoryPrototype.createWithMarkerForCharacterType(
            characterType);
    IntegerViewFactory markerLessIntValueDisplayFactory = IntValueDisplayFactoryPrototype.createWithoutMarkerForCharacterType(
            characterType);
    CharacterView characterView = new TaskedCharacterView(intValueDisplayFactory, markerLessIntValueDisplayFactory);
    IBonusPointManagement bonusPointManagement = new BonusPointManagement(character);
    IExperiencePointManagement experiencePointManagement = new ExperiencePointManagement(character);
    PointPresentationStrategy pointPresentation = choosePointPresentation(character, characterView,
            bonusPointManagement, experiencePointManagement, resources);
    new CharacterPresenter(character, characterView, resources, model, pointPresentation).initPresentation();
    item.getItemData().setClean();
    return characterView;
  }

  private PointPresentationStrategy choosePointPresentation(ICharacter character, CharacterView characterView,
                                                            IBonusPointManagement bonusPointManagement,
                                                            IExperiencePointManagement experiencePointManagement,
                                                            Resources resources) {
    if (character.getCharacterTemplate().isNpcOnly()) {
      return new NpcPointPresentation();
    }
    return new PlayerCharacterPointPresentation(resources, character, characterView, bonusPointManagement,
            experiencePointManagement);
  }
}
