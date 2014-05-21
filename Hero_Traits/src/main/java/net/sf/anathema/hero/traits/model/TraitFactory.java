package net.sf.anathema.hero.traits.model;

import net.sf.anathema.hero.traits.model.lists.IIdentifiedCasteTraitTypeList;
import net.sf.anathema.hero.concept.CasteType;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.model.trait.TraitRulesImpl;
import net.sf.anathema.hero.traits.model.trait.template.TraitTemplateMap;
import net.sf.anathema.hero.traits.template.TraitTemplate;

import java.util.ArrayList;
import java.util.List;

public class TraitFactory {

  private Hero hero;

  public TraitFactory(Hero hero) {
    this.hero = hero;
  }

  public Trait[] createTraits(IIdentifiedCasteTraitTypeList list, IncrementChecker checker, TraitTemplateMap templateMap) {
    List<Trait> newTraits = new ArrayList<>();
    for (TraitType type : list.getAll()) {
      CasteType[] casteTypes = list.getTraitCasteTypes(type);
      Trait trait = createTrait(type, casteTypes, checker, templateMap);
      newTraits.add(trait);
    }
    return newTraits.toArray(new Trait[newTraits.size()]);
  }

  private Trait createTrait(TraitType traitType, CasteType[] casteTypes, IncrementChecker checker, TraitTemplateMap templateMap) {
    TraitTemplate traitTemplate = templateMap.getTemplate(traitType);
    TraitRules favorableTraitRules = new TraitRulesImpl(traitType, traitTemplate, hero);
    ValueChangeChecker valueChecker = new FriendlyValueChangeChecker();
    return new DefaultTrait(hero, favorableTraitRules, casteTypes, valueChecker, checker);
  }
}