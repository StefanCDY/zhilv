package com.company.zhilv.service;

import com.company.zhilv.entity.Agreement;
import com.company.zhilv.entity.AgreementStatus;
import com.haulmont.cuba.core.Persistence;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Service(AgreementService.NAME)
public class AgreementServiceBean implements AgreementService {
    @Inject
    private Persistence persistence;

    @Transactional
    @Override
    public Agreement cancelAgreement(Agreement agreement) {
        agreement.setStatus(AgreementStatus.CANCELED);
        agreement = persistence.getEntityManager().merge(agreement);
        return agreement;
    }
}