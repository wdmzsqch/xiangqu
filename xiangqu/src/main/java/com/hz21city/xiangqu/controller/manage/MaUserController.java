package com.hz21city.xiangqu.controller.manage;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.common.Constants;
import com.hz21city.xiangqu.pojo.AdminInfo;
import com.hz21city.xiangqu.pojo.Area;
import com.hz21city.xiangqu.pojo.CheckInfo;
import com.hz21city.xiangqu.pojo.RechargeInfo;
import com.hz21city.xiangqu.pojo.UserAddress;
import com.hz21city.xiangqu.pojo.UserInfo;
import com.hz21city.xiangqu.pojo.VerifyCode;
import com.hz21city.xiangqu.service.manage.IMaMissionService;
import com.hz21city.xiangqu.service.manage.IMaOrderService;
import com.hz21city.xiangqu.service.manage.IMaStatsService;
import com.hz21city.xiangqu.service.manage.IMaUserService;
import com.ibm.icu.math.BigDecimal;

@Controller
public class MaUserController extends MaBaseController {

	@Resource
	private IMaUserService maUserService;
	@Resource
	private IMaOrderService orderSerivce;
	@Resource
	private IMaMissionService maMissionService;
	@Resource
	private IMaStatsService maStatsService;

	@RequestMapping("/login")
	public ModelAndView login(HttpSession session, String username, String pwd, String vcode) {
		ModelAndView mv = new ModelAndView();
		if (CommonUtils.isEmptyString(username) || CommonUtils.isEmptyString(pwd) || CommonUtils.isEmptyString(vcode)) {
			mv.setViewName("/manage/login");
			mv.addObject(Constants.PAGE_ERR_KEY, "请输入用户名、密码和验证码");
			return mv;
		}
		AdminInfo adminInfo = maUserService.getAdminInfo(username, CommonUtils.MD5(pwd));
		if (adminInfo == null) {
			mv.setViewName("/manage/login");
			mv.addObject(Constants.PAGE_ERR_KEY, "用户名或者密码错误");
		} else {
			// VerifyCode code = maUserService.getVerifyCode(adminInfo.getMobile());
			// Date nowdate = new Date();
			// if (code == null || !code.getCode().endsWith(vcode)) {
			// mv.addObject(Constants.PAGE_ERR_KEY, "验证码错误");
			// } else if ((nowdate.getTime() - code.getCreateTime().getTime()) > 300000) {
			// mv.addObject(Constants.PAGE_ERR_KEY, "验证码过期");
			// } else {
			if (adminInfo.getType() == 7) {
				mv.setViewName("redirect:/manage/point_task_list");
			} else {
				mv.setViewName("redirect:/manage/home_page");
			}
			session.setAttribute("adminUserId", adminInfo.getId());
			session.setAttribute("adminPrivilege", adminInfo.getType());
			// }
		}
		return mv;
	}

	@RequestMapping("/home_page")
	public ModelAndView homePage(String date) {
		ModelAndView mv = new ModelAndView("/manage/home_page");
		String start = null, end = null;
		if (!CommonUtils.isEmptyString(date)) {
			start = date + " 00:00:00";
			end = date + " 23:59:59";
		}
		mv.addObject("all", String.valueOf(new BigDecimal(maStatsService.getAllIncome(start, end))));
		mv.addObject("lottery", maStatsService.getAllLotteryIncome(start, end));
		mv.addObject("lotteryout", maStatsService.getAllLotteryOutcome(start, end));
		mv.addObject("allout", maStatsService.getAllOutcome(start, end));
		mv.addObject("alluser", maStatsService.getNewUserCount(start, end));
		mv.addObject("allregisteruser", maStatsService.getNewRegisterUserCount(start, end));
		mv.addObject("brushed", maStatsService.getBrushedIncome(start, end));
		mv.addObject("date", date);
		return mv;
	}

	// ----------------------用户管理-------------------------------
	/**
	 * 用户列表
	 * 
	 * @param keywords
	 * @param pageIndex
	 * @return
	 */
	@RequestMapping("/user_list")
	public ModelAndView userList(String keywords, Integer pageIndex, Integer searchType, String starttime, String endtime, Float UCollectCount, Float DCollectCount, Integer isSort, Integer baSort,
			Integer incomeSort) {
		ModelAndView mv = new ModelAndView("/manage/user_list");
		if (isSort == null) {
			isSort = 0;
		}
		if (baSort == null) {
			baSort = 0;
		}
		int page = 1;
		if (pageIndex != null && pageIndex > 0) {
			page = pageIndex;
		}
		Date startDate = null;
		if (!CommonUtils.isEmptyString(starttime)) {
			startDate = CommonUtils.str2Date(starttime, "yyyy-MM-dd");
		}
		Date endDate = null;
		if (!CommonUtils.isEmptyString(endtime)) {
			endDate = CommonUtils.str2Date(endtime, "yyyy-MM-dd");
		}
		List<UserInfo> userlist = maUserService.getUserList(keywords, page, searchType, startDate, endDate, UCollectCount, DCollectCount, isSort, baSort, incomeSort);
		if (userlist != null && userlist.size() > 0) {
			for (UserInfo userInfo : userlist) {
				// 得到省
				if (userInfo.getProvince() != null && userInfo.getProvince() != 0) {
					Area pro = maUserService.getArea(userInfo.getProvince());
					if (pro != null) {
						userInfo.setProname(pro.getName());
					}
				}
				// 得到市
				if (userInfo.getCity() != null && userInfo.getCity() != 0) {
					Area city = maUserService.getArea(userInfo.getCity());
					if (city != null) {
						userInfo.setCityname(city.getName());
					}
				}
				// 得到区
				if (userInfo.getArea() != null && userInfo.getArea() != 0) {
					Area area = maUserService.getArea(userInfo.getArea());
					if (area != null) {
						userInfo.setAreaname(area.getName());
					}
				}
			}
		}
		mv.addObject("userlist", userlist);
		mv.addObject("searchType", searchType);
		mv.addObject("pageCount", maUserService.getUserListSize(keywords, searchType, startDate, endDate, UCollectCount, DCollectCount));
		mv.addObject("pageIndex", page);
		mv.addObject("keywords", keywords);
		mv.addObject("starttime", starttime);
		mv.addObject("endtime", endtime);
		mv.addObject("UCollectCount", UCollectCount);
		mv.addObject("DCollectCount", DCollectCount);
		mv.addObject("isSort", isSort);
		mv.addObject("baSort", baSort);
		mv.addObject("incomeSort", incomeSort);
		mv.addObject("userCount", maUserService.getAllUserSize());
		return mv;
	}

	/**
	 * 兑换/购买记录
	 * 
	 * @param userId
	 * @param pageIndex
	 * @return
	 */
	@RequestMapping("/user_record")
	public ModelAndView RecordList(Long userId, Integer pageIndex) {
		ModelAndView mv = new ModelAndView("/manage/user_list_buy_record");
		int page = 1;
		if (pageIndex != null && pageIndex > 0) {
			page = pageIndex;
		}
		mv.addObject("recordlist", maUserService.getRecordList(page, userId));
		mv.addObject("pageCount", maUserService.getRecordListSize(userId));
		mv.addObject("pageIndex", page);
		mv.addObject("userId", userId);
		return mv;
	}

	/**
	 * 跳转到编辑页面
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("/user_modify")
	public ModelAndView user_modify(Long userId) {
		ModelAndView mv = new ModelAndView("/manage/user_list_modify");
		UserInfo userinfo = maUserService.getUserModeify(userId);
		mv.addObject("user_modify", userinfo);
		mv.addObject("fileurl", CommonUtils.getFileRootUrl());
		List<Area> prolist = maUserService.getAreaListByParentid(Long.valueOf(0));
		mv.addObject("prolist", prolist);
		if (userinfo.getProvince() != null && userinfo.getProvince() != 0) {
			List<Area> citylist = maUserService.getAreaListByParentid(userinfo.getProvince());
			mv.addObject("citylist", citylist);
			if (userinfo.getCity() != null && userinfo.getCity() != 0) {
				List<Area> arealist = maUserService.getAreaListByParentid(userinfo.getCity());
				mv.addObject("arealist", arealist);
			} else {
				List<Area> arealist = maUserService.getAreaListByParentid(citylist.get(0).getId());
				mv.addObject("arealist", arealist);
			}
		} else {
			List<Area> citylist = maUserService.getAreaListByParentid(prolist.get(0).getId());
			mv.addObject("citylist", citylist);
			if (citylist != null && citylist.size() > 0) {
				List<Area> arealist = maUserService.getAreaListByParentid(citylist.get(0).getId());
				mv.addObject("arealist", arealist);
			}
		}
		return mv;
	}

	/**
	 * 编辑用户
	 * 
	 * @param userinfo
	 * @param modify_pic
	 * @return
	 */
	@RequestMapping("/editor_user")
	public ModelAndView editor_user(UserInfo userinfo, @RequestParam(value = "modify_pic", required = false) MultipartFile modify_pic) {
		ModelAndView mv = new ModelAndView("redirect:/manage/user_list");
		if (modify_pic != null && !modify_pic.isEmpty()) {
			userinfo.setPic(getFilePathString(modify_pic));
		}
		maUserService.editorUserInfo(userinfo);
		return mv;
	}

	/**
	 * 跳转到修改密码页面
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("/user_password")
	public ModelAndView user_password(Long userId) {
		ModelAndView mv = new ModelAndView("/manage/user_list_modify_password");
		mv.addObject("userId", userId);
		return mv;
	}

	/**
	 * 修改密码
	 * 
	 * @param id
	 * @param password
	 * @return
	 */
	@RequestMapping("/editorpassword")
	public ModelAndView editorpassword(Long id, String password) {
		ModelAndView mv = new ModelAndView("redirect:/manage/user_list");
		UserInfo userinfo = maUserService.getUserModeify(id);
		if (!CommonUtils.isEmptyString(password)) {
			userinfo.setPassword(CommonUtils.MD5(password));
		}
		maUserService.editorUserInfo(userinfo);
		return mv;
	}

	@RequestMapping("/user_address")
	public ModelAndView user_address(Long userId, Integer pageIndex) {
		ModelAndView mv = new ModelAndView("/manage/user_list_address");
		int page = 1;
		if (pageIndex != null && pageIndex > 0) {
			page = pageIndex;
		}
		List<UserAddress> addresslist = maUserService.getUserAddressList(userId, page);
		if (addresslist != null && addresslist.size() > 0) {
			for (UserAddress userAddress : addresslist) {
				// 得到省
				if (userAddress.getProvince() != null && userAddress.getProvince() != 0) {
					Area pro = maUserService.getArea(userAddress.getProvince());
					if (pro != null) {
						userAddress.setProname(pro.getName());
					}
				}
				// 得到市
				if (userAddress.getCity() != null && userAddress.getCity() != 0) {
					Area city = maUserService.getArea(userAddress.getCity());
					if (city != null) {
						userAddress.setCityname(city.getName());
					}
				}
				// 得到区
				if (userAddress.getArea() != null && userAddress.getArea() != 0) {
					Area area = maUserService.getArea(userAddress.getArea());
					if (area != null) {
						userAddress.setAreaname(area.getName());
					}
				}
			}
		}
		mv.addObject("addresslist", addresslist);
		mv.addObject("pageCount", maUserService.getUserAddressListSize(userId));
		mv.addObject("pageIndex", page);
		mv.addObject("userId", userId);
		return mv;
	}

	// -------------------后台用户---------------
	@RequestMapping("/account_list")
	public ModelAndView accountList() {
		ModelAndView mv = new ModelAndView("/manage/permissions_account_list");
		mv.addObject("adminlist", maUserService.getAdminList());
		return mv;
	}

	@RequestMapping("/area_account_list")
	public ModelAndView area_account_list(HttpSession session) {
		ModelAndView mv = new ModelAndView("/manage/permissions_account_list");
		// long userid =
		// CommonUtils.parseLong(String.valueOf(session.getAttribute("adminUserId")),
		// 0);
		// AdminInfo adminInfo = maUserService.selectByPrimaryKey(userid);
		// mv.addObject("adminlist",
		// maUserService.getAreaAdminList(adminInfo.getAreaId(), userid));
		mv.addObject("adminlist", maUserService.getAreaAdminByType(6));
		return mv;
	}

	@RequestMapping("/edit_admin")
	public ModelAndView editAd(Long id, HttpSession session) {
		ModelAndView mv = new ModelAndView("/manage/permissions_account_insert");
		List<Area> prolist = maUserService.getAreaListByParentid(Long.valueOf(0));
		mv.addObject("prolist", prolist);
		if (id != null) {
			AdminInfo adminInfo = maUserService.selectByPrimaryKey(id);
			if (adminInfo != null) {
				if (adminInfo.getType() == 6 || adminInfo.getType() == 9) {
					if (adminInfo.getAreaId() != null) {
						Area area = maUserService.getArea(adminInfo.getAreaId());
						if (area != null) {
							if (area.getParentId() == 0) {
								mv.addObject("province_id", area.getId());
								List<Area> citylist = maUserService.getAreaListByParentid(area.getId());
								mv.addObject("citylist", citylist);
								if (citylist != null && citylist.size() > 0) {
									List<Area> arealist = maUserService.getAreaListByParentid(citylist.get(0).getId());
									mv.addObject("arealist", arealist);
								}
							} else {
								Area area2 = maUserService.getArea(area.getParentId());
								if (area2 != null) {
									if (area2.getParentId() == 0) {
										mv.addObject("province_id", area2.getId());
										mv.addObject("city_id", area.getId());
										List<Area> citylist = maUserService.getAreaListByParentid(area2.getId());
										mv.addObject("citylist", citylist);
										List<Area> arealist = maUserService.getAreaListByParentid(area.getId());
										mv.addObject("arealist", arealist);
									} else {
										Area area3 = maUserService.getArea(area2.getParentId());
										if (area3 != null) {
											mv.addObject("province_id", area3.getId());
											mv.addObject("city_id", area2.getId());
											mv.addObject("area_id", area.getId());
											List<Area> citylist = maUserService.getAreaListByParentid(area3.getId());
											mv.addObject("citylist", citylist);
											List<Area> arealist = maUserService.getAreaListByParentid(area2.getId());
											mv.addObject("arealist", arealist);
										}
									}
								}
							}
						}
					}
				} else {
					if (prolist != null && prolist.size() > 0) {
						List<Area> citylist = maUserService.getAreaListByParentid(prolist.get(0).getId());
						mv.addObject("citylist", citylist);
						if (citylist != null && citylist.size() > 0) {
							List<Area> arealist = maUserService.getAreaListByParentid(citylist.get(0).getId());
							mv.addObject("arealist", arealist);
						}
					}
				}
				mv.addObject("admin", adminInfo);
			}
		} else {
			if (prolist != null && prolist.size() > 0) {
				List<Area> citylist = maUserService.getAreaListByParentid(prolist.get(0).getId());
				mv.addObject("citylist", citylist);
				if (citylist != null && citylist.size() > 0) {
					List<Area> arealist = maUserService.getAreaListByParentid(citylist.get(0).getId());
					mv.addObject("arealist", arealist);
				}
			}
		}
		mv.addObject("rolelist", maUserService.getAllAdminRole());
		return mv;
	}

	@RequestMapping("/admin_editor")
	public void admin_editor(AdminInfo admin, Long province, Long city, Long area, HttpSession session, HttpServletResponse response) {
		AdminInfo admininfo = maUserService.getAdminInfoByName(admin.getUserName());
		if (province != null) {
			admin.setAreaId(province);
		}
		if (city != null) {
			admin.setAreaId(city);
		}
		if (area != null) {
			admin.setAreaId(area);
		}
		if (admin.getId() == null) {
			if (admininfo != null) {
				CommonUtils.setResponseStr("error", response);
			} else {
				admin.setPassword(CommonUtils.MD5(admin.getPassword()));
				admin.setCreateTime(new Date());
				maUserService.insertAdmin(admin);
				CommonUtils.setResponseStr("success", response);
			}
		} else {
			if (admininfo != null && admininfo.getId() != admin.getId()) {
				CommonUtils.setResponseStr("error", response);
			} else {
				AdminInfo adminInfo = maUserService.selectByPrimaryKey(admin.getId());
				if (!CommonUtils.isEmptyString(admin.getPassword())) {
					adminInfo.setPassword(CommonUtils.MD5(admin.getPassword()));
				}
				adminInfo.setAreaId(admin.getAreaId());
				adminInfo.setRealName(admin.getRealName());
				adminInfo.setRoleName(admin.getRoleName());
				adminInfo.setType(admin.getType());
				adminInfo.setUserName(admin.getUserName());
				adminInfo.setMobile(admin.getMobile());
				maUserService.updateByPrimaryKeySelective(adminInfo);
				CommonUtils.setResponseStr("success", response);
			}
		}
	}

	@RequestMapping("/del_admin")
	public ModelAndView delAd(Long id) {
		ModelAndView mv = new ModelAndView("redirect:/manage/account_list");
		if (maUserService.selectByPrimaryKey(id) != null) {
			maUserService.deleteByPrimaryKey(id);
		}
		return mv;
	}

	@RequestMapping("/selectArea")
	public void selectArea(Long parentid, Integer type, HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Area> list = maUserService.getAreaListByParentid(parentid);
		if (type == 1) {
			if (list != null && list.size() > 0) {
				List<Area> arealist = maUserService.getAreaListByParentid(list.get(0).getId());
				result.put("arealist", arealist);
			}
			result.put("citylist", list);
			CommonUtils.setMaptoJson(response, result);
		} else {
			result.put("arealist", list);
			CommonUtils.setMaptoJson(response, result);
		}
	}

	/**
	 * 充值
	 * 
	 * @return
	 */
	@RequestMapping("/recharge")
	public ModelAndView recharge(Long id, HttpSession session) {
		ModelAndView mv = new ModelAndView("manage/recharge");
		RechargeInfo recharge = new RechargeInfo();
		long fromrechargeId = CommonUtils.parseLong(String.valueOf(session.getAttribute("adminUserId")), 0);
		List<RechargeInfo> rechargelist = maUserService.getRechargeListByToRechargeId(id);
		if (rechargelist != null && rechargelist.size() > 0) {
			recharge = rechargelist.get(0);
			if (recharge != null) {
				CheckInfo checkInfo = maMissionService.getCheckInfoByAllWays(4, recharge.getId(), 3);
				CheckInfo checkInfo2 = maMissionService.getCheckInfoByAllWays(4, recharge.getId(), 4);
				if (checkInfo != null) {
					recharge.setCheckStatusC(1);
				} else {
					recharge.setCheckStatusC(0);
				}
				if (checkInfo2 != null) {
					recharge.setCheckStatusB(1);
				} else {
					recharge.setCheckStatusB(0);
				}
				if (checkInfo == null || checkInfo2 == null) {
					mv.addObject("recharge", recharge);
				}
			}
		}
		mv.addObject("toRechargeId", id);
		mv.addObject("fromRechargeId", fromrechargeId);
		return mv;
	}

	@RequestMapping("/editorRecharge")
	public ModelAndView editorRecharge(RechargeInfo recharge, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		long type = CommonUtils.parseLong(String.valueOf(session.getAttribute("adminPrivilege")), 0);
		recharge.setRechargeTime(new Date());
		if (recharge.getId() == null) {
			maUserService.addRecharge(recharge);
		}
		if (type == 9) {
			mv.setViewName("redirect:/manage/area_account_list");
		} else {
			mv.setViewName("redirect:/manage/account_list");
		}
		return mv;
	}

	@RequestMapping("/recharge_list")
	public ModelAndView recharge_list(Integer pageIndex, HttpSession session) {
		ModelAndView mv = new ModelAndView("manage/recharge_list");
		int page = 1;
		if (pageIndex != null && pageIndex > 0) {
			page = pageIndex;
		}
		List<RechargeInfo> rechargelist = maUserService.getRechargeListByPage(page);
		Integer checktype = CommonUtils.parseInt(String.valueOf(session.getAttribute("adminPrivilege")), 0);
		if (rechargelist != null && rechargelist.size() > 0) {
			for (RechargeInfo rechargeInfo : rechargelist) {
				CheckInfo checkinfo = maMissionService.getCheckInfoByAllWays(4, rechargeInfo.getId(), checktype);
				if (checkinfo != null) {
					rechargeInfo.setIsCheck(1);
				} else {
					rechargeInfo.setIsCheck(0);
				}
				CheckInfo checkinfo3 = maMissionService.getCheckInfoByAllWays(4, rechargeInfo.getId(), 3);
				CheckInfo checkinfo4 = maMissionService.getCheckInfoByAllWays(4, rechargeInfo.getId(), 4);
				if (checkinfo3 != null) {
					rechargeInfo.setComment(checkinfo3.getComment());
				}
				if (checkinfo4 != null) {
					rechargeInfo.setComment1(checkinfo4.getComment());
				}
			}
		}
		mv.addObject("pageCount", maUserService.getRechargeListSize());
		mv.addObject("pageIndex", page);
		mv.addObject("rechargelist", rechargelist);
		mv.addObject("checktype", checktype);
		return mv;
	}

	/**
	 * 登录时获取验证码
	 * 
	 * @param username
	 * @param response
	 */
	@RequestMapping("/loginvcode")
	public void loginvcode(String username, HttpServletResponse response) {
		AdminInfo admin = maUserService.getAdminInfoByName(username);
		if (admin != null) {
			String vcode = "";
			vcode = String.valueOf(new Random().nextInt(9000) + 1000);
			VerifyCode verifyCode = new VerifyCode();
			verifyCode.setPhone(admin.getMobile());
			verifyCode.setCode(vcode);
			verifyCode.setCreateTime(new Date());
			maUserService.insertVerifyCode(verifyCode);
			CommonUtils.YMsendSms(admin.getMobile(), "您的验证码是：" + vcode);
		} else {
			CommonUtils.setResponseStr("error1", response);
		}
	}

	@RequestMapping("/clearsession")
	public ModelAndView clearsession(HttpSession session) {
		ModelAndView mv = new ModelAndView("manage/login");
		session.removeAttribute("adminUserId");
		session.removeAttribute("adminPrivilege");
		return mv;
	}

	@RequestMapping("/invite_count")
	public ModelAndView invite_count(String start, String end) {
		ModelAndView mv = new ModelAndView("manage/invite_count");
		if (!CommonUtils.isEmptyString(start)) {
			start = start + " 00:00:00";
		}
		if (!CommonUtils.isEmptyString(end)) {
			end = end + " 23:59:59";
		}
		mv.addObject("users", maUserService.getInviteInfo(start, end));
		return mv;
	}

	@RequestMapping("/myincome")
	public ModelAndView myincom(Long userId) {
		ModelAndView mv = new ModelAndView("/manage/myincome");
		mv.addObject("myRecord", maUserService.getMyRecordList(userId));
		return mv;
	}
	// ------推广员---------------

	@RequestMapping("/promoter_list")
	public ModelAndView promoterList(String keywords, Integer pageIndex) {
		ModelAndView mv = new ModelAndView("/manage/promoter_list");
		int page = 1;
		if (pageIndex != null && pageIndex > 0) {
			page = pageIndex;
		}
		mv.addObject("userlist", maUserService.getPromoterList(keywords, page));
		mv.addObject("promoterCount", maUserService.getAllPromoterCount());
		mv.addObject("pageCount", maUserService.getPromoterListSize(keywords));
		mv.addObject("pageIndex", page);
		mv.addObject("keywords", keywords);
		return mv;
	}

	@RequestMapping("/edit_promoter")
	public ModelAndView promoterList(Long userId, Integer type) {
		ModelAndView mv = new ModelAndView("redirect:/manage/promoter_list");
		maUserService.editPromoter(userId, type);
		return mv;
	}
}
