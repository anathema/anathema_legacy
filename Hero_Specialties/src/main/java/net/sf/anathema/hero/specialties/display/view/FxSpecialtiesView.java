package net.sf.anathema.hero.specialties.display.view;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.fx.hero.traitview.FxExtensibleTraitView;
import net.sf.anathema.fx.hero.traitview.FxTraitView;
import net.sf.anathema.fx.hero.traitview.SimpleTraitViewPanel;
import net.sf.anathema.hero.display.ExtensibleTraitView;
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
  private final MigPane creationPane = new MigPane(LayoutUtils.withoutInsets());
  private final SimpleTraitViewPanel existingSpecialtiesPane = new SimpleTraitViewPanel();

  public FxSpecialtiesView() {
    pane.add(creationPane);
    pane.add(existingSpecialtiesPane.getNode(), new CC().grow().pushY());
  }

  @Override
  public ExtensibleTraitView addSpecialtyView(String abilityName, String specialtyName, RelativePath deleteIcon, int value, int maxValue) {
    FxTraitView view = FxTraitView.WithDefaultLayout(abilityName + " - " + specialtyName, maxValue);
    FxExtensibleTraitView extensibleTraitView = new FxExtensibleTraitView(view);
    extensibleTraitView.addTo(existingSpecialtiesPane);
    return extensibleTraitView;
  }

  @Override
  public SpecialtyCreationView addSpecialtyCreationView(AgnosticUIConfiguration<TraitType> configuration, RelativePath addIcon) {
    final FxSpecialtyCreationView view = new FxSpecialtyCreationView(configuration, addIcon);
    creationPane.add(view.getNode());
    return view;
  }

  @Override
  public Node getNode() {
    return pane;
  }
}