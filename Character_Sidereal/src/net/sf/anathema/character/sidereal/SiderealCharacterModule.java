package net.sf.anathema.character.sidereal;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.generic.impl.backgrounds.CharacterTypeBackgroundTemplate;
import net.sf.anathema.character.generic.impl.backgrounds.TemplateTypeBackgroundTemplate;
import net.sf.anathema.character.generic.impl.caste.CasteCollection;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.reporting.CharacterReportingModule;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.page.IPdfPartEncoder;
import net.sf.anathema.character.sidereal.additionalrules.AdditionalSiderealRules;
import net.sf.anathema.character.sidereal.caste.SiderealCaste;
import net.sf.anathema.character.sidereal.colleges.SiderealCollegeModelFactory;
import net.sf.anathema.character.sidereal.colleges.SiderealCollegeTemplate;
import net.sf.anathema.character.sidereal.colleges.SiderealCollegeViewFactory;
import net.sf.anathema.character.sidereal.colleges.persistence.SiderealCollegePersisterFactory;
import net.sf.anathema.character.sidereal.reporting.FirstEditionSiderealPartEncoder;
import net.sf.anathema.character.sidereal.template.DefaultSiderealTemplate;
import net.sf.anathema.character.sidereal.template.ISiderealSpecialCharms;
import net.sf.anathema.lib.registry.IIdentificateRegistry;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public class SiderealCharacterModule extends NullObjectCharacterModuleAdapter {
  private static final int ESSENCE_MAX = EssenceTemplate.SYSTEM_ESSENCE_MAX;
  public static final String BACKGROUND_ID_ACQUAINTANCES = "Acquaintances"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_CONNECTIONS = "Connections"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_CELESTIAL_MANSE = "CelestialManse"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_FAVOR = "Favor"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_HEAVENLY_FAVOR = "HeavenlyFavor"; //$NON-NLS-1$  
  public static final String BACKGROUND_ID_SALARY = "Salary"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_SAVANT = "Savant"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_SIFU = "Sifu"; //$NON-NLS-1$

  @Override
  public void registerCommonData(ICharacterGenerics characterGenerics) {
    ISpecialCharm[] charms = new ISpecialCharm[] {
        ISiderealSpecialCharms.OX_BODY_TECHNIQUE,
        ISiderealSpecialCharms.WORLD_SHAPING_ARTISTIC_VISION };
    characterGenerics.getCharmProvider().setSpecialCharms(CharacterType.SIDEREAL, ExaltedEdition.FirstEdition, charms);
    characterGenerics.getCasteCollectionRegistry().register(
        CharacterType.SIDEREAL,
        new CasteCollection(SiderealCaste.values()));
  }

  @Override
  public void addCharacterTemplates(ICharacterGenerics characterGenerics) {
    CharmCache charmProvider = CharmCache.getInstance();
    characterGenerics.getTemplateRegistry().register(
        new DefaultSiderealTemplate(charmProvider, new AdditionalSiderealRules()));
  }

  @Override
  public void addBackgroundTemplates(ICharacterGenerics generics) {
    IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry = generics.getBackgroundRegistry();
    ITemplateType[] defaultTemplateType = new ITemplateType[] { DefaultSiderealTemplate.TEMPLATE_TYPE };
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_ACQUAINTANCES, CharacterType.SIDEREAL));
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_CONNECTIONS, CharacterType.SIDEREAL));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_CELESTIAL_MANSE, defaultTemplateType));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_FAVOR, defaultTemplateType));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_HEAVENLY_FAVOR, defaultTemplateType));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_SALARY, defaultTemplateType));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(
        BACKGROUND_ID_SAVANT,
        defaultTemplateType,
        LowerableState.Default));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_SIFU, defaultTemplateType));
  }

  @Override
  public void addAdditionalTemplateData(ICharacterGenerics characterGenerics) {
    IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry = characterGenerics.getAdditionalModelFactoryRegistry();
    String templateId = SiderealCollegeTemplate.ID;
    additionalModelFactoryRegistry.register(templateId, new SiderealCollegeModelFactory());
    IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry = characterGenerics.getAdditionalViewFactoryRegistry();
    additionalViewFactoryRegistry.register(templateId, new SiderealCollegeViewFactory());
    IRegistry<String, IAdditionalPersisterFactory> persisterFactory = characterGenerics.getAdditonalPersisterFactoryRegistry();
    persisterFactory.register(templateId, new SiderealCollegePersisterFactory());
  }

  @Override
  public void addReportTemplates(ICharacterGenerics generics, IResources resources) {
    CharacterReportingModuleObject moduleObject = generics.getModuleObjectMap().getModuleObject(
        CharacterReportingModule.class);
    PdfEncodingRegistry registry = moduleObject.getPdfEncodingRegistry();
    IPdfPartEncoder firstEditionEncoder = new FirstEditionSiderealPartEncoder(resources, registry, ESSENCE_MAX);
    registry.setPartEncoder(CharacterType.SIDEREAL, ExaltedEdition.FirstEdition, firstEditionEncoder);
  }
}