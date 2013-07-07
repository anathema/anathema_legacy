package net.sf.anathema.hero.specialties.display.view;

import javafx.scene.Node;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.main.library.trait.view.fx.FxExtensibleTraitView;
import net.sf.anathema.character.main.library.trait.view.fx.FxGroupedColumnPanel;
import net.sf.anathema.character.main.library.trait.view.fx.FxTraitView;
import net.sf.anathema.character.main.presenter.ExtensibleTraitView;
import net.sf.anathema.character.model.view.ColumnCount;
import net.sf.anathema.hero.specialties.display.presenter.SpecialtiesConfigurationView;
import net.sf.anathema.hero.specialties.display.presenter.SpecialtyCreationView;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.platform.fx.FxThreading;
import net.sf.anathema.platform.fx.NodeHolder;
import org.tbee.javafx.scene.layout.MigPane;

public class FxSpecialtiesView implements SpecialtiesConfigurationView, NodeHolder {
  private final MigPane pane = new MigPane(LayoutUtils.withoutInsets().wrapAfter(1));
  private final MigPane existingSpecialtiesPane = new MigPane(LayoutUtils.withoutInsets());
  private final MigPane creationPane = new MigPane(LayoutUtils.withoutInsets());
  private final FxGroupedColumnPanel columnPanel = new FxGroupedColumnPanel(existingSpecialtiesPane, new ColumnCount(1));

  public FxSpecialtiesView() {
    columnPanel.startNewGroup(null);
    pane.add(creationPane);
    pane.add(existingSpecialtiesPane);
  }

  @Override
  public void initGui(ICharacterType type) {
    //nothing to do
  }

  @Override
  public ExtensibleTraitView addSpecialtyView(String abilityName, String specialtyName, RelativePath deleteIcon, int value, int maxValue) {
    FxTraitView view = new FxTraitView(abilityName + " - " + specialtyName, maxValue);
    FxExtensibleTraitView extensibleTraitView = new FxExtensibleTraitView(view);
    extensibleTraitView.addTo(columnPanel);
    return extensibleTraitView;
  }

  @Override
  public SpecialtyCreationView addSpecialtyCreationView(AgnosticUIConfiguration<TraitType> configuration, RelativePath addIcon) {
    final FxSpecialtyCreationView view = new FxSpecialtyCreationView(configuration, addIcon);
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        creationPane.add(view.getNode());
      }
    });
    return view;
  }

  @Override
  public Node getNode() {
    return pane;
  }
}