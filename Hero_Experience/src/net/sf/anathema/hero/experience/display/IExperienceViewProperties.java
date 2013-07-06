package net.sf.anathema.hero.experience.display;

import net.sf.anathema.lib.file.RelativePath;

public interface IExperienceViewProperties {

  RelativePath getDeleteIcon();

  RelativePath getAddIcon();

  String getTotalString();

  String getPointHeader();

  String getDescriptionHeader();
}