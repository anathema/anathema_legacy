package net.sf.anathema.character.main.magic.display.view.combos;

import com.google.common.base.Preconditions;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.hero.magic.display.MagicLearnPresenter;
import net.sf.anathema.hero.magic.display.MagicViewListener;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.SwingTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.AreaTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;
import org.jdesktop.swingx.JXTaskPaneContainer;
import org.jmock.example.announcer.Announcer;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.util.Arrays;

import static net.sf.anathema.lib.gui.swing.GuiUtilities.revalidate;

public class SwingComboConfigurationView implements ComboConfigurationView, IView {
  private static final int TEXT_COLUMNS = 20;
  private SwingMagicLearnView magicLearnView;
  private final JPanel viewPort = new JPanel(new MigLayout(new LC().insets("6").fill().wrapAfter(5)));
  private final JComponent content = new JScrollPane(viewPort);
  private final Announcer<ComboViewListener> comboViewListeners = Announcer.to(ComboViewListener.class);
  private final JPanel namePanel = new JPanel(new MigLayout(LayoutUtils.withoutInsets().wrapAfter(1)));
  private Tool clearButton;
  private Tool finalizeButton;
  private boolean isNameEntered;
  private boolean isDescriptionEntered;
  private final JXTaskPaneContainer comboPane = new JXTaskPaneContainer();
  private JScrollPane comboScrollPane;
  private ComboViewProperties properties;
  private MagicLearnPresenter magicLearnPresenter;

  @Override
  public void initGui(final ComboViewProperties viewProperties) {
    this.properties = viewProperties;
    this.magicLearnView = new SwingMagicLearnView(viewProperties);
    this.magicLearnPresenter = new MagicLearnPresenter(magicLearnView);
    magicLearnPresenter.initPresentation(properties);

    finalizeButton = createFinalizeComboButton(viewProperties);
    clearButton = createClearTool(viewProperties);

    magicLearnView.addLearnedListListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        enableOrDisableFinalizeButton();
        enableOrDisableClearButton();
      }
    });
    viewPort.add(new JLabel(viewProperties.getAvailableComboCharmsLabel()));
    viewPort.add(new JLabel());
    viewPort.add(new JLabel(viewProperties.getComboedCharmsLabel()));
    viewPort.add(new JLabel());
    viewPort.add(namePanel, new CC().spanY(2).alignY("top"));
    magicLearnView.addTo(viewPort);
    comboPane.setBackground(viewPort.getBackground());
    comboScrollPane = new JScrollPane(comboPane);
    viewPort.add(comboScrollPane, new CC().spanX().grow().push());
  }

  private void enableOrDisableFinalizeButton() {
    if (magicLearnView.hasMoreThanOneElementLearned()) {
      finalizeButton.enable();
    } else {
      finalizeButton.disable();
    }
  }

  private Tool createClearTool(ComboViewProperties viewProperties) {
    Command command = new Command() {
      @Override
      public void execute() {
        fireComboCleared();
      }
    };
    Tool tool = magicLearnView.addAdditionalTool();
    tool.disable();
    tool.setCommand(command);
    tool.setIcon(viewProperties.getClearButtonIcon());
    tool.setTooltip(viewProperties.getClearButtonToolTip());
    return tool;
  }

  private void fireComboCleared() {
    comboViewListeners.announce().comboCleared();
  }

  private Tool createFinalizeComboButton(ComboViewProperties viewProperties) {
    Command command = new Command() {
      @Override
      public void execute() {
        fireComboFinalized();
      }
    };
    Tool tool = magicLearnView.addAdditionalTool();
    tool.disable();
    tool.setCommand(command);
    tool.setIcon(viewProperties.getFinalizeButtonIcon());
    tool.setTooltip(viewProperties.getFinalizeButtonToolTip());
    return tool;
  }

  private void fireComboFinalized() {
    comboViewListeners.announce().comboFinalized();
  }

  @Override
  public void setAllCharms(Object[] charms) {
    magicLearnView.setAvailableMagic(Arrays.asList(charms));
  }

  @Override
  public JComponent getComponent() {
    return content;
  }

  @Override
  public void addComboViewListener(final ComboViewListener listener) {
    magicLearnPresenter.addChangeListener(new MagicViewListener() {
      @Override
      public void removeMagicRequested(Object[] removedMagic) {
        listener.charmRemoved(removedMagic);
      }

      @Override
      public void addMagicRequested(Object[] addedMagic) {
        Preconditions.checkArgument(addedMagic.length == 1, "Only one charm may be added.");
        listener.charmAdded(addedMagic[0]);
      }
    });
    comboViewListeners.addListener(listener);
  }

  @Override
  public void setComboCharms(Object[] charms) {
    magicLearnView.setLearnedMagic(Arrays.asList(charms));
  }

  @Override
  public ITextView addComboNameView(String viewTitle) {
    LineTextView textView = new LineTextView(TEXT_COLUMNS);
    textView.addTextChangedListener(new ObjectValueListener<String>() {
      @Override
      public void valueChanged(String newValue) {
        isNameEntered = newValue != null && !newValue.equals("");
        enableOrDisableClearButton();
      }
    });
    return addTextView(viewTitle, textView);
  }

  @Override
  public ComboView addComboView(String name, String description) {
    SwingComboView comboView = new SwingComboView();
    comboView.initGui(name, description);
    comboPane.add(comboView.getTaskGroup());
    revalidateView();
    return comboView;
  }

  private void revalidateView() {
    revalidate(comboPane);
    revalidate(comboScrollPane);
  }

  private ITextView addTextView(String viewTitle, SwingTextView textView) {
    namePanel.add(new JLabel(viewTitle));
    namePanel.add(textView.getComponent(), new CC().growX());
    return textView;
  }

  @Override
  public ITextView addComboDescriptionView(String viewTitle) {
    AreaTextView textView = new AreaTextView(5, TEXT_COLUMNS);
    textView.addTextChangedListener(new ObjectValueListener<String>() {
      @Override
      public void valueChanged(String newValue) {
        isDescriptionEntered = newValue != null && !newValue.equals("");
        enableOrDisableClearButton();
      }
    });
    return addTextView(viewTitle, textView);
  }

  @Override
  public void deleteView(ComboView view) {
    SwingComboView comboView = (SwingComboView) view;
    comboPane.remove(comboView.getTaskGroup());
    revalidateView();
  }

  @Override
  public void setEditState(boolean editing) {
    clearButton.setIcon(editing ? properties.getCancelEditButtonIcon() : properties.getClearButtonIcon());
    clearButton.setTooltip(editing ? properties.getCancelButtonEditToolTip() : properties.getClearButtonToolTip());
    finalizeButton.setTooltip(
            editing ? properties.getFinalizeButtonEditToolTip() : properties.getFinalizeButtonToolTip());
  }

  private void enableOrDisableClearButton() {
    if (canEnableClearButton()) {
      clearButton.enable();
    } else {
      clearButton.disable();
    }
  }

  private boolean canEnableClearButton() {
    return isDescriptionEntered || isNameEntered || magicLearnView.hasAnyElementLearned();
  }
}
