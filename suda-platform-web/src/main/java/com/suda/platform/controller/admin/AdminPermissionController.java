package com.suda.platform.controller.admin;

import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.RolePermission.AdminPermissionMenuList;
import com.suda.platform.VO.RolePermission.AdminPermissionVO;
import com.suda.platform.VO.StatusVo;
import com.suda.platform.entity.AdminPermission;
import com.suda.platform.enums.rolepermission.PermissionMenuLevEnum;
import com.suda.platform.enums.rolepermission.PermissionsMenuTypeEnum;
import com.suda.platform.service.IAdminPermissionService;
import com.util.Respons.ResponseMsg;
import com.util.Respons.ResponseUtil;
import com.util.StringUtils;
import com.util.pageinfoutil.PageUtil;
import config.advice.CommonException;
import config.annotation.LogMenthodName;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * 权限列表管理
 * @author kongling
 * @package com.suda.server.service.admin.controller
 * @date 2019-05-06  15:06
 * @project suda_cloud
 */
@RestController
@RequestMapping(value = "admin/permission",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AdminPermissionController {
    @Autowired
    private IAdminPermissionService adminPermissionService;

    /**
     * 权限列表
     * @param adminPermissionVO
     * @param pageUtil
     * @return
     */
    @RequestMapping(value = "/getPermissions")
    @ResponseBody
    public Map<String, Object> getPermissions(AdminPermissionVO adminPermissionVO, PageUtil pageUtil) {
        PageInfo<AdminPermission> lists = adminPermissionService.selectPermissionsBy(adminPermissionVO,pageUtil);
        return ResponseUtil.getSuccessMap(lists);
    }
    /**
     * 权限列表整体组装查询
     */
    @RequestMapping(value = "/getPermissionsAll")
    @ResponseBody
    public Map<String, Object> getPermissionsAll(PageUtil pageUtil) {
        PageInfo<AdminPermissionMenuList> lists = adminPermissionService.selectPermissionsByAll(pageUtil);
        return ResponseUtil.getSuccessMap(lists);
    }




    /**
     * 编辑权限（权限url,权限排序）
     */
    @RequestMapping(value = "/updatePermissions")
    @ResponseBody
    @LogMenthodName(name = "编辑后台权限列表")
    public Map<String, Object> getPermissions(AdminPermission adminPermission) {
        if(adminPermission.getId()==null || adminPermission.getMenuOrder()==null){
            throw  new CommonException(ResponseMsg.EMPTY_PARAM);
        }
        adminPermissionService.updateById(adminPermission);
        return ResponseUtil.getSuccessMap();
    }

    /**
     * 删除权限
     */
    @RequestMapping(value = "/delPermissions")
    @ResponseBody
    @LogMenthodName(name = "删除权限")
    public Map<String, Object> delPermissions(AdminPermission adminPermission) {
        if(adminPermission.getId()==null){
            throw  new CommonException(ResponseMsg.ID_IS_EMPTY);
        }
        adminPermissionService.delPermissionsById(adminPermission);
        return ResponseUtil.getSuccessMap();
    }

    /**
     * 添加权限列表
     */
    @RequestMapping(value = "/addPermission")
    @ResponseBody
    @LogMenthodName(name = "添加权限列表")
    public Map<String, Object> addPermission(AdminPermission adminPermission) {
        if(StringUtils.isBlank(adminPermission.getMenuUrl(),adminPermission.getMenuName())||adminPermission.getMenuType() ==null
            ||adminPermission.getMenuLev()==null){
            throw  new CommonException(ResponseMsg.EMPTY_PARAM);
        }
        if(adminPermission.getMenuLev()== PermissionMenuLevEnum.MENU_LEV_2.getCode().intValue()
            || adminPermission.getMenuType() == PermissionsMenuTypeEnum.MENU_TYPE_2.getCode().intValue()){
            AdminPermission permission = adminPermissionService.getById(adminPermission.getParentId());
            if(permission ==null){
                return ResponseUtil.getNotNormalMap(ResponseMsg.PERMISSION_NO_HAVE_PARENT_ID);
            }
        }
        adminPermission.setCreateTime(new Date());
        adminPermissionService.save(adminPermission);
        return ResponseUtil.getSuccessMap();
    }

    /**
     * 菜单级别code
     */
    @RequestMapping(value = "getMenuLev", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "菜单级别code")
    public Map<String, Object> getMenuLev() {
        List<StatusVo> list = new ArrayList<>();
        for (PermissionMenuLevEnum o : PermissionMenuLevEnum.values()) {
            StatusVo vo = new StatusVo();
            vo.setCode(o.getCode().intValue());
            vo.setName(o.getMessage());
            list.add(vo);
        }
        return ResponseUtil.getSuccessMap(list);
    }


    /**
     * 菜单类型code
     */
    @RequestMapping(value = "getMenuType", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "菜单类型code")
    public Map<String, Object> getMenuType() {
        List<StatusVo> list = new ArrayList<>();
        for (PermissionsMenuTypeEnum o : PermissionsMenuTypeEnum.values()) {
            StatusVo vo = new StatusVo();
            vo.setCode(o.getCode().intValue());
            vo.setName(o.getMessage());
            list.add(vo);
        }
        return ResponseUtil.getSuccessMap(list);
    }








}
