package net.sf.anathema.lib.gui.dialog.animation;

import com.google.common.base.Preconditions;

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
    Preconditions.checkNotNull(baseComponent);
    Preconditions.checkNotNull(overlaidComponent);
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