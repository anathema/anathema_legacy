/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.message;

import net.sf.anathema.lib.gui.resources.LibGuiMessages;
import net.sf.anathema.lib.gui.swing.SwingColors;
import net.sf.anathema.lib.gui.ui.AbstractObjectUi;
import net.sf.anathema.lib.message.IMessageTypeVisitor;
import net.sf.anathema.lib.message.MessageType;

import java.awt.Color;

public abstract class AbstractMessageTypeUi extends AbstractObjectUi<MessageType> {

  public static Color getColor(final MessageType type) {
    final Color[] color = new Color[1];
    type.accept(new IMessageTypeVisitor() {
      @Override
      public void visitError(final MessageType visitedType) {
        color[0] = Color.red;
      }

      @Override
      public void visitNormal(final MessageType visitedType) {
        color[0] = SwingColors.getTextAreaForegroundColor();
      }

      @Override
      public void visitWarning(final MessageType visitedType) {
        color[0] = SwingColors.getTextAreaForegroundColor();
      }

      @Override
      public void visitInformation(final MessageType visitedType) {
        color[0] = SwingColors.getTextAreaForegroundColor();
      }

      @Override
      public void visitQuestion(final MessageType visitedType) {
        color[0] = SwingColors.getTextAreaForegroundColor();
      }
    });
    return color[0];
  }

  @Override
  public final String getLabel(final MessageType type) {
    final String[] label = new String[1];
    type.accept(new IMessageTypeVisitor() {
      @Override
      public void visitInformation(final MessageType visitedType) {
        label[0] = LibGuiMessages.getString("MessageTypeUi.information.label"); //$NON-NLS-1$
      }

      @Override
      public void visitWarning(final MessageType visitedType) {
        label[0] = LibGuiMessages.getString("MessageTypeUi.warning.label"); //$NON-NLS-1$
      }

      @Override
      public void visitNormal(final MessageType visitedType) {
        label[0] = LibGuiMessages.getString("MessageTypeUi.normal.label"); //$NON-NLS-1$
      }

      @Override
      public void visitError(final MessageType visitedType) {
        label[0] = LibGuiMessages.getString("MessageTypeUi.error.label"); //$NON-NLS-1$
      }

      @Override
      public void visitQuestion(final MessageType visitedType) {
        label[0] = LibGuiMessages.getString("MessageTypeUi.question.label"); //$NON-NLS-1$
      }
    });
    return label[0];
  }
}