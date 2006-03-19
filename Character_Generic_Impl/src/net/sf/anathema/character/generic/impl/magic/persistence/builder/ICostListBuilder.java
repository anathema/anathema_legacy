package net.sf.anathema.character.generic.impl.magic.persistence.builder;

import net.sf.anathema.character.generic.magic.general.ICostList;
import net.sf.anathema.character.generic.magic.general.IPermanentCostList;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;

public interface ICostListBuilder {

  public ICostList buildTemporaryCostList(Element costListElement) throws PersistenceException;

  public IPermanentCostList buildPermanentCostList(Element costListElement) throws PersistenceException;

}
