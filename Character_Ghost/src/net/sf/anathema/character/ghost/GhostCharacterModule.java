package net.sf.anathema.character.ghost;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.magic.FirstExcellency;
import net.sf.anathema.character.generic.framework.magic.SecondExcellency;
import net.sf.anathema.character.generic.framework.magic.ThirdExcellency;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.ghost.passions.GhostPassionsModelFactory;
import net.sf.anathema.character.ghost.passions.GhostPassionsParser;
import net.sf.anathema.character.ghost.passions.GhostPassionsTemplate;
import net.sf.anathema.character.ghost.passions.GhostPassionsViewFactory;
import net.sf.anathema.character.ghost.passions.persistence.GhostPassionsPersisterFactory;
import net.sf.anathema.character.ghost.reporting.SecondEditionSpiritPartEncoder;
import net.sf.anathema.character.reporting.CharacterReportingModule;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.page.IPdfPartEncoder;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public class GhostCharacterModule extends NullObjectCharacterModuleAdapter {

  private static final int ESSENCE_MAX = EssenceTemplate.SYSTEM_ESSENCE_MAX;
  
  @Override
  public void registerCommonData(ICharacterGenerics characterGenerics)
  {
	  /*characterGenerics.getGenericCharmStatsRegistry().register(
		        CharacterType.SPIRIT,
		        new IMagicStats[] { new FirstExcellency(CharacterType.SPIRIT, ExaltedSourceBook.SecondEdition, "1 m per die"), //$NON-NLS-1$
		            new SecondExcellency(CharacterType.SPIRIT, ExaltedSourceBook.SecondEdition),
		            new ThirdExcellency(CharacterType.SPIRIT, "4 m", ExaltedSourceBook.SecondEdition), //$NON-NLS-1$
		            new InfiniteMastery(),
		            new DivineSubordination()});*/
	    characterGenerics.getAdditionalTemplateParserRegistry().register(
	            GhostPassionsTemplate.ID,
	            new GhostPassionsParser());
  }

  @Override
  public void addCharacterTemplates(ICharacterGenerics characterGenerics) {
    registerParsedTemplate(characterGenerics, "template/Ghost2nd.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/CommonGhost2nd.template"); //$NON-NLS-1$
  }

  @Override
  public void addAdditionalTemplateData(ICharacterGenerics characterGenerics) {
    IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry = characterGenerics.getAdditionalModelFactoryRegistry();
    IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry = characterGenerics.getAdditionalViewFactoryRegistry();
    IRegistry<String, IAdditionalPersisterFactory> persisterFactory = characterGenerics.getAdditonalPersisterFactoryRegistry();
    registerGhostPassions(additionalModelFactoryRegistry, additionalViewFactoryRegistry, persisterFactory);
  }
  
  private void registerGhostPassions(
		  IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry,
	      IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry,
	      IRegistry<String, IAdditionalPersisterFactory> persisterFactory)
  {
	  String templateId = GhostPassionsTemplate.ID;
	  additionalModelFactoryRegistry.register(templateId, new GhostPassionsModelFactory());  
	  additionalViewFactoryRegistry.register(templateId, new GhostPassionsViewFactory());
	  persisterFactory.register(templateId, new GhostPassionsPersisterFactory());
  }
}