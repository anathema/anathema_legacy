package net.sf.anathema.character.impl.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.character.view.ICharacterDescriptionView;
import net.sf.anathema.framework.presenter.view.AbstractTabView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.view.AreaTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LabelTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

public class CharacterDescriptionView extends AbstractTabView<Object> implements ICharacterDescriptionView {

  private static final int TEXT_COLUMS = 45;
  private EditButtonDialogPanel dialogPanel = new EditButtonDialogPanel();

  @Override
  protected void createContent(JPanel panel, Object object) {
    panel.setLayout(new BorderLayout());
    panel.add(dialogPanel.getContent(), BorderLayout.CENTER);
  }

  public ITextView addLineView(final String labelText) {
    return addTextView(labelText, new LineTextView(TEXT_COLUMS));
  }

  public ITextView addAreaView(String labelText, int rows) {
    return addTextView(labelText, new AreaTextView(rows, TEXT_COLUMS));
  }

  private ITextView addTextView(final String labelText, final ITextView textView) {
    new LabelTextView(labelText, textView).addTo(dialogPanel, false);
    return textView;
  }

  public void addEditAction(SmartAction action, int row) {
    dialogPanel.addEditAction(action, row);
  }
}