package net.sf.anathema.character.generic.framework.module;

import net.sf.anathema.character.generic.framework.CharacterGenerics;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.module.object.ICharacterModuleObject;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.Instantiater;
import net.sf.anathema.initialization.repository.IDataFileProvider;
import net.sf.anathema.lib.resources.IResourceCollection;

public class CharacterModuleContainer {

  private final CharacterGenerics characterGenerics;
  private final IResourceCollection resourceData;

  public CharacterModuleContainer(IResourceCollection resourceData, IDataFileProvider dataFileProvider, Instantiater instantiater)
          throws InitializationException {
    this.resourceData = resourceData;
    this.characterGenerics = new CharacterGenerics(dataFileProvider, instantiater, resourceData.getDataProvider());
    initializeBasicModuleSoOtherModulesCanDependOnIt();
  }

  public void addCharacterGenericsModule(ICharacterModule<? extends ICharacterModuleObject> module)
          throws InitializationException {
    module.initModuleObject(characterGenerics);
    module.registerCommonData(characterGenerics);
    module.addBackgroundTemplates(characterGenerics);
    module.addCharacterTemplates(characterGenerics);
    module.addAdditionalTemplateData(characterGenerics);
    module.addReportTemplates(characterGenerics, resourceData.getUIResources());
    characterGenerics.getModuleObjectMap().addModule(module);
  }

  public ICharacterGenerics getCharacterGenerics() {
    return characterGenerics;
  }

  private void initializeBasicModuleSoOtherModulesCanDependOnIt() throws InitializationException {
    addCharacterGenericsModule(new BasicExaltCharacterModule());
  }
}
