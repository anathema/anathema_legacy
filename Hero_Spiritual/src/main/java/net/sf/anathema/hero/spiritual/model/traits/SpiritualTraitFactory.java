package net.sf.anathema.hero.spiritual.model.traits;

import net.sf.anathema.character.main.library.trait.DefaultTrait;
import net.sf.anathema.character.main.library.trait.FriendlyValueChangeChecker;
import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.ValueChangeChecker;
import net.sf.anathema.character.main.library.trait.rules.TraitRules;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.model.TraitRulesImpl;
import net.sf.anathema.hero.traits.model.TraitTemplateMap;
import net.sf.anathema.hero.traits.template.TraitTemplate;

import java.util.ArrayList;
import java.util.List;

public class SpiritualTraitFactory {

  private Hero hero;
  private final TraitTemplateMap traitTemplateMap;

  public SpiritualTraitFactory(Hero hero, TraitTemplateMap traitTemplateMap) {
    this.hero = hero;
    this.traitTemplateMap = traitTemplateMap;
  }

  public Trait[] createTraits(TraitType[] traitTypes) {
    List<Trait> newTraits = new ArrayList<>();
    for (TraitType traitType : traitTypes) {
      newTraits.add(createTrait(traitType));
    }
    return newTraits.toArray(new Trait[newTraits.size()]);
  }

  public Trait createTrait(TraitType traitType) {
    TraitTemplate traitTemplate = traitTemplateMap.getTemplate(traitType);
    ValueChangeChecker checker = new FriendlyValueChangeChecker();
    TraitRules rules = new TraitRulesImpl(traitType, traitTemplate, hero);
    return new DefaultTrait(hero, rules, checker);
  }
}