package net.sf.anathema.character.lunar.renown.persistence;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.persistence.AbstractCharacterPersister;
import net.sf.anathema.character.lunar.renown.presenter.IRenownModel;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;

public class RenownPersister extends AbstractCharacterPersister implements IAdditionalPersister {

  private static final String TAG_RENOWN = "Renown"; //$NON-NLS-1$

  public void save(Element parent, IAdditionalModel model) {
    Element element = parent.addElement(TAG_RENOWN);
    IRenownModel renownModel = (IRenownModel) model;
    for (ITrait trait : renownModel.getAllTraits()) {
      saveTrait(element, trait.getType().getId(), trait);
    }
    ITrait face = renownModel.getFace();
    saveTrait(element, face.getType().getId(), face);
  }

  public void load(Element parent, IAdditionalModel model) throws PersistenceException {
    Element element = parent.element(TAG_RENOWN);
    IRenownModel renownModel = (IRenownModel) model;
    for (ITrait trait : renownModel.getAllTraits()) {
      restoreTrait(element, trait.getType().getId(), trait);
    }
    ITrait face = renownModel.getFace();
    restoreTrait(element, face.getType().getId(), face);
  }
}
