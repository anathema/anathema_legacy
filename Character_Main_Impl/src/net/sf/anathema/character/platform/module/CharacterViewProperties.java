package net.sf.anathema.character.platform.module;

import net.sf.anathema.character.main.framework.item.CharacterPrintNameFileScanner;
import net.sf.anathema.character.main.CharacterUI;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.view.AbstractItemTypeViewProperties;
import net.sf.anathema.lib.resources.Resources;

public class CharacterViewProperties extends AbstractItemTypeViewProperties {

  public CharacterViewProperties(IItemType type, Resources resources, CharacterPrintNameFileScanner scanner) {
    super(type, new CharacterUI().getCharacterDescriptionTabIcon(), new CharacterTypeUi(resources, scanner));
  }
}