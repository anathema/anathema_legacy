package net.sf.anathema.test.character;

import net.sf.anathema.character.generic.dummy.DummyCharacterModelContext;
import net.sf.anathema.character.generic.dummy.DummyGenericTrait;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitValueStrategy;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;

public abstract class BasicCharacterTestCase {

  protected DummyCharacterModelContext createModelContextWithEssence2(ITraitValueStrategy valueStrategy) {
    DummyCharacterModelContext modelContext = new DummyCharacterModelContext(valueStrategy);
    DummyGenericTrait essence = new DummyGenericTrait(OtherTraitType.Essence, 2);
    modelContext.getCharacter().addTrait(essence);
    return modelContext;
  }
}