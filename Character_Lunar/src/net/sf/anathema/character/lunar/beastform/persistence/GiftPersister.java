package net.sf.anathema.character.lunar.beastform.persistence;

import static net.sf.anathema.character.library.quality.persistence.IQualityPersistenceXmlConstants.ATTRIB_CREATION_ACTIVE;
import static net.sf.anathema.character.library.quality.persistence.IQualityPersistenceXmlConstants.ATTRIB_EXPERIENCE_ACTIVE;
import static net.sf.anathema.character.library.quality.persistence.IQualityPersistenceXmlConstants.ATTRIB_ID;
import static net.sf.anathema.character.library.quality.persistence.IQualityPersistenceXmlConstants.ATTRIB_VALUE;

import net.sf.anathema.character.library.quality.model.QualitySelection;
import net.sf.anathema.character.library.quality.persistence.AbstractQualityPersister;
import net.sf.anathema.character.library.quality.presenter.IQualityModel;
import net.sf.anathema.character.lunar.beastform.model.gift.IGift;
import net.sf.anathema.character.lunar.beastform.model.gift.IGiftModel;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class GiftPersister extends AbstractQualityPersister<IGift> {
  private static final String TAG_GIFTS = "Gifts"; //$NON-NLS-1$
  private static final String TAG_GIFT = "Gift"; //$NON-NLS-1$

  public void load(Element parent, IQualityModel<IGift> model) throws PersistenceException {
    Element qualityGroupElement = parent.element(getGroupElementName());
    if (qualityGroupElement == null) {
      return;
    }
    IGiftModel giftModel = (IGiftModel) model;
    for (final Element savedSelectionElement : ElementUtilities.elements(qualityGroupElement)) {
      String giftId = savedSelectionElement.attributeValue(ATTRIB_ID);
      final int value = ElementUtilities.getRequiredIntAttrib(savedSelectionElement, ATTRIB_VALUE);
      final boolean creationActive = ElementUtilities.getBooleanAttribute(
          savedSelectionElement,
          ATTRIB_CREATION_ACTIVE,
          true);
      final boolean experienceActive = ElementUtilities.getBooleanAttribute(
          savedSelectionElement,
          ATTRIB_EXPERIENCE_ACTIVE,
          false);
      model.addQualitySelection(new QualitySelection<IGift>(
          giftModel.getGiftById(giftId),
          value,
          creationActive,
          experienceActive));
    }
  }

  @Override
  protected void saveSpecials(Element element, IGift quality) {
    // Nothing to do
  }

  @Override
  protected String getGroupElementName() {
    return TAG_GIFTS;
  }

  @Override
  protected String getSingleElementName() {
    return TAG_GIFT;
  }
}