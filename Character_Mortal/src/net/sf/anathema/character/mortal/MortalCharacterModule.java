package net.sf.anathema.character.mortal;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.generic.framework.reporting.template.CharacterDescriptionReportTemplate;
import net.sf.anathema.character.generic.framework.reporting.template.MortalBasicsCharacterTemplate;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.reporting.CharacterReportingModule;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.second.part.SecondEditionMortalPartEncoder;
import net.sf.anathema.lib.resources.IResources;

public class MortalCharacterModule extends NullObjectCharacterModuleAdapter {

  @Override
  public void addCharacterTemplates(ICharacterGenerics characterGenerics) {
    registerParsedTemplate(characterGenerics, "HeroicMortal.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "HeroicMortal2nd.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "Mortal.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "Mortal2nd.template"); //$NON-NLS-1$
  }

  @Override
  public void addReportTemplates(ICharacterGenerics generics, IResources resources) {
    CharacterReportingModuleObject moduleObject = generics.getModuleObjectMap().getModuleObject(
        CharacterReportingModule.class);
    PdfEncodingRegistry registry = moduleObject.getPdfEncodingRegistry();
    SecondEditionMortalPartEncoder secondEdtionMortalPartEncoder = new SecondEditionMortalPartEncoder(
        resources,
        registry.getBaseFont());
    registry.setPartEncoder(CharacterType.MORTAL, ExaltedEdition.SecondEdition, secondEdtionMortalPartEncoder);
    generics.getReportTemplateRegistry().add(new CharacterDescriptionReportTemplate(resources));
    generics.getReportTemplateRegistry().add(new MortalBasicsCharacterTemplate(resources));
  }
}