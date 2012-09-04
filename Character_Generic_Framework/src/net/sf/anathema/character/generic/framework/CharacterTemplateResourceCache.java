package net.sf.anathema.character.generic.framework;

import java.util.List;
import java.util.Map;

import net.sf.anathema.lib.resources.ResourceFile;

public class CharacterTemplateResourceCache implements ICharacterTemplateResourceCache {
	
	private final Map<String, List<ResourceFile>> templateResources;
	private final static String CUSTOM_PATH = "repository/custom";
	
	public CharacterTemplateResourceCache(Map<String, List<ResourceFile>> templateResources) {
		this.templateResources = templateResources;
	}
	
	@Override
	public ResourceFile[] getTemplateResourcesForType(String type) {
		return templateResources.get(type).toArray(new ResourceFile[0]);
	}

	@Override
	public boolean isCustomTemplate(ResourceFile resource) {
		//TODO: Search for a safer means to evaluate custom content
		return resource.getURL().toString().contains(CUSTOM_PATH);
	}
}
