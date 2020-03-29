package com.company.zhilv.bean;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

import javax.annotation.PostConstruct;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.math.BigDecimal;

@MetaClass(name = "zhilv_SystemInfo")
public class SystemInfo extends BaseUuidEntity {
    private static final long serialVersionUID = -9094599083648747149L;

    @MetaProperty
    protected String systemName;

    @MetaProperty
    protected String systemVersion;

    @MetaProperty
    protected String systemArch;

    @MetaProperty
    protected Integer systemProcessor;

    @MetaProperty
    protected BigDecimal loadAverage;

    @MetaProperty
    protected String totalMemory;

    @MetaProperty
    protected String freeMemory;

    @MetaProperty
    protected String usedMemoryPercent;

    @MetaProperty
    protected String jvmName;

    @MetaProperty
    protected String jvmVersion;

    private final int BYTE_TO_MB = 1024 * 1024;

    private OperatingSystemMXBean operatingSystemMXBean;

    private com.sun.management.OperatingSystemMXBean sumOperatingSystemMXBean;

    private RuntimeMXBean runtimeMXBean;

    @PostConstruct
    protected void postConstruct() {
        operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        sumOperatingSystemMXBean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        runtimeMXBean = ManagementFactory.getRuntimeMXBean();
    }

    public String getSystemName() {
        return operatingSystemMXBean.getName();
    }

    public String getJvmVersion() {
        return runtimeMXBean.getSpecVersion();
    }

    public String getSystemArch() {
        return operatingSystemMXBean.getArch();
    }

    public Integer getSystemProcessor() {
        return operatingSystemMXBean.getAvailableProcessors();
    }

    public BigDecimal getLoadAverage() {
        return BigDecimal.valueOf(operatingSystemMXBean.getSystemLoadAverage()).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public String getSystemVersion() {
        return operatingSystemMXBean.getVersion();
    }

    public String getJvmName() {
        return runtimeMXBean.getVmName();
    }

    public String getTotalMemory() {
        return transferMemory(sumOperatingSystemMXBean.getTotalPhysicalMemorySize());
    }

    public String getFreeMemory() {
        return transferMemory(sumOperatingSystemMXBean.getFreePhysicalMemorySize());
    }

    public String getUsedMemoryPercent() {
        BigDecimal totalMemory = BigDecimal.valueOf(sumOperatingSystemMXBean.getTotalPhysicalMemorySize());
        BigDecimal freeMemory = BigDecimal.valueOf(sumOperatingSystemMXBean.getFreePhysicalMemorySize());
        BigDecimal percent = BigDecimal.ONE.subtract(freeMemory.divide(totalMemory, 2, BigDecimal.ROUND_HALF_UP));
        return percent.multiply(BigDecimal.valueOf(100)) + " %";
    }

    private String transferMemory(long memory) {
        long mb = memory / BYTE_TO_MB;
        if (mb > 1024) {
            return mb / 1024 + " GB";
        }
        return mb + " MB";
    }
}