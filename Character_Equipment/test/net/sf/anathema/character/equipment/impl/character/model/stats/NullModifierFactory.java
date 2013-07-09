package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.sf.anathema.hero.equipment.model.IWeaponModifiers;
import net.sf.anathema.hero.equipment.model.ModifierFactory;

import static org.mockito.Mockito.mock;

public class NullModifierFactory implements ModifierFactory {
  @Override
  public IWeaponModifiers createModifiers() {
    return mock(IWeaponModifiers.class);
  }
}