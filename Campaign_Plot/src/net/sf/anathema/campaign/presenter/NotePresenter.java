package net.sf.anathema.campaign.presenter;

import java.awt.Dimension;

import javax.swing.text.DefaultStyledDocument;

import net.sf.anathema.framework.itemdata.model.IBasicItemData;
import net.sf.anathema.framework.itemdata.view.IBasicItemDescriptionView;
import net.sf.anathema.framework.itemdata.view.IBasicItemView;
import net.sf.anathema.framework.styledtext.presentation.StyledTextManager;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

public class NotePresenter implements IPresenter {

  private final IBasicItemView view;
  private final IResources resources;
  private final IBasicItemData item;

  public NotePresenter(IBasicItemView view, IResources resources, IBasicItemData itemData) {
    this.view = view;
    this.resources = resources;
    this.item = itemData;
  }

  private String getNameLabelKey() {
    return "NoteDescription.NoteName.Label"; //$NON-NLS-1$
  }

  private String getContentLabelKey() {
    return "NoteDescription.NoteContent.Label"; //$NON-NLS-1$
  }

  private String getBorderTitleKey() {
    return "NoteDescription.BorderTitle"; //$NON-NLS-1$
  }

  public void initPresentation() {
    initDescriptionPresentation(resources.getString(getNameLabelKey()), view.addDescriptionView());
  }

  private final void initDescriptionPresentation(String nameLabel, IBasicItemDescriptionView descriptionView) {
    new TextualPresentation().initView(descriptionView.addLineTextView(nameLabel), item.getDescription().getName());
    String summaryLabel = resources.getString(getContentLabelKey());
    DefaultStyledDocument document = new DefaultStyledDocument();
    StyledTextManager.initView(new StyledTextManager(document), item.getDescription().getContent());
    descriptionView.addStyledTextView(summaryLabel, document, new Dimension(200, 200), new TextEditorProperties(
        resources));
    descriptionView.initGui(resources.getString(getBorderTitleKey()));
  }
}