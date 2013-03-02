package net.sf.anathema.character.generic.template.presentation;

import net.sf.anathema.platform.tree.document.visualizer.ITreePresentationProperties;

public interface IPresentationProperties extends ICommonPresentationProperties {

  String getSmallCasteIconResource(String casteId, String editionId);

  String getLargeCasteIconResource(String casteId, String editionId);

  String getCasteLabelResource();

  ITreePresentationProperties getCharmPresentationProperties();
}