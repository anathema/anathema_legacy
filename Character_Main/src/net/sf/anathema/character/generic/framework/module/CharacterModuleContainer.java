package net.sf.anathema.character.generic.framework.module;

import net.sf.anathema.character.generic.data.IExtensibleDataSetProvider;
import net.sf.anathema.character.generic.framework.CharacterGenerics;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.xml.additional.IAdditionalTemplateParser;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.ObjectFactory;
import net.sf.anathema.initialization.repository.IDataFileProvider;

import java.util.Collection;

public class CharacterModuleContainer {

  private final CharacterGenerics characterGenerics;

  public CharacterModuleContainer(IExtensibleDataSetProvider dataSetProvider, IDataFileProvider dataFileProvider, ObjectFactory objectFactory) throws
          InitializationException {
    this.characterGenerics = new CharacterGenerics(dataFileProvider, objectFactory, dataSetProvider);
  }

  public void addCharacterGenericsModule(ICharacterModule module) throws InitializationException {
    module.registerCommonData(characterGenerics);
    registerTemplateParsers();
    module.addCharacterTemplates(characterGenerics);
    module.addAdditionalTemplateData(characterGenerics);
  }

  private void registerTemplateParsers() {
    Collection<IAdditionalTemplateParser> allParsers = characterGenerics.getInstantiater().instantiateAll(CharacterTemplateParser.class);
    for (IAdditionalTemplateParser parser : allParsers) {
      String modelId = parser.getClass().getAnnotation(CharacterTemplateParser.class).modelId();
      characterGenerics.getAdditionalTemplateParserRegistry().register(modelId, parser);
    }
  }

  public ICharacterGenerics getCharacterGenerics() {
    return characterGenerics;
  }
}