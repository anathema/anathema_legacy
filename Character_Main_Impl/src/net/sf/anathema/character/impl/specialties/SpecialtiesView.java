package net.sf.anathema.character.impl.specialties;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.impl.view.SpecialtyView;
import net.sf.anathema.character.presenter.specialty.ISpecialtiesConfigurationView;
import net.sf.anathema.character.view.ISpecialtyView;
import net.sf.anathema.framework.presenter.view.ButtonControlledComboEditView;
import net.sf.anathema.framework.presenter.view.IButtonControlledComboEditView;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.lib.gui.IView;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class SpecialtiesView implements ISpecialtiesConfigurationView, IView {

  private final IntegerViewFactory factory;
  private final JPanel mainPanel = new JPanel(new MigLayout(withoutInsets().wrapAfter(1).fillY()));
  private final JPanel specialtyPanel = new JPanel(new MigLayout(withoutInsets().wrapAfter(5)));

  public SpecialtiesView(IntegerViewFactory factory) {
    this.factory = factory;
  }

  @Override
  public ISpecialtyView addSpecialtyView(
      String abilityName,
      String specialtyName,
      Icon deleteIcon,
      int value,
      int maxValue) {
    SpecialtyView specialtyView = new SpecialtyView(factory, abilityName, deleteIcon, specialtyName, value, maxValue);
    specialtyView.addComponents(specialtyPanel);
    return specialtyView;
  }

  @Override
  public IButtonControlledComboEditView<ITraitReference> addSpecialtySelectionView(
      String labelText,
      ListCellRenderer renderer,
      Icon addIcon) {
    ButtonControlledComboEditView<ITraitReference> objectSelectionView = new ButtonControlledComboEditView<>(
        addIcon,
        renderer);
    mainPanel.add(objectSelectionView.getComponent());
    return objectSelectionView;
  }

  @Override
  public JComponent getComponent() {
    mainPanel.add(new JScrollPane(specialtyPanel), new CC().grow().pushY());
    return mainPanel;
  }
}