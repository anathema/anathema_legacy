package net.sf.anathema.character.generic.framework.reporting.template.voidstate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.equipment.IWeaponType;
import net.sf.anathema.character.generic.framework.reporting.IAbilityReportConstants;
import net.sf.anathema.character.generic.framework.reporting.ICharacterReportConstants;
import net.sf.anathema.character.generic.framework.reporting.datasource.MeleeWeaponDataSource;
import net.sf.anathema.character.generic.framework.reporting.parameters.CombatParameterUtilities;
import net.sf.anathema.character.generic.framework.reporting.parameters.HealthParameterUtilities;
import net.sf.anathema.character.generic.framework.reporting.template.DefaultExaltCharacterReportTemplate;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IRuleSetVisitor;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.framework.reporting.IReportDataSource;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperReport;

public class ExaltVoidstateReportTemplate extends DefaultExaltCharacterReportTemplate {

  public static final String COLLEGES_DATA_SOURCE = "collegesDataSource"; //$NON-NLS-1$
  public static final String PARAM_ABILITY_SUBREPORT = "AbilitySubreport"; //$NON-NLS-1$
  public static final String PARAM_ANIMA_SUBREPORT = "AnimaSubreport"; //$NON-NLS-1$
  public static final String PARAM_BRAWL_SUBREPORT = "BrawlSubreport"; //$NON-NLS-1$
  public static final String PARAM_DESCRIPTION_SUBREPORT = "DescriptionSubreport"; //$NON-NLS-1$
  public static final String PARAM_FLAW_SUBREPORT = "FlawSubreport"; //$NON-NLS-1$
  public static final String PARAM_SEQUENCE_SUBREPORT = "SequenceSubreport"; //$NON-NLS-1$
  public static final String RESPLENDENT_DESTINY_DATA_SOURCE = "resplendentDestinyDataSource"; //$NON-NLS-1$
  public static final String PARAM_GIFT_DATA_SOURCE = "giftDataSource"; //$NON-NLS-1$
  public static final String VIRTUE_FLAW = "virtueFlaw"; //$NON-NLS-1$
  public static final String VIRTUE_FLAW_ROOT = "virtueFlawRoot"; //$NON-NLS-1$
  public static final String VIRTUE_FLAW_CONDITION = "virtueFlawCondition"; //$NON-NLS-1$
  public static final String PARAM_CHARMPAGE_SUBREPORT = "CharmPageSubreport"; //$NON-NLS-1$
  public static final String CHARMPAGE_DATASOURCE = "charmPageDatasource"; //$NON-NLS-1$
  public static final String PARAM_HEALTH_SUBREPORT = "HealthSubreport"; //$NON-NLS-1$
  public static final String PARAM_VIRTUES_SUBREPORT = "VirtuesSubreport"; //$NON-NLS-1$
  public static final String PARAM_WILLPOWER_SUBREPORT = "WillpowerSubreport"; //$NON-NLS-1$
  public static final String PARAM_MIDDLECOLUMN_SUBREPORT = "MiddleColumnSubreport"; //$NON-NLS-1$
  public static final String PARAM_ATTRIBUTE_SUBREPORT = "AttributeSubreport"; //$NON-NLS-1$
  public static final String PARAM_LUNAR_ATTRIBUTE_SUBREPORT = "LunarAttributeSubreport"; //$NON-NLS-1$
  public static final String PARAM_LUNAR_MIDDLECOLUMN_SUBREPORT = "LunarMiddleColumnSubreport"; //$NON-NLS-1$
  public static final String PARAM_SPECIALPAGE_SUBREPORT = "SpecialPageSubreport"; //$NON-NLS-1$
  public static final String PARAM_LUNAR_WILLPOWER_SUBREPORT = "LunarWillpowerSubreport"; //$NON-NLS-1$
  public static final String PARAM_LUNAR_GIFTS_SUBREPORT = "LunarGiftsSubreport"; //$NON-NLS-1$
  public static final String PARAM_PREFIX_BEASTFORM = "Beastform"; //$NON-NLS-1$
  public static final String PARAM_COMBAT_STATS_SUBREPORT = "CombatStatsSubreport"; //$NON-NLS-1$
  public static final String PARAM_BEASTFORM_BRAWL_DATA_SOURCE = "BeastformBrawlDatasource"; //$NON-NLS-1$
  public static final String HEARTS_BLOOD_DATA_SOURCE = "HeartsBloodDataSource"; //$NON-NLS-1$
  public static final String RENOWN_DATA_SOURCE = "RenownDataSource"; //$NON-NLS-1$
  public static final String PARAM_RENOWN = "RenownTotal"; //$NON-NLS-1$
  public static final String PARAM_FACE = "Face"; //$NON-NLS-1$  

  public static void addSiderealParameterClasses(Map<String, String> parameterClassesByName) {
    parameterClassesByName.put(RESPLENDENT_DESTINY_DATA_SOURCE, IReportDataSource.class.getName());
    parameterClassesByName.put(COLLEGES_DATA_SOURCE, IReportDataSource.class.getName());
  }

  public static void addSolarParameterClasses(Map<String, String> parameterClassesByName) {
    addVirtueFlawParameterClass(parameterClassesByName);
    parameterClassesByName.put(VIRTUE_FLAW_CONDITION, String.class.getName());
  }

  public static void addVirtueFlawParameterClass(Map<String, String> parameterClassesByName) {
    parameterClassesByName.put(VIRTUE_FLAW, String.class.getName());
    parameterClassesByName.put(VIRTUE_FLAW_ROOT, String.class.getName());
  }

  private void addLunarParameterClasses(Map<String, String> parameterClassesByName) {
    addBeastformParameterClasses(parameterClassesByName);
    addLunarDescriptionParameterClasses(parameterClassesByName);
  }

  public static void addLunarDescriptionParameterClasses(Map<String, String> parameterClasses) {
    parameterClasses.put(HEARTS_BLOOD_DATA_SOURCE, IReportDataSource.class.getName());
    parameterClasses.put(RENOWN_DATA_SOURCE, IReportDataSource.class.getName());
    parameterClasses.put(PARAM_RENOWN, String.class.getName());
    parameterClasses.put(PARAM_FACE, String.class.getName());
  }

  public static String getBeastFormParameter(String parameter) {
    return PARAM_PREFIX_BEASTFORM + parameter;
  }

  public static void addBeastformParameterClasses(Map<String, String> parameterClassesByName) {
    for (AttributeType type : AttributeType.values()) {
      parameterClassesByName.put(PARAM_PREFIX_BEASTFORM + type.getId(), Integer.class.getName());
    }
    parameterClassesByName.put(
        getBeastFormParameter(HealthParameterUtilities.getSoakParameter(HealthType.Bashing)),
        String.class.getName());
    parameterClassesByName.put(
        getBeastFormParameter(HealthParameterUtilities.getSoakParameter(HealthType.Lethal)),
        String.class.getName());
    for (String combatStatsParameter : CombatParameterUtilities.combatStatsParameterNames) {
      parameterClassesByName.put(getBeastFormParameter(combatStatsParameter), Integer.class.getName());
    }
    parameterClassesByName.put(PARAM_BEASTFORM_BRAWL_DATA_SOURCE, IReportDataSource.class.getName());
    addGiftDataSource(parameterClassesByName);
  }

  public static void addGiftDataSource(Map<String, String> parameterClassesByName) {
    parameterClassesByName.put(PARAM_GIFT_DATA_SOURCE, IReportDataSource.class.getName());
  }

  public static void addMiddleColumnContentParameterClasses(Map<String, String> parameterClassesByName) {
    parameterClassesByName.put(PARAM_WILLPOWER_SUBREPORT, JasperReport.class.getName());
    parameterClassesByName.put(PARAM_LUNAR_WILLPOWER_SUBREPORT, JasperReport.class.getName());
    parameterClassesByName.put(PARAM_VIRTUES_SUBREPORT, JasperReport.class.getName());
    parameterClassesByName.put(PARAM_FLAW_SUBREPORT, JasperReport.class.getName());
    parameterClassesByName.put(PARAM_LUNAR_GIFTS_SUBREPORT, JasperReport.class.getName());
  }

  private final IVoidstateSubreports subreports;

  public ExaltVoidstateReportTemplate(CharacterType type, IResources resources) {
    this(type, resources, new DefaultVoidstateSubreports(type));
  }

  public ExaltVoidstateReportTemplate(CharacterType type, IResources resources, IVoidstateSubreports subreports) {
    super(type, resources, "net/sf/anathema/character/reportdesigns/voidstate/VoidstateCharacterSheet"); //$NON-NLS-1$
    this.subreports = subreports;
  }

  @Override
  public void addExtendedParameterClasses(Map<String, String> parameterClassesByName) {
    super.addExtendedParameterClasses(parameterClassesByName);
    parameterClassesByName.put(PARAM_ATTRIBUTE_SUBREPORT, JasperReport.class.getName());
    parameterClassesByName.put(PARAM_LUNAR_ATTRIBUTE_SUBREPORT, JasperReport.class.getName());
    parameterClassesByName.put(PARAM_ABILITY_SUBREPORT, JasperReport.class.getName());
    parameterClassesByName.put(PARAM_COMBAT_STATS_SUBREPORT, JasperReport.class.getName());
    parameterClassesByName.put(PARAM_MIDDLECOLUMN_SUBREPORT, JasperReport.class.getName());
    parameterClassesByName.put(PARAM_LUNAR_MIDDLECOLUMN_SUBREPORT, JasperReport.class.getName());
    addMiddleColumnContentParameterClasses(parameterClassesByName);
    parameterClassesByName.put(PARAM_ANIMA_SUBREPORT, JasperReport.class.getName());
    parameterClassesByName.put(PARAM_BRAWL_SUBREPORT, JasperReport.class.getName());
    parameterClassesByName.put(PARAM_HEALTH_SUBREPORT, JasperReport.class.getName());
    parameterClassesByName.put(PARAM_SEQUENCE_SUBREPORT, JasperReport.class.getName());
    parameterClassesByName.put(PARAM_DESCRIPTION_SUBREPORT, JasperReport.class.getName());
    parameterClassesByName.put(PARAM_SPECIALPAGE_SUBREPORT, JasperReport.class.getName());
    parameterClassesByName.put(ICharacterReportConstants.PRINT_SPECIAL_PAGE, Boolean.class.getName());
    parameterClassesByName.put(PARAM_CHARMPAGE_SUBREPORT, JasperReport.class.getName());
    parameterClassesByName.put(CHARMPAGE_DATASOURCE, JRDataSource.class.getName());
    parameterClassesByName.put(MELEE_WEAPON_DATA_SOURCE, IReportDataSource.class.getName());
    parameterClassesByName.put(ICharacterReportConstants.BREEDING_VALUE, Integer.class.getName());
    parameterClassesByName.put(ICharacterReportConstants.MINIMUM_DAMAGE, Integer.class.getName());
    addSiderealParameterClasses(parameterClassesByName);
    addSolarParameterClasses(parameterClassesByName);
    addLunarParameterClasses(parameterClassesByName);
  }

  @Override
  protected void fillInExtendedParameters(final Map<Object, Object> parameters, final IGenericCharacter character)
      throws ReportException {
    super.fillInExtendedParameters(parameters, character);
    addSubreportParameter(parameters, PARAM_ATTRIBUTE_SUBREPORT, subreports.loadAttributeSubreport());
    addSubreportParameter(parameters, PARAM_ABILITY_SUBREPORT, subreports.loadAbilitySubreport());
    addSubreportParameter(
        parameters,
        IAbilityReportConstants.SUBREPORT_ABILITY_SET,
        subreports.loadAbilitySetSubreport());
    addSubreportParameter(
        parameters,
        IAbilityReportConstants.SUBREPORT_FIVE_ABILITY_GROUP,
        subreports.loadFiveAbilityGroupSubreport());
    addSubreportParameter(
        parameters,
        IAbilityReportConstants.SUBREPORT_TEN_ABILITY_GROUP,
        subreports.loadTenAbilityGroupSubreport());
    addSubreportParameter(
        parameters,
        IAbilityReportConstants.SUBREPORT_SINGLE_ABILITY,
        subreports.loadSingleAbilitySubreport());
    addSubreportParameter(parameters, PARAM_ABILITY_SUBREPORT, subreports.loadAbilitySubreport());
    addSubreportParameter(parameters, PARAM_ABILITY_SUBREPORT, subreports.loadAbilitySubreport());
    addSubreportParameter(parameters, PARAM_COMBAT_STATS_SUBREPORT, subreports.loadCombatStatsSubreport());
    addSubreportParameter(parameters, PARAM_MIDDLECOLUMN_SUBREPORT, subreports.loadMiddleColumnSubreport());
    addSubreportParameter(parameters, PARAM_WILLPOWER_SUBREPORT, subreports.loadWillpowerSubreport());
    addSubreportParameter(parameters, PARAM_VIRTUES_SUBREPORT, subreports.loadVirtuesSubreport());
    addSubreportParameter(parameters, PARAM_FLAW_SUBREPORT, subreports.loadFlawSubreport());
    addSubreportParameter(parameters, PARAM_ANIMA_SUBREPORT, subreports.loadAnimaSubreport());
    addSubreportParameter(parameters, PARAM_BRAWL_SUBREPORT, subreports.loadBrawlSubreport());
    addSubreportParameter(parameters, PARAM_HEALTH_SUBREPORT, subreports.loadHealthSubreport());
    addSubreportParameter(parameters, PARAM_SEQUENCE_SUBREPORT, subreports.loadSequenceSubreport());
    addSubreportParameter(parameters, PARAM_DESCRIPTION_SUBREPORT, subreports.loadDescriptionSubreport());
    addSubreportParameter(parameters, PARAM_SPECIALPAGE_SUBREPORT, subreports.loadSpecialpageSubreport());
    parameters.put(ICharacterReportConstants.PRINT_SPECIAL_PAGE, getSpecialPageParameter());
    addSubreportParameter(parameters, PARAM_CHARMPAGE_SUBREPORT, subreports.loadCharmpageSubreport());
    final List<IWeaponType> weapons = new ArrayList<IWeaponType>();
    character.getRules().accept(new IRuleSetVisitor() {
      public void visitCoreRules(IExaltedRuleSet set) {
        subreports.buildCoreRulesBrawlWeaponList(weapons);
      }

      public void visitPowerCombat(IExaltedRuleSet set) {
        subreports.buildPowerCombatBrawlWeaponList(weapons);
      }
    });
    parameters.put(MELEE_WEAPON_DATA_SOURCE, new MeleeWeaponDataSource(
        getResources(),
        character,
        character.getRules(),
        weapons.toArray(new IWeaponType[weapons.size()])));
  }

  protected Boolean getSpecialPageParameter() {
    return Boolean.FALSE;
  }

  public IIdentificate getType() {
    return new Identificate("VoidState"); //$NON-NLS-1$
  }
}