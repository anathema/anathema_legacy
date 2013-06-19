package net.sf.anathema.hero.template;

import net.sf.anathema.character.generic.data.IExtensibleDataSet;
import net.sf.anathema.character.generic.data.IExtensibleDataSetCompiler;
import net.sf.anathema.initialization.ExtensibleDataSetCompiler;
import net.sf.anathema.initialization.ObjectFactory;
import net.sf.anathema.lib.resources.ResourceFile;

import java.util.ArrayList;
import java.util.List;

@ExtensibleDataSetCompiler
public class HeroModelTemplateCompiler implements IExtensibleDataSetCompiler {

  private static final String TEMPLATE_FILE_RECOGNITION_PATTERN = "(.+?)\\.cmt";

  private final List<ResourceFile> templateResources = new ArrayList<>();

  @SuppressWarnings("UnusedParameters")
  public HeroModelTemplateCompiler(ObjectFactory objectFactory) {
    //nothing to do
  }

  @Override
  public String getName() {
    return "CharacterModelTemplateExtensions";
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
  public IExtensibleDataSet build() {
    return new HeroModelTemplateCache(templateResources);
  }
}
