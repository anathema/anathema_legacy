package net.sf.anathema.character.generic.impl.template.presentation;

import java.awt.Color;

import net.sf.anathema.character.generic.impl.IIconConstants;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.presentation.ICharmPresentationProperties;

public abstract class MortalPresentationProperties extends AbstractPresentationProperties {

  public MortalPresentationProperties(ITemplateType type) {
    super(type);
  }

  public String getCasteIconResource(String groupId) {
    return IIconConstants.UNSELECTED_BALL;
  }

  public String getBallResource() {
    return IIconConstants.MORTAL_BALL;
  }

  public Color getColor() {
    return new Color(177, 177, 253);
  }

  public ICharmPresentationProperties getCharmPresentationProperties() {
    throw new UnsupportedOperationException("Charms for Mortals not supported"); //$NON-NLS-1$
  }

  @Override
  public abstract String getNewActionResource();

}