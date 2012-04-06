package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.sf.anathema.character.equipment.IWeaponModifiers;
import net.sf.anathema.character.equipment.impl.character.model.ModifierFactory;

import static org.mockito.Mockito.mock;

public class NullModifierFactory implements ModifierFactory {
  @Override
  public IWeaponModifiers createModifiers() {
    return mock(IWeaponModifiers.class);
  }
}