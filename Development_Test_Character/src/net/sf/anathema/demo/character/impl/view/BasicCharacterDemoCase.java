package net.sf.anathema.demo.character.impl.view;

import net.sf.anathema.character.generic.impl.IIconConstants;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.character.library.intvalue.IntValueDisplayFactory;
import net.sf.anathema.framework.resources.IAnathemaResources;
import de.jdemo.extensions.SwingDemoCase;

public class BasicCharacterDemoCase extends SwingDemoCase {

  protected final IIntValueDisplayFactory createMortalGuiConfiguration(IAnathemaResources resources) {
    return new IntValueDisplayFactory(resources, resources.getImageIcon(IIconConstants.MORTAL_BALL));
  }
}