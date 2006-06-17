package net.sf.anathema.character.impl.persistence;

import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.ATTRIB_TYPE;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_CASTE;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_CHARACTER_CONCEPT;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_CONCEPT;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_MOTIVATION;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_NATURE;
import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.caste.ICasteTypeVisitor;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.character.model.concept.ICharacterConcept;
import net.sf.anathema.character.model.concept.IMotivation;
import net.sf.anathema.character.model.concept.INature;
import net.sf.anathema.character.model.concept.INatureType;
import net.sf.anathema.character.model.concept.IWillpowerRegainingConceptVisitor;
import net.sf.anathema.character.model.concept.NatureProvider;
import net.sf.anathema.framework.persistence.AbstractPersister;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class CharacterConceptPersister extends AbstractPersister {

  public void save(Element parent, ICharacterConcept characterConcept) {
    final Element characterConceptElement = parent.addElement(TAG_CHARACTER_CONCEPT);
    saveCaste(characterConceptElement, characterConcept.getCaste());
    characterConcept.getWillpowerRegainingConcept().accept(new IWillpowerRegainingConceptVisitor() {
      public void accept(INature nature) {
        saveNature(characterConceptElement, nature);
      }

      public void accept(IMotivation motivation) {
        saveTextualDescription(characterConceptElement, TAG_MOTIVATION, motivation.getDescription());
      }
    });
    saveTextualDescription(characterConceptElement, TAG_CONCEPT, characterConcept.getConcept());
  }

  private void saveCaste(Element parent, ITypedDescription<ICasteType< ? extends ICasteTypeVisitor>> caste) {
    ICasteType< ? extends ICasteTypeVisitor> casteType = caste.getType();
    if (casteType.getId() != null) {
      Element casteElement = parent.addElement(TAG_CASTE);
      casteElement.addAttribute(ATTRIB_TYPE, casteType.getId());
    }
  }

  private void saveNature(Element parent, INature nature) {
    Element natureElement = parent.addElement(TAG_NATURE);
    INatureType natureType = nature.getDescription().getType();
    if (natureType != null) {
      natureElement.addAttribute(ATTRIB_TYPE, natureType.getId());
    }
  }

  public void load(Element parent, ICharacterConcept characterConcept, ICasteCollection casteCollection)
      throws PersistenceException {
    final Element conceptElement = parent.element(TAG_CHARACTER_CONCEPT);
    loadCaste(conceptElement, characterConcept, casteCollection);
    characterConcept.getWillpowerRegainingConcept().accept(new IWillpowerRegainingConceptVisitor() {
      public void accept(INature nature) {
        loadNature(conceptElement, nature);
      }

      public void accept(IMotivation motivation) {
        restoreTextualDescription(conceptElement, TAG_MOTIVATION, motivation.getDescription());
      }
    });
    restoreTextualDescription(conceptElement, TAG_CONCEPT, characterConcept.getConcept());
  }

  private void loadNature(Element parent, INature nature) {
    Element natureElement = parent.element(TAG_NATURE);
    if (natureElement == null) {
      return;
    }
    String natureTypeId = natureElement.attributeValue(ATTRIB_TYPE);
    if (natureTypeId != null) {
      nature.getDescription().setType(NatureProvider.getInstance().getById(natureTypeId));
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