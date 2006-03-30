package net.sf.anathema.character.generic.template.presentation;

import java.awt.Color;

public interface IPresentationProperties extends ICommonPresentationProperties {

  public String getCasteIconResource(String groupId);

  public Color getColor();

  public String getCasteResourceBase();

  public String getCasteLabelResource();

  public ICharmPresentationProperties getCharmPresentationProperties();
}