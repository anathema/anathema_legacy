package net.sf.anathema.framework.preferences.perspective;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import net.sf.anathema.framework.preferences.elements.PreferenceView;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.platform.fx.Navigation;
import net.sf.anathema.platform.fx.NodeHolder;

import java.util.ArrayList;

public class FxPreferencesNavigation extends Navigation implements PreferencesNavigation {
  private final ArrayList<PreferenceView> availableViews;
  private final FxPreferencesView preferencesView;

  public FxPreferencesNavigation(ArrayList<PreferenceView> preferenceViews, FxPreferencesView preferencesView) {
    this.availableViews = preferenceViews;
    this.preferencesView = preferencesView;
  }

  @Override
  public PreferenceView addSection(String title, Class viewClass) {
    for (final PreferenceView view : availableViews) {
      if (viewClass.isInstance(view)) {
        Button trigger = new Button(title);
        addElementToNavigation(trigger);
        trigger.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent actionEvent) {
            preferencesView.show(title, (NodeHolder)view);
          }
        });
        return view;
      }
    }
    throw new InitializationException("Unsupported preference view: " + viewClass.getName()); 
  }
}