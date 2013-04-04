package net.sf.anathema.campaign.presenter;

import net.sf.anathema.campaign.note.model.IBasicItemData;
import net.sf.anathema.campaign.note.view.IBasicItemDescriptionView;
import net.sf.anathema.campaign.note.view.NoteView;
import net.sf.anathema.framework.styledtext.IStyledTextView;
import net.sf.anathema.framework.styledtext.model.IStyledTextChangeListener;
import net.sf.anathema.framework.styledtext.model.IStyledTextualDescription;
import net.sf.anathema.framework.styledtext.model.ITextPart;
import net.sf.anathema.framework.styledtext.presentation.StyledText;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

public class NotePresenter {
  private static final String NOTE_NAME_LABEL = "NoteDescription.NoteName.Label";
  private static final String NOTE_CONTENT_LABEL = "NoteDescription.NoteContent.Label";
  private final NoteView view;
  private final Resources resources;
  private final IBasicItemData item;

  public NotePresenter(NoteView view, Resources resources, IBasicItemData itemData) {
    this.view = view;
    this.resources = resources;
    this.item = itemData;
  }

  public void initPresentation() {
    String nameLabel = resources.getString(NOTE_NAME_LABEL);
    String summaryLabel = resources.getString(NOTE_CONTENT_LABEL);
    IBasicItemDescriptionView descriptionView = view.getDescriptionView();
    ITextView textView = descriptionView.addLineTextView(nameLabel);
    new TextualPresentation().initView(textView, item.getDescription().getName());
    IStyledTextView styledTextView = descriptionView.addStyledTextView(summaryLabel);
    initView(styledTextView, item.getDescription().getContent());
  }


  private void initView(final StyledText manager, final IStyledTextualDescription textualDescription) {
    manager.addStyledTextListener(new IStyledTextChangeListener() {
      @Override
      public void textChanged(ITextPart[] newParts) {
        textualDescription.setText(newParts);
      }
    });
    manager.setText(textualDescription.getTextParts());
    textualDescription.addTextChangedListener(new IStyledTextChangeListener() {
      @Override
      public void textChanged(ITextPart[] newParts) {
        manager.setText(newParts);
      }
    });
  }
}