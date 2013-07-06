package net.sf.anathema.character.model.traits.creation;

import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.character.library.trait.FriendlyValueChangeChecker;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.ValueChangeChecker;
import net.sf.anathema.character.library.trait.rules.TraitRules;
import net.sf.anathema.hero.model.Hero;

import java.util.ArrayList;
import java.util.List;

public class DefaultTraitFactory {

  private Hero hero;
  private final TypedTraitTemplateFactory factory;

  public DefaultTraitFactory(Hero hero, TypedTraitTemplateFactory factory) {
    this.hero = hero;
    this.factory = factory;
  }

  public Trait[] createTraits(TraitType[] traitTypes) {
    List<Trait> newTraits = new ArrayList<>();
    for (TraitType traitType : traitTypes) {
      newTraits.add(createTrait(traitType));
    }
    return newTraits.toArray(new Trait[newTraits.size()]);
  }

  public Trait createTrait(TraitType traitType) {
    ITraitTemplate traitTemplate = factory.create(traitType);
    ValueChangeChecker checker = new FriendlyValueChangeChecker();
    TraitRules rules = new TraitRules(traitType, traitTemplate, hero);
    return new DefaultTrait(hero, rules, checker);
  }
}