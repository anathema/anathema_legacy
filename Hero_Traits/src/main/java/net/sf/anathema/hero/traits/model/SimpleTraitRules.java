package net.sf.anathema.hero.traits.model;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.main.library.trait.rules.TraitRules;
import net.sf.anathema.character.main.template.ITraitLimitation;
import net.sf.anathema.character.main.traits.ITraitTemplate;
import net.sf.anathema.character.main.traits.ModificationType;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.limitation.AgeBasedLimitation;
import net.sf.anathema.character.main.traits.limitation.EssenceBasedLimitation;
import net.sf.anathema.character.main.traits.limitation.StaticTraitLimitation;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.template.LimitationType;
import net.sf.anathema.hero.traits.template.TraitTemplate;
import net.sf.anathema.lib.data.Range;

public class SimpleTraitRules implements TraitRules {
  private int capModifier = 0;
  private final TraitTemplate template;
  private final TraitType traitType;
  private Range modifiedCreationRange;
  private Hero hero;

  public SimpleTraitRules(TraitType traitType, TraitTemplate template, Hero hero) {
    this.traitType = traitType;
    this.hero = hero;
    this.template = template;
  }

  @Override
  public int getAbsoluteMaximumValue() {
    return getLimitation().getAbsoluteLimit(hero);
  }

  private ITraitLimitation getLimitation() {
    if (template.limitation.type == LimitationType.Static) {
      return new StaticTraitLimitation(template.limitation.value);
    }
    if (template.limitation.type == LimitationType.Age) {
      return new AgeBasedLimitation(template.limitation.value);
    }
    return new EssenceBasedLimitation();
  }

  private int getCreationMaximumValue() {
    if (modifiedCreationRange == null) {
      return getCurrentMaximumValue(true);
    }
    return modifiedCreationRange.getUpperBound();
  }

  @Override
  public int getCurrentMaximumValue(boolean modified) {
    return getLimitation().getCurrentMaximum(hero, modified) + (modified ? capModifier : 0);
  }

  @Override
  public int getAbsoluteMinimumValue() {
    if (modifiedCreationRange == null) {
      return template.minimumValue;
    }
    return modifiedCreationRange.getLowerBound();
  }

  @Override
  public int getStartValue() {
    return template.startValue;
  }

  @Override
  public void setCapModifier(int modifier) {
    capModifier = modifier;
  }

  @Override
  public boolean isReducible() {
    return template.modificationType == ModificationType.Free;
  }

  @Override
  public TraitType getType() {
    return traitType;
  }

  @Override
  public void setModifiedCreationRange(Range range) {
    this.modifiedCreationRange = range;
  }

  @Override
  public int getExperiencedValue(int creationValue, int demandedValue) {
    Range range;
    int maximumValue = getCurrentMaximumValue(true);
    if (isReducible()) {
      range = new Range(getAbsoluteMinimumValue(), maximumValue);
    } else {
      boolean isImmutable = template.modificationType == ModificationType.Immutable;
      range = new Range(Math.max(Math.min(creationValue, maximumValue), getAbsoluteMinimumValue()), isImmutable ? creationValue : maximumValue);
    }
    return getCorrectedValue(demandedValue, range);
   }

  @Override
  public int getCreationValue(int demandedValue) {
    Range currentCreationPointRange = new Range(getAbsoluteMinimumValue(), getCreationMaximumValue());
    return getCorrectedValue(demandedValue, currentCreationPointRange);
  }

  private int getCorrectedValue(int value, Range allowedRange) {
    if (allowedRange.contains(value)) {
      return value;
    }
    if (value < allowedRange.getLowerBound()) {
      return allowedRange.getLowerBound();
    }
    return allowedRange.getUpperBound();
  }

  @Override
  public boolean isRequiredFavored() {
    return false;
  }
}