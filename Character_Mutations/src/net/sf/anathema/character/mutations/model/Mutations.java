package net.sf.anathema.character.mutations.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook;

public class Mutations {

	private final List<IMutation> mutations = new ArrayList<IMutation>();

	public void add(String id, MutationType type) {
		mutations.add(new Mutation(id, type));
	}
	
	public void add(String id, MutationType type, ExaltedSourceBook book, int page) {
		mutations.add(new Mutation(id, type, book, page));
	}

	public List<IMutation> asList() {
		return mutations;
	}
}