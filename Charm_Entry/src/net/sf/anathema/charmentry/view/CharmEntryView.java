package net.sf.anathema.charmentry.view;

import java.awt.Component;

import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.charmentry.presenter.IKeywordView;

public class CharmEntryView {

  private final JPanel content = new JPanel(new GridDialogLayout(1, false));

  public BasicDataView addBasicDataView() {
    BasicDataView view = new BasicDataView();
    content.add(view.getContent());
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
}