package com.suda.platform.VO;

import java.util.List;

/**
 * @author 卫星
 * @package com.shop.VO
 * @date 2019-04-24  16:18
 * @project niuwan_cloud
 */

public class CityVO {

    private Integer id;
    /**
     * 市
     */
    private String city;

    private List<CountyVO> countys;

    public List<CountyVO> getCountys() {
        return countys;
    }

    public void setCountys(List<CountyVO> countys) {
        this.countys = countys;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
