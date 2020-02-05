package com.company.zhilv.service;

import com.company.zhilv.entity.Contract;
import com.company.zhilv.entity.ContractStatus;
import com.haulmont.cuba.core.Persistence;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Service(ContractService.NAME)
public class ContractServiceBean implements ContractService {
    @Inject
    private Persistence persistence;

    @Transactional
    @Override
    public Contract cancelContract(Contract contract) {
        contract.setStatus(ContractStatus.CANCELED);
        contract = persistence.getEntityManager().merge(contract);
        return contract;
    }
}