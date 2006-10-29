package net.sf.anathema.campaign.view;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.StyledDocument;

import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.framework.itemdata.view.IBasicItemDescriptionView;
import net.sf.anathema.framework.presenter.view.AbstractInitializableContentView;
import net.sf.anathema.framework.styledtext.IStyledTextView;
import net.sf.anathema.framework.styledtext.ITextEditorProperties;
import net.sf.anathema.framework.styledtext.TextEditor;
import net.sf.anathema.lib.workflow.container.factory.StandardPanelBuilder;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public class BasicItemDescriptionView extends AbstractInitializableContentView<Object> implements IBasicItemDescriptionView {

  private final StandardPanelBuilder standardPanelBuilder = new StandardPanelBuilder();

  private static final int COLUMN_COUNT = 45;

  public ITextView addLineTextView(final String labelName) {
    return standardPanelBuilder.addLineTextView(labelName, COLUMN_COUNT);
  }

  public IStyledTextView addStyledTextView(
      final String labelName,
      StyledDocument document,
      final Dimension preferredSize,
      ITextEditorProperties properties) {
    final TextEditor textEditor = new TextEditor(document, properties, preferredSize);
    standardPanelBuilder.addDialogComponent(new IDialogComponent() {
      public int getColumnCount() {
        return 2;
      }

      public void fillInto(JPanel panel, int columnCount) {
        GridDialogLayoutData labelLayoutData = new GridDialogLayoutData();
        labelLayoutData.setHorizontalAlignment(GridAlignment.BEGINNING);
        labelLayoutData.setVerticalAlignment(GridAlignment.BEGINNING);
        panel.add(new JLabel(labelName), labelLayoutData);
        JComponent component = textEditor.getComponent();
        panel.add(component, GridDialogLayoutData.FILL_BOTH);
      }
    });
    return textEditor;
  }

  @Override
  protected void createContent(JPanel panel, Object properties) {
    panel.setLayout(new GridDialogLayout(1, false));
    JPanel descriptionContent = standardPanelBuilder.getUntitledContent();
    panel.add(descriptionContent, GridDialogLayoutData.FILL_BOTH);
  }
}