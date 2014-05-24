package net.sf.anathema.hero.type;

import net.sf.anathema.character.framework.data.ExtensibleDataSet;
import net.sf.anathema.character.framework.data.IExtensibleDataSetCompiler;
import net.sf.anathema.character.framework.data.IExtensibleDataSetProvider;
import net.sf.anathema.character.framework.type.CharacterType;
import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.framework.environment.dependencies.Weight;
import net.sf.anathema.framework.environment.resources.ResourceFile;
import net.sf.anathema.initialization.ExtensibleDataSetCompiler;

@Weight(weight = 1)
@ExtensibleDataSetCompiler
public class HeroTypeCompiler implements IExtensibleDataSetCompiler {

  private static final String TEMPLATE_FILE_RECOGNITION_PATTERN = "(.+?)\\.charactertype";
  private final ExtensibleCharacterTypes types = new ExtensibleCharacterTypes();
  private final CharacterTypeGson gson = new CharacterTypeGson();

  @SuppressWarnings("UnusedParameters")
  public HeroTypeCompiler(ObjectFactory objectFactory, IExtensibleDataSetProvider provider) {
    //nothing to do
  }

  @Override
  public String getName() {
    return "Character types";
  }

  @Override
  public String getRecognitionPattern() {
    return TEMPLATE_FILE_RECOGNITION_PATTERN;
  }

  @Override
  public void registerFile(ResourceFile resource) throws Exception {
    CharacterType type = gson.fromJson(resource.getURL());
    types.add(type);
  }

  @Override
  public ExtensibleDataSet build() {
    return types;
  }
}