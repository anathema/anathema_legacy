package net.sf.anathema.character.generic.framework.unsupported;

import net.sf.anathema.character.generic.impl.template.presentation.AbstractPresentationProperties;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;

public class DragonKingPresentationProperties extends AbstractPresentationProperties {

  public DragonKingPresentationProperties(ITemplateType templateType) {
    super(templateType);
  }

  @Override
  public String getSmallCasteIconResource(String casteId, String editionId) {
    return null;
  }

  @Override
  public ITreePresentationProperties getCharmPresentationProperties() {
    return new DragonKingCharmPresentationProperties();
  }
}