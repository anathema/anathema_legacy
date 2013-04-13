package net.sf.anathema.character.equipment.item.view.fx;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Separator;
import net.sf.anathema.character.equipment.item.view.ToolListView;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.platform.fx.ConfigurableListCellFactory;
import net.sf.anathema.platform.tool.FxButtonTool;
import org.tbee.javafx.scene.layout.MigPane;

import java.util.Arrays;
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
        buttonPanel = new MigPane();
        content = new MigPane(fillWithoutInsets().wrapAfter(1));
        content.add(list);
        content.add(buttonPanel);
        list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
      }
    });
  }

  @Override
  public void setObjects(T[] items) {
    list.setItems(new ObservableListWrapper<>(Arrays.asList(items)));
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
  public List<T> getSelectedItems() {
    return list.getSelectionModel().getSelectedItems();
  }

  @Override
  public Tool addTool() {
    final FxButtonTool tool = new FxButtonTool();
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        buttonPanel.getChildren().add(tool.getNode());
      }
    });
    return tool;
  }

  public Node getNode() {
    return content;
  }

  public void setUiConfiguration(final AgnosticUIConfiguration<T> configuration) {
    waitForContent();
    list.setCellFactory(new ConfigurableListCellFactory<>(configuration));
  }

  public void setHeader(final String headerText) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        content.getChildren().add(0, new Label(headerText));
        content.getChildren().add(0, new Separator());
      }
    });
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