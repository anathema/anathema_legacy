package net.sf.anathema.demo.character.impl.view;

import net.sf.anathema.character.generic.framework.resources.CharacterTemplateResourceProvider;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.character.library.intvalue.IntValueDisplayFactory;
import net.sf.anathema.framework.resources.IAnathemaResources;
import de.jdemo.extensions.SwingDemoCase;

public class BasicCharacterDemoCase extends SwingDemoCase {

  protected final IIntValueDisplayFactory createMortalGuiConfiguration(IResources resources) {
    return new IntValueDisplayFactory(
        resources,
        new CharacterTemplateResourceProvider(resources).getMediumBallResource(CharacterType.MORTAL));
  }
}