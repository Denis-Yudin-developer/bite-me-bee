package ru.coderiders.bitemebee.visitor.hive;

import ru.coderiders.commons.rest.dto.HiveSnapshotDto;

public interface HiveSnapshotVisitor {
    void visit(HiveSnapshotVisitorDto visitor, HiveSnapshotDto hiveSnapshot);
}
