package net.sf.anathema.character.impl.persistence;

import org.dom4j.Element;

import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.ATTRIB_NAME;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_RULES;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_RULESET;

public class RulesPersister {

  public static final String SECOND_EDITION = "SecondEdition";

  public void save(Element parent) {
    Element rulesElement = parent.addElement(TAG_RULES);
    Element ruleSetElement = rulesElement.addElement(TAG_RULESET);
    ruleSetElement.addAttribute(ATTRIB_NAME, SECOND_EDITION);
  }
}