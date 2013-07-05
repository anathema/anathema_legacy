package net.sf.anathema.hero.specialties.display.view;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.library.trait.view.swing.SimpleTraitView;
import net.sf.anathema.character.library.trait.view.swing.SwingExtensibleTraitView;
import net.sf.anathema.character.presenter.ExtensibleTraitView;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.hero.specialties.display.presenter.SpecialtiesConfigurationView;
import net.sf.anathema.hero.specialties.display.presenter.SpecialtyCreationView;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.ui.ConfigurableListCellRenderer;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class SpecialtiesView implements SpecialtiesConfigurationView, IView {

  private final IntegerViewFactory factory;
  private final JPanel mainPanel = new JPanel(new MigLayout(withoutInsets().wrapAfter(1).fillY()));
  private final JPanel specialtyPanel = new JPanel(new MigLayout(withoutInsets().wrapAfter(3)));

  public SpecialtiesView(IntegerViewFactory factory) {
    this.factory = factory;
  }

  @Override
  public ExtensibleTraitView addSpecialtyView(String abilityName, String specialtyName, RelativePath deleteIcon,
                                              int value, int maxValue) {
    SimpleTraitView view = SimpleTraitView.RightAlignedWithoutUpperBounds(factory, abilityName + " - " + specialtyName,
            value, maxValue);
    SwingExtensibleTraitView extensibleTraitView = new SwingExtensibleTraitView(view);
    extensibleTraitView.addComponents(specialtyPanel);
    return extensibleTraitView;
  }

  @Override
  public SpecialtyCreationView addSpecialtySelectionView(AgnosticUIConfiguration<ITraitReference> configuration,
                                                         RelativePath addIcon) {
    SwingSpecialtyCreationView objectSelectionView = new SwingSpecialtyCreationView(addIcon,
            new ConfigurableListCellRenderer(configuration));
    mainPanel.add(objectSelectionView.getComponent(), 0);
    return objectSelectionView;
  }

  @Override
  public JComponent getComponent() {
    mainPanel.add(new JScrollPane(specialtyPanel), new CC().grow().pushY());
    return mainPanel;
  }
}