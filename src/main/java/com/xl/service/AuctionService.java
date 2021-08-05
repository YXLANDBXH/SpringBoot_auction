package com.xl.service;

import com.xl.domain.Auction;
import com.xl.domain.AuctionRecord;

import java.util.List;

/**
 * @author XLong
 * @create 2021-08-05 9:55
 */
public interface AuctionService {
    //列表查询
//    List<Auction> findAllAuction();
    //条件查询
    List<Auction> findAuction(Auction auction);
    //根据auctionId查询出竞拍详情
    Auction selectAucAndRecordByAuctionId(String auctionId);
}
