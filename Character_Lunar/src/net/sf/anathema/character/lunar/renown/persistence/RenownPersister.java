package net.sf.anathema.character.lunar.renown.persistence;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.persistence.TraitPersister;
import net.sf.anathema.character.lunar.renown.presenter.IRenownModel;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;

public class RenownPersister implements IAdditionalPersister {

  private static final String TAG_RENOWN = "Renown"; //$NON-NLS-1$
  private final TraitPersister traitPersister = new TraitPersister();

  public void save(Element parent, IAdditionalModel model) {
    Element element = parent.addElement(TAG_RENOWN);
    IRenownModel renownModel = (IRenownModel) model;
    for (ITrait trait : renownModel.getAllTraits()) {
      traitPersister.saveTrait(element, trait.getType().getId(), trait);
    }
    ITrait face = renownModel.getFace();
    traitPersister.saveTrait(element, face.getType().getId(), face);
  }

  public void load(Element parent, IAdditionalModel model) throws PersistenceException {
    Element element = parent.element(TAG_RENOWN);
    IRenownModel renownModel = (IRenownModel) model;
    for (ITrait trait : renownModel.getAllTraits()) {
      traitPersister.restoreTrait(element, trait.getType().getId(), trait);
    }
    ITrait face = renownModel.getFace();
    traitPersister.restoreTrait(element, face.getType().getId(), face);
  }
}
