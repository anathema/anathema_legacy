package net.sf.anathema.character.lunar;

import net.sf.anathema.character.generic.framework.xml.additional.IAdditionalTemplateParser;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.lunar.beastform.BeastformTemplate;

import org.dom4j.Element;

public class LunarBeastformParser implements IAdditionalTemplateParser {

  public IAdditionalTemplate parse(Element element) {
    return new BeastformTemplate();
  }
}