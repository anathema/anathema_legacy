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
import net.sf.anathema.character.main.advance.ExperienceSelectionListener;
import net.sf.anathema.character.main.advance.IExperiencePointEntry;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.platform.fx.NodeHolder;
import net.sf.anathema.platform.tool.FxButtonTool;
import org.jmock.example.announcer.Announcer;
import org.tbee.javafx.scene.layout.MigPane;

public class FxExperienceView implements ExperienceView, NodeHolder {
  private final MigPane content = new MigPane(new LC().wrapAfter(1).fill());
  private final TableView<IExperiencePointEntry> table = new TableView<>();
  private final Announcer<ExperienceUpdateListener> updateAnnouncer = new Announcer<>(ExperienceUpdateListener.class);
  private final Announcer<ExperienceSelectionListener> entryAnnouncer = new Announcer<>(ExperienceSelectionListener.class);
  private final MigPane buttonPanel = new MigPane();
  private final Label totalLabel = new Label();

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
    MigPane totalPanel = createTotalPanel(properties);
    content.add(buttonPanel);
    content.add(table, new CC().push().grow().span());
    content.add(totalPanel, new CC().pushX().growX().spanX());
  }

  @Override
  public Tool addTool() {
    FxButtonTool tool = FxButtonTool.ForAnyPurpose();
    buttonPanel.add(tool.getNode());
    return tool;
  }

  private MigPane createTotalPanel(IExperienceViewProperties properties) {
    MigPane migPane = new MigPane(LayoutUtils.withoutInsets().fill());
    migPane.add(new Label(properties.getTotalString()));
    migPane.add(totalLabel, new CC().alignX("right"));
    return migPane;
  }

  @Override
  public void addSelectionListener(ExperienceSelectionListener listener) {
    entryAnnouncer.addListener(listener);
  }

  @Override
  public void setTotalValueLabel(int overallExperiencePoints) {
    totalLabel.setText(String.valueOf(overallExperiencePoints));
  }

  @Override
  public void addUpdateListener(ExperienceUpdateListener experienceUpdateListener) {
    updateAnnouncer.addListener(experienceUpdateListener);
  }

  @Override
  public void setSelection(IExperiencePointEntry entry) {
    table.getSelectionModel().select(entry);
  }

  @Override
  public void setEntries(IExperiencePointEntry... allEntries) {
    clearEntries();
    addEntries(allEntries);
    forceTableRefresh();
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

  private void clearEntries() {
    table.getItems().removeAll(table.getItems());
  }

  private void addEntries(IExperiencePointEntry[] allEntries) {
    for (IExperiencePointEntry entry : allEntries) {
      table.getItems().add(entry);
    }
  }

  private void forceTableRefresh() {
    table.getColumns().get(0).setVisible(false);
    table.getColumns().get(0).setVisible(true);
  }
}