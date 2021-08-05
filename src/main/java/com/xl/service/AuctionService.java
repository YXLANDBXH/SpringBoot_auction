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
    //发布拍卖品
    void publishAuctions(Auction auction);
    //根据id修改
    void updateAuctionById(Auction auction);
    //删除
    void delAuctionById(String auctionId);
    //根据id查询信息
    Auction findAuctionById(String auctionId);
}
