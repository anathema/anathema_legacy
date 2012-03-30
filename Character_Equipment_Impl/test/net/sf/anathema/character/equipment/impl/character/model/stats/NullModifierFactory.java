package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.sf.anathema.character.equipment.IEquipmentModifiers;
import net.sf.anathema.character.equipment.impl.character.model.ModifierFactory;

import static org.mockito.Mockito.mock;

public class NullModifierFactory implements ModifierFactory {
  @Override
  public IEquipmentModifiers createModifiers() {
    return mock(IEquipmentModifiers.class);
  }
}