/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.action;

import com.google.common.base.Preconditions;
import net.sf.anathema.lib.gui.icon.IconImageIcon;
import net.sf.anathema.lib.gui.swing.GuiUtilities;
import net.sf.anathema.lib.gui.swing.IEnableable;
import net.sf.anathema.lib.lang.StringUtilities;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import java.awt.Component;
import java.awt.event.ActionEvent;

public abstract class SmartAction extends AbstractAction implements IEnableable {

  public SmartAction() {
    this(new ActionConfiguration());
  }

  public SmartAction(final String name) {
    this(new ActionConfiguration(name));
  }

  public SmartAction(final String name, final Icon icon) {
    this(new ActionConfiguration(name, icon));
  }

  public SmartAction(final Icon icon) {
    this(null, icon);
  }

  public SmartAction(final IActionConfiguration configuration) {
    Preconditions.checkNotNull(configuration);
    setName(configuration.getName());
    setIcon(configuration.getIcon());
    final String toolTipText = configuration.getToolTipText();
    setToolTipText(StringUtilities.isNullOrEmpty(toolTipText) ? null : toolTipText);
  }

  @Override
  public final void actionPerformed(final ActionEvent e) {
    final Component parentComponent = getParentComponent(e);
    execute(parentComponent);
  }

  /**
   * Called from the action when being invoked by the user. The given parentComponeent can be used
   * as parent for any dialogs shown in this method.
   *
   * @param parentComponent A parent swing from which the action was invoked. Can be used as
   *                        parent for new dialogs.
   */
  protected abstract void execute(Component parentComponent);

  /**
   * Sets the name of the action - may include the mnemonic character but must not contain line
   * delimiters. Mnemonics are indicated by an '&' that causes the next character to be the
   * mnemonic. When the user presses a key sequence that matches the mnemonic, a selection event
   * occurs. On most platforms, the mnemonic appears underlined but may be emphasised in a platform
   * specific manner. The mnemonic indicator character '&' can be escaped by doubling it in the
   * string, causing a single '&' to be displayed.
   */
  public final void setName(final String name) {
    if (name != null) {
      final MnemonicLabel mnemonicLabel = MnemonicLabelParser.parse(name);
      putValue(Action.NAME, mnemonicLabel.getPlainText());
      if (mnemonicLabel.getMnemonicCharacter() != null) {
        setMnemonic(mnemonicLabel.getMnemonicCharacter().charValue());
      }
    } else {
      putValue(Action.NAME, name);
    }
  }

  protected final Component getParentComponent(final ActionEvent e) {
    return GuiUtilities.getWindowFor(e);
  }

  public final void setMnemonic(final int keyCode) {
    putValue(MNEMONIC_KEY, new Integer(keyCode));
  }

  public final void setMnemonic(final char character) {
    final char ch = Character.toUpperCase(character);
    if (!isLetter(ch) && !isDigit(ch)) {
      throw new IllegalArgumentException("Unsupported mnemonic character'" + character + "'."); //$NON-NLS-1$ //$NON-NLS-2$
    }
    setMnemonic((int) ch);
  }

  public final void setAcceleratorKey(final KeyStroke keyStroke) {
    putValue(ACCELERATOR_KEY, keyStroke);
  }

  private static boolean isDigit(final char ch) {
    return ch >= '0' && ch <= '9';
  }

  private static boolean isLetter(final char ch) {
    return Character.isLetter(ch);
  }

  public final void setIcon(final Icon icon) {
    if (icon instanceof ImageIcon || icon == null) {
      putValue(SMALL_ICON, icon);
      return;
    }
    final ImageIcon imageIcon = new IconImageIcon(icon);
    putValue(SMALL_ICON, imageIcon);
  }

  public final void setToolTipText(final String shortDescription) {
    putValue(Action.SHORT_DESCRIPTION, shortDescription);
  }
}