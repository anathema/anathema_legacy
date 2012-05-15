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

  public AutoWrappingTextComponent(final String text, final int width) {
    Preconditions.checkNotNull(text);
    this.width = width;
    setForeground(SwingColors.getTextAreaForegroundColor());
    setBackground(SwingColors.getTextAreaBackgroundColor());
    content.setTextBlocks(TextBlockFactory.createTextBlocks(text));
    setOpaque(true);
    final MouseAdapter mouseListener = new MouseAdapter() {
      private TextPosition startPosition;

      @Override
      public void mouseReleased(final MouseEvent e) {
        if (!isEnabled()) {
          return;
        }
        final TextPosition position = getTextPositionAt(e.getPoint());
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
      public void mousePressed(final MouseEvent e) {
        if (!isEnabled()) {
          return;
        }
        clearSelection();
        final TextPosition position = getTextPositionAt(e.getPoint());
        startPosition = position;
        requestFocus();
      }

      @Override
      public void mouseDragged(final MouseEvent e) {
        if (!isEnabled()) {
          return;
        }
        final TextPosition position = getTextPositionAt(e.getPoint());
        if (position == null) {
          return;
        }
        if (startPosition == null) {
          return;
        }
        selectionModel.setValue(TextSelection.createSelection(startPosition, position));
      }

      private TextPosition getTextPositionAt(final Point point) {
        final FontMetrics metrics = getFontMetrics(getFont());
        final TextPositionFindingHandler finder = new TextPositionFindingHandler(metrics, point);
        render(metrics, getWidth(), finder);
        final TextPosition textPosition = finder.getTextPosition();
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
      public Toolkit getObject() {
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
    final FontMetrics fontMetrics = getFontMetrics(getFont());
    LineCountingAndMaxWidthCalculatingRenderingHandler handler = new LineCountingAndMaxWidthCalculatingRenderingHandler(
        fontMetrics);
    render(fontMetrics, width, handler);
    final int preferredSizeWidth = Math.max(handler.getMaxLineWidth(), width);
    if (preferredSizeWidth > width) {
      handler = new LineCountingAndMaxWidthCalculatingRenderingHandler(fontMetrics);
      render(fontMetrics, preferredSizeWidth, handler);
    }
    final int lineCount = handler.getLineCount();
    return new Dimension(preferredSizeWidth, (fontMetrics.getHeight() * lineCount));
  }

  @Override
  public void paintComponent(final Graphics g) {
    if (isOpaque()) {
      g.setColor(getBackground());
      g.fillRect(0, 0, getWidth(), getHeight());
    }
    g.setFont(getFont());
    final FontMetrics metrics = g.getFontMetrics();
    render(metrics, getWidth(), new TextGraphicsRenderingHandler(g, getForeground()));
  }

  protected void render(
      final FontMetrics metrics,
      final int layoutWidth,
      final IBlockRenderingHandler blockRenderer) {
    final LineBuffer lineBuffer = new LineBuffer(
        metrics,
        layoutWidth,
        blockRenderer,
        textAlignment,
        selectionModel);
    final int xOffset = 0;
    int x = xOffset;
    for (int blockIndex = 0; blockIndex < content.getBlockCount(); ++blockIndex) {
      final TextBlock block = content.getBlock(blockIndex);
      final int spaceLeft = layoutWidth - x;
      final int blockWidth = metrics.stringWidth(block.text);
      final boolean fits = spaceLeft >= blockWidth;
      if (!fits && blockIndex > 0) {
        lineBuffer.handleAutoLineBreak();
        x = xOffset;
      }
      lineBuffer.add(block, blockWidth);
      x += blockWidth;
      final TextBlockDelimiter delimiter = block.delimiter;
      if (delimiter.isNewLine()) {
        lineBuffer.handleNewLine();
        x = xOffset;
      }
      else {
        x += delimiter.calculateWidth(metrics);
      }
    }
  }

  public void setText(final String text) {
    clearSelection();
    content.setTextBlocks(TextBlockFactory.createTextBlocks(text));
    invalidate();
    repaint();
  }

  private void clearSelection() {
    selectionModel.setValue(null);
  }

}