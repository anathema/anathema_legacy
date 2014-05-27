package net.sf.anathema.hero.charms.display.magic;

import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.file.RelativePath;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.List;

public class MagicLearnPresenter {

  private final Announcer<MagicViewListener> control = Announcer.to(MagicViewListener.class);
  private final List<ButtonUpdater> updaters = new ArrayList<>();
  private final MagicLearnView view;

  public MagicLearnPresenter(MagicLearnView view) {
    this.view = view;
  }

  public void initPresentation(MagicLearnProperties properties) {
    createAddMagicButton(properties.getAddButtonIcon(), properties.getAddButtonToolTip(), properties);
    createRemoveMagicButton(properties.getRemoveButtonIcon(), properties.getRemoveButtonToolTip(), properties);
    updateButtons();
  }

  private void createRemoveMagicButton(RelativePath icon, String tooltip, MagicLearnProperties properties) {
    Command command = () -> fireRemoveRequested(view.getSelectedLearnedValues());
    Tool tool = createTool(icon, tooltip, command);
    RemoveButtonUpdater buttonUpdater = new RemoveButtonUpdater(properties, tool, view);
    updaters.add(buttonUpdater);
    view.addLearnedMagicSelectedListener(buttonUpdater::updateButton);
  }

  private void createAddMagicButton(RelativePath icon, String tooltip, MagicLearnProperties properties) {
    Command command = () -> fireAddRequested(view.getSelectedAvailableValues());
    Tool tool = createTool(icon, tooltip, command);
    AddButtonUpdater buttonUpdater = new AddButtonUpdater(properties, tool, view);
    updaters.add(buttonUpdater);
    view.addAvailableMagicSelectedListener(buttonUpdater::updateButton);
  }

  private void fireRemoveRequested(List<Object> removedMagics) {
    Object[] objects = removedMagics.toArray(new Object[removedMagics.size()]);
    control.announce().removeMagicRequested(objects);
  }

  private void fireAddRequested(List<Object> addedMagics) {
    Object[] objects = addedMagics.toArray(new Object[addedMagics.size()]);
    control.announce().addMagicRequested(objects);
  }

  public void addChangeListener(MagicViewListener listener) {
    control.addListener(listener);
  }
  
  public void updateButtons(){
    updaters.forEach(ButtonUpdater::updateButton);
  }

  private Tool createTool(RelativePath icon, String tooltip, Command command) {
    Tool tool = view.addMainTool();
    tool.setIcon(icon);
    tool.setTooltip(tooltip);
    tool.setCommand(command);
    return tool;
  }
}