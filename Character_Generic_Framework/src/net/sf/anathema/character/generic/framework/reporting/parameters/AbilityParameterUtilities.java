package net.sf.anathema.character.generic.framework.reporting.parameters;

import java.util.Map;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.framework.reporting.IAbilityReportConstants;
import net.sf.anathema.character.generic.framework.reporting.ICharacterReportConstants;
import net.sf.anathema.character.generic.framework.reporting.datasource.AbilitySetDataSource;
import net.sf.anathema.character.generic.framework.reporting.datasource.SpecialtiesDataSource;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.framework.reporting.IReportDataSource;
import net.sf.anathema.lib.resources.IResources;
import net.sf.jasperreports.engine.JasperReport;

public class AbilityParameterUtilities {

  public static void addAbilityParameterClasses(Map<String, String> parameterClassesByName) {
    addClassicAbilityParameterClasses(parameterClassesByName);
    addRevisedAbilityParameterClasses(parameterClassesByName);
  }

  public static void addRevisedAbilityParameterClasses(Map<String, String> parameterClassesByName) {
    parameterClassesByName.put(IAbilityReportConstants.PARAM_ABILITYSET_DATASOURCE, IReportDataSource.class.getName());
    parameterClassesByName.put(IAbilityReportConstants.SUBREPORT_ABILITY_SET, JasperReport.class.getName());
    parameterClassesByName.put(IAbilityReportConstants.SUBREPORT_FIVE_ABILITY_GROUP, JasperReport.class.getName());
    parameterClassesByName.put(IAbilityReportConstants.SUBREPORT_TEN_ABILITY_GROUP, JasperReport.class.getName());
    parameterClassesByName.put(IAbilityReportConstants.SUBREPORT_SINGLE_ABILITY, JasperReport.class.getName());
  }

  public static void addClassicAbilityParameterClasses(Map<String, String> parameterClassesByName) {
    for (AbilityType abilityType : AbilityType.values()) {
      parameterClassesByName.put(abilityType.getId(), Integer.class.getName());
      parameterClassesByName.put(abilityType.getId() + "_boolean", Boolean.class.getName()); //$NON-NLS-1$
    }
    parameterClassesByName.put(ICharacterReportConstants.SPECIALTIES_DATA_SOURCE, IReportDataSource.class.getName());
  }

  public final static void fillInAbilities(
      IGenericCharacter character,
      Map<Object, Object> parameters,
      IResources resources) {
    fillInClassicAbilities(character, parameters);
    fillInRevisedAbilities(character, parameters, resources);
  }

  private static void fillInRevisedAbilities(
      IGenericCharacter character,
      Map<Object, Object> parameters,
      IResources resources) {
    parameters.put(IAbilityReportConstants.PARAM_ABILITYSET_DATASOURCE, new AbilitySetDataSource(resources, character));
  }

  private static void fillInClassicAbilities(IGenericCharacter character, Map<Object, Object> parameters) {
    for (AbilityType abilityType : AbilityType.getAbilityTypes(character.getRules().getEdition())) {
      CharacterParameterUtilities.fillInTrait(character.getTraitCollection().getTrait(abilityType), parameters);
      Boolean marked = character.getTraitCollection().isFavoredOrCasteTrait(abilityType);
      parameters.put(abilityType.getId() + "_boolean", marked); //$NON-NLS-1$
    }
    parameters.put(ICharacterReportConstants.SPECIALTIES_DATA_SOURCE, new SpecialtiesDataSource(character));
  }

}