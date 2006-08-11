package net.sf.anathema.character.generic.template.presentation;

import java.awt.Color;

public interface IPresentationProperties extends ICommonPresentationProperties {

  public String getSmallCasteIconResource(String casteId, String editionId);

  public Color getColor();

  public String getCasteLabelResource();

  public ICharmPresentationProperties getCharmPresentationProperties();
}