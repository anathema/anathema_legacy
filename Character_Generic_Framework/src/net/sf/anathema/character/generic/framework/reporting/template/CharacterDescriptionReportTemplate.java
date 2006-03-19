package net.sf.anathema.character.generic.framework.reporting.template;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class CharacterDescriptionReportTemplate extends AbstractCharacterReportTemplate {

  public CharacterDescriptionReportTemplate(IResources resources) {
    super(resources, "net/sf/anathema/character/reportdesigns/CharacterDescription"); //$NON-NLS-1$
  }

  public boolean supports(IGenericCharacter character) {
    return true;
  }

  public IIdentificate getType() {
    return new Identificate("Description"); //$NON-NLS-1$
  }
}