package net.sf.anathema.character.lunar.beastform.persistence;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.library.trait.persistence.TraitPersister;
import net.sf.anathema.character.lunar.beastform.model.BeastformModel;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformAttribute;
import net.sf.anathema.framework.persistence.AbstractPersister;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class BeastformPersister extends AbstractPersister implements IAdditionalPersister {
  private static final String TAG_BEASTFORM = "Beastform"; //$NON-NLS-1$
  private static final String TAG_BEAST_ATTRIBUTES = "BeastAttributes"; //$NON-NLS-1$
  private final TraitPersister traitPersister = new TraitPersister();

  private final GiftPersister persister = new GiftPersister();

  public void save(Element parent, IAdditionalModel model) {
    Element beastformElement = parent.addElement(TAG_BEASTFORM);
    BeastformModel beastformModel = (BeastformModel) model;
    saveAttributes(beastformElement, beastformModel);
    saveGifts(beastformElement, beastformModel);
  }

  private void saveGifts(Element beastformElement, BeastformModel beastformModel) {
    persister.save(beastformElement, beastformModel.getGiftModel());
  }

  private void saveAttributes(Element beastformElement, BeastformModel beastformModel) {
    Element attributesElement = beastformElement.addElement(TAG_BEAST_ATTRIBUTES);
    for (IBeastformAttribute attribute : beastformModel.getAttributes()) {
      traitPersister.saveTrait(attributesElement, attribute.getTrait().getType().getId(), attribute.getTrait());
    }
  }

  public void load(Element parent, IAdditionalModel model) throws PersistenceException {
    Element beastformElement = parent.element(TAG_BEASTFORM);
    BeastformModel beastformModel = (BeastformModel) model;
    loadAttributes(beastformElement, beastformModel);
    loadGifts(beastformElement, beastformModel);
  }

  private void loadGifts(Element beastformElement, BeastformModel beastformModel) throws PersistenceException {
    persister.load(beastformElement, beastformModel.getGiftModel());
  }

  private void loadAttributes(Element beastformElement, BeastformModel beastformModel) throws PersistenceException {
    Element attributesElement = beastformElement.element(TAG_BEAST_ATTRIBUTES);
    for (Element attributeElement : ElementUtilities.elements(attributesElement)) {
      String attributeTypeId = attributeElement.getName();
      IBeastformAttribute attribute = beastformModel.getAttributeByType(AttributeType.valueOf(attributeTypeId));
      traitPersister.restoreTrait(attributeElement, attribute.getTrait());
    }
  }
}