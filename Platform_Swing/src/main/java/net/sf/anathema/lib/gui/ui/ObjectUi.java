package net.sf.anathema.lib.gui.ui;

import javax.swing.Icon;

public interface ObjectUi<T> {

  Icon getIcon(T value);

  String getLabel(T value);
}