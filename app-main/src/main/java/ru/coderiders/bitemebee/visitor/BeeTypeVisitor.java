package ru.coderiders.bitemebee.visitor;

import ru.coderiders.bitemebee.entity.BeeType;
import ru.coderiders.commons.rest.dto.HiveSnapshotDto;

public interface BeeTypeVisitor {
    void visit(BeeType beeType, HiveSnapshotDto hiveSnapshot);
}
