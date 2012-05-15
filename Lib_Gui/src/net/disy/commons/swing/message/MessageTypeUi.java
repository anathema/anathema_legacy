/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.message;

import net.disy.commons.swing.icon.EmptyIcon;
import net.disy.commons.swing.image.DisyCommonsSwingImageProvider;
import net.sf.anathema.lib.message.IMessageTypeVisitor;
import net.sf.anathema.lib.message.MessageType;

import javax.swing.Icon;

public class MessageTypeUi extends AbstractMessageTypeUi {

  public static final Icon errorIcon = DisyCommonsSwingImageProvider.getInstance().getImageIcon(
      "message/small/error.gif"); //$NON-NLS-1$
  public static final Icon warningIcon = DisyCommonsSwingImageProvider.getInstance().getImageIcon(
      "message/small/warning.gif"); //$NON-NLS-1$
  public static final Icon infoIcon = DisyCommonsSwingImageProvider.getInstance().getImageIcon(
      "message/small/info.gif"); //$NON-NLS-1$
  public static final Icon normalIcon = EmptyIcon.DEFAULT_ICON;
  public static final Icon questionIcon = DisyCommonsSwingImageProvider.getInstance().getImageIcon(
      "message/small/question.gif"); //$NON-NLS-1$

  private static MessageTypeUi instance = new MessageTypeUi();

  @Override
  public Icon getIcon(final MessageType type) {
    final Icon[] icon = new Icon[1];
    type.accept(new IMessageTypeVisitor() {
      @Override
      public void visitError(final MessageType visitedType) {
        icon[0] = errorIcon;
      }

      @Override
      public void visitNormal(final MessageType visitedType) {
        icon[0] = normalIcon;
      }

      @Override
      public void visitWarning(final MessageType visitedType) {
        icon[0] = warningIcon;
      }

      @Override
      public void visitInformation(final MessageType visitedType) {
        icon[0] = infoIcon;
      }

      @Override
      public void visitQuestion(final MessageType visitedType) {
        icon[0] = questionIcon;
      }
    });
    return icon[0];
  }

  private MessageTypeUi() {
    //no instance available
  }

  public static MessageTypeUi getInstance() {
    return instance;
  }
}