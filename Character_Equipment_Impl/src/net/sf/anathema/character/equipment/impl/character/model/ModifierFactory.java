package net.sf.anathema.character.equipment.impl.character.model;


import net.sf.anathema.character.equipment.IWeaponModifiers;

public interface ModifierFactory {

  IWeaponModifiers createModifiers();
}