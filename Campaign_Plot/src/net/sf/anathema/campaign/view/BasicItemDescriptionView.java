package net.sf.anathema.campaign.view;

import net.miginfocom.layout.CC;
import net.sf.anathema.campaign.note.view.IBasicItemDescriptionView;
import net.sf.anathema.framework.styledtext.IStyledTextView;
import net.sf.anathema.framework.styledtext.ITextEditorProperties;
import net.sf.anathema.framework.styledtext.TextEditor;
import net.sf.anathema.lib.workflow.container.factory.MigPanelBuilder;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.text.StyledDocument;

public class BasicItemDescriptionView implements IBasicItemDescriptionView {
  private final MigPanelBuilder panelBuilder = new MigPanelBuilder();
  private static final int COLUMN_COUNT = 45;

  @Override
  public ITextView addLineTextView(String labelName) {
    return panelBuilder.addLineTextView(labelName, COLUMN_COUNT);
  }

  @Override
  public IStyledTextView addStyledTextView(String labelName, StyledDocument document,
                                           ITextEditorProperties properties) {
    TextEditor textEditor = new TextEditor(document, properties);
    panelBuilder.addComponent(new JLabel(labelName), new CC().alignY("top"));
    panelBuilder.addComponent(textEditor.getComponent(), new CC().grow().push());
    return textEditor;
  }

  @Override
  public final JComponent getComponent() {
    return panelBuilder.getUntitledContent();
  }
}