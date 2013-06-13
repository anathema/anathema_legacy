package net.sf.anathema.character.equipment.impl.character;

import net.sf.anathema.character.equipment.impl.character.persister.EquipmentAdditionalModelPersister;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.framework.messaging.IMessaging;

public class EquipmentAdditionalPersisterFactory implements IAdditionalPersisterFactory {

  @Override
  public IAdditionalPersister createPersister(IMessaging messaging) {
    return new EquipmentAdditionalModelPersister(messaging);
  }
}