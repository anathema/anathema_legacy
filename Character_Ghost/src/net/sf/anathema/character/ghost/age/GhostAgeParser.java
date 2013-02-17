package net.sf.anathema.character.ghost.age;

import net.sf.anathema.character.generic.framework.xml.additional.IAdditionalTemplateParser;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import org.dom4j.Element;

public class GhostAgeParser implements IAdditionalTemplateParser
{
	@Override
	public IAdditionalTemplate parse(Element element)
	{
		return new GhostAgeTemplate();
	}
}
