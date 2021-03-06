package com.ez.system.controller;

import com.ez.annotation.SystemLogController;
import com.ez.system.entity.SysRole;
import com.ez.system.entity.SysUser;
import com.ez.system.entity.SysUserRole;
import com.ez.system.service.SysOrgService;
import com.ez.system.service.SysRoleService;
import com.ez.system.service.SysUserRoleService;
import com.ez.system.service.SysUserService;
import com.ez.util.FormatDateUtil;
import com.ez.util.WaterIdGener;
import com.ez.util.WebTool;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenez
 * @2016-10-20
 * @Email: chenez 787818013@qq.com
 * @version 1.0
 */
@Controller
@RequestMapping(value="/ez/system/sysuser/")
public class SysUserController {

	@Resource
	private SysUserService sysUserService;
	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private SysOrgService sysOrgService;
	@Resource
	private SysUserRoleService sysUserRoleService;

	/**
	 * 跳到列表页面
	 * @return
	 */
	@RequestMapping(value="list")
	@SystemLogController(description = "获取系统用户列表页面")
	public String list(Model model){
		return "/ez/system/sysuser/list";
	}


	/**
	 * 跳到新增页面
	 * @return
	 */
	@RequestMapping(value="addUI")
	public String addUI(Model model){
		String companyList=sysOrgService.findAllCompany(null);
		String dptList=sysOrgService.findAllDpt(null);
		model.addAttribute("companyList",companyList);
		model.addAttribute("dptList",dptList);
		return "ez/system/sysuser/add";
	}

	/**
	 * 保存新增
	 * @param model
	 * @param sysuser
	 * @return
	 */
	@RequestMapping(value="add")
	public String add(Model model,SysUser sysuser,HttpServletResponse response,HttpServletRequest request){
		String result="{\"msg\":\"suc\"}";
		try {
			String userno= WaterIdGener.getWaterId();
			sysuser.setUserno(userno);
			if (!"1".equals(sysuser.getIsused())){
				sysuser.setIsused("0");
			}
			sysuser.setUptdate(FormatDateUtil.getFormatDate("yyyy-MM-dd"));
			sysUserService.add(sysuser);
		} catch (Exception e) {
			result="{\"msg\":\"fail\",\"message\":\"" + WebTool.getErrorMsg(e.getMessage())+"\"}";
			e.printStackTrace();
		}
		 WebTool.writeJson(result, response);
		 return null;
	}


	/**
	 * post方式分页查询
	 * @param page
	 * @param sysuser
	 * @return map
	 */
	@RequestMapping(value="showlist",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> showlist(SysUser sysuser, Page<SysUser> page){
		List<SysUser> list = sysUserService.query(page, sysuser);
		PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(list);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", pageInfo.getTotal());
		return map;
	}
	/**
	 * 根据id删除
	 * @param model
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="deleteById",method=RequestMethod.POST)
	public String deleteById(Model model,String ids, HttpServletResponse response){
		String result="{\"status\":1,\"message\":\"删除成功！\"}";
		try{
			sysUserService.delete(ids);
		}catch(Exception e){
			result="{\"status\":0,\"message\":\"" +WebTool.getErrorMsg(e.getMessage())+"\"}";
			e.printStackTrace();
		}
		WebTool.writeJson(result, response);
		return null;
	}
	
	/**
	 * 查询&修改单条记录
	 * @param model
	 * @param userno
	 * @param typeKey
	 * @return
	 */
	@RequestMapping(value="getById")
	public String getById(Model model,String userno,int typeKey){
		SysUser sysuser = sysUserService.getById(userno);
		String companyList=sysOrgService.findAllCompany(sysuser.getCompanyno());
		String dptList=sysOrgService.findAllDpt(sysuser.getDptno());
		model.addAttribute("sysuser", sysuser);
		model.addAttribute("companyList",companyList);
		model.addAttribute("dptList",dptList);
		if(typeKey == 1){
			return "ez/system/sysuser/edit";
		}else if(typeKey == 2){
			return "ez/system/sysuser/view";
		}else{
			return "ez/system/sysuser/view_1";
		}
	}
	
	/**
	 * 更新修改的信息
	 * @param model
	 * @param sysuser
	 * @return
	 */
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String updateSysUser(Model model,SysUser sysuser,HttpServletResponse response){		
		String result="{\"msg\":\"suc\"}";
		try {
			if (!"1".equals(sysuser.getIsused())){
				sysuser.setIsused("0");
			}
			sysuser.setUptdate(FormatDateUtil.getFormatDate("yyyy-MM-dd"));
			sysUserService.modify(sysuser);
		} catch (Exception e) {
			result="{\"msg\":\"fail\",\"message\":\"" +WebTool.getErrorMsg(e.getMessage())+"\"}";
			e.printStackTrace();
		}
		 WebTool.writeJson(result, response);
		 return null;		
		
	}
	
	
	/**
	 * 批量删除数据
	 * 
	 * @param model
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "deleteAll")
	@SystemLogController(description = "批量删除角色数据")
	public String deleteAll(String[] ids, Model model, HttpServletResponse response) {
		String result = "{\"status\":1,\"message\":\"删除成功！\"}";
		try {
			for (String id : ids) {
				sysUserService.delete(id);
			}
		} catch (Exception e) {
			result="{\"status\":0,\"message\":\"" +WebTool.getErrorMsg(e.getMessage())+"\"}";
			e.printStackTrace();
		}
		WebTool.writeJson(result, response);
		return null;
	}

	/**
	 * 跳转到分配角色页面
	 * @param model
	 * @param userno
	 * @return
	 */
	@RequestMapping(value = "assignrolelist")
	@SystemLogController(description = "跳转到分配角色页面")
	public String assignroleList (Model model,String userno){
		List<SysUserRole> sysUserRoleList = sysUserRoleService.findById(userno);
		List<SysRole> sysRoleList=sysRoleService.findAll();
		if (null!=sysRoleList && sysRoleList.size()>0){
			for (SysRole sysRole:sysRoleList){
				if (null!=sysUserRoleList && sysUserRoleList.size()>0){
					for (SysUserRole sysUserRole:sysUserRoleList){
						if (sysRole.getRoleId().equals(sysUserRole.getRoleId())){
							sysRole.setHasRole(true);
						}
					}
				}
			}
		}
		model.addAttribute("sysRoleList",sysRoleList);
		model.addAttribute("userno",userno);
		return "ez/system/sysuser/assignrolelist";
	}

	/**
	 * modify the roles and rights of user by chenez 20170430
	 * @param model
	 * @param sysUserRole
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value="assignrole",method=RequestMethod.POST)
	public String assignrole(Model model,SysUserRole sysUserRole,HttpServletResponse response,HttpServletRequest request){
		String result="{\"msg\":\"suc\"}";
		try {
			String[] roleIds = request.getParameterValues("roleId");
			sysUserRoleService.assignrole(roleIds,sysUserRole);
		} catch (Exception e) {
			result="{\"msg\":\"fail\",\"message\":\"" +WebTool.getErrorMsg(e.getMessage())+"\"}";
			e.printStackTrace();
		}
		WebTool.writeJson(result, response);
		return null;
	}
	
}

