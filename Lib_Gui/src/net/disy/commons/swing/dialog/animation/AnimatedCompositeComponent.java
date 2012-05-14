/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.dialog.animation;

import net.disy.commons.core.util.Ensure;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimatedCompositeComponent extends JPanel {

  private boolean overlaidComponentVisible;
  private int overlayPosition = 0;

  private final Timer timer;

  public AnimatedCompositeComponent(
      final JComponent baseComponent,
      final JComponent overlaidComponent) {
    Ensure.ensureArgumentNotNull(baseComponent);
    Ensure.ensureArgumentNotNull(overlaidComponent);
    setLayout(new AnimatedCompositeLayout(baseComponent, overlaidComponent));
    add(overlaidComponent);
    add(baseComponent);

    timer = new Timer(15, new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        updateAnimation();
      }
    });
    timer.setInitialDelay(0);
  }

  public void setOverlaidComponentVisible(final boolean overlaidComponentVisible) {
    if (overlaidComponentVisible == this.overlaidComponentVisible) {
      return;
    }
    this.overlaidComponentVisible = overlaidComponentVisible;
    overlayPosition = getHeight() - overlayPosition;
    timer.start();
  }

  private void updateAnimation() {
    if (oneStep()) {
      revalidate();
    }
    else {
      timer.stop();
    }
  }

  private boolean oneStep() {
    if (overlayPosition <= 0) {
      overlayPosition = 0;
      return false;
    }
    --overlayPosition;
    return true;
  }

  public boolean isOverlayVisible() {
    return overlaidComponentVisible;
  }

  public int getOverlayPosition() {
    return overlayPosition;
  }
}