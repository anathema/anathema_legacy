package net.sf.anathema.character.impl.persistence;

import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.main.model.concept.CharacterConcept;
import net.sf.anathema.character.main.model.description.HeroDescription;
import net.sf.anathema.character.model.IIntegerDescription;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.framework.persistence.TextPersister;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.ATTRIB_AGE;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.ATTRIB_TYPE;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_CASTE;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_CHARACTER_CONCEPT;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_CONCEPT;

public class CharacterConceptPersister {

  private final TextPersister textPersister = new TextPersister();

  public void save(Element parent, CharacterConcept characterConcept) {
    Element characterConceptElement = parent.addElement(TAG_CHARACTER_CONCEPT);
    saveCaste(characterConceptElement, characterConcept.getCaste());
    saveAge(characterConceptElement, characterConcept.getAge());
  }

  private void saveCaste(Element parent, ITypedDescription<ICasteType> caste) {
    ICasteType casteType = caste.getType();
    if (casteType.getId() != null) {
      Element casteElement = parent.addElement(TAG_CASTE);
      casteElement.addAttribute(ATTRIB_TYPE, casteType.getId());
    }
  }

  private void saveAge(Element parent, IIntegerDescription age) {
    parent.addAttribute(ATTRIB_AGE, Integer.toString(age.getValue()));
  }

  public void load(Element parent, CharacterConcept characterConcept, HeroDescription description, ICasteCollection casteCollection) throws
          PersistenceException {
    Element conceptElement = parent.element(TAG_CHARACTER_CONCEPT);
    loadCaste(conceptElement, characterConcept, casteCollection);
    loadAge(conceptElement, characterConcept);
    textPersister.restoreTextualDescription(conceptElement, TAG_CONCEPT, description.getConcept());
  }

  private void loadCaste(Element parent, CharacterConcept characterConcept, ICasteCollection casteCollection) throws PersistenceException {
    Element casteElement = parent.element(TAG_CASTE);
    if (casteElement == null) {
      return;
    }
    String casteTypeId = ElementUtilities.getRequiredAttrib(casteElement, ATTRIB_TYPE);
    characterConcept.getCaste().setType(casteCollection.getById(casteTypeId));
  }

  private void loadAge(Element parent, CharacterConcept characterConcept) throws PersistenceException {
    int age = ElementUtilities.getIntAttrib(parent, ATTRIB_AGE, 0);
    characterConcept.getAge().setValue(age);
  }
}