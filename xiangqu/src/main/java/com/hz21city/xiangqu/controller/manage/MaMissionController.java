package com.hz21city.xiangqu.controller.manage;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.pojo.AdminInfo;
import com.hz21city.xiangqu.pojo.Area;
import com.hz21city.xiangqu.pojo.CheckInfo;
import com.hz21city.xiangqu.pojo.IncomeRecord;
import com.hz21city.xiangqu.pojo.MissionCatogry;
import com.hz21city.xiangqu.pojo.MissionInfo;
import com.hz21city.xiangqu.pojo.RechargeInfo;
import com.hz21city.xiangqu.pojo.TaskNeeds;
import com.hz21city.xiangqu.pojo.UserInfo;
import com.hz21city.xiangqu.service.manage.IMaMissionService;
import com.hz21city.xiangqu.service.manage.IMaShopService;
import com.hz21city.xiangqu.service.manage.IMaUserService;

@Controller
public class MaMissionController extends MaBaseController {

	@Resource
	private IMaMissionService maMissionService;
	@Resource
	private IMaShopService maShopService;
	@Resource
	private IMaUserService maUserService;

	@RequestMapping("/task_list")
	public ModelAndView task_list(HttpSession session, Integer searchtype, String keywords, Integer pageIndex, Long province, Long city, Long area, Long cotegory_id) {
		ModelAndView mv = new ModelAndView("/manage/task_list");
		Integer checktype = CommonUtils.parseInt(String.valueOf(session.getAttribute("adminPrivilege")), 0);
		Long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("adminUserId")), 0);
		if (pageIndex == null || pageIndex <= 0) {
			pageIndex = 1;
		}
		if (checktype == 6) {
			List<Area> prolist = maShopService.getAreaListByParentid(Long.valueOf(0));
			mv.addObject("prolist", prolist);
			AdminInfo adminInfo = maUserService.selectByPrimaryKey(userid);
			if (adminInfo.getAreaId() != null) {
				Area areaw = maUserService.getArea(adminInfo.getAreaId());
				if (areaw != null) {
					if (areaw.getParentId() == 0) {
						province = areaw.getId();
						city = null;
						area = null;
						List<Area> citylist = maUserService.getAreaListByParentid(areaw.getId());
						mv.addObject("citylist", citylist);
						if (citylist != null && citylist.size() > 0) {
							List<Area> arealist = maUserService.getAreaListByParentid(citylist.get(0).getId());
							mv.addObject("arealist", arealist);
						}
					} else {
						Area areaw2 = maUserService.getArea(areaw.getParentId());
						if (areaw2 != null) {
							if (areaw2.getParentId() == 0) {
								province = areaw2.getId();
								city = areaw.getId();
								area = null;
								List<Area> citylist = maUserService.getAreaListByParentid(areaw2.getId());
								mv.addObject("citylist", citylist);
								List<Area> arealist = maUserService.getAreaListByParentid(areaw.getId());
								mv.addObject("arealist", arealist);
							} else {
								Area areaw3 = maUserService.getArea(areaw2.getParentId());
								if (areaw3 != null) {
									province = areaw3.getId();
									city = areaw2.getId();
									area = areaw.getId();
									List<Area> citylist = maUserService.getAreaListByParentid(areaw3.getId());
									mv.addObject("citylist", citylist);
									List<Area> arealist = maUserService.getAreaListByParentid(areaw2.getId());
									mv.addObject("arealist", arealist);
								}
							}
						}
					}
				}
			}
			AdminInfo admininfo = maUserService.selectByPrimaryKey(userid);
			Integer alloutTimes = maMissionService.getAllOutTimesMission(userid);
			if (admininfo.getIntegral() != null) {
				mv.addObject("leftcount", admininfo.getIntegral() - alloutTimes);
			} else {
				mv.addObject("leftcount", 0);
			}
			mv.addObject("usecount", alloutTimes);
		} else {
			if (province != null && province != 0) {
				List<Area> prolist = maShopService.getAreaListByParentid(Long.valueOf(0));
				mv.addObject("prolist", prolist);
				List<Area> citylist = maShopService.getAreaListByParentid(province);
				mv.addObject("citylist", citylist);
				if (city != null && city != 0) {
					List<Area> arealist = maShopService.getAreaListByParentid(city);
					mv.addObject("arealist", arealist);
				} else {
					List<Area> arealist = maShopService.getAreaListByParentid(citylist.get(0).getId());
					mv.addObject("arealist", arealist);
				}
			} else {
				List<Area> prolist = maShopService.getAreaListByParentid(Long.valueOf(0));
				mv.addObject("prolist", prolist);
				if (prolist != null && prolist.size() > 0) {
					List<Area> citylist = maShopService.getAreaListByParentid(prolist.get(0).getId());
					mv.addObject("citylist", citylist);
					if (citylist != null && citylist.size() > 0) {
						List<Area> arealist = maShopService.getAreaListByParentid(citylist.get(0).getId());
						mv.addObject("arealist", arealist);
					}
				}
			}
		}
		List<MissionCatogry> cotegorylist = maMissionService.getAllCotegoryList();
		mv.addObject("province", province);
		mv.addObject("city", city);
		mv.addObject("area", area);
		mv.addObject("cotegory_id", cotegory_id);
		mv.addObject("cotegorylist", cotegorylist);
		List<MissionInfo> list = new ArrayList<MissionInfo>();
		if (checktype == 6) {
			list = maMissionService.getMissionList(searchtype, keywords, pageIndex, province, city, area, cotegory_id, userid);
		} else {
			list = maMissionService.getMissionList(searchtype, keywords, pageIndex, province, city, area, cotegory_id, null);
		}
		if (list != null && list.size() > 0) {
			for (MissionInfo missionInfo : list) {
				CheckInfo checkinfo = maMissionService.getCheckInfoByAllWays(1, missionInfo.getId(), checktype);
				if (checkinfo != null) {
					missionInfo.setIsCheck(1);
				} else {
					missionInfo.setIsCheck(0);
				}
				CheckInfo checkinfo1 = maMissionService.getCheckInfoByAllWays(1, missionInfo.getId(), 1);
				CheckInfo checkinfo2 = maMissionService.getCheckInfoByAllWays(1, missionInfo.getId(), 2);
				CheckInfo checkinfo3 = maMissionService.getCheckInfoByAllWays(1, missionInfo.getId(), 3);
				CheckInfo checkinfo4 = maMissionService.getCheckInfoByAllWays(1, missionInfo.getId(), 4);
				if (checkinfo1 != null) {
					missionInfo.setComment(checkinfo1.getComment());
				}
				if (checkinfo2 != null) {
					missionInfo.setComment1(checkinfo2.getComment());
				}
				if (checkinfo3 != null) {
					missionInfo.setComment2(checkinfo3.getComment());
				}
				if (checkinfo4 != null) {
					missionInfo.setComment3(checkinfo4.getComment());
				}
			}
		}
		mv.addObject("missionlist", list);
		if (checktype == 6) {
			mv.addObject("pageCount", maMissionService.getMissionListSize(searchtype, keywords, province, city, area, cotegory_id, userid));
			mv.addObject("perCount", maMissionService.getAreaMissionCount(province, city, area));
		} else {
			mv.addObject("pageCount", maMissionService.getMissionListSize(searchtype, keywords, province, city, area, cotegory_id, null));
			mv.addObject("perCount", maMissionService.getAllMissionSize());
		}
		mv.addObject("pageIndex", pageIndex);
		mv.addObject("keywords", keywords);
		mv.addObject("searchtype", searchtype);
		mv.addObject("webrooturl", CommonUtils.getWebRootUrl());
		mv.addObject("checktype", checktype);
		return mv;
	}

	@RequestMapping("/task_detail")
	public ModelAndView taskDetail(HttpSession session, Long id) {
		ModelAndView mv = new ModelAndView("/manage/task_insert");
		Integer checktype = CommonUtils.parseInt(String.valueOf(session.getAttribute("adminPrivilege")), 0);
		Long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("adminUserId")), 0);
		AdminInfo adminInfo = maUserService.selectByPrimaryKey(userid);
		List<Area> prolist = maShopService.getAreaListByParentid(Long.valueOf(0));
		mv.addObject("prolist", prolist);
		if (id != null && id > 0) {
			MissionInfo mission = maMissionService.getMissionById(id);
			mv.addObject("mission", mission);
			if (checktype == 6) {
				if (adminInfo.getAreaId() != null) {
					Area areaw = maUserService.getArea(adminInfo.getAreaId());
					if (areaw != null) {
						if (areaw.getParentId() == 0) {
							List<Area> citylist = maUserService.getAreaListByParentid(areaw.getId());
							mv.addObject("citylist", citylist);
							if (citylist != null && citylist.size() > 0) {
								List<Area> arealist = maUserService.getAreaListByParentid(citylist.get(0).getId());
								mv.addObject("arealist", arealist);
							}
							mv.addObject("province_id", areaw.getId());
						} else {
							Area areaw2 = maUserService.getArea(areaw.getParentId());
							if (areaw2 != null) {
								if (areaw2.getParentId() == 0) {
									List<Area> citylist = maUserService.getAreaListByParentid(areaw2.getId());
									mv.addObject("citylist", citylist);
									List<Area> arealist = maUserService.getAreaListByParentid(areaw.getId());
									mv.addObject("arealist", arealist);
									mv.addObject("province_id", areaw2.getId());
									mv.addObject("city_id", areaw.getId());
								} else {
									Area areaw3 = maUserService.getArea(areaw2.getParentId());
									if (areaw3 != null) {
										List<Area> citylist = maUserService.getAreaListByParentid(areaw3.getId());
										mv.addObject("citylist", citylist);
										List<Area> arealist = maUserService.getAreaListByParentid(areaw2.getId());
										mv.addObject("arealist", arealist);
										mv.addObject("province_id", areaw3.getId());
										mv.addObject("city_id", areaw2.getId());
										mv.addObject("area_id", areaw.getId());
									}
								}
							}
						}
					}
				}
				CheckInfo checkinfo1 = maMissionService.getCheckInfoByAllWays(1, id, 1);
				CheckInfo checkinfo2 = maMissionService.getCheckInfoByAllWays(1, id, 2);
				CheckInfo checkinfo3 = maMissionService.getCheckInfoByAllWays(1, id, 3);
				CheckInfo checkinfo4 = maMissionService.getCheckInfoByAllWays(1, id, 4);
				if (checkinfo1 != null || checkinfo2 != null || checkinfo3 != null || checkinfo4 != null) {
					mv.addObject("allcheck", 1);
				} else {
					mv.addObject("allcheck", 0);
				}
			} else {
				if (mission.getProvince() != null && mission.getProvince() != 0) {
					List<Area> citylist = maShopService.getAreaListByParentid(mission.getProvince());
					mv.addObject("citylist", citylist);
					if (mission.getCity() != null && mission.getCity() != 0) {
						List<Area> arealist = maShopService.getAreaListByParentid(mission.getCity());
						mv.addObject("arealist", arealist);
					} else {
						List<Area> arealist = maShopService.getAreaListByParentid(citylist.get(0).getId());
						mv.addObject("arealist", arealist);
					}
				} else {
					List<Area> citylist = maShopService.getAreaListByParentid(prolist.get(0).getId());
					mv.addObject("citylist", citylist);
					if (citylist != null && citylist.size() > 0) {
						List<Area> arealist = maShopService.getAreaListByParentid(citylist.get(0).getId());
						mv.addObject("arealist", arealist);
					}
				}
				CheckInfo checkinfo1 = maMissionService.getCheckInfoByAllWays(1, id, 1);
				CheckInfo checkinfo2 = maMissionService.getCheckInfoByAllWays(1, id, 2);
				CheckInfo checkinfo3 = maMissionService.getCheckInfoByAllWays(1, id, 3);
				CheckInfo checkinfo4 = maMissionService.getCheckInfoByAllWays(1, id, 4);
				if (checkinfo1 != null || checkinfo2 != null || checkinfo3 != null || checkinfo4 != null) {
					mv.addObject("allcheck", 1);
				} else {
					mv.addObject("allcheck", 0);
				}
			}
		} else {
			if (checktype == 6) {
				if (adminInfo.getAreaId() != null) {
					Area areaw = maUserService.getArea(adminInfo.getAreaId());
					if (areaw != null) {
						if (areaw.getParentId() == 0) {
							List<Area> citylist = maUserService.getAreaListByParentid(areaw.getId());
							mv.addObject("citylist", citylist);
							if (citylist != null && citylist.size() > 0) {
								List<Area> arealist = maUserService.getAreaListByParentid(citylist.get(0).getId());
								mv.addObject("arealist", arealist);
							}
							mv.addObject("province_id", areaw.getId());
						} else {
							Area areaw2 = maUserService.getArea(areaw.getParentId());
							if (areaw2 != null) {
								if (areaw2.getParentId() == 0) {
									List<Area> citylist = maUserService.getAreaListByParentid(areaw2.getId());
									mv.addObject("citylist", citylist);
									List<Area> arealist = maUserService.getAreaListByParentid(areaw.getId());
									mv.addObject("arealist", arealist);
									mv.addObject("province_id", areaw2.getId());
									mv.addObject("city_id", areaw.getId());
								} else {
									Area areaw3 = maUserService.getArea(areaw2.getParentId());
									if (areaw3 != null) {
										List<Area> citylist = maUserService.getAreaListByParentid(areaw3.getId());
										mv.addObject("citylist", citylist);
										List<Area> arealist = maUserService.getAreaListByParentid(areaw2.getId());
										mv.addObject("arealist", arealist);
										mv.addObject("province_id", areaw3.getId());
										mv.addObject("city_id", areaw2.getId());
										mv.addObject("area_id", areaw.getId());
									}
								}
							}
						}
					}
				}
				mv.addObject("allcheck", 0);
			} else {
				if (prolist != null && prolist.size() > 0) {
					List<Area> citylist = maShopService.getAreaListByParentid(prolist.get(0).getId());
					mv.addObject("citylist", citylist);
					if (citylist != null && citylist.size() > 0) {
						List<Area> arealist = maShopService.getAreaListByParentid(citylist.get(0).getId());
						mv.addObject("arealist", arealist);
					}
				}
				mv.addObject("allcheck", 0);
			}
		}
		mv.addObject("fileurl", CommonUtils.getFileRootUrl());
		mv.addObject("shoplist", maMissionService.getShopList());
		mv.addObject("cotegorylist", maMissionService.getAllCotegoryList());
		mv.addObject("checktype", checktype);
		return mv;
	}

	@RequestMapping("/task_edit")
	public ModelAndView editTask(HttpSession session, MissionInfo missionInfo, @RequestParam(value = "taskpic", required = false) MultipartFile taskpic, String startDateStr, String endTimeStr) {
		ModelAndView mv = new ModelAndView("redirect:/manage/task_list");
		Integer checktype = CommonUtils.parseInt(String.valueOf(session.getAttribute("adminPrivilege")), 0);
		Long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("adminUserId")), 0);
		AdminInfo adminInfo = maUserService.selectByPrimaryKey(userid);
		if (checktype == 6) {
			if (adminInfo.getAreaId() != null) {
				Area areaw = maUserService.getArea(adminInfo.getAreaId());
				if (areaw != null) {
					if (areaw.getParentId() == 0) {
						List<Area> citylist = maUserService.getAreaListByParentid(areaw.getId());
						mv.addObject("citylist", citylist);
						if (citylist != null && citylist.size() > 0) {
							List<Area> arealist = maUserService.getAreaListByParentid(citylist.get(0).getId());
							mv.addObject("arealist", arealist);
						}
						missionInfo.setProvince(areaw.getId());
					} else {
						Area areaw2 = maUserService.getArea(areaw.getParentId());
						if (areaw2 != null) {
							if (areaw2.getParentId() == 0) {
								List<Area> citylist = maUserService.getAreaListByParentid(areaw2.getId());
								mv.addObject("citylist", citylist);
								List<Area> arealist = maUserService.getAreaListByParentid(areaw.getId());
								mv.addObject("arealist", arealist);
								missionInfo.setProvince(areaw2.getId());
								missionInfo.setCity(areaw.getId());
							} else {
								Area areaw3 = maUserService.getArea(areaw2.getParentId());
								if (areaw3 != null) {
									List<Area> citylist = maUserService.getAreaListByParentid(areaw3.getId());
									mv.addObject("citylist", citylist);
									List<Area> arealist = maUserService.getAreaListByParentid(areaw2.getId());
									mv.addObject("arealist", arealist);
									missionInfo.setProvince(areaw3.getId());
									missionInfo.setCity(areaw2.getId());
									missionInfo.setArea(areaw.getId());
								}
							}
						}
					}
				}
			}
		}
		if (taskpic != null && !taskpic.isEmpty()) {
			missionInfo.setPic(getFilePathString(taskpic));
		}
		if (!CommonUtils.isEmptyString(startDateStr)) {
			missionInfo.setStartDate(CommonUtils.str2Date(startDateStr, "yyyy-MM-dd"));
		}
		if (!CommonUtils.isEmptyString(endTimeStr)) {
			endTimeStr = endTimeStr + " 23:59:59";
			missionInfo.setEndTime(CommonUtils.str2Date(endTimeStr, "yyyy-MM-dd HH:mm:ss"));
		}
		if (missionInfo.getId() == null) {
			missionInfo.setCreateUserId(userid);
			// 1个积分=0.01元 1元=100积分
			// int times = (int) (missionInfo.getTotalMoney() / missionInfo.getIncome() * 100);
			missionInfo.setTotalMoney(missionInfo.getTotalTimes() * missionInfo.getIncome() / 100);
			missionInfo.setRamianTimes(missionInfo.getTotalTimes());
			Date date = new Date();
			missionInfo.setPublishTime(date);
			missionInfo.setState(2);
			missionInfo.setOnline(1);// ---修改默认下线
		} else {
			// MissionInfo mission = maMissionService.getMissionById(missionInfo.getId());
			int usetimes = maMissionService.incomeCount(missionInfo.getId());
			// int times = (int) (missionInfo.getTotalMoney() / missionInfo.getIncome() * 100);
			// missionInfo.setTotalTimes(times);
			missionInfo.setTotalMoney(missionInfo.getTotalTimes() * missionInfo.getIncome() / 100);
			int ramianTimes = missionInfo.getTotalTimes() - usetimes - (missionInfo.getShowTimes() == null ? 0 : missionInfo.getShowTimes());
			if (ramianTimes < 0) {
				ramianTimes = 0;
			}
			missionInfo.setRamianTimes(ramianTimes);
			MissionInfo mission = maMissionService.getMissionById(missionInfo.getId());
			if (mission.getOnline() == 1) {
				CheckInfo checkinfo1 = maMissionService.getCheckInfoByAllWays(1, missionInfo.getId(), 1);
				CheckInfo checkinfo2 = maMissionService.getCheckInfoByAllWays(1, missionInfo.getId(), 2);
				CheckInfo checkinfo3 = maMissionService.getCheckInfoByAllWays(1, missionInfo.getId(), 3);
				CheckInfo checkinfo4 = maMissionService.getCheckInfoByAllWays(1, missionInfo.getId(), 4);
				if (checkinfo1 != null && checkinfo2 != null && checkinfo3 != null && checkinfo4 != null) {
					maMissionService.delCheckInfo(checkinfo1);
					maMissionService.delCheckInfo(checkinfo2);
					maMissionService.delCheckInfo(checkinfo3);
					maMissionService.delCheckInfo(checkinfo4);
				}
			}
			if (missionInfo.getProvince() == null) {
				missionInfo.setProvince(0l);
			}
			if (missionInfo.getCity() == null) {
				missionInfo.setCity(0l);
			}
			if (missionInfo.getArea() == null) {
				missionInfo.setArea(0l);
			}
		}
		missionInfo.setUpdateTime(new Date());
		maMissionService.updateMission(missionInfo);
		return mv;
	}

	@RequestMapping("/addtimes")
	public void addtimes(Long mission_id, Integer times, HttpServletResponse response) {
		MissionInfo missionInfo = maMissionService.getMissionById(mission_id);
		int usetimes = missionInfo.getTotalTimes() - missionInfo.getRamianTimes();
		int totalTimes = missionInfo.getTotalTimes() + times;
		missionInfo.setTotalTimes(totalTimes);
		missionInfo.setRamianTimes(totalTimes - usetimes);
		missionInfo.setTotalMoney(totalTimes * missionInfo.getIncome() / 100);
		missionInfo.setState(2);
		maMissionService.updateMission(missionInfo);
		CommonUtils.setResponseStr("success", response);
		return;
	}

	@RequestMapping("/online")
	public void online(Long id, Integer type, HttpServletResponse response) {
		MissionInfo missionInfo = maMissionService.getMissionById(id);
		if (type == 1) {
			missionInfo.setOnline(1);
			maMissionService.updateMission(missionInfo);
			CommonUtils.setResponseStr("success", response);
			return;
		} else {
			missionInfo.setOnline(0);
			maMissionService.updateMission(missionInfo);
			CommonUtils.setResponseStr("success", response);
			return;
		}
	}

	@RequestMapping("/task_needs")
	public ModelAndView merchants_list(Long shopId, String keywords, Integer pageIndex) {
		ModelAndView mv = new ModelAndView("/manage/taskneed_list");
		int page = 1;
		if (pageIndex != null && pageIndex > 0) {
			page = pageIndex;
		}
		mv.addObject("tasklist", maMissionService.getTaskNeedsList(shopId, keywords, page));
		mv.addObject("pageCount", maMissionService.getTaskNeedsListSize(shopId, keywords));
		mv.addObject("shoplist", maMissionService.getShopList());
		mv.addObject("pageIndex", pageIndex);
		mv.addObject("shopId", shopId);
		mv.addObject("keywords", keywords);
		return mv;
	}

	@RequestMapping("/editor_task")
	public ModelAndView editor_task(TaskNeeds taskneeds) {
		ModelAndView mv = new ModelAndView("redirect:/manage/task_needs");
		taskneeds.setAcceptTime(new Date());
		maMissionService.updateByPrimaryKeySelective(taskneeds);
		return mv;
	}

	@RequestMapping("/selectMissionArea")
	public void selectArea(Long parentid, Integer type, HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Area> list = maShopService.getAreaListByParentid(parentid);
		if (type == 1) {
			if (list != null && list.size() > 0) {
				List<Area> arealist = maShopService.getAreaListByParentid(list.get(0).getId());
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
	 * 置顶
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/sort_up")
	public ModelAndView sort_up(Long id) {
		ModelAndView mv = new ModelAndView("redirect:/manage/task_list");
		Integer maxSort = maMissionService.getMaxSort();
		MissionInfo mission = maMissionService.getMissionById(id);
		mission.setSort(maxSort + 1);
		maMissionService.updateMission(mission);
		return mv;
	}

	@RequestMapping("/endMission")
	public ModelAndView endMission(Long id) {
		ModelAndView mv = new ModelAndView("redirect:/manage/task_list");
		MissionInfo mission = maMissionService.getMissionById(id);
		mission.setState(3);
		maMissionService.updateMission(mission);
		return mv;
	}

	@RequestMapping("/adminCheck")
	public void adminCheck(Long relativeId, Integer type, String comment, HttpSession session, HttpServletResponse response) {
		Integer checktype = CommonUtils.parseInt(String.valueOf(session.getAttribute("adminPrivilege")), 0);
		Long admin_id = CommonUtils.parseLong(String.valueOf(session.getAttribute("adminUserId")), 0);
		AdminInfo adminInfo = maUserService.selectByPrimaryKey(admin_id);
		CheckInfo checkinfo = maMissionService.getCheckInfoByAllWays(type, relativeId, checktype);
		if (checkinfo == null) {
			checkinfo = new CheckInfo();
			checkinfo.setAdminAccount(adminInfo.getUserName());
			checkinfo.setCheckTime(new Date());
			checkinfo.setCheckType(checktype);
			checkinfo.setComment(comment);
			checkinfo.setRelativeId(relativeId);
			checkinfo.setType(type);
			maMissionService.addCheckInfo(checkinfo);
		} else {
			checkinfo.setAdminAccount(adminInfo.getUserName());
			checkinfo.setCheckTime(new Date());
			checkinfo.setCheckType(checktype);
			checkinfo.setComment(comment);
			checkinfo.setRelativeId(relativeId);
			checkinfo.setType(type);
			maMissionService.updateCheckInfo(checkinfo);
		}
		if (type == 4) {
			RechargeInfo recharge = maUserService.getRechargeInfo(relativeId);
			if (checktype == 3) {
				CheckInfo checkinfo2 = maMissionService.getCheckInfoByAllWays(4, relativeId, 4);
				if (checkinfo2 != null) {
					AdminInfo admininfo = maUserService.selectByPrimaryKey(recharge.getToRechargeId());
					if (admininfo != null) {
						admininfo.setIntegral(recharge.getRechargeMoney());
						maUserService.updateByPrimaryKeySelective(admininfo);
					}
				}
			} else if (checktype == 4) {
				CheckInfo checkinfo2 = maMissionService.getCheckInfoByAllWays(4, relativeId, 3);
				if (checkinfo2 != null) {
					AdminInfo admininfo = maUserService.selectByPrimaryKey(recharge.getToRechargeId());
					if (admininfo != null) {
						admininfo.setIntegral(recharge.getRechargeMoney());
						maUserService.updateByPrimaryKeySelective(admininfo);
					}
				}
			}
		}
		CommonUtils.setResponseStr("success", response);
	}

	@RequestMapping("/checkAreaAdminTimes")
	public void checkAreaAdminTimes(HttpSession session, Integer totalTimes, HttpServletResponse response) {
		Long admin_id = CommonUtils.parseLong(String.valueOf(session.getAttribute("adminUserId")), 0);
		AdminInfo adminInfo = maUserService.selectByPrimaryKey(admin_id);
		Integer alloutTimes = maMissionService.getAllOutTimesMission(admin_id);
		if ((adminInfo.getIntegral() - alloutTimes) < totalTimes) {
			CommonUtils.setResponseStr("error", response);
			return;
		} else {
			CommonUtils.setResponseStr("success", response);
		}
	}

	@RequestMapping("/point_task_list")
	public ModelAndView task_list(Integer searchtype, String keywords, Integer pageIndex, Long province, Long city, Long area, Long cotegory_id) {
		ModelAndView mv = new ModelAndView("/manage/point_task_list");
		if (pageIndex == null || pageIndex <= 0) {
			pageIndex = 1;
		}
		if (province != null && province != 0) {
			List<Area> prolist = maShopService.getAreaListByParentid(Long.valueOf(0));
			mv.addObject("prolist", prolist);
			List<Area> citylist = maShopService.getAreaListByParentid(province);
			mv.addObject("citylist", citylist);
			if (city != null && city != 0) {
				List<Area> arealist = maShopService.getAreaListByParentid(city);
				mv.addObject("arealist", arealist);
			} else {
				List<Area> arealist = maShopService.getAreaListByParentid(citylist.get(0).getId());
				mv.addObject("arealist", arealist);
			}
		} else {
			List<Area> prolist = maShopService.getAreaListByParentid(Long.valueOf(0));
			mv.addObject("prolist", prolist);
			if (prolist != null && prolist.size() > 0) {
				List<Area> citylist = maShopService.getAreaListByParentid(prolist.get(0).getId());
				mv.addObject("citylist", citylist);
				if (citylist != null && citylist.size() > 0) {
					List<Area> arealist = maShopService.getAreaListByParentid(citylist.get(0).getId());
					mv.addObject("arealist", arealist);
				}
			}
		}
		List<MissionCatogry> cotegorylist = maMissionService.getAllCotegoryList();
		mv.addObject("province", province);
		mv.addObject("city", city);
		mv.addObject("area", area);
		mv.addObject("cotegory_id", cotegory_id);
		mv.addObject("cotegorylist", cotegorylist);
		mv.addObject("missionlist", maMissionService.getMissionList(searchtype, keywords, pageIndex, province, city, area, cotegory_id, null));
		mv.addObject("pageCount", maMissionService.getMissionListSize(searchtype, keywords, province, city, area, cotegory_id, null));
		mv.addObject("pageIndex", pageIndex);
		mv.addObject("keywords", keywords);
		mv.addObject("searchtype", searchtype);
		mv.addObject("webrooturl", CommonUtils.getWebRootUrl());
		return mv;
	}

	@ResponseBody
	@RequestMapping("/edit_task_point")
	public String editTaskPoint(Long missioid, Integer point) {
		MissionInfo missionInfo = maMissionService.getMissionById(missioid);
		if (missionInfo != null) {
			missionInfo.setShowTimes(point);
			int usetimes = maMissionService.incomeCount(missionInfo.getId());
			missionInfo.setTotalMoney(missionInfo.getTotalTimes() * missionInfo.getIncome() / 100);
			int ramianTimes = missionInfo.getTotalTimes() - usetimes - (missionInfo.getShowTimes() == null ? 0 : missionInfo.getShowTimes());
			if (ramianTimes < 0) {
				ramianTimes = 0;
			}
			missionInfo.setRamianTimes(ramianTimes);
			missionInfo.setUpdateTime(new Date());
			maMissionService.updateMission(missionInfo);
		}
		return "success";
	}

	@RequestMapping("/testShare")
	public synchronized void testShare(Long mission_id, HttpServletResponse response) {
		MissionInfo missionInfo = maMissionService.getMissionById(mission_id);
		if (missionInfo != null) {
			if (missionInfo.getRamianTimes() <= 0) {
				CommonUtils.setResponseStr("error", response);
			} else {
				Integer randnumber = (int) (new Random().nextInt(7) + 1);
				for (int i = 0; i < randnumber; i++) {
					UserInfo userinfo = maUserService.getUserRandom();
					if (userinfo != null) {
						IncomeRecord record = new IncomeRecord();
						record.setIncome(missionInfo.getIncome());
						record.setType(1);
						record.setRelativeId(missionInfo.getId());
						record.setTime(new Date());
						record.setUserId(userinfo.getId());
						record.setFromUserId(0l);
						maMissionService.addIncomeRecord(record);
						userinfo.setBalance(userinfo.getBalance() + missionInfo.getIncome());
						maUserService.editorUserInfo(userinfo);
						missionInfo.setRamianTimes(missionInfo.getRamianTimes() - 1);
						if (missionInfo.getRamianTimes() <= 0) {
							missionInfo.setState(3);
							maMissionService.updateMission(missionInfo);
							break;
						}
						maMissionService.updateMission(missionInfo);
					}
				}
				CommonUtils.setResponseStr("success", response);
			}
		} else {
			CommonUtils.setResponseStr("error", response);
		}
	}
}
