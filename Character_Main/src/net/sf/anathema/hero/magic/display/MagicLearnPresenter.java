package net.sf.anathema.hero.magic.display;

import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.file.RelativePath;
import org.jmock.example.announcer.Announcer;

import java.util.List;

public class MagicLearnPresenter {

  private final Announcer<MagicViewListener> control = Announcer.to(MagicViewListener.class);
  private final MagicLearnView view;

  public MagicLearnPresenter(MagicLearnView view) {
    this.view = view;
  }

  public void initPresentation(MagicLearnProperties properties) {
    createAddMagicButton(properties.getAddButtonIcon(), properties.getAddButtonToolTip(), properties);
    createRemoveMagicButton(properties.getRemoveButtonIcon(), properties.getRemoveButtonToolTip(), properties);
  }

  private void createRemoveMagicButton(RelativePath icon, String tooltip, final MagicLearnProperties properties) {
    Command command = new Command() {
      @Override
      public void execute() {
        fireRemoveRequested(view.getSelectedLearnedValues());
      }
    };
    final Tool tool = view.addMainTool();
    tool.setIcon(icon);
    tool.setTooltip(tooltip);
    tool.setCommand(command);
    view.addLearnedMagicSelectedListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        List selectedValues = view.getSelectedLearnedValues();
        boolean allowed = properties.isRemoveAllowed(selectedValues);
        if (allowed) {
          tool.enable();
        } else {
          tool.disable();
        }
      }
    });
  }

  private void createAddMagicButton(RelativePath icon, String tooltip, final MagicLearnProperties properties) {
    Command command = new Command() {
      @Override
      public void execute() {
        fireAddRequested(view.getSelectedAvailableValues());
      }
    };
    final Tool tool = view.addMainTool();
    tool.setIcon(icon);
    tool.setTooltip(tooltip);
    tool.setCommand(command);
    view.addAvailableMagicSelectedListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        boolean available = properties.isMagicSelectionAvailable(view.getSelectedAvailableValues());
        if (available) {
          tool.enable();
        } else {
          tool.disable();
        }
      }
    });
  }

  private void fireRemoveRequested(List<Object> removedMagics) {
    Object[] objects = removedMagics.toArray(new Object[removedMagics.size()]);
    control.announce().magicRemoved(objects);
  }

  private void fireAddRequested(List<Object> addedMagics) {
    Object[] objects = addedMagics.toArray(new Object[addedMagics.size()]);
    control.announce().magicAdded(objects);
  }

  public void addChangeListener(MagicViewListener listener) {
    control.addListener(listener);
  }
}