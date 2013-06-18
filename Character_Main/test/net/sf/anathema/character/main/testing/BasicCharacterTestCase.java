package net.sf.anathema.character.main.testing;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.TraitValueStrategy;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.main.testing.dummy.DummyCharacterModelContext;
import net.sf.anathema.character.main.testing.dummy.DummyGenericTrait;

public class BasicCharacterTestCase {

  public DummyCharacterModelContext createModelContextWithEssence2(TraitValueStrategy valueStrategy) {
    return createCharacterWithEssence(valueStrategy, 2);
  }

  public DummyCharacterModelContext createModelContextWithEssence6(TraitValueStrategy valueStrategy) {
    return createCharacterWithEssence(valueStrategy, 6);
  }

  private DummyCharacterModelContext createCharacterWithEssence(TraitValueStrategy valueStrategy, int currentValue) {
    DummyCharacterModelContext modelContext = new DummyCharacterModelContext(valueStrategy);
    DummyGenericTrait essence = new DummyGenericTrait(OtherTraitType.Essence, currentValue);
    modelContext.getCharacter().addTrait(essence);
    return modelContext;
  }
}