package net.sf.anathema.namegenerator.anathema;

import javax.swing.Icon;

import net.sf.anathema.lib.resources.AbstractUI;
import net.sf.anathema.lib.resources.IResources;

public class NamegeneratorUI extends AbstractUI {

  public NamegeneratorUI(IResources resources) {
    super(resources);
  }

  public Icon getNameGeneratorTabIcon() {
    return getIcon("NoteTabIcon.png"); //$NON-NLS-1$
  }
}