package net.sf.anathema.character.lunar.beastform.model;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.character.library.trait.IValueChangeChecker;
import net.sf.anathema.character.library.trait.rules.TraitRules;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformAttribute;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

public class SpiritFormAttribute implements IBeastformAttribute
{
  private final IDefaultTrait spiritTrait;
  private int newValue = 0;

  // TODO: Available dots limit max. value
  public SpiritFormAttribute(
      final IGenericTrait baseTrait,
      final ICharacterModelContext charContext,
      ITraitContext context) {
    ITraitTemplate template = SimpleTraitTemplate.createStaticLimitedTemplate(1, 12);
    TraitRules traitRules = new TraitRules(baseTrait.getType(), template, context.getLimitationContext());
    IValueChangeChecker incrementChecker = new IValueChangeChecker() {
      public boolean isValidNewValue(int value) {
    	  return value != spiritTrait.getCurrentValue() &&
    	  	!charContext.getBasicCharacterContext().isExperienced();
      }
    };
    spiritTrait = new DefaultTrait(traitRules, context, incrementChecker);
    spiritTrait.addCurrentValueListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        SpiritFormAttribute.this.newValue = newValue;
      }
    });
  }

  public int getPointCost() {
    return 0;
  }

  public IDefaultTrait getTrait() {
    return spiritTrait;
  }

  public int getAdditionalDots() {
    return 0;
  }

  public void recalculate() {
    spiritTrait.setCurrentValue(newValue);
  }
}