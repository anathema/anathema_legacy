package net.sf.anathema.character.abyssal.caste;

import net.sf.anathema.character.abyssal.template.IAbyssalCasteVisitor;
import net.sf.anathema.character.generic.caste.ICasteType;

public enum AbyssalCaste implements ICasteType<IAbyssalCasteVisitor> {

  Dusk {
    public void accept(IAbyssalCasteVisitor visitor) {
      visitor.visitDusk(this);
    }
  },
  Midnight {
    public void accept(IAbyssalCasteVisitor visitor) {
      visitor.visitMidnight(this);
    }
  },
  Daybreak {
    public void accept(IAbyssalCasteVisitor visitor) {
      visitor.visitDaybreak(this);
    }
  },

  Day {
    public void accept(IAbyssalCasteVisitor visitor) {
      visitor.visitDay(this);
    }
  },
  Moonshadow {
    public void accept(IAbyssalCasteVisitor visitor) {
      visitor.visitMoonshadow(this);
    }
  };

  public String getId() {
    return name();
  }

  @Override
  public String toString() {
    return name();
  }
}