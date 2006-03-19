package net.sf.anathema.acceptance.fixture.character;

import net.sf.anathema.character.generic.framework.CharacterModuleContainerInitializer;
import net.sf.anathema.character.generic.framework.module.CharacterModuleContainer;
import net.sf.anathema.framework.resources.AnathemaResources;
import net.sf.anathema.lib.resources.IResources;
import fit.Fixture;
import fit.Parse;

public class SetupCharacterPlatformFixture extends Fixture {

  @Override
  public void doTable(Parse parse) {
    IResources resources = new AnathemaResources();
    CharacterSummary characterSummary = new CharacterSummary(summary);
    characterSummary.setResources(resources);
    CharacterModuleContainer container = new CharacterModuleContainerInitializer().initContainer(resources);
    characterSummary.setCharacterModuleContainer(container);
  }
}