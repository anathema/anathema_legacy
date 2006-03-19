package net.sf.anathema.character.impl.persistence;

import net.sf.anathema.character.model.advance.IExperiencePointConfiguration;
import net.sf.anathema.character.model.advance.IExperiencePointEntry;
import net.sf.anathema.framework.persistence.AbstractPersister;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class ExperiencePointsPersister extends AbstractPersister {

  private static final String ATTRIB_POINTS = "points"; //$NON-NLS-1$
  private static final String TAG_ENTRY = "Entry"; //$NON-NLS-1$
  private static final String TAG_EXPERIENCE = "Experience"; //$NON-NLS-1$

  public void load(Element parentElement, IExperiencePointConfiguration experiencePoints) throws PersistenceException {
    Element experienceElement = parentElement.element(TAG_EXPERIENCE);
    if (experienceElement == null) {
      return;
    }
    for (Object entryElement : experienceElement.elements(TAG_ENTRY)) {
      IExperiencePointEntry entry = experiencePoints.addEntry();
      restoreEntry((Element) entryElement, entry);
    }
  }

  private void saveEntry(Element parent, IExperiencePointEntry entry) {
    Element entryElement = parent.addElement(TAG_ENTRY);
    ElementUtilities.addAttribute(entryElement, ATTRIB_POINTS, entry.getExperiencePoints());
    saveTextualDescription(entryElement, ICharacterXmlConstants.TAG_DESCRIPTION, entry.getTextualDescription());
  }

  public void save(Element parentElement, IExperiencePointConfiguration experiencePoints) {
    Element experienceElement = parentElement.addElement(TAG_EXPERIENCE);
    for (IExperiencePointEntry entry : experiencePoints.getAllEntries()) {
      saveEntry(experienceElement, entry);
    }
  }

  private void restoreEntry(Element element, IExperiencePointEntry entry) throws PersistenceException {
    entry.setExperiencePoints(ElementUtilities.getIntAttrib(element, ATTRIB_POINTS, 0));
    restoreTextualDescription(element, ICharacterXmlConstants.TAG_DESCRIPTION, entry.getTextualDescription());
  }
}