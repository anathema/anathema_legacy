package net.sf.anathema.character.impl.model.traits.creation;

import net.sf.anathema.character.generic.additionalrules.IAdditionalTraitRules;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.template.ITraitTemplateCollection;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.character.library.trait.IValueChangeChecker;
import net.sf.anathema.character.library.trait.rules.TraitRules;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;

import java.util.ArrayList;
import java.util.List;

public class DefaultTraitFactory extends AbstractTraitFactory {

  private final ITraitContext traitContext;
  private final ITraitTemplateCollection templateCollection;

  public DefaultTraitFactory(ITraitContext traitContext, ITraitTemplateCollection templateCollection,
                             IAdditionalTraitRules additionalRules) {
    super(traitContext, additionalRules);
    this.traitContext = traitContext;
    this.templateCollection = templateCollection;
  }

  public IDefaultTrait[] createTraits(ITraitType[] traitTypes) {
    List<IDefaultTrait> newTraits = new ArrayList<>();
    for (ITraitType traitType : traitTypes) {
      newTraits.add(createTrait(traitType));
    }
    return newTraits.toArray(new IDefaultTrait[newTraits.size()]);
  }

  public IDefaultTrait createTrait(ITraitType traitType) {
    ITraitTemplate traitTemplate = templateCollection.getTraitTemplate(traitType);
    IValueChangeChecker checker = createValueIncrementChecker(traitType);
    TraitRules rules = new TraitRules(traitType, traitTemplate, traitContext.getLimitationContext());
    return new DefaultTrait(rules, traitContext, checker);
  }
}