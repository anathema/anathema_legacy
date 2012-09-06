package net.sf.anathema.character.generic.framework;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.data.IExtensibleDataSet;
import net.sf.anathema.character.generic.data.IExtensibleDataSetCompiler;
import net.sf.anathema.initialization.ExtensibleDataSetCompiler;
import net.sf.anathema.lib.resources.ResourceFile;

@ExtensibleDataSetCompiler
public class CharacterTemplateExtensionResourceCompiler implements IExtensibleDataSetCompiler {
	
	private static final String TEMPLATE_FILE_RECOGNITION_PATTERN = "(.+?)\\.template";
	  
	private final List<ResourceFile> templateResources = new ArrayList<ResourceFile>();

	@Override
	public String getName() {
		return "CharacterTemplateExtensions";
	}

	@Override
	public String getRecognitionPattern() {
		return TEMPLATE_FILE_RECOGNITION_PATTERN;
	}

	@Override
	public void registerFile(ResourceFile resource) throws Exception {
		templateResources.add(resource);
	}

	@Override
	public IExtensibleDataSet build() {
		return new CharacterTemplateExtensionResourceCache(templateResources);
	}
}
