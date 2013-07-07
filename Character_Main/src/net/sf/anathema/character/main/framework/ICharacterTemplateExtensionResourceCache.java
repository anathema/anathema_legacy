package net.sf.anathema.character.main.framework;

import net.sf.anathema.character.main.data.IExtensibleDataSet;
import net.sf.anathema.lib.resources.ResourceFile;

public interface ICharacterTemplateExtensionResourceCache extends IExtensibleDataSet {
  ResourceFile getTemplateResource(String fileName);
}
