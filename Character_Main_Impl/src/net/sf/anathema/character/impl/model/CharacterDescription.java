package net.sf.anathema.character.impl.model;

import net.sf.anathema.character.model.ICharacterDescription;
import net.sf.anathema.lib.workflow.textualdescription.ISimpleTextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public class CharacterDescription implements ICharacterDescription {

  private final ISimpleTextualDescription nameDescription = new SimpleTextualDescription();
  private final ISimpleTextualDescription periphraseDescription = new SimpleTextualDescription();
  private final ISimpleTextualDescription characterization = new SimpleTextualDescription();
  private final ISimpleTextualDescription physicalDescription = new SimpleTextualDescription();
  private final ISimpleTextualDescription notes = new SimpleTextualDescription();

  public ISimpleTextualDescription getName() {
    return nameDescription;
  }

  public ISimpleTextualDescription getPeriphrase() {
    return periphraseDescription;
  }

  public ISimpleTextualDescription getCharacterization() {
    return characterization;
  }

  public ISimpleTextualDescription getPhysicalDescription() {
    return physicalDescription;
  }

  public ISimpleTextualDescription getNotes() {
    return notes;
  }
}