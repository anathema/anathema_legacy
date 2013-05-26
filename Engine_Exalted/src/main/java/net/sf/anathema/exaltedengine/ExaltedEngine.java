package net.sf.anathema.exaltedengine;

import net.sf.anathema.characterengine.command.AddQuality;
import net.sf.anathema.characterengine.engine.DefaultEngine;
import net.sf.anathema.characterengine.persona.Persona;
import net.sf.anathema.characterengine.quality.Name;
import net.sf.anathema.characterengine.quality.QualityKey;
import net.sf.anathema.characterengine.quality.Type;
import net.sf.anathema.exaltedengine.attributes.AttributeFactory;
import net.sf.anathema.exaltedengine.essence.EssenceFactory;
import net.sf.anathema.exaltedengine.numericquality.SetMaximumValue;
import net.sf.anathema.exaltedengine.numericquality.SetMinimumValue;

public class ExaltedEngine {

  public static final Type ATTRIBUTE = new Type("Attribute");
  public static final Type ESSENCE = new Type("Essence");
  private final DefaultEngine engine = new DefaultEngine();

  public ExaltedEngine() {
    engine.setFactory(ESSENCE, new EssenceFactory());
    engine.setFactory(ATTRIBUTE, new AttributeFactory());
  }

  public Persona createCharacter() {
    Persona character = engine.createCharacter();
    addEssence(character);
    addAttributes(character);
    return character;
  }

  private void addEssence(Persona character) {
    character.execute(new AddQuality(new QualityKey(ESSENCE, new Name("Essence"))));
    //character.execute(new SetMinimumValue(ESSENCE, 1));
  }

  private void addAttributes(Persona character) {
    String[] attributeNames = {"Strength", "Dexterity", "Stamina", "Charisma", "Manipulation", "Appearance", "Perception", "Intelligence", "Wits"};
    for (String name : attributeNames) {
      addAttribute(character, name);
    }
    character.execute(new SetMinimumValue(ATTRIBUTE, 1));
    character.execute(new SetMaximumValue(ATTRIBUTE, 5));
  }

  private void addAttribute(Persona character, String name) {
    character.execute(new AddQuality(new QualityKey(ATTRIBUTE, new Name(name))));
  }
}