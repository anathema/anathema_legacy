package net.sf.anathema.character.main.magic.model.charm;

import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

@SuppressWarnings("UnusedDeclaration")
public interface IExtendedCharmData extends ICharmData {
  Identifier COMBO_BASIC_ATTRIBUTE = new SimpleIdentifier("Combo-Basic");
  Identifier COMBO_OK_ATTRIBUTE = new SimpleIdentifier("Combo-OK");
  Identifier COMPULSION_ATTRIBUTE = new SimpleIdentifier("Compulsion");
  Identifier COUNTERATTACK_ATTRIBUTE = new SimpleIdentifier("Counterattack");
  Identifier CRIPPLING_ATTRIBUTE = new SimpleIdentifier("Crippling");
  Identifier EMOTION_ATTRIBUTE = new SimpleIdentifier("Emotion");

  Identifier HOLY_ATTRIBUTE = new SimpleIdentifier("Holy");
  Identifier ILLUSION_ATTRIBUTE = new SimpleIdentifier("Illusion");
  Identifier KNOCKBACK_ATTRIBUTE = new SimpleIdentifier("Knockback");
  Identifier MANDATE_ATTRIBUTE = new SimpleIdentifier("Mandate");
  Identifier OBVIOUS_ATTRIBUTE = new SimpleIdentifier("Obvious");
  Identifier OVERDRIVE_ATTRIBUTE = new SimpleIdentifier("Overdrive");
  Identifier POISON_ATTRIBUTE = new SimpleIdentifier("Poison");
  Identifier SERVITUDE_ATTRIBUTE = new SimpleIdentifier("Servitude");
  Identifier SHAPING_ATTRIBUTE = new SimpleIdentifier("Shaping");
  Identifier SICKNESS_ATTRIBUTE = new SimpleIdentifier("Sickness");
  Identifier SOCIAL_ATTRIBUTE = new SimpleIdentifier("Social");
  Identifier STACKABLE_ATTRIBUTE = new SimpleIdentifier("Stackable");
  Identifier TOUCH_ATTRIBUTE = new SimpleIdentifier("Touch");
  Identifier TRAINING_ATTRIBUTE = new SimpleIdentifier("Training");
  Identifier WAR_ATTRIBUTE = new SimpleIdentifier("War");

  Identifier EXCLUSIVE_ATTRIBUTE = new SimpleIdentifier("Exclusive");
}