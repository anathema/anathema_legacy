package net.sf.anathema.campaign.view;

import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogLayoutDataFactory;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.framework.itemdata.view.IBasicItemDescriptionView;
import net.sf.anathema.framework.presenter.view.AbstractInitializableContentView;
import net.sf.anathema.framework.styledtext.IStyledTextView;
import net.sf.anathema.framework.styledtext.ITextEditorProperties;
import net.sf.anathema.framework.styledtext.TextEditor;
import net.sf.anathema.lib.workflow.container.factory.StandardPanelBuilder;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.StyledDocument;
import java.awt.Dimension;

public class BasicItemDescriptionView extends AbstractInitializableContentView<Object> implements
    IBasicItemDescriptionView {

  private final StandardPanelBuilder standardPanelBuilder = new StandardPanelBuilder();

  private static final int COLUMN_COUNT = 45;

  @Override
  public ITextView addLineTextView(String labelName) {
    return standardPanelBuilder.addLineTextView(labelName, COLUMN_COUNT);
  }

  @Override
  public IStyledTextView addStyledTextView(
      final String labelName,
      StyledDocument document,
      Dimension preferredSize,
      ITextEditorProperties properties) {
    final TextEditor textEditor = new TextEditor(document, properties, preferredSize);
    standardPanelBuilder.addDialogComponent(new IDialogComponent() {
      @Override
      public int getColumnCount() {
        return 2;
      }

      @Override
      public void fillInto(JPanel panel, int columnCount) {
        panel.add(new JLabel(labelName), GridDialogLayoutDataFactory.createTopData());
        panel.add(textEditor.getComponent(), GridDialogLayoutData.FILL_BOTH);
      }
    });
    return textEditor;
  }

  @Override
  protected void createContent(JPanel panel, Object properties) {
    panel.setLayout(new MigLayout(new LC().wrapAfter(1).insets("2").fill()));
    panel.add(standardPanelBuilder.getUntitledContent(), new CC().grow().push());
  }
}