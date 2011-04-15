package net.sf.anathema.character.ghost.passions;

import org.dom4j.Element;

import net.sf.anathema.character.generic.framework.xml.additional.IAdditionalTemplateParser;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;

public class GhostPassionsParser implements IAdditionalTemplateParser
{
	@Override
	public IAdditionalTemplate parse(Element element)
	{
		return new GhostPassionsTemplate();
	}
}
