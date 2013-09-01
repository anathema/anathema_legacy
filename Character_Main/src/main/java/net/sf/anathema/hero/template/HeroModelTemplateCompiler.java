package net.sf.anathema.hero.template;

import net.sf.anathema.character.main.framework.data.ExtensibleDataSet;
import net.sf.anathema.character.main.framework.data.IExtensibleDataSetCompiler;
import net.sf.anathema.initialization.ExtensibleDataSetCompiler;
import net.sf.anathema.initialization.ObjectFactory;
import net.sf.anathema.framework.environment.resources.ResourceFile;

@ExtensibleDataSetCompiler
public class HeroModelTemplateCompiler implements IExtensibleDataSetCompiler {

  private static final String TEMPLATE_FILE_RECOGNITION_PATTERN = "(.+?)\\.template";
  private HeroModelTemplateCache templates = new HeroModelTemplateCache();


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
    templates.add(resource);
  }

  @Override
  public ExtensibleDataSet build() {
    return templates;
  }
}