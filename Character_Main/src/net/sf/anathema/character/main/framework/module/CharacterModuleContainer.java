package net.sf.anathema.character.main.framework.module;

import net.sf.anathema.character.main.framework.data.IExtensibleDataSetProvider;
import net.sf.anathema.character.main.framework.CharacterGenerics;
import net.sf.anathema.character.main.framework.HeroEnvironment;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.ObjectFactory;
import net.sf.anathema.initialization.repository.DataFileProvider;

public class CharacterModuleContainer {

  private final CharacterGenerics characterGenerics;

  public CharacterModuleContainer(IExtensibleDataSetProvider dataSetProvider, DataFileProvider dataFileProvider, ObjectFactory objectFactory) throws
          InitializationException {
    this.characterGenerics = new CharacterGenerics(dataFileProvider, objectFactory, dataSetProvider);
  }

  public void addCharacterGenericsModule(ICharacterModule module) throws InitializationException {
    module.registerCommonData(characterGenerics);
    module.addCharacterTemplates(characterGenerics);
    module.addAdditionalTemplateData(characterGenerics);
  }

  public HeroEnvironment getCharacterGenerics() {
    return characterGenerics;
  }
}