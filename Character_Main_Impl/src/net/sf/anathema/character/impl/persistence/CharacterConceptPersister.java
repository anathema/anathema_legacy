package net.sf.anathema.character.impl.persistence;

import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.ATTRIB_TYPE;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_CASTE;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_CHARACTER_CONCEPT;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_CONCEPT;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_NATURE;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.character.model.concept.ICharacterConcept;
import net.sf.anathema.character.model.concept.INatureProvider;
import net.sf.anathema.character.model.concept.INatureType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.workflow.textualdescription.ISimpleTextualDescription;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class CharacterConceptPersister {

  private final INatureProvider natureProvider;

  public CharacterConceptPersister(INatureProvider provider) {
    this.natureProvider = provider;
  }

  public void save(Element parent, ICharacterConcept characterConcept) {
    Element characterConceptElement = parent.addElement(TAG_CHARACTER_CONCEPT);
    saveCaste(characterConceptElement, characterConcept.getCaste());
    saveNature(characterConceptElement, characterConcept.getNature());
    saveConcept(characterConceptElement, characterConcept.getConcept());
  }

  private void saveConcept(Element parent, ISimpleTextualDescription concept) {
    Element conceptElement = parent.addElement(TAG_CONCEPT);
    String text = concept.getText();
    if (StringUtilities.isNullOrEmpty(text)) {
      return;
    }
    conceptElement.addText(text);
  }

  private void saveCaste(Element parent, ITypedDescription<ICasteType> caste) {
    ICasteType casteType = caste.getType();
    if (casteType.getId() != null) {
      Element casteElement = parent.addElement(TAG_CASTE);
      casteElement.addAttribute(ATTRIB_TYPE, casteType.getId());
    }
  }

  private void saveNature(Element parent, ITypedDescription<INatureType> nature) {
    Element natureElement = parent.addElement(TAG_NATURE);
    INatureType natureType = nature.getType();
    if (natureType != null) {
      natureElement.addAttribute(ATTRIB_TYPE, natureType.getId());
    }
  }

  public void load(Element parent, ICharacterConcept characterConcept, ICasteCollection casteCollection)
      throws PersistenceException {
    Element conceptElement = parent.element(TAG_CHARACTER_CONCEPT);
    loadCaste(conceptElement, characterConcept, casteCollection);
    loadNature(conceptElement, characterConcept);
    loadConcept(conceptElement, characterConcept);
  }

  private void loadConcept(Element parent, ICharacterConcept characterConcept) {
    Element conceptElement = parent.element(TAG_CONCEPT);
    Element textualElement = conceptElement;
    if (textualElement != null) {
      characterConcept.getConcept().setText(textualElement.getText());
    }
  }

  private void loadNature(Element parent, ICharacterConcept characterConcept) {
    Element natureElement = parent.element(TAG_NATURE);
    if (natureElement == null) {
      return;
    }
    String natureTypeId = natureElement.attributeValue(ATTRIB_TYPE);
    if (natureTypeId != null) {
      characterConcept.getNature().setType(natureProvider.getById(natureTypeId));
    }
  }

  private void loadCaste(Element parent, ICharacterConcept characterConcept, ICasteCollection casteCollection)
      throws PersistenceException {
    Element casteElement = parent.element(TAG_CASTE);
    if (casteElement == null) {
      return;
    }
    String casteTypeId = ElementUtilities.getRequiredAttrib(casteElement, ATTRIB_TYPE);
    characterConcept.getCaste().setType(casteCollection.getById(casteTypeId));
  }
}