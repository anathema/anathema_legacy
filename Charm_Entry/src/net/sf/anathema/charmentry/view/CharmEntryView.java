package net.sf.anathema.charmentry.view;

import java.awt.Component;

import javax.swing.JPanel;

public class CharmEntryView {

  private final JPanel content = new JPanel();

  public BasicDataView addBasicDataView() {
    BasicDataView view = new BasicDataView();
    content.add(view.getContent());
    return view;
  }

  public Component getContent() {
    return content;
  }
}