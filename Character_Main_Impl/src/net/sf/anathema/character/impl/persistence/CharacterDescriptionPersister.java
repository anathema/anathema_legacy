package net.sf.anathema.character.impl.persistence;

import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_CHARACTERIZATION;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_CHARACTER_NAME;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_DESCRIPTION;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_NOTES;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_PERIPHRASE;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_PHYSICAL_DESCRIPTION;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_PLAYER;
import net.sf.anathema.character.model.ICharacterDescription;
import net.sf.anathema.framework.persistence.TextPersister;

import org.dom4j.Element;

public class CharacterDescriptionPersister {
  private final TextPersister textPersister = new TextPersister();

  public void load(Element parent, ICharacterDescription description) {
    Element descriptionElement = parent.element(TAG_DESCRIPTION);
    if (descriptionElement == null) {
      return;
    }
    textPersister.restoreTextualDescription(descriptionElement, TAG_CHARACTER_NAME, description.getName());
    textPersister.restoreTextualDescription(descriptionElement, TAG_PLAYER, description.getPlayer());
    textPersister.restoreTextualDescription(descriptionElement, TAG_CHARACTERIZATION, description.getCharacterization());
    textPersister.restoreTextualDescription(
        descriptionElement,
        TAG_PHYSICAL_DESCRIPTION,
        description.getPhysicalDescription());
    textPersister.restoreTextualDescription(descriptionElement, TAG_PERIPHRASE, description.getPeriphrase());
    textPersister.restoreTextualDescription(descriptionElement, TAG_NOTES, description.getNotes());
  }

  public void save(Element parent, ICharacterDescription description) {
    Element descriptionElement = parent.addElement(TAG_DESCRIPTION);
    textPersister.saveNonEmptyText(descriptionElement, TAG_CHARACTER_NAME, description.getName().getText());
    textPersister.saveNonEmptyText(descriptionElement, TAG_PLAYER, description.getPlayer().getText());
    textPersister.saveNonEmptyText(descriptionElement, TAG_CHARACTERIZATION, description.getCharacterization()
        .getText());
    textPersister.saveNonEmptyText(descriptionElement, TAG_PHYSICAL_DESCRIPTION, description.getPhysicalDescription()
        .getText());
    textPersister.saveNonEmptyText(descriptionElement, TAG_PERIPHRASE, description.getPeriphrase().getText());
    textPersister.saveNonEmptyText(descriptionElement, TAG_NOTES, description.getNotes().getText());
  }
}