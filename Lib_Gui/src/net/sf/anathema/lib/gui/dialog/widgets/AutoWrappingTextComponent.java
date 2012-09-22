package net.sf.anathema.lib.gui.dialog.widgets;

import com.google.common.base.Preconditions;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.gui.swing.SwingColors;
import net.sf.anathema.lib.model.ObjectModel;
import net.sf.anathema.lib.provider.Provider;
import net.sf.anathema.lib.text.TextAlignment;

import javax.swing.JComponent;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AutoWrappingTextComponent extends JComponent {

  private final int width;
  private final TextContent content = new TextContent();
  private final ObjectModel<TextSelection> selectionModel = new ObjectModel<TextSelection>();
  private TextAlignment textAlignment = TextAlignment.LEFT;

  public AutoWrappingTextComponent(String text, int width) {
    Preconditions.checkNotNull(text);
    this.width = width;
    setForeground(SwingColors.getTextAreaForegroundColor());
    setBackground(SwingColors.getTextAreaBackgroundColor());
    content.setTextBlocks(TextBlockFactory.createTextBlocks(text));
    setOpaque(true);
    MouseAdapter mouseListener = new MouseAdapter() {
      private TextPosition startPosition;

      @Override
      public void mouseReleased(MouseEvent e) {
        if (!isEnabled()) {
          return;
        }
        TextPosition position = getTextPositionAt(e.getPoint());
        if (position == null) {
          startPosition = null;
          return;
        }
        if (startPosition == null) {
          return;
        }
        selectionModel.setValue(TextSelection.createSelection(startPosition, position));
      }

      @Override
      public void mousePressed(MouseEvent e) {
        if (!isEnabled()) {
          return;
        }
        clearSelection();
        TextPosition position = getTextPositionAt(e.getPoint());
        startPosition = position;
        requestFocus();
      }

      @Override
      public void mouseDragged(MouseEvent e) {
        if (!isEnabled()) {
          return;
        }
        TextPosition position = getTextPositionAt(e.getPoint());
        if (position == null) {
          return;
        }
        if (startPosition == null) {
          return;
        }
        selectionModel.setValue(TextSelection.createSelection(startPosition, position));
      }

      private TextPosition getTextPositionAt(Point point) {
        FontMetrics metrics = getFontMetrics(getFont());
        TextPositionFindingHandler finder = new TextPositionFindingHandler(metrics, point);
        render(metrics, getWidth(), finder);
        TextPosition textPosition = finder.getTextPosition();
        if (textPosition == null) {
          if (point.y < 0) {
            return new TextPosition(0, 0);
          }
          return content.getLastTextPosition();
        }
        return textPosition;
      }
    };
    addMouseListener(mouseListener);
    addMouseMotionListener(mouseListener);
    selectionModel.addChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        repaint();
      }
    });
    addKeyListener(new TextComponentKeyListener(content, selectionModel, new Provider<Toolkit>() {
      @Override
      public Toolkit getValue() {
        return getToolkit();
      }
    }));
  }

  @Override
  public Dimension getMinimumSize() {
    return getPreferredSize();
  }

  @Override
  public Dimension getPreferredSize() {
    FontMetrics fontMetrics = getFontMetrics(getFont());
    LineCountingAndMaxWidthCalculatingRenderingHandler handler = new LineCountingAndMaxWidthCalculatingRenderingHandler(
        fontMetrics);
    render(fontMetrics, width, handler);
    int preferredSizeWidth = Math.max(handler.getMaxLineWidth(), width);
    if (preferredSizeWidth > width) {
      handler = new LineCountingAndMaxWidthCalculatingRenderingHandler(fontMetrics);
      render(fontMetrics, preferredSizeWidth, handler);
    }
    int lineCount = handler.getLineCount();
    return new Dimension(preferredSizeWidth, (fontMetrics.getHeight() * lineCount));
  }

  @Override
  public void paintComponent(Graphics g) {
    if (isOpaque()) {
      g.setColor(getBackground());
      g.fillRect(0, 0, getWidth(), getHeight());
    }
    g.setFont(getFont());
    FontMetrics metrics = g.getFontMetrics();
    render(metrics, getWidth(), new TextGraphicsRenderingHandler(g, getForeground()));
  }

  protected void render(
      FontMetrics metrics,
      int layoutWidth,
      IBlockRenderingHandler blockRenderer) {
    LineBuffer lineBuffer = new LineBuffer(
        metrics,
        layoutWidth,
        blockRenderer,
        textAlignment,
        selectionModel);
    int xOffset = 0;
    int x = xOffset;
    for (int blockIndex = 0; blockIndex < content.getBlockCount(); ++blockIndex) {
      TextBlock block = content.getBlock(blockIndex);
      int spaceLeft = layoutWidth - x;
      int blockWidth = metrics.stringWidth(block.text);
      boolean fits = spaceLeft >= blockWidth;
      if (!fits && blockIndex > 0) {
        lineBuffer.handleAutoLineBreak();
        x = xOffset;
      }
      lineBuffer.add(block, blockWidth);
      x += blockWidth;
      TextBlockDelimiter delimiter = block.delimiter;
      if (delimiter.isNewLine()) {
        lineBuffer.handleNewLine();
        x = xOffset;
      }
      else {
        x += delimiter.calculateWidth(metrics);
      }
    }
  }

  public void setText(String text) {
    clearSelection();
    content.setTextBlocks(TextBlockFactory.createTextBlocks(text));
    invalidate();
    repaint();
  }

  private void clearSelection() {
    selectionModel.setValue(null);
  }

}