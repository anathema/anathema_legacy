package net.sf.anathema.character.impl.model.traits.creation;

import net.sf.anathema.character.generic.additionalrules.IAdditionalTraitRules;
import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.IValueChangeChecker;
import net.sf.anathema.character.library.trait.rules.TraitRules;

import java.util.ArrayList;
import java.util.List;

public class DefaultTraitFactory {

  private final ITraitContext traitContext;
  private final IAdditionalTraitRules additionalRules;
  private final TypedTraitTemplateFactory factory;

  public DefaultTraitFactory(ITraitContext traitContext, IAdditionalTraitRules additionalRules,
                             TypedTraitTemplateFactory factory) {
    this.traitContext = traitContext;
    this.additionalRules = additionalRules;
    this.factory = factory;
  }

  public Trait[] createTraits(ITraitType[] traitTypes) {
    List<Trait> newTraits = new ArrayList<>();
    for (ITraitType traitType : traitTypes) {
      newTraits.add(createTrait(traitType));
    }
    return newTraits.toArray(new Trait[newTraits.size()]);
  }

  public Trait createTrait(ITraitType traitType) {
    ITraitTemplate traitTemplate = factory.create(traitType);
    ILimitationContext limitationContext = traitContext.getLimitationContext();
    IValueChangeChecker checker = new AdditionRulesTraitValueChangeChecker(traitType, limitationContext, additionalRules);
    TraitRules rules = new TraitRules(traitType, traitTemplate, limitationContext);
    return new DefaultTrait(rules, traitContext, checker);
  }
}