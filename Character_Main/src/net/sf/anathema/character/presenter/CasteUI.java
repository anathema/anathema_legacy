package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.caste.CasteType;
import net.sf.anathema.character.generic.impl.persistence.SecondEdition;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.lib.file.RelativePath;

public class CasteUI {

  private final IPresentationProperties properties;

  public CasteUI(IPresentationProperties properties) {
    this.properties = properties;
  }

  public RelativePath getSmallCasteIconPath(CasteType type) {
    return properties.getSmallCasteIconResource(type.getId(), SecondEdition.SECOND_EDITION);
  }
}