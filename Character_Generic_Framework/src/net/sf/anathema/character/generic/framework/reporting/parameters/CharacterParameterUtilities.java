package net.sf.anathema.character.generic.framework.reporting.parameters;

import java.util.Map;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.framework.reporting.ICharacterReportConstants;
import net.sf.anathema.character.generic.framework.reporting.datasource.SpecialtiesDataSource;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IRuleSetVisitor;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.framework.reporting.IReportDataSource;

public class CharacterParameterUtilities {

  public static void addAbilityParameterClasses(Map<String, String> parameterClassesByName) {
    for (AbilityType abilityType : AbilityType.values()) {
      parameterClassesByName.put(abilityType.getId(), Integer.class.getName());
      parameterClassesByName.put(abilityType.getId() + "_boolean", Boolean.class.getName()); //$NON-NLS-1$
    }
    parameterClassesByName.put(ICharacterReportConstants.SPECIALTIES_DATA_SOURCE, IReportDataSource.class.getName());
  }

  public final static void fillInAbilities(IGenericCharacter character, Map<Object, Object> parameters) {
    for (AbilityType abilityType : AbilityType.values()) {
      CharacterParameterUtilities.fillInTrait(character.getTrait(abilityType), parameters);
      Boolean marked = character.isFavoredOrCasteTrait(abilityType);
      parameters.put(abilityType.getId() + "_boolean", marked); //$NON-NLS-1$
    }
    parameters.put(ICharacterReportConstants.SPECIALTIES_DATA_SOURCE, new SpecialtiesDataSource(character));
  }

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
      CharacterParameterUtilities.fillInTrait(character.getTrait(type), parameters);
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

  public static final void fillInTemplate(IGenericCharacter character, Map<Object, Object> parameters) {
    parameters.put(ICharacterReportConstants.CHARACTER_TYPE, character.getTemplate()
        .getTemplateType()
        .getCharacterType()
        .getId());
    final String[] ruleString = new String[1];
    character.getRules().accept(new IRuleSetVisitor() {
      public void visitCoreRules(IExaltedRuleSet set) {
        ruleString[0] = "1st Edition / Core Rules";
      }

      public void visitPowerCombat(IExaltedRuleSet set) {
        ruleString[0] = "1st Edition / Power Combat";
      }
    });
    parameters.put(ICharacterReportConstants.RULESET, ruleString[0]);
  }

  public static final void fillInConcept(IGenericCharacter character, Map<Object, Object> parameters) {
    parameters.put(ICharacterReportConstants.CONCEPT, character.getConcept().getConceptText());
  }

  public static final void fillInNature(IGenericCharacter character, Map<Object, Object> parameters) {
    parameters.put(ICharacterReportConstants.NATURE, character.getConcept().getNatureName());
    parameters.put(ICharacterReportConstants.NATURE_WILLPOWER_CONDITION, character.getConcept().getWillpowerCondition());
  }

  public static final void fillInCharacterDescription(IGenericDescription description, Map<Object, Object> parameters) {
    parameters.put(ICharacterReportConstants.CHARACTER_NAME, description.getName());
    parameters.put(ICharacterReportConstants.PERIPHRASE, description.getPeriphrase());
    parameters.put(ICharacterReportConstants.CHARACTERIZATION, description.getCharacterization());
    parameters.put(ICharacterReportConstants.PHYSICAL_APPEARANCE, description.getPhysicalAppearance());
  }
}