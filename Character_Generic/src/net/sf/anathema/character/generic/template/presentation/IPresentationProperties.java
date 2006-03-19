package net.sf.anathema.character.generic.template.presentation;

import java.awt.Color;

public interface IPresentationProperties {

  public String getBallResource();

  public String getCasteIconResource(String groupId);

  public Color getColor();

  public String getNewActionResource();

  public String getCasteResourceBase();

  public String getCasteLabelResource();

  public ICharmPresentationProperties getCharmPresentationProperties();
}