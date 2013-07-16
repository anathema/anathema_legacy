package net.sf.anathema.character.main.magic.parser.combos;

import net.sf.anathema.character.main.magic.charm.CharmException;
import net.sf.anathema.character.main.magic.combos.IComboRestrictions;
import org.dom4j.Element;

public interface IComboRulesBuilder {

  IComboRestrictions buildComboRules(Element rulesElement) throws CharmException;
}