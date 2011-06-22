package net.sf.anathema.character.spirit;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.magic.FirstExcellency;
import net.sf.anathema.character.generic.framework.magic.SecondExcellency;
import net.sf.anathema.character.generic.framework.magic.ThirdExcellency;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.reporting.CharacterReportingModule;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.page.IPdfPartEncoder;
import net.sf.anathema.character.spirit.generic.DivineSubordination;
import net.sf.anathema.character.spirit.generic.InfiniteMastery;
import net.sf.anathema.character.spirit.reporting.SecondEditionSpiritPartEncoder;
import net.sf.anathema.lib.resources.IResources;

public class SpiritCharacterModule extends NullObjectCharacterModuleAdapter {

  private static final int ESSENCE_MAX = EssenceTemplate.SYSTEM_ESSENCE_MAX;
  
  @Override
  public void registerCommonData(ICharacterGenerics characterGenerics)
  {
	  characterGenerics.getGenericCharmStatsRegistry().register(
		        CharacterType.SPIRIT,
		        new IMagicStats[] { new FirstExcellency(CharacterType.SPIRIT, ExaltedSourceBook.SecondEdition, "1 m per die"), //$NON-NLS-1$
		            new SecondExcellency(CharacterType.SPIRIT, ExaltedSourceBook.SecondEdition),
		            new ThirdExcellency(CharacterType.SPIRIT, "4 m", ExaltedSourceBook.SecondEdition), //$NON-NLS-1$
		            new InfiniteMastery(),
		            new DivineSubordination()});
  }

  @Override
  public void addCharacterTemplates(ICharacterGenerics characterGenerics) {
    registerParsedTemplate(characterGenerics, "template/Spirit2nd.template"); //$NON-NLS-1$
  }

  @Override
  public void addReportTemplates(ICharacterGenerics generics, IResources resources) {
    CharacterReportingModuleObject moduleObject = generics.getModuleObjectMap().getModuleObject(
        CharacterReportingModule.class);
    PdfEncodingRegistry registry = moduleObject.getPdfEncodingRegistry();
    IPdfPartEncoder secondEditionEncoder = new SecondEditionSpiritPartEncoder(resources, registry, ESSENCE_MAX);
    registry.setPartEncoder(CharacterType.SPIRIT, ExaltedEdition.SecondEdition, secondEditionEncoder);
  }
}