package net.sf.anathema.character.main.framework;

import net.sf.anathema.character.main.framework.data.ExtensibleDataSet;
import net.sf.anathema.character.main.framework.data.IExtensibleDataSetCompiler;
import net.sf.anathema.initialization.ExtensibleDataSetCompiler;
import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.framework.environment.resources.ResourceFile;

@ExtensibleDataSetCompiler
public class CharacterTemplateResourceCompiler implements IExtensibleDataSetCompiler {

  private static final String CHARACTER_FILE_RECOGNITION_PATTERN = "Character_(.+?)\\.xml";

  private final SimpleCharacterTemplateCache templateResources = new SimpleCharacterTemplateCache();

  @SuppressWarnings("UnusedParameters")
  public CharacterTemplateResourceCompiler(ObjectFactory objectFactory) {
    //nothing to do
  }

  @Override
  public String getName() {
    return "CharacterTemplates";
  }

  @Override
  public String getRecognitionPattern() {
    return CHARACTER_FILE_RECOGNITION_PATTERN;
  }

  @Override
  public void registerFile(ResourceFile resource) throws Exception {
    templateResources.add(resource);
  }

  @Override
  public ExtensibleDataSet build() {
    return templateResources;
  }
}