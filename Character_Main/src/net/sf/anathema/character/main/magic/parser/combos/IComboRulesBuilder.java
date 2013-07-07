package net.sf.anathema.character.main.magic.parser.combos;

import net.sf.anathema.character.main.magic.model.charm.CharmException;
import net.sf.anathema.character.main.magic.model.combos.IComboRestrictions;
import org.dom4j.Element;

public interface IComboRulesBuilder {

  IComboRestrictions buildComboRules(Element rulesElement) throws CharmException;
}