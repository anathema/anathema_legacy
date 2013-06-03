package net.sf.anathema.campaign.view;

import net.miginfocom.layout.CC;
import net.sf.anathema.campaign.note.view.IBasicItemDescriptionView;
import net.sf.anathema.framework.styledtext.IStyledTextView;
import net.sf.anathema.campaign.styledtext.ITextEditorProperties;
import net.sf.anathema.campaign.styledtext.TextEditor;
import net.sf.anathema.lib.workflow.container.factory.MigPanelBuilder;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.text.DefaultStyledDocument;

public class BasicItemDescriptionView implements IBasicItemDescriptionView {
  private final MigPanelBuilder panelBuilder = new MigPanelBuilder();
  private final ITextEditorProperties editorProperties;

  public BasicItemDescriptionView(ITextEditorProperties editorProperties) {
    this.editorProperties = editorProperties;
  }

  @Override
  public ITextView addLineTextView(String labelName) {
    return panelBuilder.addLineTextView(labelName);
  }

  @Override
  public IStyledTextView addStyledTextView(String labelName) {
    DefaultStyledDocument styledDocument = new DefaultStyledDocument();
    TextEditor textEditor = new TextEditor(styledDocument, editorProperties);
    panelBuilder.addComponent(new JLabel(labelName), new CC().alignY("top"));
    panelBuilder.addComponent(textEditor.getComponent(), new CC().grow().push());
    return textEditor;
  }

  public final JComponent getComponent() {
    return panelBuilder.getUntitledContent();
  }
}