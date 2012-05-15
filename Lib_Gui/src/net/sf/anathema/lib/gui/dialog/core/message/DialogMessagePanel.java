/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.dialog.core.message;

import com.google.common.base.Preconditions;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.gui.dialog.animation.AnimatedCompositeComponent;
import net.sf.anathema.lib.gui.dialog.animation.OverlaidComponentBorder;

import javax.swing.JComponent;

public class DialogMessagePanel {

  private final DialogMessageModel messageModel;
  private final DialogMessageComponent baseMessageComponent;
  private final DialogMessageComponent overlaidMessageComponent;
  private final AnimatedCompositeComponent content;

  public DialogMessagePanel(final DialogMessageModel messageModel) {
    Preconditions.checkNotNull(messageModel);
    this.messageModel = messageModel;
    baseMessageComponent = new DialogMessageComponent(false);
    overlaidMessageComponent = new DialogMessageComponent(true);
    overlaidMessageComponent.setBorder(new OverlaidComponentBorder());

    this.content = new AnimatedCompositeComponent(baseMessageComponent, overlaidMessageComponent);

    messageModel.addChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        updateMessage();
      }
    });
    updateMessage();
  }

  private void updateMessage() {
    baseMessageComponent.setMessage(messageModel.getBaseMessage());
    overlaidMessageComponent.setMessage(messageModel.getOverlaidMessage());
    content.setOverlaidComponentVisible(messageModel.isOverlaidMessageActive());
  }

  public JComponent getContent() {
    return content;
  }
}