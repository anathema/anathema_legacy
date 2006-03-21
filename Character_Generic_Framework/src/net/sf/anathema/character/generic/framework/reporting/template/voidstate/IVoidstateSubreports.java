package net.sf.anathema.character.generic.framework.reporting.template.voidstate;

import java.io.InputStream;
import java.util.List;

import net.sf.anathema.character.generic.equipment.IWeaponType;

public interface IVoidstateSubreports {

  public InputStream loadAbilitySubreport();

  public InputStream loadAbilitySetSubreport();

  public InputStream loadFiveAbilityGroupSubreport();

  public InputStream loadTenAbilityGroupSubreport();

  public InputStream loadSingleAbilitySubreport();

  public InputStream loadFlawSubreport();

  public InputStream loadAnimaSubreport();

  public InputStream loadBrawlSubreport();

  public InputStream loadSequenceSubreport();

  public InputStream loadDescriptionSubreport();

  public void buildPowerCombatBrawlWeaponList(List<IWeaponType> weapons);

  public void buildCoreRulesBrawlWeaponList(List<IWeaponType> weapons);

  public InputStream loadCharmpageSubreport();

  public InputStream loadHealthSubreport();

  public InputStream loadVirtuesSubreport();

  public InputStream loadWillpowerSubreport();

  public InputStream loadMiddleColumnSubreport();

  public InputStream loadAttributeSubreport();

  public InputStream loadSpecialpageSubreport();

  public InputStream loadCombatStatsSubreport();
}