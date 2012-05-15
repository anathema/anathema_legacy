package net.sf.anathema.lib.gui.container;

import com.google.common.base.Preconditions;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IGridDialogLayoutData;
import net.disy.commons.swing.layout.util.LayoutUtilities;
import net.sf.anathema.lib.gui.swing.BorderUtilities;
import net.sf.anathema.lib.gui.swing.GuiUtilities;
import net.sf.anathema.lib.util.Closure;
import net.sf.anathema.lib.util.NullClosure;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TitledPanel extends JPanel {

  public TitledPanel(String title, JComponent content) {
    this(title, content, new GridDialogLayoutData());
  }

  public TitledPanel(
      String title,
      JComponent content,
      IGridDialogLayoutData layoutData) {
    this(title, content, layoutData, new NullClosure<TitledBorder>());
  }

  public TitledPanel(
      String title,
      final JComponent content,
      IGridDialogLayoutData layoutData,
      Closure<TitledBorder> decorator) {
    super(new GridLayout(1, 0));
    Preconditions.checkNotNull(title);
    Preconditions.checkNotNull(content);
    TitledBorder titledBorder = new TitledBorder(title);
    BorderUtilities.attachDisableableTitledBorder(this, titledBorder);
    decorator.execute(titledBorder);

    setBorder(new CompoundBorder(getBorder(), new EmptyBorder(
        LayoutUtilities.getDpiAdjusted(2),
        LayoutUtilities.getDpiAdjusted(4),
        LayoutUtilities.getDpiAdjusted(4),
        LayoutUtilities.getDpiAdjusted(4))));
    add(content, layoutData);

    content.addPropertyChangeListener(
        GuiUtilities.ENABLED_PROPERTY_NAME,
        new PropertyChangeListener() {
          @Override
          public void propertyChange(PropertyChangeEvent evt) {
            setEnabled(content.isEnabled());
          }
        });
    setEnabled(content.isEnabled());
  }
}