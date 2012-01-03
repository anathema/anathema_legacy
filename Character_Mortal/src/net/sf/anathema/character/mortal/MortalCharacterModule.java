package net.sf.anathema.character.mortal;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.reporting.CharacterReportingModule;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.pdf.layout.extended.Extended1stEditionMortalPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.Extended2ndEditionMortalPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedEncodingRegistry;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.generic.impl.rules.ExaltedEdition.FirstEdition;
import static net.sf.anathema.character.generic.impl.rules.ExaltedEdition.SecondEdition;
import static net.sf.anathema.character.generic.type.CharacterType.MORTAL;

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
    registerExtendedParts(resources, moduleObject.getExtendedEncodingRegistry());
  }

  private void registerExtendedParts(IResources resources, ExtendedEncodingRegistry registry) {
    BaseFont baseFont = registry.getBaseFont();
    registry.setPartEncoder(MORTAL, SecondEdition, new Extended2ndEditionMortalPartEncoder(resources, baseFont));
    registry.setPartEncoder(MORTAL, FirstEdition, new Extended1stEditionMortalPartEncoder(resources, baseFont, registry));
  }
}
