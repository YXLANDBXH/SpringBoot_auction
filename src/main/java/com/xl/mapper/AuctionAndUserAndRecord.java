package com.xl.mapper;

import com.xl.domain.Auction;
import com.xl.domain.AuctionCustomer;

import java.util.List;

/**
 * @author XLong
 * @create 2021-08-05 15:03
 */
public interface AuctionAndUserAndRecord {
    //根据auctionId查询出竞拍详情
    Auction selectAucAndRecordByAuctionId(String auctionId);
    //查询拍卖已经结束的商品
    List<AuctionCustomer> selectAuctionEndTime();
    //查看正在拍卖中的商品
    List<Auction> selectAuctionNoEndTime();
}
