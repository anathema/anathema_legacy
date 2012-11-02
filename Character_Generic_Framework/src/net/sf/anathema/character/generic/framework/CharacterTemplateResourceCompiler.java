package net.sf.anathema.character.generic.framework;

import net.sf.anathema.character.generic.data.IExtensibleDataSet;
import net.sf.anathema.character.generic.data.IExtensibleDataSetCompiler;
import net.sf.anathema.initialization.ExtensibleDataSetCompiler;
import net.sf.anathema.lib.resources.ResourceFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ExtensibleDataSetCompiler
public class CharacterTemplateResourceCompiler implements IExtensibleDataSetCompiler {
	
	private static final String CHARACTER_FILE_RECOGNITION_PATTERN = "Character_(.+?)_(.+?)\\.xml";
	private static final String CHARACTER_DATA_EXTRACTION_PATTERN = ".*/Character_(.+?)_(.+?)\\.xml";
	  
	private final Map<String, List<ResourceFile>> templateResources = new HashMap<>();

	@Override
	public String getName() {
		return "CharacterTemplates";
	}

	@Override
	public String getRecognitionPattern() {
		return CHARACTER_FILE_RECOGNITION_PATTERN;
	}

	@Override
	public void registerFile(ResourceFile resource) throws Exception {
		Matcher matcher = Pattern.compile(CHARACTER_DATA_EXTRACTION_PATTERN).matcher(resource.getFileName());
	    matcher.matches();
	    String typeString = matcher.group(1);
	    List<ResourceFile> resources = templateResources.get(typeString);
	    if (resources == null) {
	    	resources = new ArrayList<>();
	    	templateResources.put(typeString, resources);
	    }
	    resources.add(resource);
	}

	@Override
	public IExtensibleDataSet build() {
		return new CharacterTemplateResourceCache(templateResources);
	}
}
