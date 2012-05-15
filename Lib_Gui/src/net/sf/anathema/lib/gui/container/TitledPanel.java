/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.container;

import com.google.common.base.Preconditions;
import net.disy.commons.core.util.IClosure;
import net.disy.commons.core.util.NullClosure;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IGridDialogLayoutData;
import net.disy.commons.swing.layout.util.LayoutUtilities;
import net.sf.anathema.lib.gui.swing.BorderUtilities;
import net.sf.anathema.lib.gui.swing.GuiUtilities;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TitledPanel extends JPanel {

  public TitledPanel(final String title, final JComponent content) {
    this(title, content, new GridDialogLayoutData());
  }

  public TitledPanel(
      final String title,
      final JComponent content,
      final IGridDialogLayoutData layoutData) {
    this(title, content, layoutData, new NullClosure<TitledBorder>());
  }

  public TitledPanel(
      final String title,
      final JComponent content,
      final IGridDialogLayoutData layoutData,
      final IClosure<TitledBorder> decorator) {
    super(new GridLayout(1, 0));
    Preconditions.checkNotNull(title);
    Preconditions.checkNotNull(content);
    final TitledBorder titledBorder = new TitledBorder(title);
    BorderUtilities.attachDisableableTitledBorder(this, titledBorder);
    decorator.execute(titledBorder);

    setBorder(new CompoundBorder(getBorder(), new EmptyBorder(
        LayoutUtilities.getDpiAdjusted(2),
        LayoutUtilities.getDpiAdjusted(4),
        LayoutUtilities.getDpiAdjusted(4),
        LayoutUtilities.getDpiAdjusted(4))));
    add(content, layoutData);

    content.addPropertyChangeListener(
        GuiUtilities.ENABLED_PROPERTY_NAME,
        new PropertyChangeListener() {
          @Override
          public void propertyChange(final PropertyChangeEvent evt) {
            setEnabled(content.isEnabled());
          }
        });
    setEnabled(content.isEnabled());
  }
}