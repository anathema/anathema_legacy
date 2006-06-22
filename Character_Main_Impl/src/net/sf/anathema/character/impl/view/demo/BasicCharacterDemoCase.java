package net.sf.anathema.character.impl.view.demo;

import net.sf.anathema.character.generic.impl.IIconConstants;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.character.library.intvalue.IntValueDisplayFactory;
import net.sf.anathema.framework.resources.IAnathemaResources;
import net.sf.anathema.lib.exception.AnathemaException;
import de.jdemo.extensions.SwingDemoCase;

public class BasicCharacterDemoCase extends SwingDemoCase {

  protected final IIntValueDisplayFactory createMortalGuiConfiguration(IAnathemaResources resources)
      throws AnathemaException {
    return new IntValueDisplayFactory(resources, resources.getImageIcon(IIconConstants.MORTAL_BALL));
  }
}