package com.xl.service;

import com.xl.domain.Auction;
import com.xl.domain.AuctionCustomer;

import java.util.List;

/**
 * @author XLong
 * @create 2021-08-05 16:04
 */
public interface AuctionRecordService {
    //实现竞拍功能
    void saveAuctionRecord(com.xl.domain.AuctionRecord auctionRecord) throws Exception;

    //查询拍卖已经结束的商品
    List<AuctionCustomer> selectAuctionEndTime();

    //查看正在拍卖中的商品
    List<Auction> selectAuctionNoEndTime();
}
