package net.sf.anathema.character.abyssal.resonance;

import net.sf.anathema.character.abyssal.resonance.AbyssalResonanceTemplate;
import net.sf.anathema.character.generic.framework.xml.additional.IAdditionalTemplateParser;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;

import org.dom4j.Element;

public class AbyssalResonanceParser implements IAdditionalTemplateParser {

  public IAdditionalTemplate parse(Element element) {
    return new AbyssalResonanceTemplate();
  }
}
