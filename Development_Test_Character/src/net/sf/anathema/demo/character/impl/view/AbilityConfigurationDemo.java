package net.sf.anathema.demo.character.impl.view;

import javax.swing.Icon;
import javax.swing.JPanel;

import net.sf.anathema.character.impl.view.GroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.framework.resources.AnathemaResources;

public class AbilityConfigurationDemo extends BasicCharacterDemoCase {

  public void demo() {
    AnathemaResources resources = new AnathemaResources();
    JPanel panel = new JPanel();
    GroupedFavorableTraitConfigurationView configurationView = new GroupedFavorableTraitConfigurationView(
        panel,
        1,
        createMortalGuiConfiguration(resources),
        createMortalGuiConfiguration(resources));
    configurationView.startNewTraitGroup("TestGroup"); //$NON-NLS-1$
    configurationView.addTraitView("AbilityLabel", 2, 5, null, false, new IIconToggleButtonProperties() { //$NON-NLS-1$
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
    show(panel);
  }
}