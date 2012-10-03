package net.sf.anathema.campaign.view;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.framework.itemdata.view.IBasicItemDescriptionView;
import net.sf.anathema.framework.presenter.view.AbstractInitializableContentView;
import net.sf.anathema.framework.styledtext.IStyledTextView;
import net.sf.anathema.framework.styledtext.ITextEditorProperties;
import net.sf.anathema.framework.styledtext.TextEditor;
import net.sf.anathema.lib.workflow.container.factory.MigPanelBuilder;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.StyledDocument;
import java.awt.Dimension;

public class BasicItemDescriptionView extends AbstractInitializableContentView<Object> implements IBasicItemDescriptionView {
  private final MigPanelBuilder panelBuilder = new MigPanelBuilder();
  private static final int COLUMN_COUNT = 45;

  @Override
  public ITextView addLineTextView(String labelName) {
    return panelBuilder.addLineTextView(labelName, COLUMN_COUNT);
  }

  @Override
  public IStyledTextView addStyledTextView(final String labelName, StyledDocument document, Dimension preferredSize,
                                           ITextEditorProperties properties) {
    TextEditor textEditor = new TextEditor(document, properties, preferredSize);
    panelBuilder.addComponent(new JLabel(labelName), new CC().alignY("top"));
    panelBuilder.addComponent(textEditor.getComponent(), new CC().grow().push());
    return textEditor;
  }

  @Override
  protected void createContent(JPanel panel, Object properties) {
    panel.setLayout(new MigLayout(new LC().wrapAfter(1).insets("2").fill()));
    panel.add(panelBuilder.getUntitledContent(), new CC().grow().push());
  }
}