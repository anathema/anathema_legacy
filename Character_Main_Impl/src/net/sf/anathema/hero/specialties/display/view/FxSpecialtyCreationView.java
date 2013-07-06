package net.sf.anathema.hero.specialties.display.view;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.hero.specialties.display.presenter.SpecialtyCreationView;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.platform.fx.FxThreading;
import net.sf.anathema.platform.tool.FxButtonTool;
import org.tbee.javafx.scene.layout.MigPane;

import java.util.Arrays;

public class FxSpecialtyCreationView implements SpecialtyCreationView {

  private final ComboBox<ITraitReference> box = new ComboBox<>();
  private final TextField field = new TextField();
  private final FxButtonTool tool = FxButtonTool.ForToolbar();
  private final MigPane pane = new MigPane();

  public FxSpecialtyCreationView(RelativePath addIcon) {
    pane.add(box);
    pane.add(field);
    pane.add(tool.getNode());
    tool.setIcon(addIcon);
  }

  @Override
  public void addSelectionChangedListener(final ObjectValueListener<ITraitReference> listener) {
    box.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ITraitReference>() {
      @Override
      public void changed(ObservableValue observableValue, ITraitReference o, ITraitReference newValue) {
        listener.valueChanged(newValue);
      }
    });
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
    box.getSelectionModel().clearSelection();
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
  public void setObjects(final ITraitReference[] references) {
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        box.setItems(new ObservableListWrapper<>(Arrays.asList(references)));
      }
    });
  }

  public Node getNode() {
    return pane;
  }
}