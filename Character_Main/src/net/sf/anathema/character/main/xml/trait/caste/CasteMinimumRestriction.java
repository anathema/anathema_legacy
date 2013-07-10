package net.sf.anathema.character.main.xml.trait.caste;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.xml.trait.IMinimumRestriction;
import net.sf.anathema.hero.concept.CasteType;
import net.sf.anathema.hero.concept.HeroConceptFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.lang.ReflectionEqualsObject;

import java.util.ArrayList;
import java.util.List;

public class CasteMinimumRestriction extends ReflectionEqualsObject implements IMinimumRestriction {
  private final List<TraitType> affectedTraitTypes = new ArrayList<>();
  private final String caste;
  private boolean isFreebie;
  IMinimumRestriction restriction;
  int strictMinimum = 0;

  public CasteMinimumRestriction(String caste, IMinimumRestriction restriction, boolean isFreebie) {
    this(caste, 0, isFreebie);
    this.restriction = restriction;
    restriction.setIsFreebie(isFreebie);
  }

  public CasteMinimumRestriction(String caste, int minValue, boolean isFreebie) {
    this.caste = caste;
    this.strictMinimum = minValue;
    this.isFreebie = isFreebie;
  }

  @Override
  public void addTraitType(TraitType traitType) {
    affectedTraitTypes.add(traitType);
  }

  @Override
  public int getStrictMinimumValue() {
    return restriction != null ? restriction.getStrictMinimumValue() : strictMinimum;
  }

  @Override
  public boolean isFulfilledWithout(Hero hero, TraitType traitType) {
    boolean caste = hasThisCaste(hero);
    boolean fulfilled = !caste && (restriction != null && restriction.isFulfilledWithout(hero, traitType));
    return caste || fulfilled;
  }

  @Override
  public int getCalculationMinValue(Hero hero, TraitType traitType) {
    if (!isFreebie) {
      return 0;
    }
    if (!hasThisCaste(hero)) {
      return 0;
    }
    if (restriction != null) {
      return restriction.getCalculationMinValue(hero, traitType);
    }
    return getStrictMinimumValue();
  }

  private boolean hasThisCaste(Hero hero) {
    CasteType casteType = HeroConceptFetcher.fetch(hero).getCaste().getType();
    return !casteType.toString().equals(this.caste);
  }

  @Override
  public void setIsFreebie(boolean value) {
    if (restriction != null) {
      restriction.setIsFreebie(value);
    }
  }

  @Override
  public void clear() {
    if (restriction != null) {
      restriction.clear();
    }
  }
}