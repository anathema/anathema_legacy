package net.sf.anathema.character.impl.persistence;

import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.ATTRIB_NAME;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_RULES;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_RULESET;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class RulesPersister {

  public void save(Element parent, IExaltedRuleSet rules) {
    Element rulesElement = parent.addElement(TAG_RULES);
    Element ruleSetElement = rulesElement.addElement(TAG_RULESET);
    ruleSetElement.addAttribute(ATTRIB_NAME, rules.getId());
  }

  public IExaltedRuleSet load(Element parent) throws PersistenceException {
    Element rulesElement = parent.element(TAG_RULES);
    if (rulesElement == null) {
      return ExaltedRuleSet.CoreRules;
    }
    Element ruleSetElement = rulesElement.element(TAG_RULESET);
    String ruleSetId = ElementUtilities.getRequiredAttrib(ruleSetElement, ATTRIB_NAME);
    return ExaltedRuleSet.valueOf(ruleSetId);
  }
}