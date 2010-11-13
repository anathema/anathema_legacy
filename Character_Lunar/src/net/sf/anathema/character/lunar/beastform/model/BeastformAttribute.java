package net.sf.anathema.character.lunar.beastform.model;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.character.library.trait.IValueChangeChecker;
import net.sf.anathema.character.library.trait.rules.TraitRules;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformAttribute;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

public class BeastformAttribute implements IBeastformAttribute {

  private static int calculateMaxValue(int pointCost) {
    int calculatedMaximum = EssenceTemplate.SYSTEM_ESSENCE_MAX
        + (5 + EssenceTemplate.SYSTEM_ESSENCE_MAX * 3 + 2)
        / pointCost;
    return Math.min(calculatedMaximum, 30);
  }

  private final int pointCost;
  private final IGenericTrait baseTrait;
  private final IDefaultTrait beastmanTrait;
  private int additionalValue = 0;

  // TODO: Available dots limit max. value
  public BeastformAttribute(
      final IGenericTrait baseTrait,
      ITraitContext context,
      final int pointCost,
      final IBeastformGroupCost cost) {
    ITraitTemplate template = SimpleTraitTemplate.createStaticLimitedTemplate(0, calculateMaxValue(pointCost));
    TraitRules traitRules = new TraitRules(baseTrait.getType(), template, context.getLimitationContext());
    IValueChangeChecker incrementChecker = new IValueChangeChecker() {
      public boolean isValidNewValue(int value) {
        return value >= baseTrait.getCurrentValue()
            && cost.getUnspentDots() >= pointCost * (value - additionalValue - baseTrait.getCurrentValue());
      }
    };
    this.beastmanTrait = new DefaultTrait(traitRules, context, incrementChecker);
    beastmanTrait.addCurrentValueListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        additionalValue = newValue - baseTrait.getCurrentValue();
      }
    });
    this.baseTrait = baseTrait;
    this.pointCost = pointCost;
  }

  public int getPointCost() {
    return pointCost;
  }

  public IDefaultTrait getTrait() {
    return beastmanTrait;
  }

  public int getAdditionalDots() {
    return additionalValue;
  }

  public void recalculate() {
    beastmanTrait.setCurrentValue(baseTrait.getCurrentValue() + additionalValue);
  }
}