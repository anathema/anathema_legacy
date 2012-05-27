package net.sf.anathema.character.impl.view;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.impl.view.advantage.BackgroundSelectionView;
import net.sf.anathema.character.library.intvalue.IRemovableTraitView;
import net.sf.anathema.character.library.trait.view.RearButtonTraitViewWrapper;
import net.sf.anathema.character.library.trait.view.SimpleTraitView;
import net.sf.anathema.character.view.BackgroundView;
import net.sf.anathema.character.view.BackgroundViewProperties;
import net.sf.anathema.framework.presenter.view.AbstractInitializableContentView;
import net.sf.anathema.framework.presenter.view.ButtonControlledComboEditView;
import net.sf.anathema.framework.presenter.view.IButtonControlledComboEditView;
import net.sf.anathema.framework.presenter.view.ITextFieldComboBoxEditor;
import net.sf.anathema.framework.value.IntegerViewFactory;

import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import static net.disy.commons.swing.layout.grid.GridDialogLayoutDataFactory.createHorizontalSpanData;

public class SeparateBackgroundView extends AbstractInitializableContentView<BackgroundViewProperties> implements BackgroundView {
  private final JPanel backgroundSelectionPanel = new JPanel(new GridDialogLayout(3, false));
  private final JPanel backgroundDisplayPanel = new JPanel(new GridDialogLayout(2, false));
  private final IntegerViewFactory guiConfiguration;

  public SeparateBackgroundView(IntegerViewFactory intValueDisplayFactory) {
    this.guiConfiguration = intValueDisplayFactory;
  }

  @Override
  public void createContent(JPanel content, BackgroundViewProperties properties) {
    content.setLayout(new FlowLayout(FlowLayout.LEFT));
    JPanel innerPanel = new JPanel(new GridDialogLayout(2, false));
    content.add(innerPanel);
    JPanel backgroundPanel = createBackgroundPanel(properties.getBackgroundTitle());
    innerPanel.add(backgroundPanel, createHorizontalSpanData(2, GridDialogLayoutData.FILL_HORIZONTAL));
  }

  private JPanel createBackgroundPanel(String title) {
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBorder(new TitledBorder(title));
    panel.add(backgroundSelectionPanel, BorderLayout.CENTER);
    panel.add(backgroundDisplayPanel, BorderLayout.SOUTH);
    return panel;
  }

  @Override
  public IButtonControlledComboEditView<Object> addBackgroundSelectionView(String labelText,
                                                                           ListCellRenderer backgroundRenderer,
                                                                           ITextFieldComboBoxEditor backgroundEditor,
                                                                           Icon addIcon) {
    ButtonControlledComboEditView<Object> objectSelectionView = new BackgroundSelectionView<Object>(addIcon, labelText,
            backgroundRenderer, backgroundEditor);
    backgroundSelectionPanel.add(objectSelectionView.getComponent());
    return objectSelectionView;
  }

  @Override
  public IRemovableTraitView<SimpleTraitView> addBackgroundView(Icon deleteIcon, String labelText, int value,
                                                                int maxValue) {
    SimpleTraitView view = new SimpleTraitView(guiConfiguration, labelText, value, maxValue);
    RearButtonTraitViewWrapper<SimpleTraitView> backgroundView = new RearButtonTraitViewWrapper<SimpleTraitView>(view,
            deleteIcon);
    backgroundView.addComponents(backgroundDisplayPanel);
    return backgroundView;
  }
}