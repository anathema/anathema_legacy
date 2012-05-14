/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.icon;

import net.disy.commons.core.model.IChangeableModel;
import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.lib.gui.action.IconUtilities;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Image;

public class IconImageIcon extends ImageIcon {

  public IconImageIcon(final Icon icon) {
    super(createImage(icon));
    if (icon instanceof IChangeableModel) {
      final IChangeableModel changeableModel = (IChangeableModel) icon;
      changeableModel.addChangeListener(new IChangeListener() {
        @Override
        public void stateChanged() {
          setImage(createImage(icon));
        }
      });
    }
  }

  private static Image createImage(final Icon icon) {
    return IconUtilities.createBufferedImage(icon);
  }

}
