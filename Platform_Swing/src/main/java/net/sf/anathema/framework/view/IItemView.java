package net.sf.anathema.framework.view;

import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.IView;

public interface IItemView extends IView {

  void setName(String newName);

  String getName();

  void addNameChangedListener(ObjectValueListener<String> nameListener);

  void removeNameChangedListener(ObjectValueListener<String> nameListener);

  RelativePath getIconPath();
}