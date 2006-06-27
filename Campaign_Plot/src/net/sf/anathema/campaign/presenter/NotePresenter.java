package net.sf.anathema.campaign.presenter;

import net.sf.anathema.framework.itemdata.AbstractBasicItemPresenter;
import net.sf.anathema.framework.itemdata.model.IBasicItemData;
import net.sf.anathema.framework.itemdata.view.IBasicItemView;
import net.sf.anathema.lib.resources.IResources;

public class NotePresenter extends AbstractBasicItemPresenter {

  private final IBasicItemView view;
  private final IResources resources;

  public NotePresenter(IBasicItemView view, IResources resources, IBasicItemData itemData) {
    super(resources, itemData);
    this.view = view;
    this.resources = resources;
  }

  @Override
  protected String getNameLabelKey() {
    return "NoteDescription.NoteName.Label"; //$NON-NLS-1$
  }

  @Override
  protected String getContentLabelKey() {
    return "NoteDescription.NoteContent.Label"; //$NON-NLS-1$
  }

  @Override
  protected String getBorderTitleKey() {
    return "NoteDescription.BorderTitle"; //$NON-NLS-1$
  }

  public void initPresentation() {
    initDescriptionPresentation(
        resources.getString(getNameLabelKey()),
        view.addDescriptionView(resources.getString(getBorderTitleKey())));
  }
}
