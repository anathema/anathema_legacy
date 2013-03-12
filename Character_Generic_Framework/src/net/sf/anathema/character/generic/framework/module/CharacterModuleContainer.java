package net.sf.anathema.character.generic.framework.module;

import net.sf.anathema.character.generic.data.IExtensibleDataSetProvider;
import net.sf.anathema.character.generic.framework.CharacterGenerics;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.xml.additional.IAdditionalTemplateParser;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.Instantiater;
import net.sf.anathema.initialization.repository.IDataFileProvider;
import net.sf.anathema.lib.resources.IResources;

import java.util.Collection;

public class CharacterModuleContainer {

  private final CharacterGenerics characterGenerics;
  private final IResources resources;

  public CharacterModuleContainer(IResources resources, IExtensibleDataSetProvider dataSetProvider,
                                  IDataFileProvider dataFileProvider,
                                  Instantiater instantiater) throws InitializationException {
    this.resources = resources;
    this.characterGenerics = new CharacterGenerics(dataFileProvider, instantiater, dataSetProvider);
  }

  public void addCharacterGenericsModule(ICharacterModule<?> module) throws InitializationException {
    module.initModuleObject(characterGenerics, resources);
    module.registerCommonData(characterGenerics);
    registerTemplateParsers();
    module.addCharacterTemplates(characterGenerics);
    module.addBackgroundTemplates(characterGenerics);
    module.addAdditionalTemplateData(characterGenerics);
    characterGenerics.getModuleObjectMap().addModule(module);
  }

  private void registerTemplateParsers() {
    Collection<IAdditionalTemplateParser> allParsers = characterGenerics.getInstantiater().instantiateAll(
            CharacterTemplateParser.class);
    for (IAdditionalTemplateParser parser : allParsers) {
      String modelId = parser.getClass().getAnnotation(CharacterTemplateParser.class).modelId();
      characterGenerics.getAdditionalTemplateParserRegistry().register(modelId, parser);
    }
  }

  public ICharacterGenerics getCharacterGenerics() {
    return characterGenerics;
  }
}