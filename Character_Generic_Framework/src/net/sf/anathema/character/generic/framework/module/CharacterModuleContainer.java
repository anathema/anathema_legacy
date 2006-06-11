package net.sf.anathema.character.generic.framework.module;

import net.sf.anathema.character.generic.framework.CharacterGenerics;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.module.object.ICharacterModuleObject;
import net.sf.anathema.lib.resources.IResources;

public class CharacterModuleContainer {

  private CharacterGenerics characterGenerics = new CharacterGenerics();
  private final IResources resources;

  public CharacterModuleContainer(IResources resources) {
    this.resources = resources;
    addCharacterGenericsModule(new BasicExaltCharacterModule());
  }

  public void addCharacterGenericsModule(ICharacterModule< ? extends ICharacterModuleObject> module) {
    module.initModuleObject();
    module.registerCommonData(characterGenerics);
    module.addCharacterTemplates(characterGenerics);
    module.addBackgroundTemplates(characterGenerics);
    module.addAdditionalTemplateData(characterGenerics);
    module.addReportTemplates(characterGenerics, resources);
    characterGenerics.getModuleObjectMap().addModule(module);
  }

  public ICharacterGenerics getCharacterGenerics() {
    return characterGenerics;
  }
}