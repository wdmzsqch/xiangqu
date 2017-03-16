<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="topbar-wrap white">
	<div class="topbar-inner clearfix">
		<div class="topbar-logo-wrap clearfix">
			<h1 class="topbar-logo none">
				<a href="index.html" class="navbar-brand">后台管理</a>
			</h1>
			<ul class="navbar-list clearfix">
				<li><a class="on" href="./home_page">首页</a></li>

			</ul>
		</div>
		<div class="top-info-wrap">
			<ul class="top-info-list clearfix">
				<li><a href="./clearmsession">退出</a></li>
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
			<li><a>广告位列表</a>
					<ul class="sub-menu">
						<li ><a href="./ad_point_list" id="li_1">广告位列表</a></li>
						<li ><a href="./mark_ad_point_list" id="li_2">点位记录</a></li>
					</ul></li>
				<li>
			<li><a>太原广告位列表</a>
					<ul class="sub-menu">
						<li ><a href="./ad_point_list_taiyuan" id="li_3">太原广告位列表</a></li>
						<li ><a href="./mark_ad_point_list_taiyuan" id="li_4">太原点位记录</a></li>
					</ul></li>
				<li>
		</ul>
	</div>
</div>
<!--/sidebar-->