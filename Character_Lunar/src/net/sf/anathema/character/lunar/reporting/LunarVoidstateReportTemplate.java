package net.sf.anathema.character.lunar.reporting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.IWeaponType;
import net.sf.anathema.character.generic.framework.reporting.ICharacterReportConstants;
import net.sf.anathema.character.generic.framework.reporting.datasource.MeleeWeaponDataSource;
import net.sf.anathema.character.generic.framework.reporting.parameters.HealthParameterUtilities;
import net.sf.anathema.character.generic.framework.reporting.template.voidstate.ExaltVoidstateReportTemplate;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.impl.CombatStatisticsConfiguration;
import net.sf.anathema.character.generic.impl.equipment.IEquippedWeapon;
import net.sf.anathema.character.generic.impl.equipment.WeaponStatisticsCalculator;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.traits.IFavorableGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.library.virtueflaw.model.IVirtueFlaw;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawModel;
import net.sf.anathema.character.lunar.beastform.BeastformTemplate;
import net.sf.anathema.character.lunar.beastform.model.BeastformModel;
import net.sf.anathema.character.lunar.beastform.model.gift.IGiftModel;
import net.sf.anathema.character.lunar.heartsblood.HeartsBloodTemplate;
import net.sf.anathema.character.lunar.heartsblood.presenter.IHeartsBloodModel;
import net.sf.anathema.character.lunar.renown.RenownTemplate;
import net.sf.anathema.character.lunar.renown.presenter.IRenownModel;
import net.sf.anathema.character.lunar.virtueflaw.LunarVirtueFlawTemplate;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.lib.resources.IResources;

public class LunarVoidstateReportTemplate extends ExaltVoidstateReportTemplate {

  private static final LunarVoidstateSubreports subreports = new LunarVoidstateSubreports();
  private final IResources resources;

  public LunarVoidstateReportTemplate(IResources resources) {
    super(CharacterType.LUNAR, resources, subreports);
    this.resources = resources;
  }

  @Override
  protected Boolean getSpecialPageParameter() {
    return Boolean.TRUE;
  }

  @Override
  protected void fillInCharacterTypeSpecificParameters(Map<Object, Object> parameters, IGenericCharacter character)
      throws ReportException {
    addSubreportParameter(parameters, PARAM_LUNAR_ATTRIBUTE_SUBREPORT, subreports.loadLunarAttributeSubreport());
    addSubreportParameter(parameters, PARAM_LUNAR_MIDDLECOLUMN_SUBREPORT, subreports.loadLunarMiddleColumnSubreport());
    addSubreportParameter(parameters, PARAM_LUNAR_WILLPOWER_SUBREPORT, subreports.loadLunarWillpowerSubreport());
    addSubreportParameter(parameters, PARAM_LUNAR_GIFTS_SUBREPORT, subreports.loadLunarGiftsSubreport());
    BeastformModel beastformModel = (BeastformModel) character.getAdditionalModel(BeastformTemplate.TEMPLATE_ID);
    fillInBeastformParameters(parameters, character, beastformModel);
    fillInAttributeFavorization(parameters, character);
    fillInDescription(parameters, character);
    fillInVirtueFlaw(parameters, character);
  }

  private void fillInAttributeFavorization(Map<Object, Object> parameters, IGenericCharacter character) {
    for (AttributeType type : AttributeType.values()) {
      parameters.put(type.getId() + "_boolean", ((IFavorableGenericTrait) character.getTrait(type)).isCasteOrFavored()); //$NON-NLS-1$         
    }
  }

  private void fillInBeastformParameters(
      Map<Object, Object> parameters,
      IGenericCharacter character,
      BeastformModel beastformModel) {
    IGenericTraitCollection traitCollection = beastformModel.getTraitCollection();
    fillInBeastformAttributes(parameters, traitCollection);
    fillInSoak(parameters, beastformModel);
    fillInGifts(parameters, beastformModel);
    IExaltedRuleSet rules = character.getRules();
    fillInCombatStats(parameters, traitCollection, rules);
    fillInBeastformBrawlWeapons(parameters, traitCollection, rules, beastformModel.getGiftModel());
  }

  private void fillInVirtueFlaw(Map<Object, Object> parameters, IGenericCharacter character) {
    IVirtueFlaw virtueFlaw = ((IVirtueFlawModel) character.getAdditionalModel(LunarVirtueFlawTemplate.TEMPLATE_ID)).getVirtueFlaw();
    parameters.put(ExaltVoidstateReportTemplate.VIRTUE_FLAW, virtueFlaw.getName().getText());
    String virtueString;
    ITraitType root = virtueFlaw.getRoot();
    if (root == null) {
      virtueString = resources.getString("Lunar.VirtueFlaw.Reporting.HighestVirtue"); //$NON-NLS-1$
    }
    else {
      virtueString = resources.getString(root.getId());
    }
    parameters.put(ExaltVoidstateReportTemplate.VIRTUE_FLAW_ROOT, virtueString);
  }

  private void fillInDescription(Map<Object, Object> parameters, IGenericCharacter character) {
    IHeartsBloodModel heartsBloodModel = (IHeartsBloodModel) character.getAdditionalModel(HeartsBloodTemplate.TEMPLATE_ID);
    parameters.put(HEARTS_BLOOD_DATA_SOURCE, new HeartsBloodDataSource(heartsBloodModel));
    IRenownModel renownModel = (IRenownModel) character.getAdditionalModel(RenownTemplate.TEMPLATE_ID);
    parameters.put(RENOWN_DATA_SOURCE, new RenownDataSource(resources, renownModel));
    parameters.put(PARAM_RENOWN, String.valueOf(renownModel.calculateTotalRenown()));
    int faceRank = renownModel.getFace().getCurrentValue();
    parameters.put(PARAM_FACE, faceRank + ": " + resources.getString("Lunar.Renown.Rank." + faceRank)); //$NON-NLS-1$ //$NON-NLS-2$
  }

  private void fillInBeastformBrawlWeapons(
      Map<Object, Object> parameters,
      IGenericTraitCollection traitCollection,
      IExaltedRuleSet rules,
      final IGiftModel model) {
    IWeaponType[] brawlWeaponList = new BeastformBrawlEquipmentConfiguration(model, rules).getBrawlWeaponList();
    WeaponStatisticsCalculator weaponStatisticsCalculator = new WeaponStatisticsCalculator(traitCollection, rules, true);
    final List<IEquippedWeapon> weapons = new ArrayList<IEquippedWeapon>();
    for (IWeaponType weapon : brawlWeaponList) {
      IEquippedWeapon[] finalWeaponStatistics = weaponStatisticsCalculator.calculateWeaponStatistics(weapon);
      Collections.addAll(weapons, finalWeaponStatistics);
    }
    parameters.put(PARAM_BEASTFORM_BRAWL_DATA_SOURCE, new MeleeWeaponDataSource(
        getResources(),
        weapons.toArray(new IEquippedWeapon[weapons.size()])));
  }

  private void fillInCombatStats(
      Map<Object, Object> parameters,
      IGenericTraitCollection traitCollection,
      IExaltedRuleSet rules) {
    CombatStatisticsConfiguration configuration = new CombatStatisticsConfiguration(traitCollection, rules, true);
    parameters.put(getBeastFormParameter(ICharacterReportConstants.INTITIATIVE), configuration.getBaseInitiative());
    parameters.put(getBeastFormParameter(ICharacterReportConstants.DODGE), configuration.getDodgePool());
    parameters.put(getBeastFormParameter(ICharacterReportConstants.KNOCKDOWN), configuration.getKnockdownResistance());
    parameters.put(getBeastFormParameter(ICharacterReportConstants.STUN_THRESHOLD), configuration.getStunThreshold());
    parameters.put(getBeastFormParameter(ICharacterReportConstants.STUN_DURATION), configuration.getStunDuration());
  }

  private void fillInGifts(Map<Object, Object> parameters, BeastformModel beastformModel) {
    parameters.put(PARAM_GIFT_DATA_SOURCE, new BeastformGiftDatasource(beastformModel.getGiftModel(), resources));
  }

  private void fillInSoak(Map<Object, Object> parameters, BeastformModel beastformModel) {
    addSoakParameter(parameters, beastformModel, HealthType.Bashing);
    addSoakParameter(parameters, beastformModel, HealthType.Lethal);
  }

  private void fillInBeastformAttributes(Map<Object, Object> parameters, IGenericTraitCollection traitCollection) {
    for (AttributeType type : AttributeType.values()) {
      parameters.put(getBeastFormParameter(type.getId()), traitCollection.getTrait(type).getCurrentValue());
    }
  }

  private void addSoakParameter(Map<Object, Object> parameters, BeastformModel beastformModel, HealthType healthType) {
    parameters.put(
        getBeastFormParameter(HealthParameterUtilities.getSoakParameter(healthType)),
        String.valueOf(beastformModel.getCurrentSoakValue(healthType) + "(" //$NON-NLS-1$
            + String.valueOf(beastformModel.getHardnessValue(healthType))
            + ")")); //$NON-NLS-1$
  }
}