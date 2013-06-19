package net.sf.anathema.character.generic.framework.xml.trait;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.main.hero.Hero;
import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

import java.util.ArrayList;
import java.util.List;

public class GenericRestrictedTraitTemplate extends ReflectionCloneableObject<IClonableTraitTemplate> implements IClonableTraitTemplate {

  private final List<IMinimumRestriction> restrictions = new ArrayList<>();
  private final TraitType traitType;
  private IClonableTraitTemplate traitTemplate;

  public GenericRestrictedTraitTemplate(IClonableTraitTemplate traitTemplate, IMinimumRestriction restriction, TraitType traitType) {
    Preconditions.checkNotNull(traitTemplate);
    Preconditions.checkNotNull(restriction);
    Preconditions.checkNotNull(traitType);
    this.traitType = traitType;
    this.traitTemplate = traitTemplate;
    restrictions.add(restriction);
    restriction.addTraitType(traitType);
  }

  public void addRestriction(IMinimumRestriction restriction) {
    restrictions.add(restriction);
  }

  @Override
  public ITraitLimitation getLimitation() {
    return traitTemplate.getLimitation();
  }

  @Override
  public LowerableState getLowerableState() {
    return traitTemplate.getLowerableState();
  }

  @Override
  public int getStartValue() {
    return traitTemplate.getStartValue();
  }

  @Override
  public int getZeroLevelValue() {
    return traitTemplate.getZeroLevelValue();
  }

  @Override
  public boolean isRequiredFavored() {
    return traitTemplate.isRequiredFavored();
  }

  @Override
  public int getMinimumValue(Hero hero) {
    boolean restricted = false;
    int minimum = 0;
    for (IMinimumRestriction restriction : restrictions) {
      restriction.clear();
    }
    for (IMinimumRestriction restriction : restrictions) {
      if (!restriction.isFulfilledWithout(hero, traitType)) {
        int newMin = restriction.getStrictMinimumValue();
        minimum = newMin > minimum ? newMin : minimum;
        restricted = true;
      }
    }
    if (restricted) {
      return minimum;
    }
    return traitTemplate.getMinimumValue(hero);
  }

  @Override
  public int getCalculationMinValue(Hero hero, TraitType type) {
    int minimum = 0;
    for (IMinimumRestriction restriction : restrictions) {
      restriction.clear();
    }
    for (IMinimumRestriction restriction : restrictions) {
      int newMin = restriction.getCalculationMinValue(hero, type);
      minimum = newMin > minimum ? newMin : minimum;
    }
    return Math.max(minimum, traitTemplate.getCalculationMinValue(hero, type));
  }

  public TraitType getTraitType() {
    return traitType;
  }

  public List<IMinimumRestriction> getRestrictions() {
    return restrictions;
  }

  public void setTemplate(IClonableTraitTemplate template) {
    traitTemplate = template;
  }

  public IClonableTraitTemplate getTemplate() {
    return traitTemplate;
  }

  @Override
  public GenericRestrictedTraitTemplate clone() {
    GenericRestrictedTraitTemplate clone = (GenericRestrictedTraitTemplate) super.clone();
    clone.traitTemplate = traitTemplate.clone();
    return clone;
  }

  @Override
  public String getTag() {
    return null;
  }
}