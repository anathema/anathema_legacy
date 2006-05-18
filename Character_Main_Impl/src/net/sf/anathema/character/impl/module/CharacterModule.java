package net.sf.anathema.character.impl.module;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.ICharacterGenericsExtension;
import net.sf.anathema.character.generic.framework.configuration.ICharacterPreferencesConstants;
import net.sf.anathema.character.generic.framework.reporting.template.ICharacterReportTemplate;
import net.sf.anathema.character.impl.module.preferences.RulesetPreferenceElement;
import net.sf.anathema.character.impl.reporting.CharacterReportingInitializer;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.extension.IExtensionPoint;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.module.AbstractAnathemaModule;
import net.sf.anathema.framework.module.PreferencesElementsExtensionPoint;
import net.sf.anathema.framework.resources.IAnathemaResources;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.registry.ICollectionRegistry;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public class CharacterModule extends AbstractAnathemaModule {

  private final ExaltedCharacterItemTypeConfiguration characterTypeConfiguration;

  public CharacterModule() throws AnathemaException {
    characterTypeConfiguration = new ExaltedCharacterItemTypeConfiguration();
    addItemTypeConfiguration(characterTypeConfiguration);
  }

  @Override
  public void fillModelExtensionPoints(IRegistry<String, IExtensionPoint> extensionPointRegistry, IAnathemaModel model) {
    super.fillModelExtensionPoints(extensionPointRegistry, model);
    characterTypeConfiguration.setCharacterGenerics(getCharacterGenerics(model));
  }

  @Override
  public void initModel(IAnathemaModel model) {
    super.initModel(model);
    ICollectionRegistry<ICharacterReportTemplate> reportTemplates = getCharacterGenerics(model).getReportTemplateRegistry();
    new CharacterReportingInitializer().initReporting(reportTemplates, model.getReportRegistry(), getResources());
  }

  @Override
  public void initPresentation(IResources resources, IAnathemaView view) {
    super.initPresentation(resources, view);
    ICharacterGenerics characterGenerics = getCharacterGenerics(getAnathemaModel());
    IItemType characterItemType = characterTypeConfiguration.getItemType();
    new CharacterModulePresenter(getAnathemaModel(), view, resources, characterItemType, characterGenerics);
    new CharacterPerformanceTuner(getAnathemaModel(), getResources()).startTuning(characterGenerics, characterItemType);
  }

  @Override
  public void fillPresentationExtensionPoints(
      IRegistry<String, IExtensionPoint> extensionPointRegistry,
      IAnathemaModel model,
      IResources resources,
      IAnathemaView view) {
    super.fillPresentationExtensionPoints(extensionPointRegistry, model, resources, view);
    PreferencesElementsExtensionPoint preferencesPoint = (PreferencesElementsExtensionPoint) extensionPointRegistry.get(PreferencesElementsExtensionPoint.ID);
    preferencesPoint.register(ICharacterPreferencesConstants.RULESET_PREFERENCE, new RulesetPreferenceElement());
    // preferencesPoint.register(ICharacterPreferencesConstants.CHARMORDER_PREFERENCE, new
    // CharmOrderPreferenceElement());
  }

  private final ICharacterGenerics getCharacterGenerics(IAnathemaModel anathemaModel) {
    IRegistry<String, IExtensionPoint> extensionPointRegistry = anathemaModel.getExtensionPointRegistry();
    ICharacterGenericsExtension extension = (ICharacterGenericsExtension) extensionPointRegistry.get(ICharacterGenericsExtension.ID);
    return extension.getCharacterGenerics();
  }

  @Override
  public void initAnathemaResources(IAnathemaResources resources) {
    super.initAnathemaResources(resources);
    resources.addStringResourceHandler(createStringProvider("Backgrounds", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Castes", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Character", resources.getLocale())); //$NON-NLS-1$    
    resources.addStringResourceHandler(createStringProvider("Overview", resources.getLocale())); //$NON-NLS-1$    
    resources.addStringResourceHandler(createStringProvider("CharacterModule", resources.getLocale())); //$NON-NLS-1$    
    resources.addStringResourceHandler(createStringProvider("CharacterSheet", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Spells_Sorcery", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Spells_Necromancy", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Weapons", resources.getLocale())); //$NON-NLS-1$
  }
}