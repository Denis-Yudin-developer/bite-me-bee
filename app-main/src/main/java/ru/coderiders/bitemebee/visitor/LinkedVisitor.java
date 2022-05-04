package ru.coderiders.bitemebee.visitor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.coderiders.bitemebee.entity.BeeType;
import ru.coderiders.commons.rest.dto.HiveSnapshotDto;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LinkedVisitor implements BeeTypeVisitor {
    private final List<BeeTypeVisitor> visitors;

    @Override
    public void visit(BeeType beeType, HiveSnapshotDto hiveSnapshot) {
        visitors.forEach(beeTypeVisitor -> beeTypeVisitor.visit(beeType, hiveSnapshot));
    }
}
