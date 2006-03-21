package net.sf.anathema.character.lunar.reporting;

import java.io.InputStream;
import java.util.List;

import net.sf.anathema.character.generic.equipment.IWeaponType;
import net.sf.anathema.character.generic.framework.reporting.template.voidstate.DefaultVoidstateSubreports;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.impl.equipment.MeleeWeaponType;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.lunar.beastform.model.gift.BrawlWeaponProvidingGift;
import net.sf.anathema.character.lunar.beastform.model.gift.GiftVisitorAdapter;
import net.sf.anathema.character.lunar.beastform.model.gift.IGift;
import net.sf.anathema.character.lunar.beastform.model.gift.IGiftModel;

public class LunarVoidstateSubreports extends DefaultVoidstateSubreports {

  public LunarVoidstateSubreports() {
    super(CharacterType.LUNAR);
  }

  @Override
  public InputStream loadHealthSubreport() {
    String descriptionSubreportResourceLocation = FILE_BASE + "VoidstateLunarHealthSubreport.jasper"; //$NON-NLS-1$
    return loadSystemResource(descriptionSubreportResourceLocation);
  }

  @Override
  public InputStream loadDescriptionSubreport() {
    String descriptionSubreportResourceLocation = FILE_BASE + "VoidstateLunarDescriptionSubreport.jasper"; //$NON-NLS-1$;
    return loadSystemResource(descriptionSubreportResourceLocation);
  }

  @Override
  public InputStream loadSpecialpageSubreport() {
    String beastmanSubreportResourceLocation = FILE_BASE + "VoidstateBeastformPage.jasper"; //$NON-NLS-1$;
    return loadSystemResource(beastmanSubreportResourceLocation);
  }

  @Override
  public InputStream loadAttributeSubreport() {
    String abilitySubreportResourceLocation = FILE_BASE + "VoidstateLunarAttributeSubreport.jasper"; //$NON-NLS-1$
    return loadSystemResource(abilitySubreportResourceLocation);
  }

  @Override
  public InputStream loadAbilitySetSubreport() {
    String abilitySubreportResourceLocation = FILE_BASE + "VoidstateThreeGroupAbilitySetSubreport.jasper"; //$NON-NLS-1$
    return loadSystemResource(abilitySubreportResourceLocation);
  }

  public InputStream loadLunarAttributeSubreport() {
    String subreportResourceLocation = FILE_BASE + "VoidstateBeastformAttributeSubreport.jasper"; //$NON-NLS-1$;
    return loadSystemResource(subreportResourceLocation);
  }

  public InputStream loadLunarMiddleColumnSubreport() {
    String subreportResourceLocation = FILE_BASE + "BeastformMiddleColumnSubreport.jasper"; //$NON-NLS-1$;
    return loadSystemResource(subreportResourceLocation);
  }

  public InputStream loadLunarWillpowerSubreport() {
    String subreportResourceLocation = FILE_BASE + "VoidstateBeastformWillpowerSubreport.jasper"; //$NON-NLS-1$;
    return loadSystemResource(subreportResourceLocation);
  }

  public InputStream loadLunarGiftsSubreport() {
    String subreportResourceLocation = FILE_BASE + "VoidstateBeastformGiftsSubreport.jasper"; //$NON-NLS-1$;
    return loadSystemResource(subreportResourceLocation);
  }

  public void buildBeastformCoreRulesBrawlWeaponList(List<IWeaponType> weapons, IGiftModel giftmodel) {
    final BrawlWeaponProvidingGift[] currentGift = getActiveBrawlWeaponGift(giftmodel);
    MeleeWeaponType handWeapon = new MeleeWeaponType(
        "Weapons.Brawl.Fist", AbilityType.Brawl, 0, 0, 0, HealthType.Bashing, 0, null); //$NON-NLS-1$
    MeleeWeaponType biteWeapon = null;
    if (currentGift[0] != null) {
      handWeapon = currentGift[0].getHandWeapon(ExaltedRuleSet.CoreRules);
      biteWeapon = currentGift[0].getBiteWeapon(ExaltedRuleSet.CoreRules);
    }
    weapons.add(handWeapon);
    weapons.add(new MeleeWeaponType("Weapons.Brawl.Clinch", AbilityType.Brawl, 0, 0, 2, HealthType.Bashing, 0, null)); //$NON-NLS-1$
    weapons.add(new MeleeWeaponType("Weapons.Brawl.Kick", AbilityType.Brawl, -3, -1, 2, HealthType.Bashing, -1, null)); //$NON-NLS-1$
    if (biteWeapon != null) {
      weapons.add(biteWeapon);
    }
  }

  private BrawlWeaponProvidingGift[] getActiveBrawlWeaponGift(IGiftModel giftmodel) {
    final BrawlWeaponProvidingGift[] currentGift = new BrawlWeaponProvidingGift[1];
    for (IQualitySelection<IGift> selection : giftmodel.getSelectedQualities()) {
      selection.getQuality().accept(new GiftVisitorAdapter() {
        @Override
        public void acceptBrawlWeaponProvidingGift(BrawlWeaponProvidingGift gift) {
          currentGift[0] = gift.getHighestInHierarchy(currentGift[0]);
        }
      });
    }
    return currentGift;
  }

  public void buildBeastformPowerCombatBrawlWeaponList(List<IWeaponType> weapons, IGiftModel giftmodel) {
    final BrawlWeaponProvidingGift[] currentGift = getActiveBrawlWeaponGift(giftmodel);
    MeleeWeaponType handWeapon = new MeleeWeaponType(
        "Weapons.Brawl.Fist", AbilityType.Brawl, 0, 1, 0, HealthType.Bashing, 2, 5); //$NON-NLS-1$
    MeleeWeaponType biteWeapon = null;
    if (currentGift[0] != null) {
      handWeapon = currentGift[0].getHandWeapon(ExaltedRuleSet.PowerCombat);
      biteWeapon = currentGift[0].getBiteWeapon(ExaltedRuleSet.PowerCombat);
    }
    weapons.add(handWeapon);
    weapons.add(new MeleeWeaponType("Weapons.Brawl.Clinch", AbilityType.Brawl, -6, 0, 2, HealthType.Bashing, 0, 1)); //$NON-NLS-1$
    weapons.add(new MeleeWeaponType("Weapons.Brawl.Kick", AbilityType.Brawl, -3, 1, 3, HealthType.Bashing, -3, 3)); //$NON-NLS-1$
    if (biteWeapon != null) {
      weapons.add(biteWeapon);
    }
  }
}