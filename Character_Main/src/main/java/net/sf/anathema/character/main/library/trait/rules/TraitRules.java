package net.sf.anathema.character.main.library.trait.rules;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.main.traits.ITraitTemplate;
import net.sf.anathema.character.main.traits.ModificationType;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.data.Range;

public class TraitRules implements ITraitRules {
  private int capModifier = 0;
  private final ITraitTemplate template;
  private final TraitType traitType;
  private Range modifiedCreationRange;
  private Hero hero;

  public TraitRules(TraitType traitType, ITraitTemplate template, Hero hero) {
    Preconditions.checkNotNull(traitType, "TemplateType must not be null.");
    Preconditions.checkNotNull(template, "Template must not be null.");
    this.traitType = traitType;
    this.hero = hero;
    this.template = template;
  }

  @Override
  public int getAbsoluteMaximumValue() {
    return template.getLimitation().getAbsoluteLimit(hero);
  }

  private int getCreationMaximumValue() {
    if (modifiedCreationRange == null) {
      return getCurrentMaximumValue(true);
    }
    return modifiedCreationRange.getUpperBound();
  }

  @Override
  public int getCurrentMaximumValue(boolean modified) {
    return template.getLimitation().getCurrentMaximum(hero, modified) + (modified ? capModifier : 0);
  }

  @Override
  public int getAbsoluteMinimumValue() {
    if (modifiedCreationRange == null) {
      return template.getMinimumValue(hero);
    }
    return modifiedCreationRange.getLowerBound();
  }

  @Override
  public int getStartValue() {
    return template.getStartValue();
  }

  @Override
  public void setCapModifier(int modifier) {
    capModifier = modifier;
  }

  @Override
  public boolean isReducible() {
    return template.getModificationType() == ModificationType.Free;
  }

  protected final ITraitTemplate getTemplate() {
    return template;
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
      boolean isImmutable = template.getModificationType() == ModificationType.Immutable;
      range = new Range(Math.max(Math.min(creationValue, maximumValue), getAbsoluteMinimumValue()), isImmutable ? creationValue : maximumValue);
    }
    int correctedValue = getCorrectedValue(demandedValue, range);
    if (isReducible()) {
      return correctedValue;
    }
    return correctedValue;
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
}