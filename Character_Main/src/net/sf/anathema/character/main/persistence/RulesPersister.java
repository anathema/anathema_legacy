package net.sf.anathema.character.main.persistence;

import org.dom4j.Element;

import static net.sf.anathema.character.main.persistence.SecondEdition.SECOND_EDITION;
import static net.sf.anathema.character.main.persistence.ICharacterXmlConstants.ATTRIB_NAME;
import static net.sf.anathema.character.main.persistence.ICharacterXmlConstants.TAG_RULES;
import static net.sf.anathema.character.main.persistence.ICharacterXmlConstants.TAG_RULESET;

public class RulesPersister {

  public void save(Element parent) {
    Element rulesElement = parent.addElement(TAG_RULES);
    Element ruleSetElement = rulesElement.addElement(TAG_RULESET);
    ruleSetElement.addAttribute(ATTRIB_NAME, SECOND_EDITION);
  }
}