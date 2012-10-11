package net.sf.anathema.campaign.presenter;

import net.sf.anathema.campaign.note.model.IBasicItemData;
import net.sf.anathema.campaign.note.view.IBasicItemDescriptionView;
import net.sf.anathema.campaign.note.view.IBasicItemView;
import net.sf.anathema.framework.styledtext.presentation.StyledTextManager;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

import javax.swing.text.DefaultStyledDocument;

public class NotePresenter {
  private static final String NOTE_NAME_LABEL = "NoteDescription.NoteName.Label";
  private static final String NOTE_CONTENT_LABEL = "NoteDescription.NoteContent.Label";
  private static final String NOTE_BORDER_TITLE = "NoteDescription.BorderTitle";
  private final IBasicItemView view;
  private final IResources resources;
  private final IBasicItemData item;

  public NotePresenter(IBasicItemView view, IResources resources, IBasicItemData itemData) {
    this.view = view;
    this.resources = resources;
    this.item = itemData;
  }

  public void initPresentation() {
    String nameLabel = resources.getString(NOTE_NAME_LABEL);
    String summaryLabel = resources.getString(NOTE_CONTENT_LABEL);
    String borderTitle = resources.getString(NOTE_BORDER_TITLE);
    IBasicItemDescriptionView descriptionView = view.getDescriptionView();
    new TextualPresentation().initView(descriptionView.addLineTextView(nameLabel), item.getDescription().getName());
    DefaultStyledDocument document = new DefaultStyledDocument();
    StyledTextManager.initView(new StyledTextManager(document), item.getDescription().getContent());
    descriptionView.addStyledTextView(summaryLabel, document, new TextEditorProperties(
        resources));
    descriptionView.initGui(borderTitle);
  }
}
