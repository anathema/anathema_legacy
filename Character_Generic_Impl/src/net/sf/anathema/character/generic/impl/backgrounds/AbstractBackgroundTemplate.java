package net.sf.anathema.character.generic.impl.backgrounds;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.traits.types.ITraitTypeVisitor;
import net.sf.anathema.lib.util.Identificate;

public abstract class AbstractBackgroundTemplate extends Identificate implements IBackgroundTemplate {

  public AbstractBackgroundTemplate(String id) {
    super(id);
  }

  public final void accept(ITraitTypeVisitor visitor) {
    visitor.visitBackground(this);
  }
}