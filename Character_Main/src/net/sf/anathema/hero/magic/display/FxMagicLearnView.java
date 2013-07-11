package net.sf.anathema.hero.magic.display;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import net.miginfocom.layout.CC;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.platform.fx.ConfigurableListCellFactory;
import net.sf.anathema.platform.fx.FxThreading;
import net.sf.anathema.platform.tool.FxButtonTool;
import org.jmock.example.announcer.Announcer;
import org.tbee.javafx.scene.layout.MigPane;

import java.util.List;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class FxMagicLearnView implements MagicLearnView {
  private final ListView availableMagicList = new ListView();
  private final ListView learnedMagicList = new ListView();
  private final MigPane centerButtons = new MigPane(withoutInsets().wrapAfter(1));
  private final MigPane endButtons = new MigPane(withoutInsets().wrapAfter(1));
  private final MigPane content = new MigPane(withoutInsets().fill());


  public FxMagicLearnView(MagicLearnProperties properties) {
    availableMagicList.setCellFactory(new ConfigurableListCellFactory(properties.getAvailableMagicRenderer()));
    availableMagicList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    learnedMagicList.setCellFactory(new ConfigurableListCellFactory(properties.getAvailableMagicRenderer()));
    content.add(availableMagicList, new CC().grow().push());
    content.add(centerButtons);
    content.add(learnedMagicList, new CC().grow().push());
    content.add(endButtons);
  }

  @Override
  public void setAvailableMagic(final List magics) {
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        availableMagicList.setItems(new ObservableListWrapper(magics));
      }
    });
  }

  @Override
  public void setLearnedMagic(List magics) {
    learnedMagicList.setItems(new ObservableListWrapper(magics));
  }

  @Override
  public boolean hasMoreThanOneElementLearned() {
    return learnedMagicList.getItems().size() > 1;
  }

  @Override
  public boolean hasAnyElementLearned() {
    return learnedMagicList.getItems().size() > 0;
  }

  @Override
  public void addLearnedListListener(final ChangeListener changeListener) {
    learnedMagicList.itemsProperty().addListener(new javafx.beans.value.ChangeListener() {
      @Override
      public void changed(ObservableValue observableValue, Object o, Object o2) {
        changeListener.changeOccurred();
      }
    });
  }

  @Override
  public Tool addAdditionalTool() {
    return addToolTo(endButtons);
  }

  @Override
  public Tool addMainTool() {
    return addToolTo(centerButtons);
  }

  @Override
  public List getSelectedLearnedValues() {
    return learnedMagicList.getSelectionModel().getSelectedItems();
  }

  @Override
  public List getSelectedAvailableValues() {
    return availableMagicList.getSelectionModel().getSelectedItems();
  }

  @Override
  public void addAvailableMagicSelectedListener(final ChangeListener changeListener) {
    addChangeListener(changeListener, availableMagicList);
  }

  @Override
  public void addLearnedMagicSelectedListener(final ChangeListener changeListener) {
    addChangeListener(changeListener, learnedMagicList);
  }

  private void addChangeListener(final ChangeListener changeListener, ListView list) {
    list.getSelectionModel().selectedItemProperty().addListener(new javafx.beans.value.ChangeListener() {
      @Override
      public void changed(ObservableValue observableValue, Object o, Object o2) {
        changeListener.changeOccurred();
      }
    });
  }

  private Tool addToolTo(final MigPane target) {
    final FxButtonTool tool = FxButtonTool.ForAnyPurpose();
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        target.add(tool.getNode());
      }
    });
    return tool;
  }

  public Node getNode() {
    return content;
  }
}