package net.sf.anathema.character.generic.template.presentation;

import java.awt.Color;

public interface IPresentationProperties extends ICommonPresentationProperties {

  public String getCasteIconResource(String casteId);

  public String getSmallCasteIconResource(String casteId);

  public Color getColor();

  public String getCasteResourceBase();

  public String getCasteLabelResource();

  public ICharmPresentationProperties getCharmPresentationProperties();

}