package net.sf.anathema.hero.combos.display.view;

import net.sf.anathema.hero.combos.display.presenter.ComboContainer;
import net.sf.anathema.hero.combos.display.presenter.ComboView;
import org.jdesktop.swingx.JXTaskPaneContainer;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import java.awt.Color;

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
  public ComboView addView(String name, String description) {
    SwingComboView comboView = new SwingComboView(comboPane, name, description);
    comboPane.add(comboView.getComponent());
    return comboView;
  }
}