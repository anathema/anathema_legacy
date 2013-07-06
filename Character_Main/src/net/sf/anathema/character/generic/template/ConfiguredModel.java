package net.sf.anathema.character.generic.template;

import net.sf.anathema.lib.lang.ReflectionEqualsObject;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class ConfiguredModel extends ReflectionEqualsObject{

  public Identifier modelId;
  public String modelTemplateId;

  public ConfiguredModel(String modelId, String modelTemplateId) {
    this.modelId = new SimpleIdentifier(modelId);
    this.modelTemplateId = modelTemplateId;
  }
 }
