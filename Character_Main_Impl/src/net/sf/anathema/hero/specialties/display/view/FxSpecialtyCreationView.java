package net.sf.anathema.hero.specialties.display.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.hero.specialties.display.presenter.SpecialtyCreationView;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.platform.fx.FxThreading;
import net.sf.anathema.platform.fx.selection.ComboBoxSelectionView;
import net.sf.anathema.platform.tool.FxButtonTool;
import org.tbee.javafx.scene.layout.MigPane;

public class FxSpecialtyCreationView implements SpecialtyCreationView {

  private final ComboBoxSelectionView<TraitType> box;
  private final TextField field = new TextField();
  private final FxButtonTool tool = FxButtonTool.ForToolbar();
  private final MigPane pane = new MigPane();

  public FxSpecialtyCreationView(AgnosticUIConfiguration<TraitType> configuration, RelativePath addIcon) {
    this.box = new ComboBoxSelectionView<>("", configuration);
    pane.add(box.getNode());
    pane.add(field);
    pane.add(tool.getNode());
    tool.setIcon(addIcon);
  }

  @Override
  public void addSelectionChangedListener(final ObjectValueListener<TraitType> listener) {
    box.addObjectSelectionChangedListener(listener);
  }

  @Override
  public void addEditChangedListener(final ObjectValueListener<String> listener) {
    field.textProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {
        listener.valueChanged(s2);
      }
    });
  }

  @Override
  public void whenAddButtonIsClicked(Command command) {
    tool.setCommand(command);
  }

  @Override
  public void clear() {
    box.clearSelection();
    field.textProperty().setValue(null);
  }

  @Override
  public void setButtonEnabled(boolean enabled) {
    if (enabled) {
      tool.enable();
    } else {
      tool.disable();
    }
  }

  @Override
  public void setObjects(final TraitType[] references) {
    box.setObjects(references);
  }

  @Override
  public void selectTrait(final TraitType currentTrait) {
    box.setSelectedObject(currentTrait);
  }

  @Override
  public void enterName(final String currentName) {
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        field.setText(currentName);
      }
    });
  }

  public Node getNode() {
    return pane;
  }
}