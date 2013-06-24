package net.sf.anathema.character.platform.specialties;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.library.trait.view.SimpleTraitView;
import net.sf.anathema.character.library.trait.view.SwingExtensibleTraitView;
import net.sf.anathema.character.presenter.ExtensibleTraitView;
import net.sf.anathema.character.presenter.specialty.ISpecialtiesConfigurationView;
import net.sf.anathema.framework.presenter.view.ButtonControlledComboEditView;
import net.sf.anathema.framework.presenter.view.IButtonControlledComboEditView;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.lib.file.RelativePath;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class SpecialtiesView implements ISpecialtiesConfigurationView, IView {

  private final IntegerViewFactory factory;
  private final JPanel mainPanel = new JPanel(new MigLayout(withoutInsets().wrapAfter(1).fillY()));
  private final JPanel specialtyPanel = new JPanel(new MigLayout(withoutInsets().wrapAfter(3)));

  public SpecialtiesView(IntegerViewFactory factory) {
    this.factory = factory;
  }

  @Override
  public ExtensibleTraitView addSpecialtyView(String abilityName, String specialtyName, RelativePath deleteIcon,
                                              int value, int maxValue) {
    SimpleTraitView view = new SimpleTraitView(factory, abilityName + " - " + specialtyName, value, maxValue);
    SwingExtensibleTraitView extensibleTraitView = new SwingExtensibleTraitView(view);
    extensibleTraitView.addComponents(specialtyPanel);
    return extensibleTraitView;
  }

  @Override
  public IButtonControlledComboEditView<ITraitReference> addSpecialtySelectionView(String labelText,
                                                                                   ListCellRenderer renderer,
                                                                                   RelativePath addIcon) {
    ButtonControlledComboEditView<ITraitReference> objectSelectionView = new ButtonControlledComboEditView<>(addIcon,
            renderer);
    mainPanel.add(objectSelectionView.getComponent(), 0);
    return objectSelectionView;
  }

  @Override
  public JComponent getComponent() {
    mainPanel.add(new JScrollPane(specialtyPanel), new CC().grow().pushY());
    return mainPanel;
  }
}