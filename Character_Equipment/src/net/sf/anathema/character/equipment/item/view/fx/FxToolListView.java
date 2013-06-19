package net.sf.anathema.character.equipment.item.view.fx;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import net.miginfocom.layout.CC;
import net.sf.anathema.character.equipment.item.view.ToolListView;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.util.Closure;
import net.sf.anathema.platform.fx.ConfigurableListCellFactory;
import net.sf.anathema.platform.tool.FxButtonTool;
import org.tbee.javafx.scene.layout.MigPane;

import java.util.List;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;

public class FxToolListView<T> implements ToolListView<T> {

  private ListView<T> list;
  private MigPane buttonPanel;
  private MigPane content;

  public FxToolListView() {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        list = new ListView<>();
        list.getStyleClass().add("tool-list");
        buttonPanel = new MigPane();
        buttonPanel.getStyleClass().add("tool-buttons");
        content = new MigPane(fillWithoutInsets().wrapAfter(1));
        content.add(list, new CC().push().grow().span());
        content.add(buttonPanel);
        list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
      }
    });
  }

  @Override
  public void setObjects(final List<T> items) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        list.setItems(FXCollections.observableArrayList(items));
      }
    });
  }

  @Override
  public void addListSelectionListener(final Runnable listener) {
    waitForContent();
    list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<T>() {
      @Override
      public void changed(ObservableValue<? extends T> observableValue, T t, T t2) {
        listener.run();
      }
    });
  }

  @Override
  public void addListSelectionListener(final Closure<T> listener) {
    waitForContent();
    list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<T>() {
      @Override
      public void changed(ObservableValue<? extends T> observableValue, T t, T newSelection) {
        listener.execute(newSelection);
      }
    });
  }

  @Override
  public List<T> getSelectedItems() {
    return list.getSelectionModel().getSelectedItems();
  }

  @Override
  public Tool addTool() {
    final FxButtonTool tool = FxButtonTool.ForAnyPurpose();
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        buttonPanel.getChildren().add(tool.getNode());
      }
    });
    return tool;
  }

  @Override
  public void refresh() {
    /*ObservableList<T> items = list.getItems();
    int index = list.getSelectionModel().getSelectedIndex();
    list.setItems(null);*/
    list.layout();
    /*list.setItems(FXCollections.observableList(items));
    list.getSelectionModel().select(index);*/
  }

  public Node getNode() {
    return content;
  }

  public void setUiConfiguration(final AgnosticUIConfiguration<T> configuration) {
    waitForContent();
    list.setCellFactory(new ConfigurableListCellFactory<>(configuration));
  }

  private void waitForContent() {
    while (content == null) {
      try {
        Thread.sleep(50);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}