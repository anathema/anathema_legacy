package net.sf.anathema.hero.experience.display;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.character.main.advance.IExperiencePointEntry;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.platform.fx.NodeHolder;
import net.sf.anathema.platform.tool.FxButtonTool;
import org.jmock.example.announcer.Announcer;
import org.tbee.javafx.scene.layout.MigPane;

public class FxExperienceView implements ExperienceView, NodeHolder {
  private final MigPane content = new MigPane(new LC().wrapAfter(1).fill());
  private final TableView<IExperiencePointEntry> table = new TableView<>();
  private final Announcer<ExperienceUpdateListener> updateAnnouncer = new Announcer<>(ExperienceUpdateListener.class);
  private final Announcer<ExperienceConfigurationViewListener> entryAnnouncer = new Announcer<>(ExperienceConfigurationViewListener.class);
  private FxButtonTool removeTool;
  private Label totalLabel;

  @Override
  public void initGui(IExperienceViewProperties properties) {
    TableColumn<IExperiencePointEntry, String> pointsColumn = createPointsColumn(properties);
    TableColumn<IExperiencePointEntry, String> descriptionColumn = createDescriptionColumn(properties);
    table.setEditable(true);
    table.getColumns().addAll(descriptionColumn, pointsColumn);
    table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<IExperiencePointEntry>() {
      @Override
      public void changed(ObservableValue observableValue, IExperiencePointEntry o, IExperiencePointEntry newValue) {
        entryAnnouncer.announce().selectionChanged(newValue);
      }
    });
    MigPane buttonPanel = createButtonPanel(properties);
    MigPane totalPanel = createTotalPanel(properties);
    content.add(buttonPanel);
    content.add(table, new CC().push().grow().span());
    content.add(totalPanel, new CC().pushX().growX().spanX());
  }

  private MigPane createTotalPanel(IExperienceViewProperties properties) {
    MigPane migPane = new MigPane(LayoutUtils.withoutInsets().fill());
    migPane.add(new Label(properties.getTotalString()));
    this.totalLabel = new Label();
    migPane.add(totalLabel, new CC().alignX("right"));
    return migPane;
  }

  private MigPane createButtonPanel(IExperienceViewProperties properties) {
    MigPane buttonPanel = new MigPane();
    addAddButton(properties, buttonPanel);
    addRemoveButton(properties, buttonPanel);
    return buttonPanel;
  }

  private void addAddButton(IExperienceViewProperties properties, MigPane buttonPanel) {
    FxButtonTool tool = FxButtonTool.ForAnyPurpose();
    tool.setIcon(properties.getAddIcon());
    tool.setCommand(new Command() {
      @Override
      public void execute() {
        entryAnnouncer.announce().addRequested();
      }
    });
    buttonPanel.add(tool.getNode());
  }

  private void addRemoveButton(IExperienceViewProperties properties, MigPane buttonPanel) {
    this.removeTool = FxButtonTool.ForAnyPurpose();
    removeTool.setIcon(properties.getDeleteIcon());
    removeTool.setCommand(new Command() {
      @Override
      public void execute() {
        entryAnnouncer.announce().removeRequested();
      }
    });
    buttonPanel.add(removeTool.getNode());
  }


  @Override
  public void addExperienceConfigurationViewListener(ExperienceConfigurationViewListener listener) {
    entryAnnouncer.addListener(listener);
  }

  @Override
  public void setRemoveButtonEnabled(boolean enabled) {
    if (enabled) {
      removeTool.enable();
    } else {
      removeTool.disable();
    }
  }

  @Override
  public void setTotalValueLabel(int overallExperiencePoints) {
    totalLabel.setText(String.valueOf(overallExperiencePoints));
  }

  @Override
  public void addEntry(IExperiencePointEntry entry) {
    table.getItems().add(entry);
  }

  @Override
  public void clearEntries() {
    table.getItems().removeAll(table.getItems());
  }

  @Override
  public void addUpdateListener(ExperienceUpdateListener experienceUpdateListener) {
    updateAnnouncer.addListener(experienceUpdateListener);
  }

  @Override
  public int getNumberOfEntriesOnDisplay() {
    return table.getItems().size();
  }

  @Override
  public void setSelection(IExperiencePointEntry entry) {
    table.getSelectionModel().select(entry);
  }

  @Override
  public void addAllEntries(IExperiencePointEntry... allEntries) {
    clearEntries();
    for (IExperiencePointEntry entry : allEntries) {
      addEntry(entry);
    }
    table.getColumns().get(0).setVisible(false);
    table.getColumns().get(0).setVisible(true);
  }

  @Override
  public Node getNode() {
    return content;
  }

  private TableColumn<IExperiencePointEntry, String> createPointsColumn(IExperienceViewProperties properties) {
    TableColumn<IExperiencePointEntry, String> pointColumn = new TableColumn<>(properties.getPointHeader());
    pointColumn.prefWidthProperty().bind(table.widthProperty().divide(4));
    Callback<TableColumn<IExperiencePointEntry, String>, TableCell<IExperiencePointEntry, String>> cellCallback = TextFieldTableCell.forTableColumn();
    pointColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<IExperiencePointEntry, String>, ObservableValue<String>>() {
      @Override
      public ObservableValue<String> call(TableColumn.CellDataFeatures<IExperiencePointEntry, String> features) {
        return new SimpleStringProperty(String.valueOf(features.getValue().getExperiencePoints()));
      }
    });
    pointColumn.setCellFactory(cellCallback);
    pointColumn.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<IExperiencePointEntry, String>>() {
              @Override
              public void handle(TableColumn.CellEditEvent<IExperiencePointEntry, String> event) {
                IExperiencePointEntry experienceEntry = event.getRowValue();
                Integer experiencePoints = getChangedPointValue(event, experienceEntry);
                String description = experienceEntry.getTextualDescription().getText();
                updateAnnouncer.announce().update(experiencePoints, description);
              }
            }
    );
    return pointColumn;
  }

  private Integer getChangedPointValue(TableColumn.CellEditEvent<IExperiencePointEntry, String> event, IExperiencePointEntry experienceEntry) {
    try {
      return Integer.valueOf(event.getNewValue());
    } catch (NumberFormatException e) {
      return experienceEntry.getExperiencePoints();
    }
  }

  private TableColumn<IExperiencePointEntry, String> createDescriptionColumn(IExperienceViewProperties properties) {
    TableColumn<IExperiencePointEntry, String> descriptionColumn = new TableColumn<>(properties.getDescriptionHeader());
    descriptionColumn.prefWidthProperty().bind(table.widthProperty().multiply(3).divide(4).subtract(5));
    Callback<TableColumn<IExperiencePointEntry, String>, TableCell<IExperiencePointEntry, String>> cellCallback = TextFieldTableCell.forTableColumn();
    descriptionColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<IExperiencePointEntry, String>, ObservableValue<String>>() {
      @Override
      public ObservableValue<String> call(TableColumn.CellDataFeatures<IExperiencePointEntry, String> features) {
        return new SimpleStringProperty(features.getValue().getTextualDescription().getText());
      }
    });
    descriptionColumn.setCellFactory(cellCallback);
    descriptionColumn.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<IExperiencePointEntry, String>>() {
              @Override
              public void handle(TableColumn.CellEditEvent<IExperiencePointEntry, String> event) {
                IExperiencePointEntry experienceEntry = event.getRowValue();
                Integer experiencePoints = experienceEntry.getExperiencePoints();
                String description = event.getNewValue();
                updateAnnouncer.announce().update(experiencePoints, description);
              }
            }
    );
    return descriptionColumn;
  }
}