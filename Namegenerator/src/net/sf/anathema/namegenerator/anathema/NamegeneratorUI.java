package net.sf.anathema.namegenerator.anathema;

import java.util.Random;

import javax.swing.Icon;

import net.sf.anathema.lib.resources.AbstractUI;
import net.sf.anathema.lib.resources.IResources;

public class NamegeneratorUI extends AbstractUI {

  public NamegeneratorUI(IResources resources) {
    super(resources);
  }

  public Icon getNameGeneratorTabIcon() {
    int icon = new Random().nextInt(3);
    return getIcon("TabNamegenerator" + icon + "16.png"); //$NON-NLS-1$ //$NON-NLS-2$
  }
}