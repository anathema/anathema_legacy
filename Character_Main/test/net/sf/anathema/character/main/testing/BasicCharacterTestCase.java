package net.sf.anathema.character.main.testing;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.TraitValueStrategy;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.main.testing.dummy.DummyHero;
import net.sf.anathema.character.main.testing.dummy.models.DummyHeroConcept;
import net.sf.anathema.character.main.testing.dummy.models.DummyOtherTraitModel;
import net.sf.anathema.character.main.testing.dummy.models.DummyTraitModel;

public class BasicCharacterTestCase {

  public DummyHero createModelContextWithEssence2(TraitValueStrategy valueStrategy) {
    return createCharacterWithEssence(valueStrategy, 2);
  }

  public DummyHero createModelContextWithEssence6(TraitValueStrategy valueStrategy) {
    return createCharacterWithEssence(valueStrategy, 6);
  }

  private DummyHero createCharacterWithEssence(TraitValueStrategy valueStrategy, int currentValue) {
    DummyHero hero = new DummyHero();
    DummyOtherTraitModel otherTraitModel = new DummyOtherTraitModel();
    hero.addModel(otherTraitModel);
    hero.addModel(new DummyHeroConcept());
    hero.addModel(createTraits(valueStrategy));
    otherTraitModel.getTrait(OtherTraitType.Essence).setCurrentValue(currentValue);
    return hero;
  }

  private DummyTraitModel createTraits(TraitValueStrategy valueStrategy) {
    DummyTraitModel traits = new DummyTraitModel();
    traits.valueStrategy = valueStrategy;
    return traits;
  }
}