package net.sf.anathema.character.impl.testing;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.framework.CharacterModuleContainerInitializer;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitValueStrategy;
import net.sf.anathema.character.generic.framework.module.CharacterModuleContainer;
import net.sf.anathema.character.generic.impl.testing.DummyGenericTrait;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.impl.model.test.DummyCharacterTemplate;
import net.sf.anathema.framework.resources.AnathemaResources;
import net.sf.anathema.lib.testing.BasicTestCase;

public class BasicCharacterTestCase extends BasicTestCase {

  public final IGenericCharacter createCharacterAbstraction() {
    DummyCharacterTemplate template = new DummyCharacterTemplate();
    return new DummyGenericCharacter(template);
  }

  protected final ICharacterGenerics createCharacterGenerics() {
    CharacterModuleContainerInitializer initializer = new CharacterModuleContainerInitializer();
    CharacterModuleContainer container = initializer.initContainer(new AnathemaResources());
    return container.getCharacterGenerics();
  }

  protected final DummyCharacterModelContext createModelContextWithEssence2(final ITraitValueStrategy valueStrategy) {
    DummyCharacterModelContext modelContext = new DummyCharacterModelContext(valueStrategy);
    modelContext.getCharacter().addTrait(new DummyGenericTrait(OtherTraitType.Essence, 2));
    return modelContext;
  }
}