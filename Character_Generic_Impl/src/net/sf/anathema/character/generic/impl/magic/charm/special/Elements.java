package net.sf.anathema.character.generic.impl.magic.charm.special;

public enum Elements
{
	  Air, Earth, Fire, Water, Wood;

	  public String getId() {
	    return name();
	  }

	  @Override
	  public String toString() {
	    return name();
	  }
}
