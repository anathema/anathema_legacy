package net.sf.anathema.character.impl.persistence;

import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_CHARACTERIZATION;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_CHARACTER_NAME;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_DESCRIPTION;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_NOTES;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_PERIPHRASE;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_PHYSICAL_DESCRIPTION;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_PLAYER;

import net.sf.anathema.character.library.trait.persistence.AbstractCharacterPersister;
import net.sf.anathema.character.model.ICharacterDescription;

import org.dom4j.Element;

public class CharacterDescriptionPersister extends AbstractCharacterPersister {

  public void load(Element parent, ICharacterDescription description) {
    Element descriptionElement = parent.element(TAG_DESCRIPTION);
    if (descriptionElement == null) {
      return;
    }
    restoreTextualDescription(descriptionElement, TAG_CHARACTER_NAME, description.getName());
    restoreTextualDescription(descriptionElement, TAG_PLAYER, description.getPlayer());
    restoreTextualDescription(descriptionElement, TAG_CHARACTERIZATION, description.getCharacterization());
    restoreTextualDescription(descriptionElement, TAG_PHYSICAL_DESCRIPTION, description.getPhysicalDescription());
    restoreTextualDescription(descriptionElement, TAG_PERIPHRASE, description.getPeriphrase());
    restoreTextualDescription(descriptionElement, TAG_NOTES, description.getNotes());
  }

  public void save(Element parent, ICharacterDescription description) {
    Element descriptionElement = parent.addElement(TAG_DESCRIPTION);
    saveNonEmptyText(descriptionElement, TAG_CHARACTER_NAME, description.getName().getText());
    saveNonEmptyText(descriptionElement, TAG_PLAYER, description.getPlayer().getText());
    saveNonEmptyText(descriptionElement, TAG_CHARACTERIZATION, description.getCharacterization().getText());
    saveNonEmptyText(descriptionElement, TAG_PHYSICAL_DESCRIPTION, description.getPhysicalDescription().getText());
    saveNonEmptyText(descriptionElement, TAG_PERIPHRASE, description.getPeriphrase().getText());
    saveNonEmptyText(descriptionElement, TAG_NOTES, description.getNotes().getText());
  }
}