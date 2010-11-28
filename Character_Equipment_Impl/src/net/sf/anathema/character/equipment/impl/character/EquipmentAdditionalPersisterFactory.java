package net.sf.anathema.character.equipment.impl.character;

import net.sf.anathema.character.equipment.impl.character.persister.EquipmentAdditionalModelPersister;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.framework.messaging.IAnathemaMessaging;

public class EquipmentAdditionalPersisterFactory implements IAdditionalPersisterFactory {

  public IAdditionalPersister createPersister(IAnathemaMessaging messaging) {
    return new EquipmentAdditionalModelPersister(messaging);
  }
}