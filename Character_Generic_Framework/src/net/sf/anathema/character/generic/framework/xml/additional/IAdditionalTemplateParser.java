package net.sf.anathema.character.generic.framework.xml.additional;

import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import org.dom4j.Element;

public interface IAdditionalTemplateParser {

  IAdditionalTemplate parse(Element element);
}