package net.sf.anathema.character.sidereal.flawedfate;

import net.sf.anathema.character.generic.framework.xml.additional.IAdditionalTemplateParser;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;

import org.dom4j.Element;

public class SiderealFlawedFateParser implements IAdditionalTemplateParser {

  public IAdditionalTemplate parse(Element element) {
    return new SiderealFlawedFateTemplate();
  }
}