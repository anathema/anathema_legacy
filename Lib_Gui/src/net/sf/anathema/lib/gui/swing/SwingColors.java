/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.swing;

import javax.swing.UIManager;
import java.awt.Color;

public class SwingColors {

  public static Color getControlColor() {
    return UIManager.getColor("control"); //$NON-NLS-1$
  }

  public static Color getControlDkShadowColor() {
    return UIManager.getColor("controlDkShadow"); //$NON-NLS-1$
  }

  public static Color getControlHighlightColor() {
    return UIManager.getColor("controlHighlight"); //$NON-NLS-1$
  }

  public static Color getControlLtHighlightColor() {
    final Color ltHighlightColor = UIManager.getColor("controlLtHighlight"); //$NON-NLS-1$
    if (ltHighlightColor == null) {
      //Happens with "Nimbus" L&F
      return getControlHighlightColor();
    }
    return ltHighlightColor;
  }

  public static Color getControlShadowColor() {
    return UIManager.getColor("controlShadow"); //$NON-NLS-1$
  }

  public static Color getPanelBackgroundColor() {
    return UIManager.getColor("Panel.background"); //$NON-NLS-1$
  }

  public static Color getPanelForegroundColor() {
    return UIManager.getColor("Panel.foreground"); //$NON-NLS-1$
  }

  public static Color getTableFocusCellBackgroundColor() {
    return UIManager.getColor("Table.focusCellBackground"); //$NON-NLS-1$
  }

  public static Color getTableFocusCellForegroundColor() {
    return UIManager.getColor("Table.focusCellForeground"); //$NON-NLS-1$
  }

  public static Color getTableHeaderBackgroundColor() {
    return UIManager.getColor("TableHeader.background"); //$NON-NLS-1$
  }

  public static Color getTableHeaderForegroundColor() {
    return UIManager.getColor("TableHeader.foreground"); //$NON-NLS-1$
  }

  public static Color getTableSelectionBackgroundColor() {
    return UIManager.getColor("Table.selectionBackground"); //$NON-NLS-1$
  }

  public static Color getTableSelectionForegroundColor() {
    return UIManager.getColor("Table.selectionForeground"); //$NON-NLS-1$
  }

  public static Color getTreeLineColor() {
    return UIManager.getColor("Tree.line"); //$NON-NLS-1$
  }

  public static Color getTreeSelectionBackgroundColor() {
    return UIManager.getColor("Tree.selectionBackground"); //$NON-NLS-1$
  }

  public static Color getTreeSelectionBorderColor() {
    return UIManager.getColor("Tree.selectionBorderColor"); //$NON-NLS-1$
  }

  public static Color getTreeSelectionForegroundColor() {
    return UIManager.getColor("Tree.selectionForeground"); //$NON-NLS-1$
  }

  public static Color getTreeTextBackgroundColor() {
    return UIManager.getColor("Tree.textBackground"); //$NON-NLS-1$
  }

  public static Color getTreeTextForegroundColor() {
    return UIManager.getColor("Tree.textForeground"); //$NON-NLS-1$
  }

  public static Color getTextAreaForegroundColor() {
    return UIManager.getColor("TextArea.foreground"); //$NON-NLS-1$
  }

  public static Color getTextAreaBackgroundColor() {
    return UIManager.getColor("TextArea.background"); //$NON-NLS-1$
  }

  public static Color getTextFieldInactiveBackgroundColor() {
    return UIManager.getColor("TextField.inactiveBackground"); //$NON-NLS-1$
  }

  public static Color getTextAreaCaretForegroundColor() {
    return UIManager.getColor("TextArea.caretForeground"); //$NON-NLS-1$
  }

  public static Color getTextAreaSelectionBackgroundColor() {
    return UIManager.getColor("TextArea.selectionBackground"); //$NON-NLS-1$
  }

  public static Color getTextAreaSelectionForegroundColor() {
    return UIManager.getColor("TextArea.selectionForeground"); //$NON-NLS-1$
  }

  public static Color getTextAreaInactiveForegroundColor() {
    return UIManager.getColor("TextArea.inactiveForeground"); //$NON-NLS-1$
  }

  public static Color getLabelForegroundColor() {
    return UIManager.getColor("Label.foreground"); //$NON-NLS-1$
  }

  public static Color getLabelBackgroundColor() {
    return UIManager.getColor("Label.background"); //$NON-NLS-1$
  }

  public static Color getHyperlinkColor() {
    //20.11.2006 (gebhard): I don't know: Is there a swing L&F-Constant for this color?
    return Color.BLUE;
  }
}