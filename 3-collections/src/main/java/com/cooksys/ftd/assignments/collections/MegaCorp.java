package com.cooksys.ftd.assignments.collections;

import com.cooksys.ftd.assignments.collections.hierarchy.Hierarchy;
import com.cooksys.ftd.assignments.collections.model.Capitalist;
import com.cooksys.ftd.assignments.collections.model.FatCat;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class MegaCorp implements Hierarchy<Capitalist, FatCat> {
	Set<Capitalist> heirarchy = new HashSet<Capitalist>();
	/**
     * Adds a given element to the hierarchy.
     * <p>
     * If the given element is already present in the hierarchy,
     * do not add it and return false
     * <p>
     * If the given element has a parent and the parent is not part of the hierarchy,
     * add the parent and then add the given element
     * <p>
     * If the given element has no parent but is a Parent itself,
     * add it to the hierarchy
     * <p>
     * If the given element has no parent and is not a Parent itself,
     * do not add it and return false
     *
     * @param capitalist the element to add to the hierarchy
     * @return true if the element was added successfully, false otherwise
     */
    @Override
    public boolean add(Capitalist capitalist) {
    	if (capitalist == null || !heirarchy.add(capitalist) || !(capitalist.hasParent() || isParent(capitalist))) return false;
    	if (capitalist.hasParent()) heirarchy.add(capitalist.getParent());
    	return true;
    }
    
    private boolean isParent(Capitalist capitalist) {
    	return capitalist instanceof FatCat;
    }

    /**
     * @param capitalist the element to search for
     * @return true if the element has been added to the hierarchy, false otherwise
     */
    @Override
    public boolean has(Capitalist capitalist) {
        return heirarchy.contains(capitalist);
    }

    /**
     * @return all elements in the hierarchy,
     * or an empty set if no elements have been added to the hierarchy
     */
    @Override
    public Set<Capitalist> getElements() {
        return heirarchy;
    }

    /**
     * @return all parent elements in the hierarchy,
     * or an empty set if no parents have been added to the hierarchy
     */
    @Override
    public Set<FatCat> getParents() {
    	Set<FatCat> parents = new HashSet<FatCat>();
    	for (Capitalist cap : this.getElements()) {
        	if (isParent(cap)) parents.add((FatCat) cap);
        } return parents;
    }

    /**
     * @param fatCat the parent whose children need to be returned
     * @return all elements in the hierarchy that have the given parent as a direct parent,
     * or an empty set if the parent is not present in the hierarchy or if there are no children
     * for the given parent
     */
    @Override
    public Set<Capitalist> getChildren(FatCat fatCat) {
    	Set<Capitalist> children = new HashSet<Capitalist>();
    	for (Capitalist cap : this.heirarchy) {
    		if (cap.getParent().equals(fatCat)) children.add(fatCat);
    	} return children;
    }

    /**
     * @return a map in which the keys represent the parent elements in the hierarchy,
     * and the each value is a set of the direct children of the associate parent, or an
     * empty map if the hierarchy is empty.
     */
    @Override
    public Map<FatCat, Set<Capitalist>> getHierarchy() {
    	HashMap<FatCat, Set<Capitalist>> map = new HashMap<FatCat, Set<Capitalist>>();
    	Set<FatCat> parents = this.getParents();
    	for (FatCat fatcat : parents) map.put(fatcat, getChildren(fatcat));
    	return map;
    }

    /**
     * @param capitalist
     * @return the parent chain of the given element, starting with its direct parent,
     * then its parent's parent, etc, or an empty list if the given element has no parent
     * or if its parent is not in the hierarchy
     */
    @Override
    public List<FatCat> getParentChain(Capitalist capitalist) {
        List<FatCat> chain = new ArrayList<FatCat>();
        while (true) {
        	if (!capitalist.hasParent()) {
	        	return chain;
        	} chain.add(capitalist.getParent());
        	capitalist = capitalist.getParent();
        }
    }
}
