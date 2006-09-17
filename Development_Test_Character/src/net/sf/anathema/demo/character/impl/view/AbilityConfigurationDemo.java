package net.sf.anathema.demo.character.impl.view;

import javax.swing.Icon;

import net.sf.anathema.character.impl.view.GroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.framework.resources.AnathemaResources;

public class AbilityConfigurationDemo extends BasicCharacterDemoCase {

  public void demo() {
    AnathemaResources resources = new AnathemaResources();
    GroupedFavorableTraitConfigurationView configurationView = new GroupedFavorableTraitConfigurationView(
        1,
        createMortalGuiConfiguration(resources),
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
    configurationView.initGui(null);
    show(configurationView.getComponent());
  }
}