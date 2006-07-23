package net.sf.anathema.character.impl.module;

import javax.swing.Icon;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.ICharacterGenericsExtension;
import net.sf.anathema.character.generic.framework.resources.CharacterTemplateResourceProvider;
import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.impl.model.advance.ExperiencePointManagement;
import net.sf.anathema.character.impl.model.creation.bonus.BonusPointManagement;
import net.sf.anathema.character.impl.persistence.ExaltedCharacterPersister;
import net.sf.anathema.character.impl.view.CharacterView;
import net.sf.anathema.character.impl.view.repository.AddNewCharacterDescriptionAction;
import net.sf.anathema.character.impl.view.repository.AddNewFullCharacterAction;
import net.sf.anathema.character.library.intvalue.IntValueDisplayFactory;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.advance.IExperiencePointManagement;
import net.sf.anathema.character.model.concept.NatureProvider;
import net.sf.anathema.character.model.creation.IBonusPointManagement;
import net.sf.anathema.character.presenter.CharacterPresenter;
import net.sf.anathema.character.view.ICharacterView;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.module.AbstractItemTypeConfiguration;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.presenter.IItemViewFactory;
import net.sf.anathema.framework.presenter.action.ActionMenuItem;
import net.sf.anathema.framework.presenter.menu.IMenuItem;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.framework.repository.RepositoryConfiguration;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.resources.IResources;

public final class ExaltedCharacterItemTypeConfiguration extends AbstractItemTypeConfiguration {
  public static final String CHARACTER_ITEM_TYPE_ID = "ExaltedCharacter"; //$NON-NLS-1$

  public static ItemType createCharacterItemType() {
    return new ItemType(CHARACTER_ITEM_TYPE_ID, new RepositoryConfiguration(".ecg", "ExaltedCharacter/")); //$NON-NLS-1$ //$NON-NLS-2$
  }

  public ExaltedCharacterItemTypeConfiguration() throws AnathemaException {
    super(createCharacterItemType());
    NatureProvider.getInstance().init();
  }

  @Override
  protected IRepositoryItemPersister createPersister(IAnathemaModel model) {
    return new ExaltedCharacterPersister(getItemType(), getGenerics(model));
  }

  @Override
  protected String getPrintNameKey() {
    return "ItemType.ExaltedCharacter.PrintName"; //$NON-NLS-1$
  }

  @Override
  protected String getLoadTitleKey() {
    return "LoadCharacter.Dialog.Title"; //$NON-NLS-1$
  }

  @Override
  protected String getLoadMessageKey() {
    return "LoadCharacter.Dialog.Message.Default"; //$NON-NLS-1$
  }

  @Override
  protected IItemViewFactory createItemViewFactory(final IAnathemaModel anathemaModel, final IResources resources) {
    return new IItemViewFactory() {
      public IItemView createView(IItem item) throws AnathemaException {
        String printName = item.getDisplayName();
        ICharacter character = (ICharacter) item.getItemData();
        ICharacterStatistics statistics = character.getStatistics();
        CharacterUI characterUI = new CharacterUI(resources);
        if (statistics == null) {
          Icon icon = characterUI.getCharacterDescriptionTabIcon();
          ICharacterView characterView = new CharacterView(null, printName, icon);
          new CharacterPresenter(character, characterView, resources, getGenerics(anathemaModel), null, null).initPresentation();
          return characterView;
        }
        CharacterType characterType = character.getStatistics()
            .getCharacterTemplate()
            .getTemplateType()
            .getCharacterType();
        IntValueDisplayFactory intValueDisplayFactory = new IntValueDisplayFactory(
            resources,
            new CharacterTemplateResourceProvider(resources).getMediumBallResource(characterType));
        final Icon typeIcon = characterUI.getSmallCharacterTypeIcon(characterType);
        ICharacterView characterView = new CharacterView(intValueDisplayFactory, printName, typeIcon);
        IBonusPointManagement bonusPointManagement = new BonusPointManagement(character.getStatistics());
        IExperiencePointManagement experiencePointManagement = new ExperiencePointManagement(character.getStatistics());
        new CharacterPresenter(
            character,
            characterView,
            resources,
            getGenerics(anathemaModel),
            bonusPointManagement,
            experiencePointManagement).initPresentation();
        return characterView;
      }
    };
  }

  private ICharacterGenerics getGenerics(IAnathemaModel model) {
    ICharacterGenericsExtension genericsExtension = (ICharacterGenericsExtension) model.getExtensionPointRegistry()
        .get(ICharacterGenericsExtension.ID);
    return genericsExtension.getCharacterGenerics();
  }

  @Override
  protected IMenuItem[] createAddMenuEntries(IAnathemaView view, IAnathemaModel anathemaModel, IResources resources) {
    return new IMenuItem[] {
        new ActionMenuItem(AddNewFullCharacterAction.createMenuAction(
            getGenerics(anathemaModel),
            resources,
            anathemaModel,
            "CharacterGenerator.NewCharacter.StattedCharacter.Name")), //$NON-NLS-1$
        new ActionMenuItem(AddNewCharacterDescriptionAction.createMenuAction(
            resources,
            anathemaModel,
            "CharacterGenerator.NewCharacter.Description.Name")) }; //$NON-NLS-1$
  }
}