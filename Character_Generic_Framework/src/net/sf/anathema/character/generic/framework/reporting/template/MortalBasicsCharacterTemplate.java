package net.sf.anathema.character.generic.framework.reporting.template;

import java.util.Map;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class MortalBasicsCharacterTemplate extends AbstractStattedCharacterReportTemplate {

  public MortalBasicsCharacterTemplate(IResources resources) {
    super(resources, "net/sf/anathema/character/reportdesigns/BasicMortalCharacterSheet"); //$NON-NLS-1$
  }

  @Override
  public void addExtendedParameterClasses(Map<String, String> parameterClassesByName) {
    // Nothing to do
  }

  @Override
  protected void fillInExtendedParameters(Map<Object, Object> parameters, IGenericCharacter character)
      throws ReportException {
    // Nothing to do
  }

  public boolean supports(IGenericCharacter character) {
    return character != null
        && character.getTemplate().getTemplateType().getCharacterType().equals(CharacterType.MORTAL)
        && character.getRules().getEdition().equals(ExaltedEdition.FirstEdition);
  }

  public IIdentificate getType() {
    return new Identificate("Sheet"); //$NON-NLS-1$
  }
}