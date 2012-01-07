package net.sf.anathema.test.character;

import net.sf.anathema.character.generic.dummy.DummyCharacterModelContext;
import net.sf.anathema.character.generic.dummy.DummyGenericTrait;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitValueStrategy;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;

public class BasicCharacterTestCase {

  public DummyCharacterModelContext createModelContextWithEssence2(ITraitValueStrategy valueStrategy) {
    return createCharacterWithEssence(valueStrategy, 2);
  }

  public DummyCharacterModelContext createModelContextWithEssence6(ITraitValueStrategy valueStrategy) {
    return createCharacterWithEssence(valueStrategy, 6);
  }

  private DummyCharacterModelContext createCharacterWithEssence(ITraitValueStrategy valueStrategy, int currentValue) {
    DummyCharacterModelContext modelContext = new DummyCharacterModelContext(valueStrategy);
    DummyGenericTrait essence = new DummyGenericTrait(OtherTraitType.Essence, currentValue);
    modelContext.getCharacter().addTrait(essence);
    return modelContext;
  }
}