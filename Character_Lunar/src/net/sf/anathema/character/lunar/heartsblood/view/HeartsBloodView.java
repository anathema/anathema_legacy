package net.sf.anathema.character.lunar.heartsblood.view;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.framework.presenter.view.ISimpleTabView;

public class HeartsBloodView implements ISimpleTabView {

  private final JPanel content = new JPanel(new GridDialogLayout(1, false));
  private final JPanel selectionPanel = new JPanel(new GridDialogLayout(2, false));

  public JComponent getComponent() {
    content.add(selectionPanel, GridDialogLayoutData.FILL_HORIZONTAL);
    return content;
  }

  public boolean needsScrollbar() {
    return false;
  }

  public IAnimalFormSelectionView createAnimalFormSelectionView(
      Icon icon,
      String animalFormString,
      String animalStrengthString,
      String animalStaminaString) {
    AnimalFormSelectionView view = new AnimalFormSelectionView(
        icon,
        animalFormString,
        animalStrengthString,
        animalStaminaString);
    content.add(view.getComponent());
    return view;
  }

  public IRemovableStringView addAnimalFormView(Icon removeIcon, String string) {
    RemovableStringView view = new RemovableStringView(removeIcon, string);
    view.addContent(selectionPanel);
    content.revalidate();
    return view;
  }

  public void removeSelection(IRemovableStringView removableView) {
    removableView.delete();
  }
}