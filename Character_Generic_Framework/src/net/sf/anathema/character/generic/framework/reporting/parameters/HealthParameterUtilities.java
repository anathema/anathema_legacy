package net.sf.anathema.character.generic.framework.reporting.parameters;

import java.util.Map;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.framework.reporting.ICharacterReportConstants;
import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.health.IHealthTypeVisitor;
import net.sf.anathema.character.generic.traits.types.AttributeType;

public class HealthParameterUtilities {

  private HealthParameterUtilities() {
    throw new UnreachableCodeReachedException();
  }

  public static void addHealthMoveParameterClasses(Map<String, String> parameterClassesByName) {
    parameterClassesByName.put(ICharacterReportConstants.MOVE_AT_HEALTH_0, Integer.class.getName());
    parameterClassesByName.put(ICharacterReportConstants.MOVE_AT_HEALTH_1, Integer.class.getName());
    parameterClassesByName.put(ICharacterReportConstants.MOVE_AT_HEALTH_2, Integer.class.getName());
    parameterClassesByName.put(ICharacterReportConstants.MOVE_AT_HEALTH_4, Integer.class.getName());
  }

  public static void addSoakParameterClasses(Map<String, String> parameterClassesByName) {
    for (HealthType soakType : HealthType.values()) {
      parameterClassesByName.put(getSoakParameter(soakType), String.class.getName());
    }
  }

  public static String getSoakParameter(HealthType type) {
    return type + ICharacterReportConstants.SOAK;
  }

  public static void fillInSoak(int staminaValue, Map<Object, Object> parameters) {
    for (HealthType type : HealthType.values()) {
      parameters.put(getSoakParameter(type), String.valueOf(getSoakValue(type, staminaValue)));
    }
  }

  public static int getSoakValue(HealthType healthType, final int staminaValue) {
    final int[] soak = new int[1];
    healthType.accept(new IHealthTypeVisitor() {
      public void visitBashing(HealthType type) {
        soak[0] = staminaValue;
      }

      public void visitLethal(HealthType type) {
        soak[0] = (int) Math.floor(staminaValue / 2);
      }

      public void visitAggravated(HealthType type) {
        soak[0] = 0;
      }
    });
    return soak[0];
  }

  public static final void fillInHealth(IGenericCharacter character, Map<Object, Object> parameters) {
    for (HealthLevelType type : HealthLevelType.values()) {
      parameters.put(type.getId(), new Integer(character.getHealthLevelTypeCount(type)));
    }
    int dexterityValue = character.getTraitCollection().getTrait(AttributeType.Dexterity).getCurrentValue();
    parameters.put(ICharacterReportConstants.MOVE_AT_HEALTH_0, dexterityValue + 12);
    parameters.put(ICharacterReportConstants.MOVE_AT_HEALTH_1, dexterityValue + 8);
    parameters.put(ICharacterReportConstants.MOVE_AT_HEALTH_2, dexterityValue + 4);
    parameters.put(ICharacterReportConstants.MOVE_AT_HEALTH_4, dexterityValue);
    int painTolerance = character.getPainTolerance();
    parameters.put(ICharacterReportConstants.PAIN_TOLERANCE_1, Math.max(0, 1 - painTolerance));
    parameters.put(ICharacterReportConstants.PAIN_TOLERANCE_2, Math.max(0, 2 - painTolerance));
    parameters.put(ICharacterReportConstants.PAIN_TOLERANCE_4, Math.max(0, 4 - painTolerance));
  }

  public static void addHealthLevelParameterClasses(Map<String, String> parameterClassesByName) {
    for (HealthLevelType healthType : HealthLevelType.values()) {
      parameterClassesByName.put(healthType.getId(), Integer.class.getName());
    }
  }

  public static void addPainToleranceParameterClasses(Map<String, String> parameterClassesByName) {
    parameterClassesByName.put(ICharacterReportConstants.PAIN_TOLERANCE_1, Integer.class.getName());
    parameterClassesByName.put(ICharacterReportConstants.PAIN_TOLERANCE_2, Integer.class.getName());
    parameterClassesByName.put(ICharacterReportConstants.PAIN_TOLERANCE_4, Integer.class.getName());
  }
}