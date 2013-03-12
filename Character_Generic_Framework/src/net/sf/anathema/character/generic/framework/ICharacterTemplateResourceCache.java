package net.sf.anathema.character.generic.framework;

import net.sf.anathema.character.generic.data.IExtensibleDataSet;
import net.sf.anathema.lib.resources.ResourceFile;

public interface ICharacterTemplateResourceCache extends IExtensibleDataSet {
	ResourceFile[] getTemplateResourcesForType(String type);

	boolean isCustomTemplate(ResourceFile templateResource);
}