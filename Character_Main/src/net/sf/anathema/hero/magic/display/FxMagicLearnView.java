package net.sf.anathema.hero.magic.display;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ListView;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.platform.tool.FxButtonTool;
import org.jmock.example.announcer.Announcer;
import org.tbee.javafx.scene.layout.MigPane;

import java.util.List;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class FxMagicLearnView implements MagicLearnView {
  private final Announcer<MagicViewListener> control = Announcer.to(MagicViewListener.class);
  private final ListView availableMagicList = new ListView();
  private final ListView learnedMagicList = new ListView();
  private final MigPane centerButtons = new MigPane(withoutInsets().wrapAfter(1));
  private final MigPane endButtons = new MigPane(withoutInsets().wrapAfter(1));

  @Override
  public void setAvailableMagic(List magics) {
    availableMagicList.setItems(new ObservableListWrapper(magics));
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
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public List getSelectedAvailableValues() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void addAvailableMagicSelectedListener(ChangeListener changeListener) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void addLearnedMagicSelectedListener(ChangeListener changeListener) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  private Tool addToolTo(MigPane target) {
    FxButtonTool tool = FxButtonTool.ForAnyPurpose();
    target.add(tool.getNode());
    return tool;
  }
}