package net.sf.anathema.character.main.template.presentation;

import net.sf.anathema.lib.file.RelativePath;

public interface IPresentationProperties {
  String getNewActionResource();

  RelativePath getSmallCasteIconResource(String casteId, String editionId);

  RelativePath getLargeCasteIconResource(String casteId, String editionId);

  String getCasteLabelResource();
}