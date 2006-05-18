package net.sf.anathema.character.impl.module;

import javax.swing.Icon;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.impl.IIconConstants;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.character.generic.type.AbstractSupportedCharacterTypeVisitor;
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

  private ICharacterGenerics generics;

  public ExaltedCharacterItemTypeConfiguration() throws AnathemaException {
    super(createCharacterItemType());
    NatureProvider.getInstance().init();
  }

  @Override
  protected IRepositoryItemPersister createPersister(IAnathemaModel model) {
    return new ExaltedCharacterPersister(getItemType(), generics);
  }

  public void setCharacterGenerics(ICharacterGenerics generics) {
    this.generics = generics;
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
  protected IItemViewFactory createItemViewFactory(IAnathemaModel anathemaModel, final IResources resources) {
    return new IItemViewFactory() {
      public IItemView createView(IItem item) throws AnathemaException {
        String printName = item.getDisplayName();
        ICharacter character = (ICharacter) item.getItemData();
        ICharacterStatistics statistics = character.getStatistics();
        if (statistics == null) {
          Icon icon = resources.getImageIcon("CharacterTabIcon.png"); //$NON-NLS-1$
          ICharacterView characterView = new CharacterView(null, printName, icon);
          new CharacterPresenter(character, characterView, resources, generics, null, null).initPresentation();
          return characterView;
        }
        IPresentationProperties presentationProperties = statistics.getCharacterTemplate().getPresentationProperties();
        IntValueDisplayFactory intValueDisplayFactory = new IntValueDisplayFactory(
            resources,
            resources.getImageIcon(presentationProperties.getBallResource()));
        CharacterType characterType = character.getStatistics()
            .getCharacterTemplate()
            .getTemplateType()
            .getCharacterType();
        final Icon[] typeIcon = getCharacterTypeIcon(resources, characterType);
        ICharacterView characterView = new CharacterView(intValueDisplayFactory, printName, typeIcon[0]);
        IBonusPointManagement bonusPointManagement = new BonusPointManagement(character.getStatistics());
        IExperiencePointManagement experiencePointManagement = new ExperiencePointManagement(character.getStatistics());
        new CharacterPresenter(
            character,
            characterView,
            resources,
            generics,
            bonusPointManagement,
            experiencePointManagement).initPresentation();
        return characterView;
      }
    };
  }

  @Override
  protected IMenuItem[] createAddMenuEntries(IAnathemaView view, IAnathemaModel anathemaModel, IResources resources) {
    return new IMenuItem[] {
        new ActionMenuItem(AddNewFullCharacterAction.createMenuAction(
            generics,
            resources,
            anathemaModel,
            "CharacterGenerator.NewCharacter.StattedCharacter.Name")), //$NON-NLS-1$
        new ActionMenuItem(AddNewCharacterDescriptionAction.createMenuAction(
            resources,
            anathemaModel,
            "CharacterGenerator.NewCharacter.Description.Name")) }; //$NON-NLS-1$
  }

  private Icon[] getCharacterTypeIcon(final IResources resources, CharacterType characterType) {
    final Icon[] typeIcon = new Icon[1];
    characterType.accept(new AbstractSupportedCharacterTypeVisitor() {
      public void visitSolar(CharacterType visitedType) {
        typeIcon[0] = resources.getImageIcon(IIconConstants.SOLAR_ICON_SMALL);
      }

      public void visitMortal(CharacterType visitedType) {
        typeIcon[0] = resources.getImageIcon(IIconConstants.MORTAL_ICON_SMALL);
      }

      public void visitLunar(CharacterType type) {
        typeIcon[0] = resources.getImageIcon(IIconConstants.LUNAR_ICON_SMALL);
      }

      public void visitSidereal(CharacterType visitedType) {
        typeIcon[0] = resources.getImageIcon(IIconConstants.SIDEREAL_ICON_SMALL);
      }

      public void visitDB(CharacterType visitedType) {
        typeIcon[0] = resources.getImageIcon(IIconConstants.DB_ICON_SMALL);
      }

      public void visitAbyssal(CharacterType visitedType) {
        typeIcon[0] = resources.getImageIcon(IIconConstants.ABYSSAL_ICON_SMALL);
      }
    });
    return typeIcon;
  }
}