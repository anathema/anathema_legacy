package net.sf.anathema.character.generic.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.initialization.reflections.ResourceLoader;
import net.sf.anathema.lib.resources.ResourceFile;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class CharacterTemplateResourceProvider implements ICharacterTemplateResourceProvider {
	
	private final static String TEMPLATE_XML_STRING = ".*.template";
	private final static String TAG_TEMPLATE = "exaltedCharacterTemplate";
	private final static String ATTRIB_TYPE = "characterType";
	private final SAXReader reader = new SAXReader();
	  
	private final Map<String, List<ResourceFile>> templateResources = new HashMap<String, List<ResourceFile>>();
	
	public CharacterTemplateResourceProvider(ResourceLoader loader) {
		for(ResourceFile file : loader.getResourcesMatching(TEMPLATE_XML_STRING)) {
			try {
				  Document template = reader.read(file.getURL());
				  Element root = template.getRootElement();
				  if (root.getName().equals(TAG_TEMPLATE)) {
					  String type = root.attributeValue(ATTRIB_TYPE);
					  List<ResourceFile> typeList = templateResources.get(type);
					  if (typeList == null) {
						  typeList = new ArrayList<ResourceFile>();
						  templateResources.put(type, typeList);
					  }
					  typeList.add(file);
				  }
			  } catch (DocumentException e) {
			  }
		}
	}
	
	public ResourceFile[] getTemplateResourcesForType(String type) {
		return templateResources.get(type).toArray(new ResourceFile[0]);
	}
}
