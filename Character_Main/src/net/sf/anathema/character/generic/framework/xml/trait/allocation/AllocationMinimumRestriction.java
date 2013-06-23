package net.sf.anathema.character.generic.framework.xml.trait.allocation;

import net.sf.anathema.character.generic.framework.xml.trait.IMinimumRestriction;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.main.model.traits.TraitModelFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.lang.ReflectionEqualsObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllocationMinimumRestriction extends ReflectionEqualsObject implements IMinimumRestriction {
  private final Map<Hero, Map<TraitType, Integer>> claimMap = new HashMap<>();
  private final List<AllocationMinimumRestriction> siblings;
  private final int dotCount;
  private int strictMinimumValue = 0;
  private final List<TraitType> alternateTraitTypes = new ArrayList<>();
  private Hero latestHero = null;
  private TraitType latestTrait = null;
  private boolean isFreebie;

  public AllocationMinimumRestriction(int dotCount, List<AllocationMinimumRestriction> siblings) {
    this.dotCount = dotCount;
    this.siblings = siblings;
  }

  @Override
  public boolean isFulfilledWithout(Hero hero, TraitType traitType) {
    int remainingDots = dotCount;
    latestHero = hero;
    latestTrait = traitType;
    for (TraitType type : alternateTraitTypes) {
      if (type != traitType) {
        int currentDots = TraitModelFetcher.fetch(hero).getTrait(type).getCurrentValue();
        int externalDots = getExternalClaims(hero, type);
        int claimedDots = Math.max(currentDots - externalDots, 0);
        claimedDots = Math.min(claimedDots, remainingDots);
        claimDots(hero, type, claimedDots);
        remainingDots -= claimedDots;
      }
    }
    strictMinimumValue = remainingDots;
    return remainingDots == 0;
  }

  @Override
  public int getCalculationMinValue(Hero hero, TraitType traitType) {
    if (!isFreebie) {
      return 0;
    }
    int traitDots = 0;
    int remainingDots = dotCount;
    for (TraitType type : alternateTraitTypes) {
      int currentDots = TraitModelFetcher.fetch(hero).getTrait(type).getCurrentValue();
      int externalDots = getExternalClaims(hero, type);
      int claimedDots = Math.max(currentDots - externalDots, 0);
      claimedDots = Math.min(claimedDots, remainingDots);
      claimDots(hero, type, claimedDots);
      remainingDots -= claimedDots;
      if (type == traitType) {
        traitDots = claimedDots;
      }
    }
    return traitDots + getExternalClaims(hero, traitType);
  }

  @Override
  public void setIsFreebie(boolean value) {
    isFreebie = value;
  }

  private void claimDots(Hero hero, TraitType type, int dots) {
    Map<TraitType, Integer> map = claimMap.get(hero);
    if (map == null) {
      map = new HashMap<>();
      claimMap.put(hero, map);
    }
    map.put(type, dots);
  }

  private int getExternalClaims(Hero hero, TraitType traitType) {
    int claimed = 0;
    for (AllocationMinimumRestriction sibling : siblings) {
      if (sibling == this) {
        continue;
      }
      try {
        Map<TraitType, Integer> map = sibling.claimMap.get(hero);
        claimed += map.get(traitType);
      } catch (NullPointerException ignored) {
      }
    }
    return claimed;
  }

  @Override
  public void clear() {
    claimMap.clear();
    for (AllocationMinimumRestriction sibling : siblings) {
      sibling.claimMap.clear();
    }
  }

  @Override
  public void addTraitType(TraitType traitType) {
    alternateTraitTypes.add(traitType);
  }

  @Override
  public int getStrictMinimumValue() {
    claimDots(latestHero, latestTrait, strictMinimumValue);
    return strictMinimumValue + getExternalClaims(latestHero, latestTrait);
  }

  public String toString() {
    return "{" + dotCount + ";" + alternateTraitTypes + "}";
  }
}