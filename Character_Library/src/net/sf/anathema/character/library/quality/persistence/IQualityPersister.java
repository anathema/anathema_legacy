package net.sf.anathema.character.library.quality.persistence;

import net.sf.anathema.character.library.quality.presenter.IQuality;
import net.sf.anathema.character.library.quality.presenter.IQualityModel;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;

public interface IQualityPersister<Q extends IQuality> {

  public void save(Element parent, IQualityModel<Q> model);

  public void load(Element parent, IQualityModel<Q> model) throws PersistenceException;
}