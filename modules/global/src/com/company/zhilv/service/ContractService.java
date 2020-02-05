package com.company.zhilv.service;

import com.company.zhilv.entity.Contract;

public interface ContractService {
    String NAME = "zhilv_ContractService";

    Contract cancelContract(Contract contract);
}