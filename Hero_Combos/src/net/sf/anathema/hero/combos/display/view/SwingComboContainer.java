package net.sf.anathema.hero.combos.display.view;

import net.sf.anathema.hero.combos.display.presenter.ComboView;
import org.jdesktop.swingx.JXTaskPaneContainer;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import java.awt.Color;

import static net.sf.anathema.lib.gui.swing.GuiUtilities.revalidate;

public class SwingComboContainer implements ComboContainer {
  private final JXTaskPaneContainer comboPane = new JXTaskPaneContainer();
  private final JScrollPane comboScrollPane = new JScrollPane(comboPane);

  public JComponent getComponent() {
    return comboScrollPane;
  }

  public void adjustBackgroundColor(Color background) {
    comboPane.setBackground(background);
  }

  @Override
  public void remove(ComboView view) {
    SwingComboView comboView = (SwingComboView) view;
    comboPane.remove(comboView.getTaskGroup());
    revalidateContainer();
  }

  @Override
  public ComboView addView(String name, String description) {
    SwingComboView comboView = new SwingComboView();
    comboView.initGui(name, description);
    comboPane.add(comboView.getTaskGroup());
    revalidateContainer();
    return comboView;
  }

  private void revalidateContainer() {
    revalidate(comboPane);
    revalidate(comboScrollPane);
  }
}