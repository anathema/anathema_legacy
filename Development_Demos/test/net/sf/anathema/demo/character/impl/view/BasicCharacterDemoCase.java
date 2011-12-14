package net.sf.anathema.demo.character.impl.view;

import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.character.library.intvalue.MarkerIntValueDisplayFactory;
import net.sf.anathema.lib.resources.IResources;
import de.jdemo.extensions.SwingDemoCase;

public class BasicCharacterDemoCase extends SwingDemoCase {

  protected final IIntValueDisplayFactory createMortalGuiConfiguration(IResources resources) {
    return new MarkerIntValueDisplayFactory(resources, CharacterType.MORTAL);
  }
}