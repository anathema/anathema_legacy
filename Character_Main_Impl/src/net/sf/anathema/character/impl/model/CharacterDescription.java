package net.sf.anathema.character.impl.model;

import net.sf.anathema.character.model.ICharacterDescription;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public class CharacterDescription implements ICharacterDescription {

  private final ITextualDescription nameDescription = new SimpleTextualDescription();
  private final ITextualDescription periphraseDescription = new SimpleTextualDescription();
  private final ITextualDescription characterization = new SimpleTextualDescription();
  private final ITextualDescription physicalDescription = new SimpleTextualDescription();
  private final ITextualDescription notes = new SimpleTextualDescription();

  public ITextualDescription getName() {
    return nameDescription;
  }

  public ITextualDescription getPeriphrase() {
    return periphraseDescription;
  }

  public ITextualDescription getCharacterization() {
    return characterization;
  }

  public ITextualDescription getPhysicalDescription() {
    return physicalDescription;
  }

  public ITextualDescription getNotes() {
    return notes;
  }
}