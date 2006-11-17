package net.sf.anathema.character.lunar.reporting;

import java.util.List;

import net.sf.anathema.character.generic.equipment.IWeaponType;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.impl.equipment.AbstractBrawlWeaponConfiguration;
import net.sf.anathema.character.generic.impl.equipment.MeleeWeaponType;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.lunar.beastform.model.gift.BrawlWeaponProvidingGift;
import net.sf.anathema.character.lunar.beastform.model.gift.GiftVisitorAdapter;
import net.sf.anathema.character.lunar.beastform.model.gift.IGift;
import net.sf.anathema.character.lunar.beastform.model.gift.IGiftModel;

public class BeastformBrawlEquipmentConfiguration extends AbstractBrawlWeaponConfiguration {

  private final IGiftModel giftModel;

  public BeastformBrawlEquipmentConfiguration(IGiftModel giftModel, IExaltedRuleSet rules) {
    super(rules);
    this.giftModel = giftModel;
  }

  @Override
  protected void buildCoreRulesBrawlWeaponList(List<IWeaponType> weapons) {
    final BrawlWeaponProvidingGift[] currentGift = getActiveBrawlWeaponGift(giftModel);
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

  @Override
  protected void buildPowerCombatBrawlWeaponList(List<IWeaponType> weapons) {
    final BrawlWeaponProvidingGift[] currentGift = getActiveBrawlWeaponGift(giftModel);
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

  @Override
  protected void buildSecondEditionBrawlWeaponList(List<IWeaponType> weapons) {
    throw new UnsupportedOperationException("Use 2E sheet!"); //$NON-NLS-1$    
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
}