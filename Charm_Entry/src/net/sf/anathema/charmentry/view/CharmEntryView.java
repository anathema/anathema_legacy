package net.sf.anathema.charmentry.view;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.charmentry.presenter.IKeywordView;

public class CharmEntryView {

  private final JPanel content = new JPanel(new GridDialogLayout(2, false));

  public BasicDataView addBasicDataView() {
    BasicDataView view = new BasicDataView();
    GridDialogLayoutData data = new GridDialogLayoutData();
    data.setHorizontalSpan(2);
    content.add(view.getContent(), data);
    return view;
  }

  public Component getContent() {
    return content;
  }

  public IKeywordView addKeywordView() {
    KeywordView view = new KeywordView();
    content.add(view.getComponent());
    return view;
  }

  public JButton addSaveButton(String text) {
    JButton button = new JButton(text);
    GridDialogLayoutData data = new GridDialogLayoutData();
    data.setHorizontalAlignment(GridAlignment.END);
    data.setVerticalAlignment(GridAlignment.BEGINNING);
    content.add(button, data);
    return button;
  }
}