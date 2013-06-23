package net.sf.anathema.character.platform.module;

import net.sf.anathema.character.CharacterPrintNameFileScanner;
import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.view.AbstractItemTypeViewProperties;
import net.sf.anathema.lib.resources.Resources;

public class CharacterViewProperties extends AbstractItemTypeViewProperties {

  public CharacterViewProperties(IItemType type, Resources resources, CharacterPrintNameFileScanner scanner) {
    super(type, new CharacterUI().getCharacterDescriptionTabIcon(), new CharacterTypeUi(resources, scanner));
  }
}