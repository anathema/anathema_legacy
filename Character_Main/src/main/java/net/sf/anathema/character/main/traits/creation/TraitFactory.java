package net.sf.anathema.character.main.traits.creation;

import net.sf.anathema.character.main.library.trait.DefaultTrait;
import net.sf.anathema.character.main.library.trait.FriendlyValueChangeChecker;
import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.ValueChangeChecker;
import net.sf.anathema.character.main.library.trait.favorable.IncrementChecker;
import net.sf.anathema.character.main.library.trait.rules.TraitRulesImpl;
import net.sf.anathema.character.main.traits.ITraitTemplate;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.lists.IIdentifiedCasteTraitTypeList;
import net.sf.anathema.hero.concept.CasteType;
import net.sf.anathema.hero.model.Hero;

import java.util.ArrayList;
import java.util.List;

public class TraitFactory {

  private Hero hero;

  public TraitFactory(Hero hero) {
    this.hero = hero;
  }

  public Trait[] createTraits(IIdentifiedCasteTraitTypeList group, IncrementChecker favoredIncrementChecker, TypedTraitTemplateFactory factory) {
    List<Trait> newTraits = new ArrayList<>();
    for (TraitType type : group.getAll()) {
      CasteType[] casteTypes = group.getTraitCasteTypes(type);
      Trait trait = createTrait(type, casteTypes, favoredIncrementChecker, factory);
      newTraits.add(trait);
    }
    return newTraits.toArray(new Trait[newTraits.size()]);
  }

  private Trait createTrait(TraitType traitType, CasteType[] casteTypes, IncrementChecker favoredIncrementChecker,
                            TypedTraitTemplateFactory factory) {
    ITraitTemplate traitTemplate = factory.create(traitType);
    TraitRulesImpl favorableTraitRules = new TraitRulesImpl(traitType, traitTemplate, hero);
    ValueChangeChecker valueChecker = new FriendlyValueChangeChecker();
    return new DefaultTrait(hero, favorableTraitRules, casteTypes, valueChecker, favoredIncrementChecker);
  }
}