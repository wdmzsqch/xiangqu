<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="topbar-wrap white">
	<div class="topbar-inner clearfix">
		<div class="topbar-logo-wrap clearfix">
			<h1 class="topbar-logo none">
				<a href="index.html" class="navbar-brand">水票后台管理</a>
			</h1>
		</div>
		<div class="top-info-wrap">
			<ul class="top-info-list clearfix">
				<li><a href="./clearwsession">退出</a></li>
			</ul>
		</div>
	</div>
</div>

<div class="sidebar-wrap">
	<div class="sidebar-title">
		<h1>菜单</h1>
	</div>
	<div class="sidebar-content">
		<ul class="sidebar-list">
				<li><a>用户管理</a>
					<ul class="sub-menu">
						<li><a href="./w_user_list">用户列表</a></li>

					</ul></li>
				<li><a>水票管理</a>
					<ul class="sub-menu">
						<li><a href="./wticket_list">水票管理</a></li>
					</ul></li>
				<li><a>订水记录</a>
					<ul class="sub-menu">
						<li><a href="./orderw_list">订水记录</a></li>
					</ul></li>
				<!-- <li><a>数据管理</a>
					<ul class="sub-menu">
						<li><a href="#">数据分析</a></li>
					</ul></li> -->
		</ul>
	</div>
</div>
<!--/sidebar-->