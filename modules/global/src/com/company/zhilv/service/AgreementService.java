package com.company.zhilv.service;

import com.company.zhilv.entity.Agreement;

public interface AgreementService {
    String NAME = "zhilv_AgreementService";

    Agreement cancelAgreement(Agreement agreement);
}