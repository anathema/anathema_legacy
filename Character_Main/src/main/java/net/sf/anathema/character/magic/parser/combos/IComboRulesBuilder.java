package net.sf.anathema.character.magic.parser.combos;

import net.sf.anathema.character.magic.charm.CharmException;
import net.sf.anathema.character.magic.charm.combos.IComboRestrictions;
import org.dom4j.Element;

public interface IComboRulesBuilder {

  IComboRestrictions buildComboRules(Element rulesElement) throws CharmException;
}