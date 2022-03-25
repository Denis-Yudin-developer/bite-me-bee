package ru.coderiders.BiteMeBee.rest.api.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.coderiders.BiteMeBee.rest.api.GenPanelApi;
import ru.coderiders.BiteMeBee.rest.api.generator.BeeFamilyFeignApi;
import ru.coderiders.BiteMeBee.rest.api.generator.HiveFeignApi;

@RestController
@RequiredArgsConstructor
public class GenPanelController implements GenPanelApi {

    private final BeeFamilyFeignApi beeFamilyFeignApi;
    private final HiveFeignApi hiveFeignApi;

    @Override
    public void updateDelta(Long id, Double delta) {
        hiveFeignApi.updateDelta(id, delta);
    }

    @Override
    public void overheatHive(Long id) {
        hiveFeignApi.updateOverheatedStatus(id, true);
    }

    @Override
    public void infect(Long id) {
        beeFamilyFeignApi.updateInfectedStatus(id, true);
    }
}
