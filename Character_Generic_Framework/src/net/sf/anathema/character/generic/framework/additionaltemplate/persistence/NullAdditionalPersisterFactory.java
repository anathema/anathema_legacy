package net.sf.anathema.character.generic.framework.additionaltemplate.persistence;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.framework.messaging.IMessaging;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;

public class NullAdditionalPersisterFactory implements IAdditionalPersisterFactory {

  @Override
  public IAdditionalPersister createPersister(IMessaging messaging) {
    return new IAdditionalPersister() {
      @Override
      public void load(Element parent, IAdditionalModel model) throws PersistenceException {
        //nothing to do;        
      }

      @Override
      public void save(Element parent, IAdditionalModel model) {
        //nothing to do;
      }
    };
  }
}