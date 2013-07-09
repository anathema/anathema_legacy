package net.sf.anathema.character.equipment.character.model;

import net.sf.anathema.character.equipment.IWeaponModifiers;

public interface ModifierFactory {

  IWeaponModifiers createModifiers();
}