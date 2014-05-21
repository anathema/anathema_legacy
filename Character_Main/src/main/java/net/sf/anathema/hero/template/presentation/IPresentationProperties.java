package net.sf.anathema.hero.template.presentation;

import net.sf.anathema.lib.file.RelativePath;

public interface IPresentationProperties {

  String getNewActionResource();

  RelativePath getSmallCasteIconResource(String casteId);

  RelativePath getLargeCasteIconResource(String casteId);

  String getCasteLabelResource();
}