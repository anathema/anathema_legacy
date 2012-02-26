package net.sf.anathema.character.generic.framework.unsupported;

import net.sf.anathema.character.generic.impl.template.presentation.AbstractPresentationProperties;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;

import java.awt.Color;

public class DragonKingPresentationProperties extends AbstractPresentationProperties {

  public DragonKingPresentationProperties(ITemplateType templateType) {
    super(templateType);
  }

  @Override
  public String getSmallCasteIconResource(String casteId, String editionId) {
    return null;
  }

  public Color getColor() {
    return new Color(250, 250, 210);
  }

  public ITreePresentationProperties getCharmPresentationProperties() {
    return new DragonKingCharmPresentationProperties();
  }
}