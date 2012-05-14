/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.message;

import net.disy.commons.core.message.IMessageTypeVisitor;
import net.disy.commons.core.message.MessageType;
import net.disy.commons.swing.icon.EmptyIcon;
import net.disy.commons.swing.icon.SwingIcons;

import javax.swing.Icon;

public class LargeIconMessageTypeUi extends AbstractMessageTypeUi {

  @Override
  public Icon getIcon(final MessageType type) {
    final Icon[] icon = new Icon[1];
    type.accept(new IMessageTypeVisitor() {
      @Override
      public void visitError(final MessageType visitedType) {
        icon[0] = SwingIcons.getOptionPaneErrorIcon();
      }

      @Override
      public void visitNormal(final MessageType visitedType) {
        icon[0] = new EmptyIcon();
      }

      @Override
      public void visitWarning(final MessageType visitedType) {
        icon[0] = SwingIcons.getOptionPaneWarningIcon();
      }

      @Override
      public void visitInformation(final MessageType visitedType) {
        icon[0] = SwingIcons.getOptionPaneInformationIcon();
      }

      @Override
      public void visitQuestion(final MessageType visitedType) {
        icon[0] = SwingIcons.getOptionPaneQuestionIcon();
      }
    });
    return icon[0];
  }
}