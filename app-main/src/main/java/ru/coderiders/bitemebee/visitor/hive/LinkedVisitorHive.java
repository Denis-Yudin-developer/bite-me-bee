package ru.coderiders.bitemebee.visitor.hive;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.coderiders.commons.rest.dto.HiveSnapshotDto;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LinkedVisitorHive implements HiveSnapshotVisitor {
    private final List<HiveSnapshotVisitor> visitors;

    @Override
    public void visit(HiveSnapshotVisitorDto visitor, HiveSnapshotDto hiveSnapshot) {
        visitors.forEach(hiveSnapshotVisitor -> hiveSnapshotVisitor.visit(visitor, hiveSnapshot));
    }
}
