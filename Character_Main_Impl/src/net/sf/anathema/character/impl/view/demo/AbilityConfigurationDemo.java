package net.sf.anathema.character.impl.view.demo;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;

import net.sf.anathema.character.impl.view.GroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.framework.resources.AnathemaResources;
import net.sf.anathema.lib.exception.AnathemaException;

public class AbilityConfigurationDemo extends BasicCharacterDemoCase {

  public void demo() throws AnathemaException {
    AnathemaResources resources = new AnathemaResources();
    GroupedFavorableTraitConfigurationView configurationView = new GroupedFavorableTraitConfigurationView(
        1,
        "Abilities", //$NON-NLS-1$
        createSolarGuiConfiguration(resources));
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
        new Object[] { "Ability1 ganz lang", "Ability2 jawoll" }, //$NON-NLS-1$ //$NON-NLS-2$
        new DefaultListCellRenderer(),
        resources.getImageIcon("Green+20.png")); //$NON-NLS-1$
    configurationView.initGui(null);
    show(configurationView.getComponent());
  }
}