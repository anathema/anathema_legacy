package net.sf.anathema.character.generic.framework.reporting.parameters;

import java.util.Map;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.framework.reporting.ICharacterReportConstants;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.lib.resources.IResources;

public class CharacterParameterUtilities {

  public final static void fillInTrait(IGenericTrait trait, Map<Object, Object> parameters) {
    parameters.put(trait.getType().getId(), new Integer(trait.getCurrentValue()));
  }

  private CharacterParameterUtilities() {
    throw new UnreachableCodeReachedException();
  }

  public static void addAttributeParameterClasses(Map<String, String> parameterClassesByName) {
    for (AttributeType attributeType : AttributeType.values()) {
      parameterClassesByName.put(attributeType.getId(), Integer.class.getName());
      parameterClassesByName.put(attributeType.getId() + "_boolean", Boolean.class.getName()); //$NON-NLS-1$
    }
  }

  public static final void fillInAttributes(IGenericCharacter character, Map<Object, Object> parameters) {
    for (AttributeType type : AttributeType.values()) {
      CharacterParameterUtilities.fillInTrait(character.getTraitCollection().getTrait(type), parameters);
    }
  }

  public static void addExperienceParameterClasses(Map<String, String> parameterClassesByName) {
    parameterClassesByName.put(ICharacterReportConstants.EXPERIENCE_TOTAL, Integer.class.getName());
    parameterClassesByName.put(ICharacterReportConstants.EXPERIENCE_SPENT, Integer.class.getName());
    parameterClassesByName.put(ICharacterReportConstants.EXPERIENCE_REST, Integer.class.getName());
  }

  public static void fillExperienceParameters(Map<Object, Object> parameters, IGenericCharacter abstraction) {
    int totalXP = abstraction.getCharacterPoints().getExperiencePointsTotal();
    parameters.put(ICharacterReportConstants.EXPERIENCE_TOTAL, totalXP);
    int spentXP = abstraction.getCharacterPoints().getExperiencePointsSpent();
    parameters.put(ICharacterReportConstants.EXPERIENCE_SPENT, spentXP);
    parameters.put(ICharacterReportConstants.EXPERIENCE_REST, totalXP - spentXP);
  }

  public static void addCharacterDescriptionParameterClasses(Map<String, String> parameterClassesByName) {
    parameterClassesByName.put(ICharacterReportConstants.CHARACTER_NAME, String.class.getName());
    parameterClassesByName.put(ICharacterReportConstants.PERIPHRASE, String.class.getName());
    parameterClassesByName.put(ICharacterReportConstants.CHARACTERIZATION, String.class.getName());
    parameterClassesByName.put(ICharacterReportConstants.PHYSICAL_APPEARANCE, String.class.getName());
  }

  public static void addConceptParameterClasses(Map<String, String> parameterClassesByName) {
    parameterClassesByName.put(ICharacterReportConstants.CONCEPT, String.class.getName());
  }

  public static void addNatureParameterClasses(Map<String, String> parameterClassesByName) {
    parameterClassesByName.put(ICharacterReportConstants.NATURE, String.class.getName());
    parameterClassesByName.put(ICharacterReportConstants.NATURE_WILLPOWER_CONDITION, String.class.getName());
  }

  public static void addTemplateParameterClass(Map<String, String> parameterClassesByName) {
    parameterClassesByName.put(ICharacterReportConstants.CHARACTER_TYPE, String.class.getName());
    parameterClassesByName.put(ICharacterReportConstants.RULESET, String.class.getName());
  }

  public static final void fillInTemplate(
      IGenericCharacter character,
      Map<Object, Object> parameters,
      IResources resources) {
    parameters.put(ICharacterReportConstants.CHARACTER_TYPE, character.getTemplate()
        .getTemplateType()
        .getCharacterType()
        .getId());
    String ruleString = resources.getString("Ruleset." + character.getRules().getId()); //$NON-NLS-1$
    parameters.put(ICharacterReportConstants.RULESET, ruleString);
  }

  public static final void fillInConcept(IGenericCharacter character, Map<Object, Object> parameters) {
    parameters.put(ICharacterReportConstants.CONCEPT, character.getConcept().getConceptText());
  }

  public static final void fillInNature(
      IGenericCharacter character,
      Map<Object, Object> parameters,
      IResources resources) {
    final String conceptName = character.getConcept().getWillpowerRegainingConceptName();
    String natureName = null;
    String condition = null;
    if (conceptName != null) {
      natureName = resources.getString("Nature." //$NON-NLS-1$
          + conceptName
          + ".Name"); //$NON-NLS-1$
      condition = resources.getString("Nature." //$NON-NLS-1$
          + conceptName
          + ".Text"); //$NON-NLS-1$
    }
    parameters.put(ICharacterReportConstants.NATURE, natureName);
    parameters.put(ICharacterReportConstants.NATURE_WILLPOWER_CONDITION, condition);
  }

  public static final void fillInCharacterDescription(IGenericDescription description, Map<Object, Object> parameters) {
    parameters.put(ICharacterReportConstants.CHARACTER_NAME, description.getName());
    parameters.put(ICharacterReportConstants.PERIPHRASE, description.getPeriphrase());
    parameters.put(ICharacterReportConstants.CHARACTERIZATION, description.getCharacterization());
    parameters.put(ICharacterReportConstants.PHYSICAL_APPEARANCE, description.getPhysicalAppearance());
  }
}