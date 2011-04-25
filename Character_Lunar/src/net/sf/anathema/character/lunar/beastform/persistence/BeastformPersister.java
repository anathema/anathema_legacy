package net.sf.anathema.character.lunar.beastform.persistence;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.library.trait.persistence.TraitPersister;
import net.sf.anathema.character.lunar.beastform.model.SecondEditionBeastformModel;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformAttribute;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformModel;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class BeastformPersister implements IAdditionalPersister {
  private static final String TAG_BEASTFORM = "Beastform"; //$NON-NLS-1$
  private static final String TAG_BEAST_ATTRIBUTES = "BeastAttributes"; //$NON-NLS-1$
  private static final String TAG_SPIRIT_ATTRIBUTES = "SpiritAttributes"; //$NON-NLS-1$
  private static final String ATTRIB_SPIRIT_SHAPE = "shape";
  private final TraitPersister traitPersister = new TraitPersister();

  private final GiftPersister persister = new GiftPersister();

  public void save(Element parent, IAdditionalModel model) {
    Element beastformElement = parent.addElement(TAG_BEASTFORM);
    IBeastformModel beastformModel = (IBeastformModel) model;
    saveAttributes(beastformElement, beastformModel);
    saveGifts(beastformElement, beastformModel);
    if (model instanceof SecondEditionBeastformModel)
    	beastformElement.addAttribute(ATTRIB_SPIRIT_SHAPE,
    		((SecondEditionBeastformModel)model).getSpiritForm());
  }

  private void saveGifts(Element beastformElement, IBeastformModel beastformModel) {
    persister.save(beastformElement, beastformModel.getGiftModel());
  }

  private void saveAttributes(Element beastformElement, IBeastformModel beastformModel) {
    Element attributesElement = beastformElement.addElement(TAG_BEAST_ATTRIBUTES);
    for (IBeastformAttribute attribute : beastformModel.getAttributes()) {
      traitPersister.saveTrait(attributesElement, attribute.getTrait().getType().getId(), attribute.getTrait());
    }
    if (beastformModel instanceof SecondEditionBeastformModel)
    {
    	attributesElement = beastformElement.addElement(TAG_SPIRIT_ATTRIBUTES);
    	for (IBeastformAttribute attribute : ((SecondEditionBeastformModel)beastformModel).getSpiritAttributes())
    	      traitPersister.saveTrait(attributesElement, attribute.getTrait().getType().getId(), attribute.getTrait());
    }
  }

  public void load(Element parent, IAdditionalModel model) throws PersistenceException {
    Element beastformElement = parent.element(TAG_BEASTFORM);
    IBeastformModel beastformModel = (IBeastformModel) model;
    loadAttributes(beastformElement, beastformModel);
    loadGifts(beastformElement, beastformModel);
    String shape = beastformElement.attributeValue(ATTRIB_SPIRIT_SHAPE, "");
    if (model instanceof SecondEditionBeastformModel)
    	((SecondEditionBeastformModel)model).setSpiritForm(shape);
  }

  private void loadGifts(Element beastformElement, IBeastformModel beastformModel) throws PersistenceException {
    persister.load(beastformElement, beastformModel.getGiftModel());
  }

  private void loadAttributes(Element beastformElement, IBeastformModel beastformModel) throws PersistenceException {
    Element attributesElement = beastformElement.element(TAG_BEAST_ATTRIBUTES);
    for (Element attributeElement : ElementUtilities.elements(attributesElement)) {
      String attributeTypeId = attributeElement.getName();
      IBeastformAttribute attribute = beastformModel.getAttributeByType(AttributeType.valueOf(attributeTypeId));
      traitPersister.restoreTrait(attributeElement, attribute.getTrait());
    }
    if (beastformModel instanceof SecondEditionBeastformModel)
    {
    	SecondEditionBeastformModel model = (SecondEditionBeastformModel) beastformModel;
    	attributesElement = beastformElement.element(TAG_SPIRIT_ATTRIBUTES);
    	if (attributesElement != null)
	        for (Element attributeElement : ElementUtilities.elements(attributesElement)) {
	          String attributeTypeId = attributeElement.getName();
	          IBeastformAttribute attribute = model.getSpiritAttributeByType(AttributeType.valueOf(attributeTypeId));
	          traitPersister.restoreTrait(attributeElement, attribute.getTrait());
	        }
    }
  }
}