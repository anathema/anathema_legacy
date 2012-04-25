package net.sf.anathema.character.impl.model;

import net.sf.anathema.character.model.ICharacterDescription;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public class CharacterDescription implements ICharacterDescription {

  private final ITextualDescription nameDescription = new SimpleTextualDescription();
  private final ITextualDescription periphraseDescription = new SimpleTextualDescription();
  private final ITextualDescription characterization = new SimpleTextualDescription();
  private final ITextualDescription physicalDescription = new SimpleTextualDescription();
  private final ITextualDescription notes = new SimpleTextualDescription();
  private final ITextualDescription player = new SimpleTextualDescription();
  private final ITextualDescription concept = new SimpleTextualDescription();

  private final ITextualDescription eyes = new SimpleTextualDescription();
  private final ITextualDescription hair = new SimpleTextualDescription();
  private final ITextualDescription sex = new SimpleTextualDescription();
  private final ITextualDescription skin = new SimpleTextualDescription();
  private final ITextualDescription anima = new SimpleTextualDescription();

  @Override
  public ITextualDescription getName() {
    return nameDescription;
  }

  @Override
  public ITextualDescription getPlayer() {
    return player;
  }

  @Override
  public ITextualDescription getPeriphrase() {
    return periphraseDescription;
  }

  @Override
  public ITextualDescription getCharacterization() {
    return characterization;
  }

  @Override
  public ITextualDescription getPhysicalDescription() {
    return physicalDescription;
  }

  @Override
  public ITextualDescription getEyes() {
    return eyes;
  }

  @Override
  public ITextualDescription getHair() {
    return hair;
  }

  @Override
  public ITextualDescription getSex() {
    return sex;
  }

  @Override
  public ITextualDescription getSkin() {
    return skin;
  }
  
  @Override
  public ITextualDescription getAnima() {
	return anima;
  }

  @Override
  public ITextualDescription getNotes() {
    return notes;
  }

  private ITextualDescription[] getAllDescriptions() {
    return new ITextualDescription[] {
        nameDescription,
        periphraseDescription,
        characterization,
        physicalDescription,
        eyes,
        hair,
        sex,
        skin,
        anima,
        notes,
        player,
        concept };
  }

  @Override
  public ITextualDescription getConcept() {
    return concept;
  }

  @Override
  public void addOverallChangeListener(IObjectValueChangedListener<String> listener) {
    for (ITextualDescription description : getAllDescriptions()) {
      description.addTextChangedListener(listener);
    }
  }
}