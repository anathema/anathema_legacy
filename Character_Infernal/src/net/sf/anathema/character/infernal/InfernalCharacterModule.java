package net.sf.anathema.character.infernal;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.magic.FirstExcellency;
import net.sf.anathema.character.generic.framework.magic.SecondExcellency;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.generic.impl.caste.CasteCollection;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.type.CharacterType;

import net.sf.anathema.character.infernal.caste.InfernalCaste;
import net.sf.anathema.character.infernal.generic.EffortlessYoziDominance;
import net.sf.anathema.character.infernal.generic.SoSpeaksYozi;
import net.sf.anathema.character.infernal.generic.YoziInevitabilityTechnique;
import net.sf.anathema.character.infernal.generic.YoziMythosExultant;
import net.sf.anathema.character.infernal.patron.InfernalPatronModelFactory;
import net.sf.anathema.character.infernal.patron.InfernalPatronParser;
import net.sf.anathema.character.infernal.patron.InfernalPatronTemplate;
import net.sf.anathema.character.infernal.patron.InfernalPatronViewFactory;
import net.sf.anathema.character.infernal.patron.persistence.InfernalPatronPersisterFactory;
import net.sf.anathema.character.infernal.reporting.InfernalPartEncoder;
import net.sf.anathema.character.infernal.urge.InfernalUrgeModelFactory;
import net.sf.anathema.character.infernal.urge.InfernalUrgeParser;
import net.sf.anathema.character.infernal.urge.InfernalUrgePersisterFactory;
import net.sf.anathema.character.infernal.urge.InfernalUrgeTemplate;
import net.sf.anathema.character.infernal.urge.InfernalUrgeViewFactory;
import net.sf.anathema.character.reporting.CharacterReportingModule;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.pdf.layout.simple.ISimplePartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.simple.SimpleEncodingRegistry;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public class InfernalCharacterModule extends NullObjectCharacterModuleAdapter {

  /*private static final TemplateType baseType = new TemplateType(CharacterType.INFERNAL, new Identificate(
	  "default")); //$NON-NLS-1$*/
  
  //private static final ITemplateType[] baseTemplates = new ITemplateType[] { baseType };
	
  @Override
  public void registerCommonData(ICharacterGenerics characterGenerics) {
    characterGenerics.getCasteCollectionRegistry().register(
        CharacterType.INFERNAL,
        new CasteCollection(InfernalCaste.values()));
    characterGenerics.getAdditionalTemplateParserRegistry().register(
    		InfernalPatronTemplate.ID,
            new InfernalPatronParser());
    characterGenerics.getAdditionalTemplateParserRegistry().register(
    		InfernalUrgeTemplate.ID,
            new InfernalUrgeParser());
    
    characterGenerics.getGenericCharmStatsRegistry().register(
            CharacterType.INFERNAL,
            new IMagicStats[] { new FirstExcellency(CharacterType.INFERNAL, ExaltedSourceBook.Infernals, "1 m per die"), //$NON-NLS-1$
                new SecondExcellency(CharacterType.INFERNAL, ExaltedSourceBook.Infernals),
                new YoziMythosExultant(),
                new YoziInevitabilityTechnique(),
                new EffortlessYoziDominance(),
                new SoSpeaksYozi()});
  }
  
  @Override
  public void addCharacterTemplates(ICharacterGenerics characterGenerics) {
    registerParsedTemplate(characterGenerics, "template/Infernal2nd.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/RevisedInfernal2nd.template"); //$NON-NLS-1$
  }
  
  @Override
  public void addAdditionalTemplateData(ICharacterGenerics characterGenerics) {
    IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry = characterGenerics.getAdditionalModelFactoryRegistry();
    IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry = characterGenerics.getAdditionalViewFactoryRegistry();
    IRegistry<String, IAdditionalPersisterFactory> persisterFactory = characterGenerics.getAdditonalPersisterFactoryRegistry();
    registerInfernalPatron(additionalModelFactoryRegistry, additionalViewFactoryRegistry, persisterFactory);
    registerInfernalUrge(additionalModelFactoryRegistry, additionalViewFactoryRegistry, persisterFactory);
  }
  
  private void registerInfernalPatron(
		  IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry,
	      IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry,
	      IRegistry<String, IAdditionalPersisterFactory> persisterFactory)
  {
	  String templateId = InfernalPatronTemplate.ID;
	  additionalModelFactoryRegistry.register(templateId, new InfernalPatronModelFactory());  
	  additionalViewFactoryRegistry.register(templateId, new InfernalPatronViewFactory());
	  persisterFactory.register(templateId, new InfernalPatronPersisterFactory());
  }
  
  private void registerInfernalUrge(
		  IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry,
	      IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry,
	      IRegistry<String, IAdditionalPersisterFactory> persisterFactory)
  {
	  String templateId = InfernalUrgeTemplate.ID;
	  additionalModelFactoryRegistry.register(templateId, new InfernalUrgeModelFactory());  
	  additionalViewFactoryRegistry.register(templateId, new InfernalUrgeViewFactory());
	  persisterFactory.register(templateId, new InfernalUrgePersisterFactory());
  }
  
  @Override
  public void addReportTemplates(ICharacterGenerics generics, IResources resources) {
    CharacterReportingModuleObject moduleObject = generics.getModuleObjectMap().getModuleObject(
        CharacterReportingModule.class);
    SimpleEncodingRegistry registry = moduleObject.getSimpleEncodingRegistry();
    ISimplePartEncoder secondEditionEncoder = new InfernalPartEncoder(resources, registry, EssenceTemplate.SYSTEM_ESSENCE_MAX);
    registry.setPartEncoder(CharacterType.INFERNAL, ExaltedEdition.SecondEdition, secondEditionEncoder);
  }

}
