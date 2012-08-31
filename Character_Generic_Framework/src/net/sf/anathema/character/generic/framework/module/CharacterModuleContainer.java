package net.sf.anathema.character.generic.framework.module;

import net.sf.anathema.character.generic.framework.CharacterGenerics;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.ICharacterTemplateResourceProvider;
import net.sf.anathema.character.generic.framework.module.object.ICharacterModuleObject;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.Instantiater;
import net.sf.anathema.initialization.repository.IDataFileProvider;
import net.sf.anathema.character.generic.data.IExtensibleDataSetProvider;
import net.sf.anathema.lib.resources.IResources;

public class CharacterModuleContainer {

  private final CharacterGenerics characterGenerics;
  private final ICharacterTemplateResourceProvider templateProvider;
  private final IResources resources;

  public CharacterModuleContainer(IResources resources, IExtensibleDataSetProvider dataSetProvider,
		  						  ICharacterTemplateResourceProvider templateProvider,
                                  IDataFileProvider dataFileProvider,
                                  Instantiater instantiater) throws InitializationException {
    this.resources = resources;
    this.templateProvider = templateProvider;
    this.characterGenerics = new CharacterGenerics(dataFileProvider, instantiater, dataSetProvider);
    initializeBasicModuleSoOtherModulesCanDependOnIt();
  }

  public void addCharacterGenericsModule(
          ICharacterModule<? extends ICharacterModuleObject> module) throws InitializationException {
    module.initModuleObject(characterGenerics);
    module.registerCommonData(characterGenerics);
    module.addCharacterTemplates(templateProvider, characterGenerics);
    module.addBackgroundTemplates(characterGenerics);
    module.addAdditionalTemplateData(characterGenerics);
    module.addReportTemplates(characterGenerics, resources);
    characterGenerics.getModuleObjectMap().addModule(module);
  }

  public ICharacterGenerics getCharacterGenerics() {
    return characterGenerics;
  }

  private void initializeBasicModuleSoOtherModulesCanDependOnIt() throws InitializationException {
    addCharacterGenericsModule(new BasicExaltCharacterModule());
  }
}
