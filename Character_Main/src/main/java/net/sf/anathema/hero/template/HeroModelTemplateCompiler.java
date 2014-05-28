package net.sf.anathema.hero.template;

import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.framework.environment.resources.ResourceFile;
import net.sf.anathema.hero.framework.data.ExtensibleDataSet;
import net.sf.anathema.hero.framework.data.IExtensibleDataSetCompiler;
import net.sf.anathema.hero.framework.data.IExtensibleDataSetProvider;
import net.sf.anathema.initialization.ExtensibleDataSetCompiler;

@ExtensibleDataSetCompiler
public class HeroModelTemplateCompiler implements IExtensibleDataSetCompiler {

  private static final String TEMPLATE_FILE_RECOGNITION_PATTERN = "(.+?)\\.template";
  private HeroModelTemplateCache templates = new HeroModelTemplateCache();


  @SuppressWarnings("UnusedParameters")
  public HeroModelTemplateCompiler(ObjectFactory objectFactory, IExtensibleDataSetProvider provider) {
    //nothing to do
  }

  @Override
  public String getName() {
    return "Character model template extensions";
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