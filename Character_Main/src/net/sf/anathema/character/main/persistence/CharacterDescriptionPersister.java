package net.sf.anathema.character.main.persistence;

import net.sf.anathema.character.main.model.description.HeroDescription;
import net.sf.anathema.framework.persistence.TextPersister;
import org.dom4j.Element;

import static net.sf.anathema.character.main.persistence.ICharacterXmlConstants.*;

public class CharacterDescriptionPersister {
  private final TextPersister textPersister = new TextPersister();

  public void load(Element parent, HeroDescription description) {
    Element descriptionElement = parent.element(TAG_DESCRIPTION);
    if (descriptionElement == null) {
      return;
    }
    textPersister.restoreTextualDescription(descriptionElement, TAG_CHARACTER_NAME, description.getName());
    textPersister.restoreTextualDescription(descriptionElement, TAG_PLAYER, description.getPlayer());
    textPersister.restoreTextualDescription(descriptionElement, TAG_CHARACTERIZATION, description.getCharacterization());
    textPersister.restoreTextualDescription(descriptionElement, TAG_PHYSICAL_DESCRIPTION, description.getPhysicalDescription());
    textPersister.restoreTextualDescription(descriptionElement, TAG_PERIPHRASE, description.getPeriphrasis());
    textPersister.restoreTextualDescription(descriptionElement, TAG_NOTES, description.getNotes());
    textPersister.restoreTextualDescription(descriptionElement, TAG_CONCEPT, description.getConcept());

    textPersister.restoreTextualDescription(descriptionElement, TAG_EYES, description.getEyes());
    textPersister.restoreTextualDescription(descriptionElement, TAG_HAIR, description.getHair());
    textPersister.restoreTextualDescription(descriptionElement, TAG_SEX, description.getSex());
    textPersister.restoreTextualDescription(descriptionElement, TAG_SKIN, description.getSkin());
    textPersister.restoreTextualDescription(descriptionElement, TAG_ANIMA, description.getAnima());
  }

  public void save(Element parent, HeroDescription description) {
    Element descriptionElement = parent.addElement(TAG_DESCRIPTION);
    textPersister.saveTextualDescription(descriptionElement, TAG_CHARACTER_NAME, description.getName());
    textPersister.saveTextualDescription(descriptionElement, TAG_PLAYER, description.getPlayer());
    textPersister.saveTextualDescription(descriptionElement, TAG_CHARACTERIZATION, description.getCharacterization());
    textPersister.saveTextualDescription(descriptionElement, TAG_PHYSICAL_DESCRIPTION, description.getPhysicalDescription());
    textPersister.saveTextualDescription(descriptionElement, TAG_PERIPHRASE, description.getPeriphrasis());
    textPersister.saveTextualDescription(descriptionElement, TAG_NOTES, description.getNotes());
    textPersister.saveTextualDescription(descriptionElement, TAG_CONCEPT, description.getConcept());

    textPersister.saveTextualDescription(descriptionElement, TAG_EYES, description.getEyes());
    textPersister.saveTextualDescription(descriptionElement, TAG_HAIR, description.getHair());
    textPersister.saveTextualDescription(descriptionElement, TAG_SEX, description.getSex());
    textPersister.saveTextualDescription(descriptionElement, TAG_SKIN, description.getSkin());
    textPersister.saveTextualDescription(descriptionElement, TAG_ANIMA, description.getAnima());
  }
}