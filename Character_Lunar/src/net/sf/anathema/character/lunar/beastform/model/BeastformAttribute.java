package net.sf.anathema.character.lunar.beastform.model;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.character.library.trait.IValueChangeChecker;
import net.sf.anathema.character.library.trait.rules.TraitRules;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformAttribute;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

public class BeastformAttribute implements IBeastformAttribute {

  private static final String DEVESTATING_OGRE_ENHANCEMENT = "Lunar.DevastatingOgreEnhancement";
  private final ICharacterModelContext context;
  private final int pointCost;
  private final IGenericTrait baseTrait;
  private final IDefaultTrait beastmanTrait;
  private int additionalValue = 0;

  // TODO: Available dots limit max. value
  public BeastformAttribute(ICharacterModelContext context, final IGenericTrait baseTrait, int pointCost) {
    this.context = context;
    ITraitContext traitContext = context.getTraitContext();
    ITraitTemplate template = SimpleTraitTemplate.createStaticLimitedTemplate(0, 12);
    TraitRules traitRules = new TraitRules(baseTrait.getType(), template, traitContext.getLimitationContext());
    IValueChangeChecker incrementChecker = new IValueChangeChecker() {
      @Override
      public boolean isValidNewValue(int value) {
        return value == (baseTrait.getCurrentValue() + (hasDOE() ? 2 : 1));
      }
    };
    this.beastmanTrait = new DefaultTrait(traitRules, traitContext, incrementChecker);
    beastmanTrait.addCurrentValueListener(new IIntValueChangedListener() {
      @Override
      public void valueChanged(int newValue) {
        additionalValue = newValue - baseTrait.getCurrentValue();
      }
    });
    this.baseTrait = baseTrait;
    this.pointCost = pointCost;
  }

  private boolean hasDOE() {
    for (ICharm charm : context.getCharmContext().getCharmConfiguration().getLearnedCharms())
      if (charm.getId().equals(DEVESTATING_OGRE_ENHANCEMENT)) return true;
    return false;
  }

  @Override
  public int getPointCost() {
    return pointCost;
  }

  @Override
  public IDefaultTrait getTrait() {
    return beastmanTrait;
  }

  @Override
  public int getAdditionalDots() {
    return additionalValue;
  }

  @Override
  public void recalculate() {
    beastmanTrait.setCurrentValue(
            baseTrait.getCurrentValue() + (hasDOE() ? 2 : 1));
  }
}