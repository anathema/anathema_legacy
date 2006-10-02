package net.sf.anathema.character.generic.framework.additionaltemplate.persistence;

import net.sf.anathema.framework.messaging.IAnathemaMessaging;

public interface IAdditionalPersisterFactory {

  public IAdditionalPersister createPersister(IAnathemaMessaging messaging);
}