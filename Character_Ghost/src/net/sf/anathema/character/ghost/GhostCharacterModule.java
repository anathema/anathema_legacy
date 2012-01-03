package net.sf.anathema.character.ghost;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.generic.impl.backgrounds.TemplateTypeBackgroundTemplate;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.ghost.age.GhostAgeModelFactory;
import net.sf.anathema.character.ghost.age.GhostAgeParser;
import net.sf.anathema.character.ghost.age.GhostAgeTemplate;
import net.sf.anathema.character.ghost.fetters.GhostFettersModelFactory;
import net.sf.anathema.character.ghost.fetters.GhostFettersParser;
import net.sf.anathema.character.ghost.fetters.GhostFettersPersisterFactory;
import net.sf.anathema.character.ghost.fetters.GhostFettersTemplate;
import net.sf.anathema.character.ghost.fetters.GhostFettersViewFactory;
import net.sf.anathema.character.ghost.passions.GhostPassionsModelFactory;
import net.sf.anathema.character.ghost.passions.GhostPassionsParser;
import net.sf.anathema.character.ghost.passions.GhostPassionsTemplate;
import net.sf.anathema.character.ghost.passions.GhostPassionsViewFactory;
import net.sf.anathema.character.ghost.passions.persistence.GhostPassionsPersisterFactory;
import net.sf.anathema.character.ghost.reporting.ExtendedGhostPartEncoder;
import net.sf.anathema.character.ghost.reporting.SimpleGhostPartEncoder;
import net.sf.anathema.character.ghost.reporting.content.GhostFetterContent;
import net.sf.anathema.character.ghost.reporting.content.GhostFetterContentFactory;
import net.sf.anathema.character.ghost.reporting.content.GhostPassionContent;
import net.sf.anathema.character.ghost.reporting.content.GhostPassionContentFactory;
import net.sf.anathema.character.reporting.CharacterReportingModule;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.pdf.content.ReportContentRegistry;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.layout.simple.ISimplePartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.simple.SimpleEncodingRegistry;
import net.sf.anathema.lib.registry.IIdentificateRegistry;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identificate;

import static net.sf.anathema.character.generic.impl.rules.ExaltedEdition.SecondEdition;
import static net.sf.anathema.character.generic.impl.traits.EssenceTemplate.SYSTEM_ESSENCE_MAX;
import static net.sf.anathema.character.generic.type.CharacterType.GHOST;

public class GhostCharacterModule extends NullObjectCharacterModuleAdapter {
  public static final String BACKGROUND_ID_AGE = "Age"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_ANCESTOR_CULT = "AncestorCult"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_GRAVE_GOODS = "GraveGoods"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_UNDERWORLD_MANSE = "GhostUnderworldManse"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_UNDERWORLD_CULT = "UnderworldCult"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_WHISPERS = "GhostWhispers"; //$NON-NLS-1$

  private static final TemplateType heroicType = new TemplateType(GHOST);
  private static final TemplateType commonType = new TemplateType(GHOST, new Identificate("CommonGhost")); //$NON-NLS-1$

  private final TemplateType[] trueGhosts = { heroicType, commonType };

  @Override
  public void registerCommonData(ICharacterGenerics characterGenerics) {
    characterGenerics.getAdditionalTemplateParserRegistry().register(GhostPassionsTemplate.ID, new GhostPassionsParser());
    characterGenerics.getAdditionalTemplateParserRegistry().register(GhostFettersTemplate.ID, new GhostFettersParser());
    characterGenerics.getAdditionalTemplateParserRegistry().register(GhostAgeTemplate.ID, new GhostAgeParser());
  }

  @Override
  public void addCharacterTemplates(ICharacterGenerics characterGenerics) {
    registerParsedTemplate(characterGenerics, "template/Ghost2nd.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/CommonGhost2nd.template"); //$NON-NLS-1$
  }

  @Override
  public void addBackgroundTemplates(ICharacterGenerics generics) {
    IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry = generics.getBackgroundRegistry();
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_AGE, trueGhosts, LowerableState.Immutable));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_ANCESTOR_CULT, trueGhosts, LowerableState.Default));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_GRAVE_GOODS, trueGhosts, LowerableState.Default));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_UNDERWORLD_MANSE, trueGhosts, LowerableState.Default));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_UNDERWORLD_CULT, trueGhosts, LowerableState.Default));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_WHISPERS, trueGhosts, LowerableState.Default));
  }

  @Override
  public void addAdditionalTemplateData(ICharacterGenerics characterGenerics) {
    IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry = characterGenerics.getAdditionalModelFactoryRegistry();
    IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry = characterGenerics.getAdditionalViewFactoryRegistry();
    IRegistry<String, IAdditionalPersisterFactory> persisterFactory = characterGenerics.getAdditonalPersisterFactoryRegistry();
    registerGhostPassions(additionalModelFactoryRegistry, additionalViewFactoryRegistry, persisterFactory);
    registerGhostFetters(additionalModelFactoryRegistry, additionalViewFactoryRegistry, persisterFactory);
    registerGhostAge(additionalModelFactoryRegistry);
  }

  private void registerGhostPassions(IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry,
    IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry, IRegistry<String, IAdditionalPersisterFactory> persisterFactory) {
    String templateId = GhostPassionsTemplate.ID;
    additionalModelFactoryRegistry.register(templateId, new GhostPassionsModelFactory());
    additionalViewFactoryRegistry.register(templateId, new GhostPassionsViewFactory());
    persisterFactory.register(templateId, new GhostPassionsPersisterFactory());
  }

  private void registerGhostFetters(IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry,
    IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry, IRegistry<String, IAdditionalPersisterFactory> persisterFactory) {
    String templateId = GhostFettersTemplate.ID;
    additionalModelFactoryRegistry.register(templateId, new GhostFettersModelFactory());
    additionalViewFactoryRegistry.register(templateId, new GhostFettersViewFactory());
    persisterFactory.register(templateId, new GhostFettersPersisterFactory());
  }

  private void registerGhostAge(IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry) {
    String templateId = GhostAgeTemplate.ID;
    additionalModelFactoryRegistry.register(templateId, new GhostAgeModelFactory());
  }

  @Override
  public void addReportTemplates(ICharacterGenerics generics, IResources resources) {
    CharacterReportingModuleObject moduleObject = generics.getModuleObjectMap().getModuleObject(CharacterReportingModule.class);
    addReportContents(moduleObject.getContentRegistry(), resources);
    addSimpleParts(resources, moduleObject);
    addExtendedParts(resources, moduleObject);
  }

  private void addReportContents(ReportContentRegistry registry, IResources resources) {
    registry.addFactory(GhostFetterContent.class, new GhostFetterContentFactory(resources));
    registry.addFactory(GhostPassionContent.class, new GhostPassionContentFactory(resources));
  }

  private void addSimpleParts(IResources resources, CharacterReportingModuleObject moduleObject) {
    SimpleEncodingRegistry registry = moduleObject.getSimpleEncodingRegistry();
    ISimplePartEncoder secondEditionEncoder = new SimpleGhostPartEncoder(resources, registry, SYSTEM_ESSENCE_MAX);
    registry.setPartEncoder(GHOST, SecondEdition, secondEditionEncoder);
  }

  private void addExtendedParts(IResources resources, CharacterReportingModuleObject moduleObject) {
    ExtendedEncodingRegistry registry = moduleObject.getExtendedEncodingRegistry();
    registry.setPartEncoder(GHOST, SecondEdition, new ExtendedGhostPartEncoder(resources, registry, SYSTEM_ESSENCE_MAX));
  }
}
