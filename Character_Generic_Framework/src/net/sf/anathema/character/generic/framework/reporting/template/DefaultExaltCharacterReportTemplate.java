package net.sf.anathema.character.generic.framework.reporting.template;

import java.util.Map;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.lib.resources.IResources;

public abstract class DefaultExaltCharacterReportTemplate extends AbstractMagicUserCharacterReportTemplate {

  private final CharacterType type;

  public DefaultExaltCharacterReportTemplate(CharacterType type, IResources resources, String fileBase) {
    super(resources, fileBase);
    this.type = type;
  }

  @Override
  protected void addCharacterTypeSpecificParameterClasses(Map<String, String> parameterClassesByName) {
    // Nothing to do
  }

  @Override
  protected void fillInCharacterTypeSpecificParameters(Map<Object, Object> parameters, IGenericCharacter character)
      throws ReportException {
    // Nothing to do
  }

  public boolean supports(IGenericCharacter character) {
    return character != null
        && character.getTemplate().getTemplateType().getCharacterType().equals(type)
        && character.getRules().getEdition().equals(ExaltedEdition.FirstEdition);
  }
}