package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.*;

public class Groups extends ForwardingSet<GroupData> {

    private Set<GroupData> delegate;

    public Groups(Groups groups) {
        this.delegate = new HashSet<GroupData>(groups.delegate);
    }

    public Groups() {
        this.delegate = new HashSet<GroupData>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Groups groupData = (Groups) o;
        return Objects.equals(delegate, groupData.delegate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), delegate);
    }

    public Groups(Collection<GroupData> groups) {
        this.delegate = new HashSet<>(groups);
    }

    @Override
    protected Set<GroupData> delegate() {
        return delegate;
    }

    public Groups withAdded(GroupData group) {
        Groups groups = new Groups(this);
        groups.add(group);
        return groups;
    }

    public Groups without(GroupData group) {
        Groups groups = new Groups(this);
        groups.remove(group);
        return groups;
    }
}
