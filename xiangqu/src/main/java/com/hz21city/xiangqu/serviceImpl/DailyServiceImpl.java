/**
 * 
 */
package com.hz21city.xiangqu.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hz21city.xiangqu.pojo.DailyReword;
import com.hz21city.xiangqu.pojo.DailyRewordInfo;
import com.hz21city.xiangqu.service.IDailyService;

/**
 * @author qiuch
 *
 */
@Service("dailyService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class DailyServiceImpl implements IDailyService {

	@Override
	public List<DailyReword> getDailyList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DailyReword getdetail(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DailyRewordInfo> myreword(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getreword(Long id) {
		// TODO Auto-generated method stub
		
	}
}
