package net.sf.anathema.campaign.view;

import net.sf.anathema.campaign.note.view.IBasicItemDescriptionView;
import net.sf.anathema.campaign.note.view.IBasicItemView;
import net.sf.anathema.framework.styledtext.ITextEditorProperties;
import net.sf.anathema.framework.view.item.AbstractItemView;

import javax.swing.Icon;
import javax.swing.JComponent;

public class BasicItemView extends AbstractItemView implements IBasicItemView {
  private BasicItemDescriptionView descriptionView;

  public BasicItemView(String name, Icon icon, ITextEditorProperties editorProperties) {
    super(name, icon);
    this.descriptionView = new BasicItemDescriptionView(editorProperties);
  }

  @Override
  public IBasicItemDescriptionView getDescriptionView() {
    return descriptionView;
  }

  @Override
  public JComponent getComponent() {
    return descriptionView.getComponent();
  }
}