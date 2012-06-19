package net.sf.anathema.character.generic.framework.additionaltemplate.persistence;

import net.sf.anathema.framework.messaging.IMessaging;

public interface IAdditionalPersisterFactory {

  IAdditionalPersister createPersister(IMessaging messaging);
}