package net.sf.anathema.character.impl.view;

import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.impl.view.advantage.BackgroundSelectionView;
import net.sf.anathema.character.library.intvalue.IRemovableTraitView;
import net.sf.anathema.character.library.trait.view.RearButtonTraitViewWrapper;
import net.sf.anathema.character.library.trait.view.SimpleTraitView;
import net.sf.anathema.character.view.BackgroundView;
import net.sf.anathema.character.view.BackgroundViewProperties;
import net.sf.anathema.framework.presenter.view.ButtonControlledComboEditView;
import net.sf.anathema.framework.presenter.view.IButtonControlledComboEditView;
import net.sf.anathema.framework.presenter.view.IInitializableContentView;
import net.sf.anathema.framework.presenter.view.ITextFieldComboBoxEditor;
import net.sf.anathema.framework.value.IntegerViewFactory;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class SeparateBackgroundView implements BackgroundView, IInitializableContentView<BackgroundViewProperties> {
  private final JPanel backgroundSelectionPanel = new JPanel(new MigLayout(withoutInsets().wrapAfter(3).fillX()));
  private final JPanel backgroundDisplayPanel = new JPanel(new MigLayout(withoutInsets().wrapAfter(2).fillX()));
  private final IntegerViewFactory guiConfiguration;
  private final JPanel content = new JPanel(new FlowLayout(FlowLayout.LEFT));

  public SeparateBackgroundView(IntegerViewFactory intValueDisplayFactory) {
    this.guiConfiguration = intValueDisplayFactory;
  }

  @Override
  public void initGui(BackgroundViewProperties properties) {
    content.add(createBackgroundPanel());
  }

  @Override
  public JComponent getComponent() {
    return content;
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

  private JPanel createBackgroundPanel() {
    JPanel panel = new JPanel(new BorderLayout());
    panel.add(backgroundSelectionPanel, BorderLayout.CENTER);
    panel.add(backgroundDisplayPanel, BorderLayout.SOUTH);
    return panel;
  }
}