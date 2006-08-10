package net.sf.anathema.demo.character.impl.view;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.impl.view.GroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.framework.resources.AnathemaResources;

public class AbilityConfigurationDemo extends BasicCharacterDemoCase {

  public void demo() {
    AnathemaResources resources = new AnathemaResources();
    GroupedFavorableTraitConfigurationView configurationView = new GroupedFavorableTraitConfigurationView(
        1,
        "Abilities", //$NON-NLS-1$
        createMortalGuiConfiguration(resources));
    configurationView.startNewTraitGroup("TestGroup"); //$NON-NLS-1$
    configurationView.addTraitView("AbilityLabel", 2, 5, false, new IIconToggleButtonProperties() { //$NON-NLS-1$
          public Icon createStandardIcon() {
            return null;
          }

          public Icon createUnselectedIcon() {
            return null;
          }

          public String getToolTipText() {
            return null;
          }
        });
    configurationView.addSpecialtySelectionView("Specialties", //$NON-NLS-1$
        new ITraitType[] { AbilityType.Archery, AbilityType.Athletics },
        new DefaultListCellRenderer(),
        new BasicUi(resources).getAddIcon());
    configurationView.initGui(null);
    show(configurationView.getComponent());
  }
}