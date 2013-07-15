package net.sf.anathema.character.main.framework;

import net.sf.anathema.character.main.framework.data.ExtensibleDataSet;
import net.sf.anathema.character.main.framework.data.IExtensibleDataSetCompiler;
import net.sf.anathema.initialization.ExtensibleDataSetCompiler;
import net.sf.anathema.initialization.ObjectFactory;
import net.sf.anathema.lib.resources.ResourceFile;

import java.util.ArrayList;
import java.util.List;

@ExtensibleDataSetCompiler
public class CharacterTemplateExtensionResourceCompiler implements IExtensibleDataSetCompiler {

  private static final String TEMPLATE_FILE_RECOGNITION_PATTERN = "(.+?)\\.template";
  private final List<ResourceFile> templateResources = new ArrayList<>();

  @SuppressWarnings("UnusedParameters")
  public CharacterTemplateExtensionResourceCompiler(ObjectFactory objectFactory) {
    //nothing to do
  }

  @Override
  public String getName() {
    return "CharacterTemplateExtensions";
  }

  @Override
  public String getRecognitionPattern() {
    return TEMPLATE_FILE_RECOGNITION_PATTERN;
  }

  @Override
  public void registerFile(ResourceFile resource) throws Exception {
    templateResources.add(resource);
  }

  @Override
  public ExtensibleDataSet build() {
    return new CharacterTemplateExtensionResourceCache(templateResources);
  }
}
