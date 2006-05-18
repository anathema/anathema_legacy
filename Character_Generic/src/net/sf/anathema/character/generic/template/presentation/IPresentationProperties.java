package net.sf.anathema.character.generic.template.presentation;

import java.awt.Color;

public interface IPresentationProperties extends ICommonPresentationProperties {

  public String getSmallCasteIconResource(String casteId);

  public String getMediumCasteIconResource(String casteId);

  public Color getColor();

  public String getCasteLabelResource();

  public ICharmPresentationProperties getCharmPresentationProperties();

}