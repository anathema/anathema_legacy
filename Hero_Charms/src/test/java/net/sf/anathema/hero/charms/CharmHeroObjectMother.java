package net.sf.anathema.hero.charms;

import net.sf.anathema.character.main.traits.TraitValueStrategy;
import net.sf.anathema.character.main.traits.types.OtherTraitType;
import net.sf.anathema.hero.dummy.DummyHero;
import net.sf.anathema.hero.dummy.models.DummyHeroConcept;
import net.sf.anathema.hero.dummy.models.DummySpiritualTraitModel;
import net.sf.anathema.hero.dummy.models.DummyTraitModel;

public class CharmHeroObjectMother {

  public DummyHero createModelContextWithEssence2(TraitValueStrategy valueStrategy) {
    return createCharacterWithEssence(valueStrategy, 2);
  }

  private DummyHero createCharacterWithEssence(TraitValueStrategy valueStrategy, int currentValue) {
    DummyHero hero = new DummyHero();
    DummySpiritualTraitModel otherTraitModel = new DummySpiritualTraitModel();
    hero.addModel(otherTraitModel);
    hero.addModel(new DummyHeroConcept());
    hero.addModel(createTraits(valueStrategy));
    otherTraitModel.getTrait(OtherTraitType.Essence).setCurrentValue(currentValue);
    return hero;
  }

  public static DummyTraitModel createTraits(TraitValueStrategy valueStrategy) {
    DummyTraitModel traits = new DummyTraitModel();
    traits.valueStrategy = valueStrategy;
    return traits;
  }
}