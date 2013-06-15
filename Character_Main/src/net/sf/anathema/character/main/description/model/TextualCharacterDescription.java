package net.sf.anathema.character.main.description.model;

import net.sf.anathema.character.change.AnnounceChangeValueListener;
import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.character.model.CharacterModel;
import net.sf.anathema.character.model.Hero;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.util.Identified;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public class TextualCharacterDescription implements CharacterDescription, CharacterModel {

  private final ITextualDescription name = new SimpleTextualDescription();
  private final ITextualDescription paraphrasis = new SimpleTextualDescription();
  private final ITextualDescription characterization = new SimpleTextualDescription();
  private final ITextualDescription physicals = new SimpleTextualDescription();
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
    return name;
  }

  @Override
  public ITextualDescription getPlayer() {
    return player;
  }

  @Override
  public ITextualDescription getPeriphrasis() {
    return paraphrasis;
  }

  @Override
  public ITextualDescription getCharacterization() {
    return characterization;
  }

  @Override
  public ITextualDescription getPhysicalDescription() {
    return physicals;
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
    return new ITextualDescription[]{name, paraphrasis, characterization, physicals, eyes, hair, sex, skin, anima, notes, player, concept};
  }

  @Override
  public ITextualDescription getConcept() {
    return concept;
  }

  @Override
  public void addOverallChangeListener(ObjectValueListener<String> listener) {
    for (ITextualDescription description : getAllDescriptions()) {
      description.addTextChangedListener(listener);
    }
  }

  @Override
  public Identified getId() {
    return ID;
  }

  @Override
  public void initialize(ChangeAnnouncer announcer, Hero hero) {
    addOverallChangeListener(new AnnounceChangeValueListener(announcer));
  }
}