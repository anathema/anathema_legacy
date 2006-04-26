package net.sf.anathema.character.library.quality.persistence;

import static net.sf.anathema.character.library.quality.persistence.IQualityPersistenceXmlConstants.ATTRIB_CREATION_ACTIVE;
import static net.sf.anathema.character.library.quality.persistence.IQualityPersistenceXmlConstants.ATTRIB_EXPERIENCE_ACTIVE;
import static net.sf.anathema.character.library.quality.persistence.IQualityPersistenceXmlConstants.ATTRIB_ID;
import static net.sf.anathema.character.library.quality.persistence.IQualityPersistenceXmlConstants.ATTRIB_TYPE;
import static net.sf.anathema.character.library.quality.persistence.IQualityPersistenceXmlConstants.ATTRIB_VALUE;
import static net.sf.anathema.character.library.quality.persistence.IQualityPersistenceXmlConstants.TAG_CATEGORIZATION;
import net.sf.anathema.character.library.quality.presenter.IQuality;
import net.sf.anathema.character.library.quality.presenter.IQualityModel;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public abstract class AbstractQualityPersister<Q extends IQuality> implements IQualityPersister<Q> {

  public void save(Element parent, IQualityModel<Q> model) {
    Element perksElement = parent.addElement(getGroupElementName());
    for (IQualitySelection<Q> selection : model.getSelectedQualities()) {
      saveSelection(perksElement, selection);
    }
  }

  private void saveSelection(Element parent, IQualitySelection<Q> selection) {
    Element element = parent.addElement(getSingleElementName());
    Q quality = selection.getQuality();
    element.addAttribute(ATTRIB_ID, quality.getId());
    ElementUtilities.addAttribute(element, ATTRIB_VALUE, selection.getPointValue());
    ElementUtilities.addAttribute(element, ATTRIB_CREATION_ACTIVE, selection.isCreationActive());
    ElementUtilities.addAttribute(element, ATTRIB_EXPERIENCE_ACTIVE, selection.isExperienceActive());
    Element categorizationElement = element.addElement(TAG_CATEGORIZATION);
    categorizationElement.addAttribute(ATTRIB_TYPE, quality.getType().getId());
    saveSpecials(element, quality);
  }

  protected abstract void saveSpecials(Element element, Q quality);

  protected abstract String getGroupElementName();

  protected abstract String getSingleElementName();
}