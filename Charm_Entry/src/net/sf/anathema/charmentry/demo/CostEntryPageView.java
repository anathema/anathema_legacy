package net.sf.anathema.charmentry.demo;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.charmentry.view.CostEntryView;
import net.sf.anathema.charmentry.view.ICostEntryView;

public class CostEntryPageView implements ICostEntryPageView {

  private final JPanel content = new JPanel(new GridDialogLayout(5, false));

  public JComponent getContent() {
    return content;
  }

  public void requestFocus() {
    // Nothing to do
  }

  public void dispose() {
    // Nothing to do
  }

  public ICostEntryView addCostEntryView(String typeLabel, String costLabel, String textLabel) {
    content.add(new JLabel(typeLabel));
    CostEntryView view = new CostEntryView(costLabel, textLabel);
    view.addTo(content);
    return view;
  }
}