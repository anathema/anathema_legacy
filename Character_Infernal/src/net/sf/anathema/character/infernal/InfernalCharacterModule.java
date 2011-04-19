package net.sf.anathema.character.infernal;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.generic.impl.caste.CasteCollection;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.type.CharacterType;

import net.sf.anathema.character.infernal.caste.InfernalCaste;
import net.sf.anathema.character.infernal.patron.InfernalPatronModelFactory;
import net.sf.anathema.character.infernal.patron.InfernalPatronParser;
import net.sf.anathema.character.infernal.patron.InfernalPatronTemplate;
import net.sf.anathema.character.infernal.patron.InfernalPatronViewFactory;
import net.sf.anathema.character.infernal.patron.persistence.InfernalPatronPersisterFactory;
import net.sf.anathema.character.infernal.template.IInfernalSpecialCharms;
import net.sf.anathema.character.infernal.urge.InfernalUrgeModelFactory;
import net.sf.anathema.character.infernal.urge.InfernalUrgeParser;
import net.sf.anathema.character.infernal.urge.InfernalUrgePersisterFactory;
import net.sf.anathema.character.infernal.urge.InfernalUrgeTemplate;
import net.sf.anathema.character.infernal.urge.InfernalUrgeViewFactory;
import net.sf.anathema.lib.registry.IRegistry;

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
    
    characterGenerics.getCharmProvider().setSpecialCharms(
            CharacterType.INFERNAL,
            ExaltedEdition.SecondEdition,
            new ISpecialCharm[] {
                    IInfernalSpecialCharms.MALFEAS_EXCELLENCY,
                    IInfernalSpecialCharms.CECELYNE_EXCELLENCY,
                    IInfernalSpecialCharms.SWLIHN_EXCELLENCY,
                    IInfernalSpecialCharms.ADORJAN_EXCELLENCY,
                    IInfernalSpecialCharms.EBON_DRAGON_EXCELLENCY,
                    IInfernalSpecialCharms.KIMBERY_EXCELLENCY,
                    IInfernalSpecialCharms.WINDBORN_STRIDE,
                    IInfernalSpecialCharms.WAYWARD_DIVINITY_OVERSIGHT,
                    IInfernalSpecialCharms.ANONYMITY_THROUGH_PROPRIETY,
                    IInfernalSpecialCharms.UNQUESTIONABLE_YOZI_AUTHORITY,
                    IInfernalSpecialCharms.SCORPION_TAILED_MIRAGE_TECHNIQUE,
                    IInfernalSpecialCharms.RUNNING_TO_FOREVER,
                    IInfernalSpecialCharms.INTOLERABLE_BURNING_TRUTHS,
                    IInfernalSpecialCharms.HARDENED_DEVIL_BODY,
                    IInfernalSpecialCharms.VIRIDIAN_LEGEND_EXOSKELETON,
                    IInfernalSpecialCharms.ANALYTIC_MODELING_TECHNIQUE,
                    IInfernalSpecialCharms.UNSHATTERED_TONGUE_PERFECTION,
                    IInfernalSpecialCharms.CONSTRUCTIVE_CONVERGANCE_OF_PRINCIPLES,
                    IInfernalSpecialCharms.ESSENCE_INFUSED_EGO_PRIMACY,
                    IInfernalSpecialCharms.COSMIC_TRANSCENDENCE,
                    IInfernalSpecialCharms.TOOL_TRANSCENDING_CONSTRUCTS});
  }
  
  @Override
  public void addCharacterTemplates(ICharacterGenerics characterGenerics) {
    registerParsedTemplate(characterGenerics, "template/Infernal2nd.template"); //$NON-NLS-1$
  }
  
  @Override
  public void addAdditionalTemplateData(ICharacterGenerics characterGenerics) {
    IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry = characterGenerics.getAdditionalModelFactoryRegistry();
    IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry = characterGenerics.getAdditionalViewFactoryRegistry();
    IRegistry<String, IAdditionalPersisterFactory> persisterFactory = characterGenerics.getAdditonalPersisterFactoryRegistry();
    registerInfernalPatron(additionalModelFactoryRegistry, additionalViewFactoryRegistry, persisterFactory);
    registerInfernalUrge(additionalModelFactoryRegistry, additionalViewFactoryRegistry, persisterFactory);
    //registerFlawedFate(additionalModelFactoryRegistry, additionalViewFactoryRegistry, persisterFactory);
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

}