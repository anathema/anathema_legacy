package net.sf.anathema.character.generic.framework.unsupported;

import java.awt.Color;

import net.sf.anathema.character.generic.impl.template.presentation.AbstractPresentationProperties;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.presentation.ICharmPresentationProperties;

public class DragonBloodedPresentationProperties extends AbstractPresentationProperties {

  public DragonBloodedPresentationProperties(ITemplateType templateType) {
    super(templateType);
  }

  public Color getColor() {
    return new Color(139, 0, 0);
  }

  public ICharmPresentationProperties getCharmPresentationProperties() {
    return new SecondEditionDefaultCharmPresentationProperties();
  }

  @Override
  public String getSmallCasteIconResource(String casteId, String editionId) {
    return null;
  }
}
