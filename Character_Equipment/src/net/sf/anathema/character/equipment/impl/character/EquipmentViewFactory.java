package net.sf.anathema.character.equipment.impl.character;

import net.sf.anathema.character.equipment.character.view.EquipmentView;
import net.sf.anathema.character.equipment.impl.character.view.EquipmentViewImpl;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.main.framework.RegisteredCharacterView;
import net.sf.anathema.character.main.view.SubViewFactory;

@RegisteredCharacterView(EquipmentView.class)
public class EquipmentViewFactory implements SubViewFactory {
  @Override
  public <T> T create(ICharacterType type) {
    return (T) new EquipmentViewImpl();
  }
}