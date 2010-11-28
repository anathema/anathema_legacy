package net.sf.anathema.character.meritsflaws.persistence;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.meritsflaws.presenter.IMeritsFlawsAdditionalModel;
import net.sf.anathema.character.meritsflaws.presenter.IMeritsFlawsModel;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;

public class MeritsFlawsAdditionalPersister implements IAdditionalPersister {

  private final MeritsFlawsPersister persister = new MeritsFlawsPersister();

  public void save(Element parent, IAdditionalModel model) {
    IMeritsFlawsModel perkModel = ((IMeritsFlawsAdditionalModel) model).getMeritsFlawsModel();
    persister.save(parent, perkModel);
  }

  public void load(Element parent, IAdditionalModel model) throws PersistenceException {
    IMeritsFlawsModel perkModel = ((IMeritsFlawsAdditionalModel) model).getMeritsFlawsModel();
    persister.load(parent, perkModel);
  }
}