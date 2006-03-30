package net.sf.anathema.character.generic.framework.unsupported;

import java.awt.Color;

import net.sf.anathema.character.generic.impl.template.presentation.AbstractPresentationProperties;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.presentation.ICharmPresentationProperties;

public class DragonKingPresentationProperties extends AbstractPresentationProperties {

  public DragonKingPresentationProperties(ITemplateType templateType) {
    super(templateType);
  }

  public String getBallResource() {
    return null;
  }

  public String getCasteIconResource(String groupId) {
    return null;
  }

  public Color getColor() {
    return new Color(250, 250, 210);
  }

  public ICharmPresentationProperties getCharmPresentationProperties() {
    return new DefaultCharmPresentationProperties();
  }
}