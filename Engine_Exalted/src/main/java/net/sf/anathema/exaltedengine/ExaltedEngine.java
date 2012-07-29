package net.sf.anathema.exaltedengine;

import net.sf.anathema.characterengine.command.AddQuality;
import net.sf.anathema.characterengine.engine.DefaultEngine;
import net.sf.anathema.characterengine.persona.Persona;
import net.sf.anathema.characterengine.quality.Name;
import net.sf.anathema.characterengine.quality.QualityKey;
import net.sf.anathema.characterengine.quality.Type;

public class ExaltedEngine {

  private final DefaultEngine engine = new DefaultEngine();

  public ExaltedEngine() {
    engine.setFactory(new Type("Attribute"), new NumericQualityFactory(1));
  }

  public Persona createCharacter() {
    Persona character = engine.createCharacter();
    addAttributes(character);
    return character;
  }

  private void addAttributes(Persona character) {
    String[] attributeNames = {"Strength", "Dexterity", "Stamina", "Charisma", "Manipulation", "Appearance", "Perception", "Intelligence", "Wits"};
    for (String name : attributeNames) {
      addAttribute(character, name);
    }
  }

  private void addAttribute(Persona character, String name) {
    character.execute(new AddQuality(new QualityKey(new Type("Attribute"), new Name(name))));
  }
}