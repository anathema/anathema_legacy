package net.sf.anathema.framework.view.util;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.geom.AffineTransform;

public abstract class AbstractVerticalButtonUi extends BasicButtonUI {

  @Override
  public Dimension getPreferredSize(JComponent c) {
    Dimension dim = super.getPreferredSize(c);
    return new Dimension(dim.height + 6, dim.width + 10);
  }

  protected void paintScaffold(Graphics graphics, JComponent component) {
    AbstractButton button = (AbstractButton) component;
    ButtonModel model = button.getModel();
    if (model.isRollover()) {
      Rectangle bounds = component.getBounds();
      graphics.setColor(model.isArmed() ? UIManager.getColor("controlShadow") : UIManager.getColor("controlLHighlight"));
      graphics.fillRect(0, 0, bounds.width - 1, bounds.height - 1);
      graphics.setColor(Color.DARK_GRAY);
      graphics.drawRect(0, 0, bounds.width - 1, bounds.height - 1);
    }
  }

  @Override
  public final void paint(Graphics graphics, JComponent component) {
    paintScaffold(graphics, component);
    AbstractButton button = (AbstractButton) component;
    String text = button.getText();
    Icon icon = (button.isEnabled()) ? button.getIcon() : button.getDisabledIcon();
    if ((icon == null) && (text == null)) {
      return;
    }
    Rectangle paintIconR = new Rectangle();
    Rectangle paintTextR = new Rectangle();
    Rectangle paintViewR = new Rectangle();
    Insets paintViewInsets = new Insets(0, 0, 0, 0);

    FontMetrics fontMetrics = graphics.getFontMetrics();
    paintViewInsets = component.getInsets(paintViewInsets);

    paintViewR.x = paintViewInsets.left;
    paintViewR.y = paintViewInsets.top;

    // Use inverted height &amp; width
    paintViewR.height = component.getWidth() - (paintViewInsets.left + paintViewInsets.right);
    paintViewR.width = component.getHeight() - (paintViewInsets.top + paintViewInsets.bottom);

    paintIconR.x = paintIconR.y = paintIconR.width = paintIconR.height = 0;
    paintTextR.x = paintTextR.y = paintTextR.width = paintTextR.height = 0;

    Graphics2D g2 = (Graphics2D) graphics;
    AffineTransform tr = g2.getTransform();
    transform(g2, component);
    paintViewR.x = component.getHeight() / 2 - (int) fontMetrics.getStringBounds(text, graphics).getWidth() / 2;
    paintViewR.y = component.getWidth() / 2 - (int) fontMetrics.getStringBounds(text, graphics).getHeight() / 2;

    if (icon != null) {
      icon.paintIcon(component, graphics, paintIconR.x, paintIconR.y);
    }

    if (text != null) {
      int textX = paintTextR.x;
      int textY = paintTextR.y + fontMetrics.getAscent();
      paintText(graphics, component, new Rectangle(paintViewR.x, paintViewR.y, textX, textY), text);
    }
    g2.setTransform(tr);
  }

  protected abstract void transform(Graphics2D graphics, JComponent component);
}
