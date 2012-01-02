package net.sf.anathema.character.mortal;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.reporting.CharacterReportingModule;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.pdf.layout.simple.FirstEditionMortalPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.simple.ISimplePartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.simple.SecondEditionMortalPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.simple.SimpleEncodingRegistry;
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
    CharacterReportingModuleObject moduleObject = generics.getModuleObjectMap().getModuleObject(CharacterReportingModule.class);
    SimpleEncodingRegistry registry = moduleObject.getSimpleEncodingRegistry();
    BaseFont baseFont = registry.getBaseFont();
    ISimplePartEncoder secondEditionMortalPartEncoder = new SecondEditionMortalPartEncoder(resources, baseFont);
    ISimplePartEncoder firstEditionMortalPartEncoder = new FirstEditionMortalPartEncoder(resources, baseFont, registry);
    registry.setPartEncoder(CharacterType.MORTAL, ExaltedEdition.SecondEdition, secondEditionMortalPartEncoder);
    registry.setPartEncoder(CharacterType.MORTAL, ExaltedEdition.FirstEdition, firstEditionMortalPartEncoder);
  }
}
