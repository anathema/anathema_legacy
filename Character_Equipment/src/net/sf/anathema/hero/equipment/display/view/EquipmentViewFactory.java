package net.sf.anathema.hero.equipment.display.view;

import net.sf.anathema.character.main.framework.RegisteredCharacterView;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.character.main.view.SubViewFactory;
import net.sf.anathema.hero.equipment.display.presenter.EquipmentView;

@RegisteredCharacterView(EquipmentView.class)
public class EquipmentViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create(CharacterType type) {
    return (T) new EquipmentViewImpl();
  }
}