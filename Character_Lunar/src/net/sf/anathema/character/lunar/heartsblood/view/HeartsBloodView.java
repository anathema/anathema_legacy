package net.sf.anathema.character.lunar.heartsblood.view;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.GridDialogLayoutDataUtilities;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryView;
import net.sf.anathema.character.library.removableentry.view.AbstractRemovableEntryView;
import net.sf.anathema.character.library.removableentry.view.RemovableStringView;
import net.sf.anathema.character.library.trait.IModifiableCapTrait;

public class HeartsBloodView extends AbstractRemovableEntryView<IRemovableEntryView> {

  private final JPanel content = new JPanel(new GridDialogLayout(1, false));
  private final JPanel selectionPanel = new JPanel(new GridDialogLayout(2, false));

  public JComponent getComponent() {
    content.add(selectionPanel, GridDialogLayoutDataUtilities.createHorizontalFillNoGrab());
    return content;
  }

  public IAnimalFormSelectionView createAnimalFormSelectionView(
      Icon icon,
      String animalFormString,
      String animalStrengthString,
      String animalDexterityString,
      String animalStaminaString,
      String animalAppearanceString) {
    AnimalFormSelectionView view = new AnimalFormSelectionView(
        icon,
        animalFormString,
        animalStrengthString,
        animalDexterityString,
        animalStaminaString,
        animalAppearanceString);
    content.add(view.getComponent());
    return view;
  }

  public IRemovableEntryView addEntryView(Icon removeIcon, IModifiableCapTrait trait, String string) {
    RemovableStringView view = new RemovableStringView(removeIcon, string);
    view.addContent(selectionPanel);
    return view;
  }
}