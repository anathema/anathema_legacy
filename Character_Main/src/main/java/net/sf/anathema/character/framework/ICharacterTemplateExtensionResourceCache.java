package net.sf.anathema.character.framework;

import net.sf.anathema.framework.environment.resources.ResourceFile;
import net.sf.anathema.hero.framework.data.ExtensibleDataSet;

public interface ICharacterTemplateExtensionResourceCache extends ExtensibleDataSet {
  ResourceFile getTemplateResource(String fileName);
}