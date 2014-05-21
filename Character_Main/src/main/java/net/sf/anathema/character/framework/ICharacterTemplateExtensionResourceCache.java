package net.sf.anathema.character.framework;

import net.sf.anathema.character.framework.data.ExtensibleDataSet;
import net.sf.anathema.framework.environment.resources.ResourceFile;

public interface ICharacterTemplateExtensionResourceCache extends ExtensibleDataSet {
  ResourceFile getTemplateResource(String fileName);
}
