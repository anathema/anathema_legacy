package net.sf.anathema.character.generic.template.presentation;

import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.platform.tree.document.visualizer.ITreePresentationProperties;

public interface IPresentationProperties extends ICommonPresentationProperties {

  RelativePath getSmallCasteIconResource(String casteId, String editionId);

  RelativePath getLargeCasteIconResource(String casteId, String editionId);

  String getCasteLabelResource();

  ITreePresentationProperties getCharmPresentationProperties();
}