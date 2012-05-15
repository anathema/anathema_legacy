package net.sf.anathema.lib.gui.swing;

import javax.swing.JComponent;

public interface IEnableableComponentContainer extends IEnableable {

  JComponent[] getComponents();
}