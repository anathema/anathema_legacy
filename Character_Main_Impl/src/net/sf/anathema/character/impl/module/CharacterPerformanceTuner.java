package net.sf.anathema.character.impl.module;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.impl.magic.SpellException;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.impl.model.ExaltedCharacter;
import net.sf.anathema.character.impl.model.advance.ExperiencePointManagement;
import net.sf.anathema.character.impl.model.creation.bonus.BonusPointManagement;
import net.sf.anathema.character.impl.view.magic.MagicViewFactory;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.advance.IExperiencePointManagement;
import net.sf.anathema.character.model.creation.IBonusPointManagement;
import net.sf.anathema.character.presenter.CharacterPresenter;
import net.sf.anathema.character.presenter.charm.MagicPresenter;
import net.sf.anathema.character.view.ICharacterView;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.IItemViewFactory;
import net.sf.anathema.framework.repository.AnathemaDataItem;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.resources.IResources;

public class CharacterPerformanceTuner {

  private final IAnathemaModel model;
  private final IResources resources;

  public CharacterPerformanceTuner(IAnathemaModel model, IResources resources) {
    this.model = model;
    this.resources = resources;
  }

  public void startTuning() {
    final ICharacterGenerics characterGenerics = CharacterGenericsExtension.getCharacterGenerics(model);
    final IItemType characterItemType = model.getItemTypeRegistry().getById(
        ExaltedCharacterItemTypeConfiguration.CHARACTER_ITEM_TYPE_ID);
    new Thread(new Runnable() {
      public void run() {
        try {
          initCharacterView(characterGenerics, characterItemType);
          initCharmVisualization(characterGenerics);
        }
        catch (AnathemaException e) {
          // Nothing to do
        }
      }
    }).start();
  }

  private void initCharmVisualization(ICharacterGenerics generics) throws AnathemaException {
    ExaltedCharacter character = createStattedCharacter(generics);
    ICharacterStatistics statistics = character.getStatistics();
    MagicViewFactory magicView = new MagicViewFactory();
    new MagicPresenter(statistics, magicView, resources, generics.getTemplateRegistry()).initPresentation();
  }

  private ICharacterView initCharacterView(ICharacterGenerics characterGenerics, IItemType characterItemType)
      throws AnathemaException {
    IItemViewFactory factory = model.getViewFactoryRegistry().get(characterItemType);
    ExaltedCharacter characterData = createStattedCharacter(characterGenerics);
    ICharacterView view = (ICharacterView) factory.createView(new AnathemaDataItem(characterItemType, characterData));
    IBonusPointManagement bonusPointManagement = new BonusPointManagement(characterData.getStatistics());
    IExperiencePointManagement experiencePointManagement = new ExperiencePointManagement(characterData.getStatistics());
    new CharacterPresenter(
        characterData,
        view,
        resources,
        characterGenerics,
        bonusPointManagement,
        experiencePointManagement).initPresentation();
    return view;
  }

  private ExaltedCharacter createStattedCharacter(ICharacterGenerics characterGenerics) throws SpellException {
    ExaltedCharacter characterData = new ExaltedCharacter();
    ICharacterTemplate template = createTemplate(characterGenerics);
    characterData.createCharacterStatistics(template, characterGenerics, ExaltedRuleSet.PowerCombat);
    return characterData;
  }

  private ICharacterTemplate createTemplate(ICharacterGenerics characterGenerics) {
    return characterGenerics.getTemplateRegistry().getDefaultTemplate(CharacterType.SOLAR, ExaltedEdition.FirstEdition);
  }
}