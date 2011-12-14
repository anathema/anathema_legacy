package net.sf.anathema.acceptance.fixture.character;

import net.sf.anathema.character.generic.framework.CharacterModuleContainerInitializer;
import net.sf.anathema.character.generic.framework.module.CharacterModuleContainer;
import net.sf.anathema.framework.resources.AnathemaResources;
import net.sf.anathema.initialization.repository.IDataFileProvider;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.test.character.DemoDataFileProvider;
import fit.Fixture;
import fit.Parse;

public class SetupCharacterPlatformFixture extends Fixture {

  @Override
  public void doTable(Parse parse) {
    IResources resources = new AnathemaResources();
    IDataFileProvider dataFileProvider = new DemoDataFileProvider();
    @SuppressWarnings("unchecked")
    CharacterSummary characterSummary = new CharacterSummary(summary);
    characterSummary.setResources(resources);
    CharacterModuleContainer container;
    try {
      characterSummary.registerCharmFiles();
      container = new CharacterModuleContainerInitializer().initContainer(resources, dataFileProvider);
    }
    catch (Exception e) {
      throw new RuntimeException("Failed to initialize Character Sub-Modules.", e); //$NON-NLS-1$
    }
    characterSummary.setCharacterModuleContainer(container);
  }
}