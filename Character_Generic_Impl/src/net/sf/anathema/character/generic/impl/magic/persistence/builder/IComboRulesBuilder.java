package net.sf.anathema.character.generic.impl.magic.persistence.builder;

import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.magic.charms.IComboRestrictions;

import org.dom4j.Element;

public interface IComboRulesBuilder {

  public IComboRestrictions buildComboRules(Element rulesElement) throws CharmException;
}