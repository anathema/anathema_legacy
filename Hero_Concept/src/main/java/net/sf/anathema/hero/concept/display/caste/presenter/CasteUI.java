package net.sf.anathema.hero.concept.display.caste.presenter;

import net.sf.anathema.hero.template.presentation.IPresentationProperties;
import net.sf.anathema.hero.concept.CasteType;
import net.sf.anathema.lib.file.RelativePath;

public class CasteUI {

  private final IPresentationProperties properties;

  public CasteUI(IPresentationProperties properties) {
    this.properties = properties;
  }

  public RelativePath getSmallCasteIconPath(CasteType type) {
    return properties.getSmallCasteIconResource(type.getId());
  }
}