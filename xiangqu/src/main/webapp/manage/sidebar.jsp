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
				<li><a href="./clearsession">退出</a></li>
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
			<c:if test="${sessionScope.adminPrivilege == 1 || sessionScope.adminPrivilege == 2 || sessionScope.adminPrivilege == 3 || sessionScope.adminPrivilege == 4}">
				<li><a>任务信息管理</a>
					<ul class="sub-menu">
						<li><a href="./task_list">任务列表</a></li>

					</ul></li>
				<li><a>商品管理</a>
					<ul class="sub-menu">
						<li><a href="./goods_list">商品列表</a></li>
					</ul></li>
				<li><a>活动管理</a>
					<ul class="sub-menu">
						<li><a href="./event_list">活动列表</a></li>
					</ul></li>
				<li><a>会员活动管理</a>
					<ul class="sub-menu">
						<li><a href="./activity_list">会员活动列表</a></li>
					</ul></li>
				<li><a>中奖兑换管理</a>
					<ul class="sub-menu">
						<li><a href="./prize_list">中奖列表</a></li>
					</ul></li>
			</c:if>
			<c:if test="${sessionScope.adminPrivilege == 3 || sessionScope.adminPrivilege == 4}">
				<li><a>充值管理</a>
					<ul class="sub-menu">
						<li><a href="./recharge_list">充值列表</a></li>
					</ul></li>
			</c:if>
			<c:if test="${sessionScope.adminPrivilege == 6 }">
				<li><a>任务信息管理</a>
					<ul class="sub-menu">
						<li><a href="./task_list">任务列表</a></li>

					</ul></li>
			</c:if>
			<c:if test="${(sessionScope.adminPrivilege == 8) }">
				<li><a>用户管理</a>
					<ul class="sub-menu">
						<li><a href="./user_list">用户列表</a></li>
					</ul></li>
				<li><a>商家管理</a>
					<ul class="sub-menu">
						<li><a href="./merchants_list">商家列表</a></li>
					</ul></li>
				<li><a>任务信息管理</a>
					<ul class="sub-menu">
						<li><a href="./task_needs">任务需求列表</a></li>
						<li><a href="./task_list">任务列表</a></li>

					</ul></li>
				<li><a>商品管理</a>
					<ul class="sub-menu">
						<li><a href="./goods_list">商品列表</a></li>
					</ul></li>
				<li><a>订单管理</a>
					<ul class="sub-menu">
						<li><a href="./order_list">订单列表</a></li>
					</ul></li>
				<li><a>活动管理</a>
					<ul class="sub-menu">
						<li><a href="./event_list">活动列表</a></li>
					</ul></li>
				<li><a>专题管理</a>
					<ul class="sub-menu">
						<li><a href="./subject_list">专题列表</a></li>
					</ul></li>
				<li><a>会员活动管理</a>
					<ul class="sub-menu">
						<li><a href="./activity_list">会员活动列表</a></li>
					</ul></li>
				<li><a>优惠券管理</a>
					<ul class="sub-menu">
						<li><a href="./coupon_list">优惠券列表</a></li>
					</ul></li>
				<li><a>中奖兑换管理</a>
					<ul class="sub-menu">
						<li><a href="./prize_list">中奖列表</a></li>
					</ul></li>
				<li><a>文章管理</a>
					<ul class="sub-menu">
						<li><a href="./article_list">文章列表</a></li>
					</ul></li>
				<!-- <li><a>动态管理</a>
					<ul class="sub-menu">
						<li><a href="./dynamic">动态管理</a></li>
					</ul></li>
				<li><a>模块管理</a>
					<ul class="sub-menu">
						<li><a href="./model_list">模块管理</a></li>
					</ul></li> -->
			</c:if>
			<c:if test="${sessionScope.adminPrivilege == 9 }">
				<li><a href="./area_account_list">权限账号列表</a></li>
			</c:if>
			<c:if test="${sessionScope.adminPrivilege == 5 }">
				<li><a>用户管理</a>
					<ul class="sub-menu">
						<li><a href="./user_list">用户列表</a></li>
						<li><a href="./promoter_list">推广员列表</a></li>
					</ul></li>
				<li><a>商家管理</a>
					<ul class="sub-menu">
						<li><a href="./merchants_list">商家列表</a></li>
					</ul></li>
				<li><a>任务信息管理</a>
					<ul class="sub-menu">
						<li><a href="./task_needs">任务需求列表</a></li>
						<li><a href="./task_list">任务列表</a></li>

					</ul></li>
				<li><a>商品管理</a>
					<ul class="sub-menu">
						<li><a href="./goods_list">商品列表</a></li>
					</ul></li>
				<li><a>订单管理</a>
					<ul class="sub-menu">
						<li><a href="./order_list">订单列表</a></li>
					</ul></li>
				<li><a>活动管理</a>
					<ul class="sub-menu">
						<li><a href="./event_list">活动列表</a></li>
						<!-- 						<li><a href="./sign_list">报名列表</a></li> -->
					</ul></li>
				<li><a>专题管理</a>
					<ul class="sub-menu">
						<li><a href="./subject_list">专题列表</a></li>
					</ul></li>
				<li><a>会员活动管理</a>
					<ul class="sub-menu">
						<li><a href="./activity_list">会员活动列表</a></li>
					</ul></li>
				<li><a>优惠券管理</a>
					<ul class="sub-menu">
						<li><a href="./coupon_list">优惠券列表</a></li>
					</ul></li>
				<li><a>中奖兑换管理</a>
					<ul class="sub-menu">
						<li><a href="./prize_list">中奖列表</a></li>
					</ul></li>
				<li><a>文章管理</a>
					<ul class="sub-menu">
						<li><a href="./article_list">文章列表</a></li>
						<li><a href="./comment_list">评论列表</a></li>
					</ul></li>
				<li><a>天天奖励管理</a>
					<ul class="sub-menu">
						<li><a href="./dailyreword_list">天天奖励列表</a></li>
					</ul></li>
				<li><a>动态管理</a>
					<ul class="sub-menu">
						<li><a href="./dynamic">动态管理</a></li>
					</ul></li>
				<li><a>模块管理</a>
					<ul class="sub-menu">
						<li><a href="./model_list">模块管理</a></li>
					</ul></li>
				<li><a>
						<i class="icon-font"></i>系统管理
					</a>
					<ul class="sub-menu">
						<li><a href="./account_list">权限账号列表</a></li>
						<li><a href="./admin_role">权限组列表</a></li>
						<li><a href="./industry_list">行业列表</a></li>
						<li><a href="./city_list">城市列表</a></li>
						<li><a href="./missioncatogry_list">任务类型列表</a></li>
						<li><a href="./home_activity">首页管理</a></li>
						<li><a href="./ad_list">Banner管理</a></li>
						<li><a href="./system_message">系统消息管理</a></li>
					</ul></li>
			</c:if>
			<li><a>卡密管理</a>
					<ul class="sub-menu">
						<li><a href="./card_list">卡密列表</a></li>
						<li><a href="./card_record_list">兑换记录</a></li>
					</ul></li>
			<li><a>渠道管理</a>
					<ul class="sub-menu">
						<li><a href="./channel_list">渠道列表</a></li>
					</ul></li>
		</ul>
	</div>
</div>
<!--/sidebar-->