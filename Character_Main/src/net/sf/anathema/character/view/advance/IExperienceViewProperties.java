package net.sf.anathema.character.view.advance;

import net.sf.anathema.lib.file.RelativePath;

public interface IExperienceViewProperties {

  RelativePath getDeleteIcon();

  RelativePath getAddIcon();

  String getTotalString();

  String getPointHeader();

  String getDescriptionHeader();
}