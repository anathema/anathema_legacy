package net.sf.anathema.character.main.framework;

import net.sf.anathema.character.main.framework.data.ExtensibleDataSet;
import net.sf.anathema.lib.resources.ResourceFile;

public interface ICharacterTemplateExtensionResourceCache extends ExtensibleDataSet {
  ResourceFile getTemplateResource(String fileName);
}
