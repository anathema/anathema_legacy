package net.sf.anathema.character.generic.magic;

import net.sf.anathema.lib.util.Identified;
import net.sf.anathema.lib.util.Identifier;

@SuppressWarnings("UnusedDeclaration")
public interface IExtendedCharmData extends ICharmData {
  Identified COMBO_BASIC_ATTRIBUTE = new Identifier("Combo-Basic");
  Identified COMBO_OK_ATTRIBUTE = new Identifier("Combo-OK");
  Identified COMPULSION_ATTRIBUTE = new Identifier("Compulsion");
  Identified COUNTERATTACK_ATTRIBUTE = new Identifier("Counterattack");
  Identified CRIPPLING_ATTRIBUTE = new Identifier("Crippling");
  Identified EMOTION_ATTRIBUTE = new Identifier("Emotion");

  Identified HOLY_ATTRIBUTE = new Identifier("Holy");
  Identified ILLUSION_ATTRIBUTE = new Identifier("Illusion");
  Identified KNOCKBACK_ATTRIBUTE = new Identifier("Knockback");
  Identified MANDATE_ATTRIBUTE = new Identifier("Mandate");
  Identified OBVIOUS_ATTRIBUTE = new Identifier("Obvious");
  Identified OVERDRIVE_ATTRIBUTE = new Identifier("Overdrive");
  Identified POISON_ATTRIBUTE = new Identifier("Poison");
  Identified SERVITUDE_ATTRIBUTE = new Identifier("Servitude");
  Identified SHAPING_ATTRIBUTE = new Identifier("Shaping");
  Identified SICKNESS_ATTRIBUTE = new Identifier("Sickness");
  Identified SOCIAL_ATTRIBUTE = new Identifier("Social");
  Identified STACKABLE_ATTRIBUTE = new Identifier("Stackable");
  Identified TOUCH_ATTRIBUTE = new Identifier("Touch");
  Identified TRAINING_ATTRIBUTE = new Identifier("Training");
  Identified WAR_ATTRIBUTE = new Identifier("War");

  Identified EXCLUSIVE_ATTRIBUTE = new Identifier("Exclusive");
}