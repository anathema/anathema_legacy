package net.sf.anathema.character.lunar.heartsblood.view;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryView;
import net.sf.anathema.character.library.removableentry.view.AbstractRemovableEntryView;
import net.sf.anathema.character.library.removableentry.view.RemovableStringView;
import net.sf.anathema.character.library.trait.IModifiableCapTrait;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.icon.ImageProvider;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;
import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class HeartsBloodView extends AbstractRemovableEntryView<IRemovableEntryView> {

  private final JPanel content = new JPanel(new MigLayout(withoutInsets().fillX().wrapAfter(1)));
  private final JPanel selectionPanel = new JPanel(new MigLayout(fillWithoutInsets().wrapAfter(2)));

  @Override
  public JComponent getComponent() {
    content.add(selectionPanel, new CC().grow().push());
    return content;
  }

  public IAnimalFormSelectionView createAnimalFormSelectionView(Icon icon, String animalFormString,
                                                                String animalStrengthString,
                                                                String animalDexterityString,
                                                                String animalStaminaString,
                                                                String animalAppearanceString) {
    AnimalFormSelectionView view = new AnimalFormSelectionView(icon, animalFormString, animalStrengthString,
            animalDexterityString, animalStaminaString, animalAppearanceString);
    content.add(view.getComponent(), new CC().alignY("top").growX());
    return view;
  }

  @Override
  public IRemovableEntryView addEntryView(RelativePath removeIcon, IModifiableCapTrait trait, String string) {
    RemovableStringView view = new RemovableStringView(new ImageProvider().getImageIcon(removeIcon), string);
    view.addContent(selectionPanel);
    return view;
  }
}