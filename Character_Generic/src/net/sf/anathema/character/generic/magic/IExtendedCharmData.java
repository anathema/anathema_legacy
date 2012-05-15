package net.sf.anathema.character.generic.magic;

import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public interface IExtendedCharmData extends ICharmData {
  IIdentificate COMBO_BASIC_ATTRIBUTE = new Identificate("Combo-Basic"); //$NON-NLS-1$
  IIdentificate COMBO_OK_ATTRIBUTE = new Identificate("Combo-OK"); //$NON-NLS-1$
  IIdentificate COMPULSION_ATTRIBUTE = new Identificate("Compulsion"); //$NON-NLS-1$
  IIdentificate COUNTERATTACK_ATTRIBUTE = new Identificate("Counterattack"); //$NON-NLS-1$
  IIdentificate CRIPPLING_ATTRIBUTE = new Identificate("Crippling"); //$NON-NLS-1$
  IIdentificate EMOTION_ATTRIBUTE = new Identificate("Emotion"); //$NON-NLS-1$

  IIdentificate HOLY_ATTRIBUTE = new Identificate("Holy"); //$NON-NLS-1$
  IIdentificate ILLUSION_ATTRIBUTE = new Identificate("Illusion"); //$NON-NLS-1$
  IIdentificate KNOCKBACK_ATTRIBUTE = new Identificate("Knockback"); //$NON-NLS-1$
  IIdentificate MANDATE_ATTRIBUTE = new Identificate("Mandate"); //$NON-NLS-1$
  IIdentificate OBVIOUS_ATTRIBUTE = new Identificate("Obvious"); //$NON-NLS-1$
  IIdentificate OVERDRIVE_ATTRIBUTE = new Identificate("Overdrive"); //$NON-NLS-1$
  IIdentificate POISON_ATTRIBUTE = new Identificate("Poison"); //$NON-NLS-1$
  IIdentificate SERVITUDE_ATTRIBUTE = new Identificate("Servitude"); //$NON-NLS-1$
  IIdentificate SHAPING_ATTRIBUTE = new Identificate("Shaping"); //$NON-NLS-1$
  IIdentificate SICKNESS_ATTRIBUTE = new Identificate("Sickness"); //$NON-NLS-1$
  IIdentificate SOCIAL_ATTRIBUTE = new Identificate("Social"); //$NON-NLS-1$
  IIdentificate STACKABLE_ATTRIBUTE = new Identificate("Stackable"); //$NON-NLS-1$
  IIdentificate TOUCH_ATTRIBUTE = new Identificate("Touch"); //$NON-NLS-1$
  IIdentificate TRAINING_ATTRIBUTE = new Identificate("Training"); //$NON-NLS-1$
  IIdentificate WAR_ATTRIBUTE = new Identificate("War"); //$NON-NLS-1$

  IIdentificate EXCLUSIVE_ATTRIBUTE = new Identificate("Exclusive"); //$NON-NLS-1$
}