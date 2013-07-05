package net.sf.anathema.hero.specialties.display.view;

import javafx.scene.Node;
import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.library.trait.view.fx.FxExtensibleTraitView;
import net.sf.anathema.character.library.trait.view.fx.FxGroupedColumnPanel;
import net.sf.anathema.character.library.trait.view.fx.FxTraitView;
import net.sf.anathema.character.presenter.ExtensibleTraitView;
import net.sf.anathema.character.view.ColumnCount;
import net.sf.anathema.hero.specialties.display.presenter.SpecialtiesConfigurationView;
import net.sf.anathema.hero.specialties.display.presenter.SpecialtyCreationView;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.platform.fx.FxThreading;
import net.sf.anathema.platform.fx.NodeHolder;
import org.tbee.javafx.scene.layout.MigPane;

public class FxSpecialtiesView implements SpecialtiesConfigurationView, NodeHolder {
  private final MigPane pane = new MigPane();
  private final FxGroupedColumnPanel columnPanel = new FxGroupedColumnPanel(pane, new ColumnCount(1));

  public FxSpecialtiesView() {
    columnPanel.startNewGroup(null);
  }

  @Override
  public ExtensibleTraitView addSpecialtyView(String abilityName, String specialtyName, RelativePath deleteIcon, int value, int maxValue) {
    FxTraitView view = new FxTraitView(abilityName + " - " + specialtyName, maxValue);
    FxExtensibleTraitView extensibleTraitView = new FxExtensibleTraitView(view);
    extensibleTraitView.addTo(columnPanel);
    return extensibleTraitView;
  }

  @Override
  public SpecialtyCreationView addSpecialtySelectionView(AgnosticUIConfiguration<ITraitReference> configuration, RelativePath addIcon) {
    final FxSpecialtyCreationView view = new FxSpecialtyCreationView();
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        pane.getChildren().add(0, view.getNode());
      }
    });
    return view;
  }

  @Override
  public Node getNode() {
    return pane;
  }
}