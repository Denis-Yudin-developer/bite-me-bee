package ru.coderiders.bitemebee.visitor.family;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.coderiders.commons.rest.dto.BeeFamilySnapshotDto;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LinkedVisitorFamily implements BeeFamilySnapshotVisitor {
    private final List<BeeFamilySnapshotVisitor> visitors;

    @Override
    public void visit(FamilySnapshotVisitorDto visitor, BeeFamilySnapshotDto familySnapshot) {
        visitors.forEach(familySnapshotVisitor -> familySnapshotVisitor.visit(visitor, familySnapshot));
    }
}
