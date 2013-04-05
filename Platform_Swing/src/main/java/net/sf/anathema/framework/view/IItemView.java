package net.sf.anathema.framework.view;

import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.IView;

import javax.swing.Icon;

public interface IItemView extends IView {

  void setName(String newName);

  String getName();

  Icon getIcon();

  void addNameChangedListener(ObjectValueListener<String> nameListener);

  void removeNameChangedListener(ObjectValueListener<String> nameListener);
}