package net.sf.anathema.character.impl.module;

import net.sf.anathema.character.generic.framework.CharacterModuleContainerInitializer;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.ICharacterGenericsExtension;
import net.sf.anathema.character.generic.framework.configuration.ICharacterPreferencesConstants;
import net.sf.anathema.character.generic.framework.module.CharacterModuleContainer;
import net.sf.anathema.character.generic.framework.reporting.template.ICharacterReportTemplate;
import net.sf.anathema.character.impl.module.preferences.RulesetPreferenceElement;
import net.sf.anathema.character.impl.reporting.CharacterReportingInitializer;
import net.sf.anathema.character.impl.reporting.PageSize;
import net.sf.anathema.character.impl.reporting.PdfSheetReport;
import net.sf.anathema.character.impl.reporting.TextReport;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.extension.IExtensionPoint;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.module.AbstractAnathemaModule;
import net.sf.anathema.framework.module.PreferencesElementsExtensionPoint;
import net.sf.anathema.framework.reporting.IReportRegistry;
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
  public void initModelExtensionPoints(
      IRegistry<String, IExtensionPoint> registry,
      IAnathemaModel model,
      IResources resources) {
    super.initModelExtensionPoints(registry, model, resources);
    CharacterModuleContainer container = new CharacterModuleContainerInitializer().initContainer(resources);
    registry.register(ICharacterGenericsExtension.ID, new CharacterGenericsExtension(container.getCharacterGenerics()));
  }

  @Override
  public void initModel(IAnathemaModel model, IResources resources) {
    super.initModel(model, resources);
    ICollectionRegistry<ICharacterReportTemplate> reportTemplates = getCharacterGenerics(model).getReportTemplateRegistry();
    IReportRegistry reportRegistry = model.getReportRegistry();
    new CharacterReportingInitializer().initReporting(reportTemplates, reportRegistry, resources);
  }

  @Override
  public void initPresentation(IResources resources, IAnathemaModel model, IAnathemaView view) {
    super.initPresentation(resources, model, view);
    ICharacterGenerics characterGenerics = getCharacterGenerics(model);
    model.getReportRegistry().addReport(new PdfSheetReport(resources, characterGenerics, PageSize.A4));
    model.getReportRegistry().addReport(new PdfSheetReport(resources, characterGenerics, PageSize.Letter));
    model.getReportRegistry().addReport(new TextReport());
    IItemType characterItemType = characterTypeConfiguration.getItemType();
    new CharacterModulePresenter(model, view, resources, characterItemType, characterGenerics);
    new CharacterPerformanceTuner(model, resources).startTuning(characterGenerics, characterItemType);
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
  }

  private final ICharacterGenerics getCharacterGenerics(IAnathemaModel anathemaModel) {
    IRegistry<String, IExtensionPoint> extensionPointRegistry = anathemaModel.getExtensionPointRegistry();
    ICharacterGenericsExtension extension = (ICharacterGenericsExtension) extensionPointRegistry.get(ICharacterGenericsExtension.ID);
    return extension.getCharacterGenerics();
  }
}