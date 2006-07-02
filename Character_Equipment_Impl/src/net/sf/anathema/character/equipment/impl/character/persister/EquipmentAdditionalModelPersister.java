package net.sf.anathema.character.equipment.impl.character.persister;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;

public class EquipmentAdditionalModelPersister implements IAdditionalPersister {

  public void load(Element parent, IAdditionalModel model) throws PersistenceException {
    //Nothing to do
  }

  public void save(Element parent, IAdditionalModel model) {
    //Nothing to do
  }
}