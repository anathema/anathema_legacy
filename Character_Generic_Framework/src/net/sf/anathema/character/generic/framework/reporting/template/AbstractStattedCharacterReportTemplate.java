package net.sf.anathema.character.generic.framework.reporting.template;

import java.util.Map;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.framework.reporting.datasource.MeritsAndFlawsDataSource;
import net.sf.anathema.character.generic.framework.reporting.parameters.AdvantageParameterUtilities;
import net.sf.anathema.character.generic.framework.reporting.parameters.CharacterParameterUtilities;
import net.sf.anathema.character.generic.framework.reporting.parameters.CombatParameterUtilities;
import net.sf.anathema.character.generic.framework.reporting.parameters.HealthParameterUtilities;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.framework.reporting.IReportDataSource;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractStattedCharacterReportTemplate extends AbstractCharacterReportTemplate {

  public AbstractStattedCharacterReportTemplate(IResources resources, String fileBase) {
    super(resources, fileBase);
  }

  public static final String MERIT_AND_FLAW_DATA_SOURCE = "meritsAndFlaws"; //$NON-NLS-1$

  @Override
  public final void addParameterClasses(Map<String, String> parameterClassesByName) {
    super.addParameterClasses(parameterClassesByName);
    AdvantageParameterUtilities.addBackgroundParameterClasses(parameterClassesByName);
    AdvantageParameterUtilities.addEssenceParameterClass(parameterClassesByName);
    AdvantageParameterUtilities.addEssencePoolParameterClasses(parameterClassesByName);
    AdvantageParameterUtilities.addWillpowerParameterClasses(parameterClassesByName);
    AdvantageParameterUtilities.addVirtueParameterClasses(parameterClassesByName);
    CharacterParameterUtilities.addAbilityParameterClasses(parameterClassesByName);
    CharacterParameterUtilities.addAttributeParameterClasses(parameterClassesByName);
    CombatParameterUtilities.addCombatStatsParameterClasses(parameterClassesByName);
    CharacterParameterUtilities.addExperienceParameterClasses(parameterClassesByName);
    CharacterParameterUtilities.addConceptParameterClasses(parameterClassesByName);
    CharacterParameterUtilities.addNatureParameterClasses(parameterClassesByName);
    CharacterParameterUtilities.addTemplateParameterClass(parameterClassesByName);
    HealthParameterUtilities.addHealthMoveParameterClasses(parameterClassesByName);
    HealthParameterUtilities.addHealthLevelParameterClasses(parameterClassesByName);
    HealthParameterUtilities.addPainToleranceParameterClasses(parameterClassesByName);
    HealthParameterUtilities.addSoakParameterClasses(parameterClassesByName);
    parameterClassesByName.put(MERIT_AND_FLAW_DATA_SOURCE, IReportDataSource.class.getName());
    addExtendedParameterClasses(parameterClassesByName);
  }

  public abstract void addExtendedParameterClasses(Map<String, String> parameterClassesByName);

  @Override
  public final void fillInParameters(
      Map<Object, Object> parameters,
      IGenericCharacter character,
      IGenericDescription description) throws ReportException {
    super.fillInParameters(parameters, character, description);
    AdvantageParameterUtilities.fillInBackgrounds(character, parameters, getResources());
    AdvantageParameterUtilities.fillInEssence(character, parameters);
    AdvantageParameterUtilities.fillInEssencePools(character, parameters);
    AdvantageParameterUtilities.fillInWillpower(character, parameters);
    AdvantageParameterUtilities.fillInVirtues(character, parameters);
    CharacterParameterUtilities.fillInAbilities(character, parameters);
    CharacterParameterUtilities.fillInAttributes(character, parameters);
    CombatParameterUtilities.fillInCombatStats(character, character.getRules(), parameters);
    CharacterParameterUtilities.fillExperienceParameters(parameters, character);
    CharacterParameterUtilities.fillInConcept(character, parameters);
    CharacterParameterUtilities.fillInNature(character, parameters);
    CharacterParameterUtilities.fillInTemplate(character, parameters, getResources());
    HealthParameterUtilities.fillInHealth(character, parameters);
    HealthParameterUtilities.fillInSoak(character.getTrait(AttributeType.Stamina).getCurrentValue(), parameters);
    parameters.put(MERIT_AND_FLAW_DATA_SOURCE, new MeritsAndFlawsDataSource());
    fillInExtendedParameters(parameters, character);
  }

  protected abstract void fillInExtendedParameters(Map<Object, Object> parameters, IGenericCharacter character)
      throws ReportException;
}