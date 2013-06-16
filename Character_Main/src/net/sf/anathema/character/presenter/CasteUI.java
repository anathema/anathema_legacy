package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.impl.persistence.SecondEdition;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.ui.AbstractUI;

import javax.swing.Icon;

public class CasteUI extends AbstractUI {

  private final IPresentationProperties properties;

  public CasteUI(IPresentationProperties properties) {
    this.properties = properties;
  }

  public Icon getSmallCasteIcon(ICasteType type) {
    return getIcon(getSmallCasteIconPath(type));
  }

  public RelativePath getSmallCasteIconPath(ICasteType type) {
    return properties.getSmallCasteIconResource(type.getId(), SecondEdition.SECOND_EDITION);
  }
}