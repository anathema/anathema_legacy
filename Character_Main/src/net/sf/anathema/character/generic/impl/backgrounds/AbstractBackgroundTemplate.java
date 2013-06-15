package net.sf.anathema.character.generic.impl.backgrounds;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.traits.types.ITraitTypeVisitor;
import net.sf.anathema.lib.util.Identifier;

public abstract class AbstractBackgroundTemplate extends Identifier implements IBackgroundTemplate {

  public AbstractBackgroundTemplate(String id) {
    super(id);
  }

  @Override
  public final void accept(ITraitTypeVisitor visitor) {
    visitor.visitBackground(this);
  }
}