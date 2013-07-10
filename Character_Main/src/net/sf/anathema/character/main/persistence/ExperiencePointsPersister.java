package net.sf.anathema.character.main.persistence;

import net.sf.anathema.character.main.advance.ExperiencePointConfiguration;
import net.sf.anathema.character.main.advance.ExperiencePointEntry;
import net.sf.anathema.framework.persistence.TextPersister;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

public class ExperiencePointsPersister {

  private static final String ATTRIB_POINTS = "points";
  private static final String TAG_ENTRY = "Entry";
  private static final String TAG_EXPERIENCE = "Experience";

  private final TextPersister textPersister = new TextPersister();

  public void load(Element parentElement, ExperiencePointConfiguration experiencePoints) throws PersistenceException {
    Element experienceElement = parentElement.element(TAG_EXPERIENCE);
    if (experienceElement == null) {
      return;
    }
    for (Object entryElement : experienceElement.elements(TAG_ENTRY)) {
      ExperiencePointEntry entry = experiencePoints.addEntry();
      restoreEntry((Element) entryElement, entry);
    }
  }

  private void saveEntry(Element parent, ExperiencePointEntry entry) {
    Element entryElement = parent.addElement(TAG_ENTRY);
    ElementUtilities.addAttribute(entryElement, ATTRIB_POINTS, entry.getExperiencePoints());
    textPersister.saveTextualDescription(entryElement, ICharacterXmlConstants.TAG_DESCRIPTION, entry.getTextualDescription());
  }

  public void save(Element parentElement, ExperiencePointConfiguration experiencePoints) {
    Element experienceElement = parentElement.addElement(TAG_EXPERIENCE);
    for (ExperiencePointEntry entry : experiencePoints.getAllEntries()) {
      saveEntry(experienceElement, entry);
    }
  }

  private void restoreEntry(Element element, ExperiencePointEntry entry) throws PersistenceException {
    entry.setExperiencePoints(ElementUtilities.getIntAttrib(element, ATTRIB_POINTS, 0));
    textPersister.restoreTextualDescription(element, ICharacterXmlConstants.TAG_DESCRIPTION, entry.getTextualDescription());
  }
}