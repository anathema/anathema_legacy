package net.sf.anathema.character.infernal.urge;

import net.sf.anathema.character.generic.framework.xml.additional.IAdditionalTemplateParser;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;

import org.dom4j.Element;

public class InfernalUrgeParser implements IAdditionalTemplateParser {

  public IAdditionalTemplate parse(Element element) {
    return new InfernalUrgeTemplate();
  }
}