package net.sf.anathema.character.impl.module;

import javax.swing.Icon;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.ICharacterGenericsExtension;
import net.sf.anathema.character.generic.framework.configuration.AnathemaCharacterPreferences;
import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.impl.model.advance.ExperiencePointManagement;
import net.sf.anathema.character.impl.model.creation.bonus.BonusPointManagement;
import net.sf.anathema.character.impl.module.repository.CharacterCreationWizardPageFactory;
import net.sf.anathema.character.impl.persistence.ExaltedCharacterPersister;
import net.sf.anathema.character.impl.view.CharacterView;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.character.library.intvalue.MarkerIntValueDisplayFactory;
import net.sf.anathema.character.library.intvalue.MarkerLessIntValueDisplayFactory;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.advance.IExperiencePointManagement;
import net.sf.anathema.character.model.concept.NatureProvider;
import net.sf.anathema.character.model.creation.IBonusPointManagement;
import net.sf.anathema.character.presenter.CharacterPresenter;
import net.sf.anathema.character.view.ICharacterView;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.module.AbstractPersistableItemTypeConfiguration;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.presenter.IItemViewFactory;
import net.sf.anathema.framework.presenter.view.IItemTypeCreationViewProperties;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.framework.repository.RepositoryConfiguration;
import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.resources.IResources;

public final class ExaltedCharacterItemTypeConfiguration extends AbstractPersistableItemTypeConfiguration {
  public static final String CHARACTER_ITEM_TYPE_ID = "ExaltedCharacter"; //$NON-NLS-1$

  public ExaltedCharacterItemTypeConfiguration() throws AnathemaException {
    super(new ItemType(CHARACTER_ITEM_TYPE_ID, new RepositoryConfiguration(".ecg", "ExaltedCharacter/", "head"))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    NatureProvider.getInstance().init();
  }

  @Override
  protected IRepositoryItemPersister createPersister(IAnathemaModel model) {
    return new ExaltedCharacterPersister(getItemType(), getGenerics(model), model.getMessaging());
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
          ICharacterView characterView = new CharacterView(null, printName, icon, null);
          new CharacterPresenter(character, characterView, resources, getGenerics(anathemaModel), null, null).initPresentation();
          return characterView;
        }
        CharacterType characterType = character.getStatistics()
            .getCharacterTemplate()
            .getTemplateType()
            .getCharacterType();
        IIntValueDisplayFactory intValueDisplayFactory = new MarkerIntValueDisplayFactory(resources, characterType);
        IIntValueDisplayFactory markerLessIntValueDisplayFactory = new MarkerLessIntValueDisplayFactory(
            resources,
            characterType);
        final Icon typeIcon = characterUI.getSmallTypeIcon(characterType);
        ICharacterView characterView = new CharacterView(
            intValueDisplayFactory,
            printName,
            typeIcon,
            markerLessIntValueDisplayFactory);
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
  protected IItemTypeCreationViewProperties createItemTypeCreationProperties(
      IAnathemaModel anathemaModel,
      IResources resources) {
    IExaltedRuleSet preferredRuleset = AnathemaCharacterPreferences.getDefaultPreferences().getPreferredRuleset();
    ICharacterGenerics generics = getGenerics(anathemaModel);
    CharacterCreationWizardPageFactory factory = new CharacterCreationWizardPageFactory(
        generics,
        preferredRuleset,
        resources);
    return new CharacterCreationViewProperties(getItemType(), resources, factory, generics.getCasteCollectionRegistry());
  }
}