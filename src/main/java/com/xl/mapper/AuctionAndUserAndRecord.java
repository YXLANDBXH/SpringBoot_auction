package com.xl.mapper;

import com.xl.domain.Auction;

/**
 * @author XLong
 * @create 2021-08-05 15:03
 */
public interface AuctionAndUserAndRecord {
    //根据auctionId查询出竞拍详情
    Auction selectAucAndRecordByAuctionId(String auctionId);
}
