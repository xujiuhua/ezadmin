<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/ez/index/tablibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>菜单新增</title>
	<%@ include file="/WEB-INF/view/ez/index/top.jsp"%>
</head>
<body>
<div class="layui-field-box">
	<form id="formid" class="layui-form">
		<div class="layui-form-item">
			<label class="layui-form-label">菜单名称:</label>
			<div class="layui-input-inline">
				<input type="text" name="menuName" value="${sysMenu.menuName}" readonly class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">菜单地址:</label>
			<div class="layui-input-inline">
				<input type="text" name="menuUrl" value="${sysMenu.menuUrl}" readonly class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">菜单ID:</label>
			<div class="layui-input-inline">
				<input type="text" name="menuId" value="${sysMenu.menuId}" readonly class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">父级菜单ID:</label>
			<div class="layui-input-inline">
				<input type="text" name="parentId" value="${sysMenu.parentId}" readonly class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">菜单顺序:</label>
			<div class="layui-input-inline">
				<input type="text" name="menuOrder" value="${sysMenu.menuOrder}" readonly class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">菜单图标:</label>
			<div class="layui-input-inline">
				<input type="text" name="menuIcon" value="${sysMenu.menuIcon}" readonly class="layui-input">
			</div>
			<div class="layui-form-mid layui-word-aux">
				<i class="fa ${sysMenu.menuIcon}"></i>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">菜单类型</label>
			<div class="layui-input-inline">
				<c:if test="${sysMenu.menuType=='0'}">
					<input type="radio" name="menuType" value="0" title="开发者菜单" checked>
					<input type="radio" name="menuType" value="1" title="系统菜单" disabled>
					<input type="radio" name="menuType" value="2" title="业务菜单" disabled>
					<input type="radio" name="menuType" value="3" title="前台菜单" disabled>
				</c:if>
				<c:if test="${sysMenu.menuType=='1'}">
					<input type="radio" name="menuType" value="0" title="开发者菜单" disabled>
					<input type="radio" name="menuType" value="1" title="系统菜单" checked>
					<input type="radio" name="menuType" value="2" title="业务菜单" disabled>
					<input type="radio" name="menuType" value="3" title="前台菜单" disabled>
				</c:if>
				<c:if test="${sysMenu.menuType=='2'}">
					<input type="radio" name="menuType" value="0" title="开发者菜单" disabled>
					<input type="radio" name="menuType" value="1" title="系统菜单" disabled>
					<input type="radio" name="menuType" value="2" title="业务菜单" checked>
					<input type="radio" name="menuType" value="3" title="前台菜单" disabled>
				</c:if>
				<c:if test="${sysMenu.menuType=='3'}">
					<input type="radio" name="menuType" value="0" title="开发者菜单" disabled>
					<input type="radio" name="menuType" value="1" title="系统菜单" disabled>
					<input type="radio" name="menuType" value="2" title="业务菜单" disabled>
					<input type="radio" name="menuType" value="3" title="前台菜单" checked>
				</c:if>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" onclick="top.layer.closeAll()">关闭</button>
			</div>
		</div>
	</form>
</div>
<script type="text/javascript" src="/static/plugins/layui/layui.js" charset="utf-8"></script>
<script>
	//Demo
	layui.use(['layer', 'form','jquery'], function(){
		var layer = layui.layer
				,form = layui.form()
				,$ = layui.jquery;
	});
</script>
</body>
</html>