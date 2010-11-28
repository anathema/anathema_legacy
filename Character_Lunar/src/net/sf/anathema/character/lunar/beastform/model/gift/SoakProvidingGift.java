package net.sf.anathema.character.lunar.beastform.model.gift;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.health.IHealthTypeVisitor;
import net.sf.anathema.character.library.quality.model.QualityPrerequisite;
import net.sf.anathema.character.library.quality.presenter.IQuality;
import net.sf.anathema.character.library.quality.presenter.IQualityPredicate;

public class SoakProvidingGift extends Gift {

  private final int bonus;
  private boolean replacesPrerequisites;

  public SoakProvidingGift(String id, int bonus, boolean replacesPrerequisites) {
    super(id);
    this.replacesPrerequisites = replacesPrerequisites;
    this.bonus = bonus;
  }

  public int calculateSoak(int stamina) {
    return stamina;
  }

  public int getBonus() {
    return bonus;
  }

  @Override
  public void accept(IGiftVisitor visitor) {
    visitor.acceptSoakProvidingGift(this);
  }

  public void adjustActiveGiftList(List<SoakProvidingGift> giftList) {
    List<SoakProvidingGift> cloneList = new ArrayList<SoakProvidingGift>(giftList);
    if (replacesPrerequisites) {
      for (SoakProvidingGift gift : cloneList) {
        if (isPrerequisite(gift)) {
          giftList.remove(gift);
        }
      }
    }
    for (SoakProvidingGift gift : giftList) {
      if (gift.replacesPrerequisites && gift.isPrerequisite(this)) {
        return;
      }
    }
    giftList.add(this);
  }

  private boolean isPrerequisite(SoakProvidingGift gift) {
    List<IQualityPredicate> prerequisiteList = getPrerequisiteList();
    for (IQualityPredicate predicate : prerequisiteList) {
      if (predicate instanceof QualityPrerequisite) {
        IQuality[] prerequisiteQualities = ((QualityPrerequisite) predicate).getPrerequisiteQualities();
        return ArrayUtilities.contains(prerequisiteQualities, gift);
      }
    }
    return false;
  }

  public float getSoakStaminaModifier(HealthType type) {
    final float[] modifier = new float[1];
    type.accept(new IHealthTypeVisitor() {
      public void visitBashing(HealthType bashing) {
        modifier[0] = 1;
      }

      public void visitLethal(HealthType lethal) {
        modifier[0] = 1;
      }

      public void visitAggravated(HealthType aggravated) {
        throw new UnsupportedOperationException();
      }
    });
    return modifier[0];
  }
}