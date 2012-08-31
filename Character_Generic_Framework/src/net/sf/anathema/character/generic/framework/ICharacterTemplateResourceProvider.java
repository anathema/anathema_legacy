package net.sf.anathema.character.generic.framework;

import net.sf.anathema.lib.resources.ResourceFile;

public interface ICharacterTemplateResourceProvider {
	public ResourceFile[] getTemplateResourcesForType(String type);

	public boolean isCustomTemplate(ResourceFile templateResource);
}
