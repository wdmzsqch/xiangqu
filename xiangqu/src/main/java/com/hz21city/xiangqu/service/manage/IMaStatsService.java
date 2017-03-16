/**
 * 
 */
package com.hz21city.xiangqu.service.manage;

/**
 * @author QIUCH
 *
 */
public interface IMaStatsService {

	public abstract float getAllIncome(String start, String end);

	public abstract float getAllOutcome(String start, String end);

	public abstract float getAllLotteryIncome(String start, String end);

	public abstract float getAllLotteryOutcome(String start, String end);

	public abstract float getBrushedIncome(String start, String end);

	public abstract int getNewUserCount(String start, String end);

	public abstract int getNewRegisterUserCount(String start, String end);
}
