package net.sf.anathema.character.impl.model.traits.creation;

import net.sf.anathema.character.generic.additionalrules.IAdditionalTraitRules;
import net.sf.anathema.character.generic.caste.CasteType;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedCasteTraitTypeGroup;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.character.library.trait.IValueChangeChecker;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.favorable.IncrementChecker;
import net.sf.anathema.character.library.trait.rules.FavorableTraitRules;
import net.sf.anathema.hero.model.Hero;

import java.util.ArrayList;
import java.util.List;

public class FavorableTraitFactory {

  private Hero hero;
  private final IAdditionalTraitRules additionalRules;

  public FavorableTraitFactory(Hero hero) {
    this.hero = hero;
    this.additionalRules = hero.getTemplate().getAdditionalRules().getAdditionalTraitRules();
  }

  public Trait[] createTraits(IIdentifiedCasteTraitTypeGroup group, IncrementChecker favoredIncrementChecker, TypedTraitTemplateFactory factory) {
    List<Trait> newTraits = new ArrayList<>();
    for (TraitType type : group.getAllGroupTypes()) {
      CasteType[] casteTypes = group.getTraitCasteTypes(type);
      Trait trait = createTrait(type, casteTypes, favoredIncrementChecker, factory);
      newTraits.add(trait);
    }
    return newTraits.toArray(new Trait[newTraits.size()]);
  }

  private Trait createTrait(TraitType traitType, CasteType[] casteTypes, IncrementChecker favoredIncrementChecker,
                            TypedTraitTemplateFactory factory) {
    ITraitTemplate traitTemplate = factory.create(traitType);
    FavorableTraitRules favorableTraitRules = new FavorableTraitRules(traitType, traitTemplate, hero);
    IValueChangeChecker valueChecker = new AdditionRulesTraitValueChangeChecker(traitType, hero, additionalRules);
    return new DefaultTrait(hero, favorableTraitRules, casteTypes, valueChecker, favoredIncrementChecker);
  }
}