package com.suda.platform.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.agentuser.AgentUserVO;
import com.suda.platform.entity.AgentUser;
import com.suda.platform.service.IAgentUserService;
import com.util.Respons.ResponseMsg;
import com.util.Respons.ResponseUtil;
import com.util.pageinfoutil.PageUtil;
import config.annotation.LogMenthodName;
import config.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 *
 * 代理商管理
 *
 * @author kongling
 * @package com.suda.platform.controller.admin
 * @date 2019-06-13  11:04
 * @project charging_pile_cloud
 */
@RestController
@RequestMapping(value = "admin/agentUser",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AdminAgentUserController {

    @Autowired
    private IAgentUserService agentUserService;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 代理商列表
     *
     */
    @RequestMapping(value = "/getAgentUsers", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAgentUsers(AgentUserVO vo, HttpServletRequest req, PageUtil pageUtil) throws UnsupportedEncodingException {
        PageInfo<AgentUserVO> lists = agentUserService.selectByChoice(vo,pageUtil);
        return ResponseUtil.getSuccessMap(lists);
    }
    /**
     * 代理商编辑添加
     */
    @RequestMapping(value = "/editAndAddAgentUser", method = RequestMethod.POST)
    @ResponseBody
    @LogMenthodName(name = "代理商编辑/添加")
    public Map<String, Object> editAndAddAgentUser(AgentUser vo, HttpServletRequest req, PageUtil pageUtil) throws UnsupportedEncodingException {
        if(vo.getId() ==null){
            if(vo.getAccount()==null || vo.getPassword() ==null){
                return ResponseUtil.getNotNormalMap(ResponseMsg.ERROR_PARAM);
            }
            AgentUser agentUser = agentUserService.getOne(new QueryWrapper<AgentUser>()
            .eq("account",vo.getAccount())
            .eq("is_deleted",0)
            .last("limit 1"));
            if(agentUser !=null){
                return ResponseUtil.getNotNormalMap(ResponseMsg.USER_HAS_EXIST);
            }
            agentUserService.save(vo);
        }else {
            vo.setAccount(null);
            agentUserService.updateById(vo);
        }
        return ResponseUtil.getSuccessMap();
    }


    /**
     * 代理商删除
     */
    @RequestMapping(value = "/delAgentUser", method = RequestMethod.POST)
    @ResponseBody
    @LogMenthodName(name = "代理商删除")
    public Map<String, Object> delAgentUser(AgentUser vo, HttpServletRequest req, PageUtil pageUtil) throws UnsupportedEncodingException {
        if(vo.getId() ==null){
            return ResponseUtil.getNotNormalMap(ResponseMsg.ID_IS_EMPTY);
        }
        agentUserService.update(new UpdateWrapper<AgentUser>()
        .set("is_deleted",1)
        .eq("id",vo.getId()));
        return ResponseUtil.getSuccessMap();
    }

    /**
     * 代理商禁用启用
     */
    @RequestMapping(value = "/disableAgentUser", method = RequestMethod.POST)
    @ResponseBody
    @LogMenthodName(name = "代理商禁用启用")
    public Map<String, Object> disableAgentUser(AgentUser vo, HttpServletRequest req, PageUtil pageUtil) throws UnsupportedEncodingException {
        if(vo.getId() ==null || vo.getIsDisable() == null){
            return ResponseUtil.getNotNormalMap(ResponseMsg.ID_IS_EMPTY);
        }
        agentUserService.update(new UpdateWrapper<AgentUser>()
                .set("is_disable",vo.getIsDisable())
                .eq("id",vo.getId()));
        //存储更新后的信息
        redisUtils.setStorageAgentUser(vo.getId(), JSONObject.toJSON(agentUserService.getById(vo.getId())).toString());
        return ResponseUtil.getSuccessMap();
    }



}
