package net.sf.anathema.character.impl.model.traits.creation;

import net.sf.anathema.character.generic.additionalrules.IAdditionalTraitRules;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.template.ITraitTemplateCollection;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.character.library.trait.IModifiableTrait;
import net.sf.anathema.character.library.trait.IValueChangeChecker;
import net.sf.anathema.character.library.trait.rules.TraitRules;

public class DefaultTraitFactory extends AbstractTraitFactory {

  private final ITraitContext traitContext;
  private final ITraitTemplateCollection templateCollection;

  public DefaultTraitFactory(
      ITraitContext traitContext,
      ITraitTemplateCollection templateCollection,
      IAdditionalTraitRules additionalRules) {
    super(traitContext, additionalRules);
    this.traitContext = traitContext;
    this.templateCollection = templateCollection;
  }

  public IModifiableTrait[] createTraits(ITraitType[] traitTypes) {
    IModifiableTrait[] newTraits = new IModifiableTrait[traitTypes.length];
    for (int index = 0; index < newTraits.length; index++) {
      newTraits[index] = createTrait(traitTypes[index]);
    }
    return newTraits;
  }

  public IModifiableTrait createTrait(ITraitType traitType) {
    ITraitTemplate traitTemplate = templateCollection.getTraitTemplate(traitType);
    IValueChangeChecker checker = createValueIncrementChecker(traitType);
    TraitRules rules = new TraitRules(traitType, traitTemplate, traitContext.getLimitationContext());
    return new DefaultTrait(rules, traitContext.getTraitValueStrategy(), checker);
  }
}