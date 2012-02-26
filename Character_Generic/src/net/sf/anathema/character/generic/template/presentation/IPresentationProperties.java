package net.sf.anathema.character.generic.template.presentation;

import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;

public interface IPresentationProperties extends ICommonPresentationProperties {

  public String getSmallCasteIconResource(String casteId, String editionId);

  public String getCasteLabelResource();

  public ITreePresentationProperties getCharmPresentationProperties();
}