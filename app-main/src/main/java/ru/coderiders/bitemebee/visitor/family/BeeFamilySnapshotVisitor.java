package ru.coderiders.bitemebee.visitor.family;

import ru.coderiders.commons.rest.dto.BeeFamilySnapshotDto;

public interface BeeFamilySnapshotVisitor {
    void visit(FamilySnapshotVisitorDto visitor, BeeFamilySnapshotDto familySnapshot);
}
