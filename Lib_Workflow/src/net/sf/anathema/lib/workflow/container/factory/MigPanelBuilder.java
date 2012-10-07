package net.sf.anathema.lib.workflow.container.factory;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.lib.gui.container.TitledPanel;
import net.sf.anathema.lib.gui.layout.AdditiveView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.view.AreaTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LabelTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class MigPanelBuilder {
  private final JPanel panel = new JPanel(new MigLayout(new LC().wrapAfter(2).fill().insets("2")));

  public ITextView addLineTextView(String labelName, int columnCount) {
    return addLabelledTextView(labelName, new LineTextView(columnCount));
  }

  public ITextView addAreaTextView(String labelName, int rowCount, int columnCount) {
    return addLabelledTextView(labelName, new AreaTextView(rowCount, columnCount));
  }

  public void addComponent(JComponent component, CC constraint) {
    panel.add(component, constraint);
  }

  public void addView(AdditiveView view, CC data) {
    view.addTo(panel, data);
  }

  public JComponent getUntitledContent() {
    return panel;
  }

  public JComponent getTitledContent(String title) {
    return new TitledPanel(title, panel);
  }

  private ITextView addLabelledTextView(String labelText, ITextView textView) {
    LabelTextView labelTextView = new LabelTextView(labelText, textView);
    labelTextView.addToMigPanel(panel);
    return textView;
  }
}