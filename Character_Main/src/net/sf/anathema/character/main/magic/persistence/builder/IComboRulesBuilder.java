package net.sf.anathema.character.main.magic.persistence.builder;

import net.sf.anathema.character.main.magic.charms.CharmException;
import net.sf.anathema.character.main.magic.charms.IComboRestrictions;
import org.dom4j.Element;

public interface IComboRulesBuilder {

  IComboRestrictions buildComboRules(Element rulesElement) throws CharmException;
}