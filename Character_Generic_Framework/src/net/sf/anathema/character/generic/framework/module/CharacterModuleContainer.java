package net.sf.anathema.character.generic.framework.module;

import net.sf.anathema.character.generic.framework.CharacterGenerics;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.lib.resources.IResources;

public class CharacterModuleContainer {

  private ICharacterGenerics characterGenerics = new CharacterGenerics();
  private final IResources resources;

  public CharacterModuleContainer(IResources resources) {
    this.resources = resources;
    addCharacterGenericsModule(new MortalCharacterModule());
    addCharacterGenericsModule(new BasicExaltCharacterModule());
  }

  public void addCharacterGenericsModule(ICharacterGenericsModule module) {
    module.registerCommonData(characterGenerics);
    module.addCharacterTemplates(characterGenerics);
    module.addBackgroundTemplates(characterGenerics);
    module.addAdditionalTemplateData(characterGenerics);
    module.addReportTemplates(characterGenerics, resources);
  }

  public ICharacterGenerics getCharacterGenerics() {
    return characterGenerics;
  }
}