package net.sf.anathema.framework.view.perspective;

import net.sf.anathema.lib.file.RelativePath;

public interface PerspectiveToggle {

  void setIcon(RelativePath relativePath);

  void setTooltip(String key);

  void setText(String key);
}
