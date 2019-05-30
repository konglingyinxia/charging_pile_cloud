package com.suda.platform.enums;

/**
 * 日志记录设置系统类型
 * @author 卫星
 * @package com.fegin.common.comenum
 * @date 2019-04-11  13:34
 * @project cloud_template
 */
public enum LogAdminAgentAppEnum {
    /**
     * 后台
     */
    ADMIN_SYS(1),
    /**
     * 代理后台
     */
    AGENT_SYS(2),
    /**
     * app系统
     */
    ANDROID_SYS(1),
    IOS_SYS(2),
    PC_SYS(3),;

    private Integer  system;

    LogAdminAgentAppEnum(Integer system) {
        this.system = system;
    }

    public Integer getSystem() {
        return system;
    }

    public void setSystem(Integer system) {
        this.system = system;
    }}
