package net.sf.anathema.test.character;

import net.sf.anathema.character.generic.dummy.DummyCharacterModelContext;
import net.sf.anathema.character.generic.dummy.DummyGenericTrait;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitValueStrategy;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;

public abstract class BasicCharacterTestCase {

  protected final DummyCharacterModelContext createModelContextWithEssence2(final ITraitValueStrategy valueStrategy) {
    DummyCharacterModelContext modelContext = new DummyCharacterModelContext(valueStrategy);
    modelContext.getCharacter().addTrait(new DummyGenericTrait(OtherTraitType.Essence, 2));
    return modelContext;
  }
}